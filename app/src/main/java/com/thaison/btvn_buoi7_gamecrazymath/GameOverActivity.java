package com.thaison.btvn_buoi7_gamecrazymath;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shin on 7/20/2016.
 */
public class GameOverActivity extends Activity implements View.OnClickListener{
    private TextView tvGameOver;
    private TextView tvYourScore;
    private ImageView ivReplay;
    private int yourScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        initViews();
    }

    private void initViews() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Gabriola.ttf");
        tvGameOver = (TextView)findViewById(R.id.tv_gameover);
        tvGameOver.setTypeface(typeface);
        tvYourScore = (TextView)findViewById(R.id.tv_yourscore);
        tvYourScore.setTypeface(typeface);
        Intent intent = getIntent();
        yourScore = intent.getIntExtra("score", 0);
        tvYourScore.setText("Your score: " + yourScore);
        ivReplay = (ImageView)findViewById(R.id.iv_replay);
        ivReplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
        GameOverActivity.this.startActivity(intent);
    }
}
