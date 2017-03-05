package com.example.nickpham.game2048.Handling;

import android.content.Context;
import android.transition.Slide;
import android.util.Log;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.TabHost;
import android.widget.Toast;


import com.example.nickpham.game2048.PlayBoardActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickpham on 08/12/2016.
 */

public class Check_Move_Slides
{

    Context context;
    Button[][] Slides_W_Move;

    public Check_Move_Slides(Context context, Button[][] Slides_W_Move, int Mode_Move)
    {
        this.context       = context;
        this.Slides_W_Move = Slides_W_Move;

        // Action touch from player --->
        if (Mode_Move == 1) Check_Move_Bac_Nam();
        if (Mode_Move == 2) Check_Move_Nam_Bac();
        if (Mode_Move == 3) Check_Move_Dong_Tay();
        if (Mode_Move == 4) Check_Move_Tay_Dong();

    }


    // Mode Move = 1
    public void Check_Move_Bac_Nam()
    {
        for (int cot = 0; cot < Slides_W_Move.length; cot++)
        {
            List<Long> Du_Tinh = new ArrayList<Long>();
            for (int dong = Slides_W_Move.length - 1; dong >= 0; dong--)
            {
                if (Slides_W_Move[dong][cot].getText().toString().isEmpty() == false)
                {
                    Du_Tinh.add((long)Integer.parseInt(Slides_W_Move[dong][cot].getText().toString()));
                    Slides_W_Move[dong][cot].setText("");
                }
            }

            Du_Tinh = XapXep(Du_Tinh);
            int So_Dem = 0;

            if (Du_Tinh.size() > 0)
            {
                for (int dong = Slides_W_Move.length - 1; dong >= 0; dong--) {
                    if (So_Dem < Du_Tinh.size())
                    {
                        Slides_W_Move[dong][cot].setText(String.valueOf(Du_Tinh.get(So_Dem)));
                        So_Dem++;
                    }

                }

            }

        }

    }

    // Mode Move = 2
    public void Check_Move_Nam_Bac()
    {
        for (int cot = 0; cot < Slides_W_Move.length; cot++)
        {
            List<Long> Du_Tinh = new ArrayList<Long>();
            for (int dong = 0; dong < Slides_W_Move.length; dong ++)
            {
                if (Slides_W_Move[dong][cot].getText().toString().isEmpty() == false)
                {
                    Du_Tinh.add((long)Integer.parseInt(Slides_W_Move[dong][cot].getText().toString()));
                    Slides_W_Move[dong][cot].setText("");
                }
            }

            Du_Tinh = XapXep(Du_Tinh);
            int So_Dem = 0;

            if (Du_Tinh.size() > 0)
            {
                for (int dong = 0; dong < Slides_W_Move.length; dong++) {
                    if (So_Dem < Du_Tinh.size()) {
                        Slides_W_Move[dong][cot].setText(String.valueOf(Du_Tinh.get(So_Dem)));
                        So_Dem++;
                    }
                }
            }
        }
    }

    // Mode 3
    public void Check_Move_Dong_Tay()
    {
        for (int dong = 0; dong < Slides_W_Move.length; dong++)
        {
            List<Long> Du_Tinh = new ArrayList<Long>();
            for (int cot = 0; cot < Slides_W_Move.length; cot ++)
            {
                if (Slides_W_Move[dong][cot].getText().toString().isEmpty() == false)
                {
                    Du_Tinh.add((long)Integer.parseInt(Slides_W_Move[dong][cot].getText().toString()));
                    Slides_W_Move[dong][cot].setText("");
                }
            }

            Du_Tinh = XapXep(Du_Tinh);
            int So_Dem = 0;

            if (Du_Tinh.size() > 0)
            {
                for (int cot = 0; cot < Slides_W_Move.length; cot++) {
                    if (So_Dem < Du_Tinh.size()) {
                        Slides_W_Move[dong][cot].setText(String.valueOf(Du_Tinh.get(So_Dem)));
                        So_Dem++;
                    }
                }
            }

        }
    }

