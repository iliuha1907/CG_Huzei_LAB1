package com.cg.model;

public class LABColor {

    private double l;
    private double a;
    private double b;

    public LABColor(double l, double a, double b) {
        this.l = l;
        this.a = a;
        this.b = b;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
