package com.hpercent.snail.app.models;

/**
 * Created by koudejian on 14-11-6.
 */
public class CategoryModel {
    int drawable = 0;
    String text = "";

    public CategoryModel(int drawable, String text){
        this.drawable = drawable;
        this.text = text;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
