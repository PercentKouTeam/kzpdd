package com.hpercent.snail.app.models;

import java.util.List;

/**
 * Created by lyihuang86 on 2014/9/2.
 */
public class IndustryModel {
    public String name = "";
    public int type = 0; // 0 大类  1子类
    public int id = 0; // 唯一标识
    public List<IndustryModel> group; //成组的
}
