package util;

public class Config {
    private static final Config instance = new Config();
    private boolean musicaLigada = true, sonsLigados = true, autoSaveLigado = true;

    private Config () {

    }

    public static Config getInstance () {
        return instance;
    }
    public boolean isAutoSaveLigado() {
        return autoSaveLigado;
    }

    public void setAutoSaveLigado(boolean autoSave) {
        this.autoSaveLigado = autoSave;
    }

    public boolean isMusicaLigada() {
        return musicaLigada;
    }

    public void setMusicaLigada(boolean musicaLigada) {
        this.musicaLigada = musicaLigada;
    }

    public boolean isSonsLigados() {
        return sonsLigados;
    }

    public void setSonsLigados(boolean sonsLigados) {
        this.sonsLigados = sonsLigados;
        
    }

    
}
