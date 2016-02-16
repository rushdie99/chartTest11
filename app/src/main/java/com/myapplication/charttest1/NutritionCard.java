package com.myapplication.charttest1;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by polin on 2016/2/16.
 */
public class NutritionCard {
    Context context;
    View mCardView;
    ImageButton mNutriSwitch;
    LinearLayout mNutrilayout;
    TextView mNutriTitle;
    TextView mNutriComm;
    int mDetialTitleWidth;
    public NutritionCard(Context context ,View cardView,int detialwidth)
    {
        this.context=context;
        this.mCardView= cardView;
        this.mDetialTitleWidth=detialwidth;
        mNutriSwitch=(ImageButton)mCardView.findViewById(R.id.NutriSwitch);
        mNutriSwitch.setOnClickListener(LayoutCommand);
        //Switch card context
        mNutriSwitch.setTag(2);
        mNutrilayout = (LinearLayout) mCardView.findViewById(R.id.NutriLayout);
        mNutrilayout.setTag(1);
        mNutriTitle = (TextView)mCardView.findViewById(R.id.NutriTitle);
        mNutriComm = (TextView)mCardView.findViewById(R.id.NutriComm);

    }

    public void  setTitleText(String Text)
    {
        mNutriTitle.setText(Text);
    }

    public void  setCommText(String Text)
    {
        mNutriComm.setText(Text);
    }

    private void clearDetial(LinearLayout layout ) {
        layout.removeAllViewsInLayout();
        layout.setVisibility(View.GONE);

    }
    private View.OnClickListener LayoutCommand = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doSwitch(mNutrilayout);
        }

    };

    private void setDetial(LinearLayout layout ,String mainItem, String many_g, int Resource) {


        LinearLayout detailLayout =new LinearLayout(context);
        //width,height
        detailLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        TextView tv = new TextView(context);
        tv.setWidth(mDetialTitleWidth );
        tv.setText(mainItem);

        LinearLayout barLayout =new LinearLayout(context);
        barLayout.setGravity(Gravity.CENTER);
        barLayout.setOrientation(LinearLayout.VERTICAL);
        barLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        ImageView image=new ImageView(context);
        image.setImageResource(Resource);
        TextView bartv = new TextView(context);
        bartv.setGravity(Gravity.CENTER);
        bartv.setText(many_g);
        barLayout.addView(image);
        barLayout.addView(bartv);
        detailLayout.addView(tv);
        detailLayout.addView(barLayout);

        layout.addView(detailLayout);
    }

    private void doSwitch(LinearLayout layout )
    {

        //Data in this Way
        int tag = (Integer) layout.getTag();
        if (tag==1){

            //api in do set Data
            setDetial(layout,"熱量:3000", "2000 g", R.drawable.nutri_max);
            setDetial(layout,"碳水化合物:60", "30 g", R.drawable.nutri_more);
            setDetial(layout,"蛋白質:80", "50 g", R.drawable.nutri_normal);
            setDetial(layout,"脂肪:100", "100 仟克", R.drawable.nutri_less);
            setDetial(layout,"纖維:80", "1000 g", R.drawable.nutri_min);



            layout.setVisibility(View.VISIBLE);
            layout.setTag(2);
        }
        else
        {
            clearDetial( layout );
            layout.setTag(1);
        }
    }

}
