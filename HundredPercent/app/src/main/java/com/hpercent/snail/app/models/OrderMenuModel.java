package com.hpercent.snail.app.models;

/**
 * Created by koudejian on 14-11-4.
 */
public class OrderMenuModel {
    String name = null;
    String num = null;

    public OrderMenuModel(String name){
        this.name = name;
    }
    public OrderMenuModel(String name, String num){
        this(name);
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getNumText(){
        if("".equals(num) || num == null){
            return "";
        }else{
            return "（" + num + "）";
        }
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
