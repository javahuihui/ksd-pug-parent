package com.ksd.pug;

import java.util.ArrayList;
import java.util.HashMap;

public interface B {

    public static void main(String[] args) {
        ArrayList list= new ArrayList(20);
        System.out.println("a"+1);
        HashMap<Object, Object> map = new HashMap<>();
        map.put(null,null);
        int x=10;
        x+=x-=x-x;
        System.out.println(x);
        short a=1;
        a+=1;

    }


}
