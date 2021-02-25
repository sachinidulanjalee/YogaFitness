package com.example.yogafitness.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.renderscript.Sampler;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class YogaDB extends SQLiteAssetHelper {


            private  static final String DB_NAME= "Yoga.db";
            private static final int DB_VER = 1;
           // private static String DB_PATH = "";

    public YogaDB(Context context) {
        super(context, DB_NAME, null, DB_VER);
        //this.DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }
    public int getSettingMode()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {"Mode"};
        String sqlTable ="Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect,null,null,null,null,null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Mode"));

    }

    public void saveSettingMode(int value)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query ="UPDATE Setting SET Mode = "+value;
        db.execSQL(query);
    }

    public List<String> getWorkoutDays()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb =  new SQLiteQueryBuilder();

        String[] sqlSelect = {"Day"};
        String sqlTable ="WorkoutDays";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();
        if (c.moveToFirst())
        {
                do {
                    result.add(c.getString(c.getColumnIndex("Day")));
                }while (c.moveToNext());
        }
        return result;


    }

    public void saveDay(String value)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays(Day) VALUES('%s');",value);
        db.execSQL(query);
    }

}

