package com.myapplication.charttest1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.achartengine.renderer.Threshold;

import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int count = 7;
        Date[] dt1 = new Date[count];
        for(int i=0;i<count;i++){
            GregorianCalendar gc = new GregorianCalendar(2012,10,30,11,i,10);
            dt1[i] = gc.getTime();
        }
        Date[] dt2 = new Date[count];
        GregorianCalendar gc2=null;
        for(int i=0;i<count;i++){
            if(i%2==0) {
                gc2 = new GregorianCalendar(2012,10,30,11,i);
            }
        else
        {
                gc2 = new GregorianCalendar(2012,10,30,11,i+4);
        }
            dt2[i] = gc2.getTime();
        }

        int[] data1 = { 2000,2500,  500  ,3000,2800,3500,3700,3800};
        int[] data2 = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };

        int color1= Color.BLUE;
        
        int color2= Color.BLACK;

        Threshold []  threshold = new Threshold[]
                {
                        new Threshold(2200,1512,color1) ,
                        new Threshold(1000,50,color2)
                };
        DoubleLineChart chart = new DoubleLineChart("標題在這","壓力1","壓力2",dt1,dt2,data1,data2 ,color1,color2,threshold,MainActivity.this);

        LinearLayout lin = (LinearLayout) findViewById(R.id.l1);
        lin.addView(chart.GetView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
    }
}
