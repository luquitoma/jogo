## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Gerar instalador para Windows

O projeto pode ser empacotado como um instalador `.exe` independente do VS Code. O instalador inclui um runtime Java e os arquivos do JavaFX, portanto quem instalar o jogo não precisa instalar o VS Code nem o Java.

Requisitos para gerar o instalador:

- Windows 10 ou posterior, 64 bits.
- JDK 25 instalado e disponível no `PATH` (com `javac`, `jar` e `jpackage`).
- WiX Toolset 3.14.1 ou superior disponível no `PATH`, necessário para criar o instalador `.exe`. O script também reconhece o WiX em `%LOCALAPPDATA%\WixToolset\v3.14`.
- JavaFX SDK 26.0.2 na pasta `javafx-sdk-26.0.2` do projeto.

No PowerShell, a partir da pasta do projeto, execute:

```powershell
powershell.exe -NoProfile -ExecutionPolicy Bypass -File .\build-installer.ps1
```

O instalador `Farinha e o Último Guerreiro-1.0.0.exe` será criado na pasta `dist`. Copie esse arquivo para distribuir o jogo; ele inclui o runtime Java e as bibliotecas JavaFX, então o computador de destino não precisa ter Java ou VS Code instalados. Os avisos de licença do JavaFX também são incluídos. A saída temporária de compilação fica em `build\jpackage`.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
