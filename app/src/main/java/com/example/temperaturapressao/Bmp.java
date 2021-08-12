package com.example.temperaturapressao;

public class Bmp {

    double temperatura, pressao;

    public Bmp(double temperatura, double pressao) {
        this.temperatura = temperatura;
        this.pressao = pressao;
    }

    public Bmp() {
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPressao() {
        return pressao;
    }

    public void setPressao(double pressao) {
        this.pressao = pressao;
    }
}
