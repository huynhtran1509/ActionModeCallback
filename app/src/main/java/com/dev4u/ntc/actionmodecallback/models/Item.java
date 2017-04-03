package com.dev4u.ntc.actionmodecallback.models;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.actionmodecallback.models
 * Name project: ActionModeCallback
 * Date: 3/21/2017
 * Time: 21:16
 */

public class Item {
    int id;
    String title;
    String subTitle;

    public Item() {
    }

    public Item(int id, String title, String subTitle) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return title + " - " + subTitle;
    }
}
