package Model;

import Controller.HouseController;

import java.io.IOException;

public class House {
    private int x, y;

    public House(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }


    public void showHouse(int num) throws IOException {
        HouseController.showHouse(num, this, x);
    }

}