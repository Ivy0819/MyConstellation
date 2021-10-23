package com.swufestu.myconstellation;

public class Item {
    String info;
    String intro;

    public Item(String info, String intro) {
        this.info = info;
        this.intro = intro;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
