package com.liumq.bishi;

public class Box {

    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getAcreage() {
        assert this.width > 0;
        assert this.length > 0;
        assert this.height > 0;

        return this.height * this.length * this.width;
    }
}