    // Mode 4
    public void Check_Move_Tay_Dong()
    {
        for (int dong = 0; dong < Slides_W_Move.length; dong++)
        {
            List<Long> Du_Tinh = new ArrayList<Long>();
            for (int cot = Slides_W_Move.length - 1; cot >= 0; cot --)
            {
                if (Slides_W_Move[dong][cot].getText().toString().isEmpty() == false)
                {
                    Du_Tinh.add((long)Integer.parseInt(Slides_W_Move[dong][cot].getText().toString()));
                    Slides_W_Move[dong][cot].setText("");
                }
            }

            Du_Tinh = XapXep(Du_Tinh);
            int So_Dem = 0;

            if (Du_Tinh.size() > 0)
            {
                for (int cot = Slides_W_Move.length - 1; cot >= 0; cot --)
                {
                    if (So_Dem < Du_Tinh.size()) {
                        Slides_W_Move[dong][cot].setText(String.valueOf(Du_Tinh.get(So_Dem)));
                        So_Dem++;
                    }
                }
            }

        }
    }





    public boolean Check_Over()
    {

        boolean Have_Win  = true;

        if (Check_Emty())
        {

            Log.d("Action_Check_Over", "OK");

            for (int dong = 0; dong < Slides_W_Move.length; dong ++)
            {
                for (int cot = 0; cot < Slides_W_Move.length; cot++)
                {

                    if (dong + 1 < Slides_W_Move.length) if (Slides_W_Move[dong][cot].getText().toString().equals(Slides_W_Move[dong + 1][cot].getText().toString())) Have_Win = false;

                    if (cot  + 1 < Slides_W_Move.length) if (Slides_W_Move[dong][cot].getText().toString().equals(Slides_W_Move[dong][cot + 1].getText().toString())) Have_Win = false;

                    if (dong - 1 >= 0) if (Slides_W_Move[dong][cot].getText().toString().equals(Slides_W_Move[dong - 1][cot].getText().toString())) Have_Win = false;

                    if (cot  - 1 >= 0) if (Slides_W_Move[dong][cot].getText().toString().equals(Slides_W_Move[dong][cot - 1].getText().toString())) Have_Win = false;

                }

            }

        }else
        {

            Log.d("Action_Check_Over", "Not OK");

            Have_Win = false;

        }

        return Have_Win;

    }

    public boolean Check_Win()
    {
        boolean Win = false;

        for (int dong = 0; dong < Slides_W_Move.length; dong ++)
        {

            for (int cot = 0; cot < Slides_W_Move.length; cot ++)
            {

                if (Slides_W_Move[dong][cot].getText().toString().equals("2048"))
                {

                    Win = true;

                }

            }

        }

        return Win;
    }

    public boolean Check_Emty()
    {
        boolean Result = true;

        for (int dong = 0; dong < Slides_W_Move.length; dong ++)
        {
            for (int cot = 0; cot < Slides_W_Move.length; cot++)
            {
                if (Slides_W_Move[dong][cot].getText().toString().isEmpty())
                {
                    Result = false;
                }
            }
        }
        return Result;
    }

    public List<Long> XapXep(List<Long> Du_Tinh)
    {

        List<Long> Result_Du_Tinh = Du_Tinh;

        if (Result_Du_Tinh.size() > 0)
        {

            for (int i = 0; i < Result_Du_Tinh.size() - 1; i++)
            {
                if (String.valueOf(Result_Du_Tinh.get(i)).equals(String.valueOf(Result_Du_Tinh.get(i + 1))))
                {

                    long Total = Result_Du_Tinh.get(i) + Result_Du_Tinh.get(i + 1);

                    Result_Du_Tinh.set(i, Total);
                    Result_Du_Tinh.remove(i + 1);

                    PlayBoardActivity.Update_Score_Playing(Total);

                }

            }

        }

        return Result_Du_Tinh;
    }


    public Button[][] getSlides_W_Move()
    {
        return Slides_W_Move;
    }
}
