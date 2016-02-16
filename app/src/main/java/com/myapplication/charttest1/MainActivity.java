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
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {


    private IWXAPI api;
    ImageButton mWeChatFriendCircle;
    ImageButton mWeChatFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_report_initial);

        NutriCardBulid();

        //Wechat();
        //chart();
    }
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
            Date[] dt1 = new Date[count];
            for (int i = 0; i < count; i++) {
                GregorianCalendar gc = new GregorianCalendar(2012, 10, 30, 11, i, 10);
                dt1[i] = gc.getTime();
            }
            Date[] dt2 = new Date[count];
            GregorianCalendar gc2 = null;
            for (int i = 0; i < count; i++) {
                if (i % 2 == 0) {
                    gc2 = new GregorianCalendar(2012, 10, 30, 11, i);
                } else {
                    gc2 = new GregorianCalendar(2012, 10, 30, 11, i + 4);
                }
                dt2[i] = gc2.getTime();
            }

            int[] data1 = {2000, 2500, 500, 3000, 2800, 3500, 3700, 3800};
            int[] data2 = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400};

            int color1 = Color.BLUE;

            int color2 = Color.BLACK;

            Threshold[] threshold = new Threshold[]
                    {
                            new Threshold(2200, 1512, color1),
                            new Threshold(1000, 50, color2)
                    };
            DoubleLineChart chart = new DoubleLineChart("標題在這", "壓力1", "壓力2", dt1, dt2, data1, data2, color1, color2, threshold, MainActivity.this);

            LinearLayout lin = (LinearLayout) findViewById(R.id.l1);

            lin.addView(chart.GetView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        }

}
