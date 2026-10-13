package model;

import java.util.ArrayList;
import java.util.List;

public class Personagem {
    
    private String nome;
    private int nivel;
    private int experiencia;
    private int vida;
    private int especial;
    private int dinheiro; 
    private int ataque;
    private int defesa;
    private List<String> habilidades;
    
    public Personagem(String nome, int nivel, int experiencia, int vida, int especial, int dinheiro, int ataque, int defesa) {
        this.nome = nome;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.vida = vida;
        this.especial = especial;
        this.dinheiro = dinheiro;
        this.habilidades = new ArrayList<>();
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getEspecial() {
        return especial;
    }

    public void setEspecial(int especial) {
        this.especial = especial;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }
    
    public void adicionarHabilidade(String habilidade) {
        habilidades.add(habilidade);
    }
}
