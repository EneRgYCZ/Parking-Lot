package com.example.parkinglot;

public class Rezervari
{
    String Numar_Inmatriculare, numeParacare;

    public Rezervari ()
    {

    }

    public Rezervari(String numar_Inmatriculare, String nume_Parcare)
    {
        this.Numar_Inmatriculare = numar_Inmatriculare;
        this.numeParacare = nume_Parcare;
    }

    public String getNumar_Inmatriculare()
    {
        return Numar_Inmatriculare;
    }

    public void setNumar_Inmatriculare(String numar_Inmatriculare)
    {
        Numar_Inmatriculare = numar_Inmatriculare;
    }

    public String getNumeParacare() {
        return numeParacare;
    }

    public void setNumeParacare(String numeParacare) {
        this.numeParacare = numeParacare;
    }
}
