package com.advantech.iqescloud;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class BasicTest {

    @Test
    public void test(){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String dateString=sdf.format(new Date());
        System.out.println(dateString);
    }

    @Test
    public void testRandom(){
        Random random=new Random();
        for (int i=1;i<20;i++){
            System.out.println("A"+(random.nextInt(100)+100));
        }
    }

    @Test
    public void testStringConvertArray(){
        String s="1,2";
        String[] c=s.split(",");
        System.out.println(c.length);
        Long[] longs=new Long[c.length];
        longs[0]= Long.valueOf(c[0]);
        longs[1]=Long.valueOf(c[1]);

        System.out.println(Arrays.toString(longs));
    }
}
