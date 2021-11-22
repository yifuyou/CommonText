package com.yifuyou.commontest;

public class NumCaculater {
    public static NumCaculater GetInstance() {
        if (instance == null) {
            instance = new NumCaculater();
        }
        return instance;
    }

    private static NumCaculater instance;
    private int count;
    private boolean culBool=false;

    private NumCaculater() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCulBool(boolean bool){

         culBool=bool;
    }

    public boolean countAdd() {
        if (culBool && (count < 100)) {
            count += 10;
            return true;
        }
        culBool=false;
        return culBool;
    }


    public void reset(){
        culBool=true;
    }

    public void release() {
        instance = null;
        culBool=false;
    }

}
