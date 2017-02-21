package com.thaison.btvn_buoi7_gamecrazymath;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Shin on 7/20/2016.
 */
public class MainActivity extends Activity implements View.OnClickListener, Runnable {
    private static final int ADDITION = 0;
    private static final int SUBTRACTION = 1;
    private static final String PLUS_SIGN = "+";
    private static final String MINUS_SIGN = "-";
    private static final int PLUS = 0;
    private static final int TRUE = 0;
    private static final int UPDATE_TIME = 0;

    private ProgressBar pbTime;
    private TextView tvScore;
    private TextView tvCalculation;
    private ImageView ivTrue;
    private ImageView ivFalse;

    private int level;
    private int number1;
    private int number2;
    private int result;
    private int answer;
    private String sign;
    private String calculation;
    private Random random;
    private int score;
    private boolean isGameOver;

    private Thread thread;
    private Handler handler;
    private int time = 100;
    private boolean isFinishGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initComponents();
    }

    private void initViews() {
        pbTime = (ProgressBar) findViewById(R.id.pb_time);
        pbTime.setMax(100);
        pbTime.setProgress(time);
        tvScore = (TextView) findViewById(R.id.tv_score);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Gabriola.ttf");
        tvScore.setTypeface(typeface);
        tvCalculation = (TextView) findViewById(R.id.tv_calculation);
        tvCalculation.setTypeface(typeface);
        tvCalculation.setTextSize(90);
        ivTrue = (ImageView) findViewById(R.id.iv_true);
        ivTrue.setOnClickListener(this);
        ivFalse = (ImageView) findViewById(R.id.iv_false);
        ivFalse.setOnClickListener(this);
    }

    private void initComponents() {
        isGameOver = false;
        level = 1;
        score = 0;
        time = 100;
        random = new Random();
        initCalculation();
        tvCalculation.setText(calculation);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case UPDATE_TIME:
                        time = msg.arg1;
                        pbTime.setProgress(time);
                        break;
                }
            }
        };

        thread = new Thread(this);
        thread.start();
    }

    private void initCalculation() {
        if (level <= 15) {
            number1 = random.nextInt(10);
            number2 = random.nextInt(10);
            sign = PLUS_SIGN;
            answer = number1 + number2;
            if (random.nextInt(2) == TRUE) {
                result = answer;
            } else {
                result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(5)
                        : answer - random.nextInt(5);
            }
            while (answer < 0 || result < 0) {
                number1 = random.nextInt(10);
                number2 = random.nextInt(10);
                sign = PLUS_SIGN;
                answer = number1 + number2;
                if (random.nextInt(2) == TRUE) {
                    result = answer;
                } else {
                    result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(5)
                            : answer - random.nextInt(5);
                }
            }
        } else {
            if (level <= 30) {
                number1 = random.nextInt(20);
                number2 = random.nextInt(20);
                switch (random.nextInt(2)) {
                    case ADDITION:
                        sign = PLUS_SIGN;
                        answer = number1 + number2;
                        if (random.nextInt(2) == TRUE) {
                            result = answer;
                        } else {
                            result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(5)
                                    : answer - random.nextInt(5);
                        }
                        break;
                    case SUBTRACTION:
                        sign = MINUS_SIGN;
                        answer = number1 - number2;
                        if (random.nextInt(2) == TRUE) {
                            result = answer;
                        } else {
                            result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(5)
                                    : answer - random.nextInt(5);
                        }
                        break;
                }

                while (answer < 0 || result < 0) {
                    number1 = random.nextInt(20);
                    number2 = random.nextInt(20);
                    switch (random.nextInt(2)) {
                        case ADDITION:
                            sign = PLUS_SIGN;
                            answer = number1 + number2;
                            if (random.nextInt(2) == TRUE) {
                                result = answer;
                            } else {
                                result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(5)
                                        : answer - random.nextInt(5);
                            }
                            break;
                        case SUBTRACTION:
                            sign = MINUS_SIGN;
                            answer = number1 - number2;
                            if (random.nextInt(2) == TRUE) {
                                result = answer;
                            } else {
                                result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(5)
                                        : answer - random.nextInt(5);
                            }
                            break;
                    }
                }
            } else {
                number1 = random.nextInt(99);
                number2 = random.nextInt(99);
                switch (random.nextInt(2)) {
                    case ADDITION:
                        sign = PLUS_SIGN;
                        answer = number1 + number2;
                        if (random.nextInt(2) == TRUE) {
                            result = answer;
                        } else {
                            result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(10)
                                    : answer - random.nextInt(5);
                        }
                        break;
                    case SUBTRACTION:
                        sign = MINUS_SIGN;
                        answer = number1 - number2;
                        if (random.nextInt(2) == TRUE) {
                            result = answer;
                        } else {
                            result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(10)
                                    : answer - random.nextInt(5);
                        }
                        break;
                }

                while (answer < 0 || answer > 99 || result < 0 || result > 99) {
                    number1 = random.nextInt(99);
                    number2 = random.nextInt(99);
                    switch (random.nextInt(2)) {
                        case ADDITION:
                            sign = PLUS_SIGN;
                            answer = number1 + number2;
                            if (random.nextInt(2) == TRUE) {
                                result = answer;
                            } else {
                                result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(10)
                                        : answer - random.nextInt(5);
                            }
                            break;
                        case SUBTRACTION:
                            sign = MINUS_SIGN;
                            answer = number1 - number2;
                            if (random.nextInt(2) == TRUE) {
                                result = answer;
                            } else {
                                result = (random.nextInt(2) == PLUS) ? answer + random.nextInt(10)
                                        : answer - random.nextInt(5);
                            }
                            break;
                    }
                }
            }
        }
        calculation = number1 + " " + sign + " " + number2 + " = " + result;
    }

    private void goNextLevel() {
        level++;
        time = 100;
        pbTime.setProgress(time);
        score++;
        tvScore.setText("Your score: " + score);
        initCalculation();
        tvCalculation.setText(calculation);
    }

    private void finishGame() {
        isFinishGame = true;
        Intent intent = new Intent(MainActivity.this, GameOverActivity.class);
        intent.putExtra("score", score);
        MainActivity.this.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_true:
                if (result == answer) {
                    goNextLevel();
                } else {
                    finishGame();
                }
                break;

            case R.id.iv_false:
                if (result == answer) {
                    finishGame();
                } else {
                    goNextLevel();
                }
                break;
        }
    }

    @Override
    public void run() {
        while (!isGameOver) {
            while (time > 0 && !isFinishGame) {
                Message message = new Message();
                message.what = UPDATE_TIME;
                message.arg1 = time;
                message.setTarget(handler);
                message.sendToTarget();
                SystemClock.sleep(10);
                time -= 1;
            }
            if (time == 0) {
                isGameOver = true;
            }
            time = 100;
        }
        if (!isFinishGame) {
            isGameOver = true;
            finishGame();
        }
    }
}
