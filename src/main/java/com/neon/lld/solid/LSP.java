package com.neon.lld.solid;

public class LSP {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(4, 5);
        System.out.println(rectangle.getArea());    // 20
        Rectangle square = new Square(5);
        System.out.println(square.getArea()); // 25
        square.setWidth(4);
        System.out.println(square.getArea());   //16, !important! result will be 20 if setWidth & setHeight are not overridden in Square
    }
}

class Rectangle {
    protected int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

class Square extends Rectangle {
    Square(int side) {
        super(side, side);
    }

    @Override
    public void setWidth(int width) {
        super.width = width;
        super.height = width;
    }

    @Override
    public void setHeight(int height) {
        super.width = height;
        super.height = height;
    }
}
