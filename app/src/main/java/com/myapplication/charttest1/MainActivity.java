package com.myapplication.charttest1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.tencent.mm.sdk.openapi.IWXAPI;

import org.achartengine.renderer.Threshold;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private IWXAPI api;
    ImageButton mWeChatFriendCircle;
    ImageButton mWeChatFriend;

    ImageButton mNutriShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        this.mNutriShare = (ImageButton)findViewById(R.id.NutriShare);
//        mNutriShare.setOnClickListener(WeixiShare);
//
//
//
//        NutriCardBulid();


         chart();
    }

    private View.OnClickListener WeixiShare =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Wechat();
        }
    };


    private void NutriCardBulid()
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        NutritionCard card1= new NutritionCard(this,(View)findViewById(R.id.card1),metrics.widthPixels/3);
        card1.setTitleText("營養結構分析");
        card1.setCommText("如果長期處於隱性飢餓狀態，對人體將造成不可逆轉的損害；例如，鉀不足，體內鈉鉀平衡被破壞，會讓血壓增高，可能出現心律不整、肌肉無力等症狀。");
        NutritionCard card2= new NutritionCard(this,(View)findViewById(R.id.card2),metrics.widthPixels/3);
        card2.setTitleText("維生素分析");
        card2.setCommText("由於國人一半的主食來自米飯，金惠民建議民眾，少吃白米飯、白土司、精緻澱粉食物，多吃全穀、紫米；並且攝取足量的蔬菜水果類，適量攝取種子堅果，必要時可補充綜合維他命。");
        NutritionCard card3= new NutritionCard(this,(View)findViewById(R.id.card3),metrics.widthPixels/3);
        card3.setTitleText("礦物質分析");
        card3.setCommText("Test");
        NutritionCard card4= new NutritionCard(this,(View)findViewById(R.id.card4),metrics.widthPixels/3);
        card4.setTitleText("其他情況");
        card4.setCommText("Others");

    }

        private void Wechat() {
            this.mWeChatFriend = (ImageButton) findViewById(R.id.WeChatFriend);
            this.mWeChatFriendCircle = (ImageButton) findViewById(R.id.WeChatFriendCircle);

            this.mWeChatFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WeixinShareManager wsm = WeixinShareManager.getInstance(MainActivity.this);
                    wsm.shareByWeixin(wsm.new ShareContentText("this is zhyy program's test"), WeixinShareManager.WEIXIN_SHARE_TYPE_TALK);
//                wsm.shareByWeixin(wsm.new ShareContentPic(R.drawable.ic_launcher),
//                        WeixinShareManager.WEIXIN_SHARE_TYPE_TALK);
                }
            });

        }

        private void chart() {



            int count = 7;
            Date[] dt1 = new Date[count*3];
            int j=0;
            int month = 9;
            for(int i =0 ;i<7 ;i++) {

                Date gc = new Date(2012, month, i+1,7, 0);
                dt1[j] = gc;
                j++;
                gc = new Date(2012, month, i+1, 12, 0);
                dt1[j] = gc ;
                j++;
                gc = new Date(2012, month, i+1, 19, 0);
                dt1[j] = gc;
                j++;
            }

            Date[] dt2 = new Date[count*2];

            j=0;
            for(int i =0 ;i<7 ;i++) {

                Date gc = new Date(2012,month, i+1, 7, 0);
                dt2[j] = gc ;
                j++;
                gc = new Date(2012,month, i+1, 12, 0);
                dt2[j] =  gc;
                j++;

            }
            int []data1= new int [21];
            int[] data2 =new int [14];
            for(int i =0 ;i<data1.length ;i++)
            {
                data1[i] = (int)(Math.random()* 2000 +100);

            }
            for(int i =0 ;i<data2.length ;i++) {
                data2[i] = (int) (Math.random() * 2000 + 50);
            }
            int color1 = Color.BLUE;
            int color2 = Color.BLACK;
            Threshold[] threshold = new Threshold[]
                    {
                            new Threshold(1200, 1000, color1),
                             new Threshold(900, 500, color2)
                    };
            DoubleLineChart chart = new DoubleLineChart("PChart",new Date(2012,9,4), "壓力1", "壓力2", dt1, dt2, data1, data2, color1, color2, threshold, MainActivity.this);

            LinearLayout lin = (LinearLayout) findViewById(R.id.l1);

            lin.addView(chart.GetView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        }

}
