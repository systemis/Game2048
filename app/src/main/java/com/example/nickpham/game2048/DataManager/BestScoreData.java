package com.example.nickpham.game2048.DataManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by nickpham on 10/12/2016.
 */

public class BestScoreData extends SQLiteOpenHelper
{

    Context context;

    public final String DB_TableName     = "BestScoreSave";
    public final String DB_IDKey         = "IDKey";
    public final String DB_NewBestScore  = "NewBestScore";

    public BestScoreData(Context context)
    {
        super(context, "BestScoreDataLocate", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        String sql = "Create table if not exists " + DB_TableName + " ( "
                + DB_IDKey + " text primary key, " + DB_NewBestScore + " text" + ")";

        sqLiteDatabase.execSQL(sql);

    }


    public String Get_Best_Score()
    {

        String Best_Score = null;

        SQLiteDatabase db = getWritableDatabase();
        String sql = "Select * from " + DB_TableName;
        Cursor c = db.rawQuery(sql, null);

        if(c.moveToFirst())
        {

            Best_Score = c.getString(c.getColumnIndex(DB_NewBestScore));

        }

        return Best_Score;

    }


    public void Add_Best_Score(String ID, int Best_Score)
    {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DB_IDKey, ID);
        values.put(DB_NewBestScore, String.valueOf(Best_Score));

        int Result_Add = (int) db.insert(DB_TableName, null, values);

        if(Result_Add != -1)
        {
            // Test
        }else
        {
            // Test
        }

    }


    public void Update_Best_Score(int NewScore)
    {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DB_NewBestScore, NewScore);

        db.update(DB_TableName, values, DB_IDKey + " = ? ", new String[]{String.valueOf(1)});

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
