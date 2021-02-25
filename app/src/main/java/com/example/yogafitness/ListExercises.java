package com.example.yogafitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.yogafitness.Adapter.RecyclerViewAdapter;
import com.example.yogafitness.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

            initData();

            recyclerView =(RecyclerView) findViewById(R.id.list_ex);
            adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

    }

    private void initData() {

        exerciseList.add(new Exercise(R.drawable.easy_pose,"Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose,"Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.dog_pose,"Downward Facing Dog"));
        exerciseList.add(new Exercise(R.drawable.boat_pose,"Boat Pose"));
        exerciseList.add(new Exercise(R.drawable.half_pegion_pose,"Half Pigeon Pose"));
        exerciseList.add(new Exercise(R.drawable.low_lunge_with_back_bend_pose,"Law Lunge"));
        exerciseList.add(new Exercise(R.drawable.upward_bow_pose,"Upward Pose"));
        exerciseList.add(new Exercise(R.drawable.istockphoto_1299572741_612x612,"Crescent Lunge"));
        exerciseList.add(new Exercise(R.drawable.warrior_2_pose,"Warrior Pose"));
        exerciseList.add(new Exercise(R.drawable.pngitem_709264,"Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_iii_eagle_l,"Warrior Pose 2"));
    }
}