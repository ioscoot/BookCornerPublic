package com.example.rober.bookcorner.classes;

import java.io.Serializable;

public class Carte implements Serializable {
    public String autor;
    public String descriere;
    public String titlu;
    public int cantitate;
    public int nrAprecieri;

    public Carte() {

    }

    public Carte(String autor, int cantitate, String descriere, int nrAprecieri, String titlu) {
        this.autor = autor;
        this.descriere = descriere;
        this.titlu = titlu;
        this.cantitate = cantitate;
        this.nrAprecieri = nrAprecieri;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public long getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public long getNrAprecieri() {
        return nrAprecieri;
    }

    public void setNrAprecieri(int nrAprecieri) {
        this.nrAprecieri = nrAprecieri;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "autor='" + autor + '\'' +
                ", descriere='" + descriere + '\'' +
                ", titlu='" + titlu + '\'' +
                ", cantitate=" + cantitate +
                ", nrAprecieri=" + nrAprecieri +
                '}';
    }
}
