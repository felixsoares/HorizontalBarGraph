package br.com.felix.horizontalbargraph.model;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by user on 16/01/2018.
 */

public class BarItem {

    private String description;

    private Double value1;
    private Double value2;

    private int colorBar1;
    private int colorBar2;

    private int textColorBar1;
    private int textColorBar2;

    public BarItem(String description, Double value1, int colorBar1, int textColorBar1) {
        this.setDescription(description);
        this.setValue1(value1);
        this.setColorBar1(colorBar1);
        this.setTextColorBar1(textColorBar1);
    }

    public BarItem(String description, Double value1) {
        this.setDescription(description);
        this.setValue1(value1);
    }

    public BarItem(String description, Double value1, Double value2) {
        this.setDescription(description);
        this.setValue1(value1);
        this.setValue2(value2);
    }

    public BarItem(String description, Double value1, Double value2, int colorBar1, int colorBar2, int textColorBar1, int textColorBar2) {
        this.setDescription(description);
        this.setValue1(value1);
        this.setValue2(value2);
        this.setColorBar1(colorBar1);
        this.setColorBar2(colorBar2);
        this.setTextColorBar1(textColorBar1);
        this.setTextColorBar2(textColorBar2);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public int getColorBar1() {
        return colorBar1 != 0 ? colorBar1 : getRandomColor();
    }

    public void setColorBar1(int colorBar1) {
        this.colorBar1 = colorBar1;
    }

    public int getColorBar2() {
        return colorBar2 != 0 ? colorBar2 : getRandomColor();
    }

    public void setColorBar2(int colorBar2) {
        this.colorBar2 = colorBar2;
    }

    public int getTextColorBar1() {
        return textColorBar1 != 0 ? textColorBar1 : getRandomColor();
    }

    public void setTextColorBar1(int textColorBar1) {
        this.textColorBar1 = textColorBar1;
    }

    public int getTextColorBar2() {
        return textColorBar2 != 0 ? textColorBar2 : getRandomColor();
    }

    public void setTextColorBar2(int textColorBar2) {
        this.textColorBar2 = textColorBar2;
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
