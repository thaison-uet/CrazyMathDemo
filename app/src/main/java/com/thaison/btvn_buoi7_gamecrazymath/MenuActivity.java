package com.thaison.btvn_buoi7_gamecrazymath;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shin on 7/19/2016.
 */
public class MenuActivity extends Activity implements View.OnClickListener{
    private TextView tvTitle;
    private ImageView ivPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initViews();
    }

    private void initViews () {
        tvTitle = (TextView)findViewById(R.id.tv_title);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Gabriola.ttf");
        tvTitle.setTypeface(typeface);

        ivPlay = (ImageView)findViewById(R.id.iv_play);
        ivPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        MenuActivity.this.startActivity(intent);
    }
}
