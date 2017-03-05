package com.example.nickpham.game2048.Handling;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nickpham.game2048.PlayBoardActivity;
import com.example.nickpham.game2048.R;

import java.util.Random;


/**
 * Created by nickpham on 07/12/2016.
 */

public class About_Board_Play {


    Context context;
    LinearLayout LC_Board_Play;

    Button[][] Slides_Play = new Button[4][4];

    int Game_Play_Avd = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public About_Board_Play(Context context, LinearLayout LC_Board_Play) {

        this.context = context;
        this.LC_Board_Play = LC_Board_Play;

        // Custom Theme
        this.Setup_GUI();

        // Add Board Play to Main Layout
        this.Add_Board_Play();

        // Action touch
        this.Setup_OnTouch_Action();

        // Random to show number
        this.Random_Show_Number(2);

        this.Change_Background_Slides();

        Set_On_Click();

    }


    public void Test()
    {

        for (int i = 0; i < 2; i ++) {


            int Row = 0, Colum = 0;
            do {

                Row = new Random().nextInt(Slides_Play.length);
                Colum = new Random().nextInt(Slides_Play.length);

            } while (Slides_Play[Row][Colum].getText().toString().isEmpty() == false);


            Slides_Play[Row][Colum].setText("128");
        }
    }


    Button GET_Example_Button;
    public void Setup_GUI()
    {
        this.GET_Example_Button = PlayBoardActivity.Get_Example_Button;
    }

    View.OnTouchListener Action_Slide_F_Player;
    public void Setup_OnTouch_Action()
    {

        Action_Slide_F_Player = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {

                float x1 = 0, x2 = 0;
                float y1 = 0, y2 = 0;

                switch (event.getAction())
                {

                    case MotionEvent.ACTION_DOWN:

                        x1 = event.getX();
                        y1 = event.getX();

                    case MotionEvent.ACTION_UP:

                        x2 = event.getX();
                        y2 = event.getY();

                        if (x1 > x2)
                        {

                            Toast.makeText(context, "Previous", Toast.LENGTH_SHORT).show();

                        }else {

                            Toast.makeText(context, "Next", Toast.LENGTH_SHORT).show();

                        }

                        break;

                    default:
                        break;

                }

                return onTouch(view, event);

            }
        };

    }

    public void Add_Board_Play()
    {

        LC_Board_Play.removeAllViews();

        int So_Dem = 1;

        for (int dong = 0; dong < 4; dong++) {

            LinearLayout Row_Layout = new LinearLayout(context);
            Row_Layout.setOrientation(LinearLayout.HORIZONTAL);

            for (int cot = 0; cot < 4; cot++) {

                Button Slide_Play_S = new Button(context);

                Slide_Play_S.setTag(So_Dem);
                Slide_Play_S.setText("");

                LinearLayout.LayoutParams Params_Slides_Play = new LinearLayout.LayoutParams(GET_Example_Button.getLayoutParams().width, GET_Example_Button.getLayoutParams().height);
                Params_Slides_Play.setMargins(5, 5, 5, 5);
                Slide_Play_S.setLayoutParams(Params_Slides_Play);


                Row_Layout.addView(Slide_Play_S);

                Slides_Play[dong][cot] = Slide_Play_S;

                Slide_Play_S = null;

                So_Dem++;

            }

            Row_Layout.setOnTouchListener(Action_Slide_F_Player);

            LC_Board_Play.addView(Row_Layout);

        }

        PlayBoardActivity.Return_Pos_View_Get_Event();

    }


    // Test Slides
    public void Set_On_Click()
    {

        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Text_Slide", ((Button) view).getText().toString());

            }
        };

        for (int dong = 0; dong < Slides_Play.length; dong ++)
        {
            for (int cot = 0; cot < Slides_Play.length; cot ++)
            {
                Slides_Play[dong][cot].setOnClickListener(listener);
            }
        }

    }

    public void Random_Show_Number(int Count) {

        if (new Check_Move_Slides(context, Slides_Play, -1).Check_Emty() == false)
        {
            int Numbers[] = {2, 4};

            for (int i = 0; i < Count; i++)
            {

                int Row = 0, Colum = 0;
                do{

                    Row   = new Random().nextInt(Slides_Play.length);
                    Colum = new Random().nextInt(Slides_Play.length);

                }while (Slides_Play[Row][Colum].getText().toString().isEmpty() == false);

                Slides_Play[Row][Colum].setText(String.valueOf(Numbers[new Random().nextInt(Numbers.length)]));

            }

        }

    }

    /**
     * #AD9D8F
     * #C2B4A5
     * */

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void Check_Move_Slides(int Mode_Play)
    {
        if (Game_Play_Avd == 1) {

            final Check_Move_Slides Handling_Move = new Check_Move_Slides(context, Slides_Play, Mode_Play);
            this.Slides_Play = Handling_Move.getSlides_W_Move();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run()
                {

                    Random_Show_Number(1);
                    Change_Background_Slides();

                    if (Handling_Move.Check_Over())
                    {
                        Game_Play_Avd = 0;
                        PlayBoardActivity.Action_GameOver(context, Slides_Play);

                    }

                }
            }, 200);

            Change_Background_Slides();

        }else
        {

            Toast.makeText(context, "Game over", Toast.LENGTH_SHORT).show();
            Log.d("About_AVD_Game", "Game Over");

        }


    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void Change_Background_Slides()
    {

        for (int dong = 0; dong < Slides_Play.length; dong ++)
        {

            for (int cot = 0; cot < Slides_Play.length; cot++)
            {

                String GET_Text_Slies = Slides_Play[dong][cot].getText().toString();

                GradientDrawable Get_From_Change_Slides = (GradientDrawable) context.getResources().getDrawable(R.drawable.custom_background_slides_play);

                //if (GET_Text_Slies.isEmpty() != true) Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_Two));

                if (GET_Text_Slies.equals("2"))   Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_Two));
                if (GET_Text_Slies.equals("4"))   Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_Four));
                if (GET_Text_Slies.equals("8"))   Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_Eight));
                if (GET_Text_Slies.equals("16"))  Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_SixTeen));
                if (GET_Text_Slies.equals("32"))  Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_FityThree));
                if (GET_Text_Slies.equals("64"))  Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_SixtyFour));
                if (GET_Text_Slies.equals("128")) Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_128));
                if (GET_Text_Slies.equals("256")) Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_256));

                if (GET_Text_Slies.equals("512")) Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_512));
                if (GET_Text_Slies.equals("1024")) Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_1024));
                if (GET_Text_Slies.equals("2048")) Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play_2048));

                if (GET_Text_Slies.isEmpty())   Get_From_Change_Slides.setColor(context.getResources().getColor(R.color.Background_Slides_Play));

                Slides_Play[dong][cot].setBackgroundDrawable(Get_From_Change_Slides);

            }

        }

    }





}