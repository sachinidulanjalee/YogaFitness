package com.example.yogafitness;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yogafitness.Database.YogaDB;
import com.example.yogafitness.Model.Exercise;
import com.example.yogafitness.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Daily_Training extends AppCompatActivity {



    Button btnStart;
    ImageView ex_image;
    TextView txtGetReady,txtCountDown,txtTimer,ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;


    int ex_id = 0 , limit_time = 0;
    List<Exercise>list = new ArrayList<>();

    YogaDB yogaDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__training);

        initData();

        yogaDB = new YogaDB(this);



        btnStart = (FButton)findViewById(R.id.btnStart);
        ex_image=(ImageView)findViewById(R.id.detail_image);

        txtCountDown = (TextView)findViewById(R.id.txtCountdown);
        txtGetReady = (TextView)findViewById(R.id.txtGetReady);
        txtTimer=(TextView)findViewById(R.id.timer);
        ex_name = (TextView) findViewById(R.id.title);

        layoutGetReady = (LinearLayout)findViewById(R.id.layout_get_ready);

        progressBar = (MaterialProgressBar)findViewById(R.id.progressBar);

        //Set Data
        progressBar.setMax(list.size());

        setExercisesInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnStart.getText().toString().toLowerCase().equals("start"))
                {
                    showGetReady();
                    btnStart.setText("done");
                }
                else if (btnStart.getText().toString().toLowerCase().equals("done"))
                {
                    if (yogaDB.getSettingMode()==0)
                        exercisesEasyModeCountDown.cancel();
                    else if (yogaDB.getSettingMode()==1)
                        exercisesMediumModeCountDown.cancel();
                    else if (yogaDB.getSettingMode()==2)
                        exercisesHardModeCountDown.cancel();

                        restTimeCountDown.cancel();

                        if (ex_id < list.size())
                        {
                            showRestTime();
                            ex_id++;
                            progressBar.setProgress(ex_id);
                            txtTimer.setText("");
                        }
                        else
                            showFinished();
                }
                else
                {
                    if (yogaDB.getSettingMode()==0)
                        exercisesEasyModeCountDown.cancel();
                    else if (yogaDB.getSettingMode()==1)
                        exercisesMediumModeCountDown.cancel();
                    else if (yogaDB.getSettingMode()==2)
                        exercisesHardModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if(ex_id< list.size())
                        setExercisesInformation(ex_id);
                    else
                        showFinished();
                }
            }
        });


    }

    private void showRestTime() {
        ex_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setText("Skip");
        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        restTimeCountDown.start();

        txtGetReady.setText("REST TIME");
    }

    private void showGetReady() {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("GET READY");
        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtCountDown.setText(""+(millisUntilFinished-1000)/1000);
            }

            @Override
            public void onFinish() {
                    showExercises();
            }
        }.start();
    }

    private void showExercises() {
        if (ex_id < list.size()) //list size contains all exercises
        {
            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if (yogaDB.getSettingMode()==0)
                exercisesEasyModeCountDown.start();
            else if (yogaDB.getSettingMode()==1)
                exercisesMediumModeCountDown.start();
            else if (yogaDB.getSettingMode()==2)
                exercisesHardModeCountDown.start();


            //set data
            ex_image.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());

        }
        else
            showFinished();

    }

    private void showFinished() {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("FINISHED!!!");
        txtCountDown.setText("Congratulation! \nYou're done with today exercises");
        txtCountDown.setTextSize(20);

        //save workout done to DB
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            yogaDB.saveDay(""+ Calendar.getInstance().getTimeInMillis());
        }
    }

    //CountDown
    CountDownTimer exercisesEasyModeCountDown =  new CountDownTimer(Common.TIME_LIMIT_EASY,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtTimer.setText(""+(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
                if (ex_id < list.size()-1)
                {
                    ex_id++;
                    progressBar.setProgress(ex_id);
                    txtTimer.setText("");

                    setExercisesInformation(ex_id);
                    btnStart.setText("Start");
                }
                else
                {
                    showFinished();
                }
        }
    };
    CountDownTimer exercisesMediumModeCountDown =  new CountDownTimer(Common.TIME_LIMIT_MEDIUM,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtTimer.setText(""+(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size()-1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExercisesInformation(ex_id);
                btnStart.setText("Start");
            }
            else
            {
                showFinished();
            }
        }
    };
    CountDownTimer exercisesHardModeCountDown =  new CountDownTimer(Common.TIME_LIMIT_HARD,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtTimer.setText(""+(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size()-1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExercisesInformation(ex_id);
                btnStart.setText("Start");
            }
            else
            {
                showFinished();
            }
        }
    };


    CountDownTimer restTimeCountDown =  new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtCountDown.setText(""+(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
           setExercisesInformation(ex_id);
           showExercises();
        }
    };

    private void setExercisesInformation(int id) {

        ex_image.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");

        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);

    }

    private void initData() {

        list.add(new Exercise(R.drawable.easy_pose,"Easy Pose"));
        list.add(new Exercise(R.drawable.cobra_pose,"Cobra Pose"));
        list.add(new Exercise(R.drawable.dog_pose,"Downward Facing Dog"));
        list.add(new Exercise(R.drawable.boat_pose,"Boat Pose"));
         list.add(new Exercise(R.drawable.half_pegion_pose,"Half Pigeon Pose"));
        list.add(new Exercise(R.drawable.low_lunge_with_back_bend_pose,"Law Lunge"));
        list.add(new Exercise(R.drawable.upward_bow_pose,"Upward Pose"));
       list.add(new Exercise(R.drawable.istockphoto_1299572741_612x612,"Crescent Lunge"));
       list.add(new Exercise(R.drawable.warrior_2_pose,"Warrior Pose"));
        list.add(new Exercise(R.drawable.pngitem_709264,"Bow Pose"));
       list.add(new Exercise(R.drawable.warrior_iii_eagle_l,"Warrior Pose 2"));
    }
}