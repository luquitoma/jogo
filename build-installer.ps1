param(
    [ValidateSet("exe", "app-image")]
    [string]$PackageType = "exe"
)

$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$jdkBin = Split-Path -Parent (Get-Command javac -ErrorAction Stop).Source
$localWixBin = Join-Path $env:LOCALAPPDATA "WixToolset\v3.14"
if (Test-Path (Join-Path $localWixBin "light.exe")) {
    $env:PATH = "$localWixBin;$env:PATH"
}
$javafxLib = Join-Path $projectRoot "javafx-sdk-26.0.2\lib"
$javafxBin = Join-Path $projectRoot "javafx-sdk-26.0.2\bin"
$buildRoot = Join-Path $projectRoot "build\jpackage"
$inputDir = Join-Path $buildRoot "input"
$javafxInputDir = Join-Path $inputDir "javafx"
$legalInputDir = Join-Path $inputDir "legal"
$classesDir = Join-Path $buildRoot "classes"
$jarPath = Join-Path $inputDir "jogo.jar"
$distDir = Join-Path $projectRoot "dist"
$modules = "javafx.controls,javafx.fxml,javafx.media"
$appName = "Farinha e o " + [char]0x00DA + "ltimo Guerreiro"

foreach ($requiredPath in @(
    (Join-Path $jdkBin "jpackage.exe"),
    (Join-Path $jdkBin "javac.exe"),
    (Join-Path $jdkBin "jar.exe"),
    (Join-Path $javafxLib "javafx.controls.jar"),
    (Join-Path $projectRoot "src\App.java")
)) {
    if (-not (Test-Path $requiredPath)) {
        throw "Dependencia nao encontrada: $requiredPath"
    }
}

if (Test-Path $buildRoot) {
    Remove-Item -LiteralPath $buildRoot -Recurse -Force
}
New-Item -ItemType Directory -Path $inputDir, $javafxInputDir, $legalInputDir, $classesDir, $distDir -Force | Out-Null

$sources = @(Get-ChildItem -Path (Join-Path $projectRoot "src") -Filter "*.java" -File -Recurse)
if ($sources.Count -eq 0) {
    throw "Nenhum arquivo Java foi encontrado em src."
}

$javacArgs = @(
    "--module-path", $javafxLib,
    "--add-modules", $modules,
    "-encoding", "UTF-8",
    "-d", $classesDir
) + @($sources | ForEach-Object { $_.FullName })

& (Join-Path $jdkBin "javac.exe") @javacArgs
if ($LASTEXITCODE -ne 0) {
    throw "A compilacao do jogo falhou (codigo $LASTEXITCODE)."
}

$resources = Get-ChildItem -Path (Join-Path $projectRoot "src") -File -Recurse |
    Where-Object { $_.Extension -ne ".java" }
foreach ($resource in $resources) {
    $relativePath = $resource.FullName.Substring((Join-Path $projectRoot "src").Length).TrimStart("\")
    $destination = Join-Path $classesDir $relativePath
    $destinationDirectory = Split-Path -Parent $destination
    New-Item -ItemType Directory -Path $destinationDirectory -Force | Out-Null
    Copy-Item -LiteralPath $resource.FullName -Destination $destination -Force
}

& (Join-Path $jdkBin "jar.exe") --create --file $jarPath -C $classesDir .
if ($LASTEXITCODE -ne 0) {
    throw "Nao foi possivel criar o arquivo JAR (codigo $LASTEXITCODE)."
}

foreach ($module in @("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.media")) {
    Copy-Item -LiteralPath (Join-Path $javafxLib "$module.jar") -Destination $javafxInputDir
    Copy-Item -LiteralPath (Join-Path (Join-Path $projectRoot "javafx-sdk-26.0.2\legal") $module) -Destination $legalInputDir -Recurse
}
Get-ChildItem -Path $javafxBin -Filter "*.dll" -File |
    Copy-Item -Destination $inputDir

$jpackageArgs = @(
    "--type", $PackageType,
    "--name", $appName,
    "--app-version", "1.0.0",
    "--input", $inputDir,
    "--main-jar", "jogo.jar",
    "--main-class", "App",
    "--dest", $distDir,
    "--java-options", "--module-path `"`$APPDIR\javafx`" --add-modules $modules --enable-native-access=javafx.graphics,javafx.media -Djava.library.path=`"`$APPDIR`""
)

if ($PackageType -eq "exe") {
    $jpackageArgs += @("--win-menu", "--win-shortcut")
    $packageOutput = Join-Path $distDir "$appName-1.0.0.exe"
} else {
    $packageOutput = Join-Path $distDir $appName
}

if (Test-Path $packageOutput) {
    Remove-Item -LiteralPath $packageOutput -Recurse -Force
}

& (Join-Path $jdkBin "jpackage.exe") @jpackageArgs
if ($LASTEXITCODE -ne 0) {
    if ($PackageType -eq "exe") {
        throw "O jpackage falhou (codigo $LASTEXITCODE). Verifique se o WiX Toolset esta instalado."
    }
    throw "O jpackage falhou (codigo $LASTEXITCODE)."
}

if ($PackageType -eq "exe") {
    Write-Host "Instalador criado em: $distDir"
} else {
    Write-Host "Aplicativo portatil criado em: $distDir"
}
