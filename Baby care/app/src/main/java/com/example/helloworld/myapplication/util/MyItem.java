package com.example.helloworld.myapplication.util;

import android.graphics.drawable.Drawable;

public class MyItem {
    private String name;
    private String contents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public MyItem(String name, String contents)
    {
        this.name = name;
        this.contents = contents;
    }
}
