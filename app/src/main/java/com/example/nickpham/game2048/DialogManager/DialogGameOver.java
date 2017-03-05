package com.example.nickpham.game2048.DialogManager;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nickpham.game2048.Handling.About_Board_Play;
import com.example.nickpham.game2048.Handling.Check_Move_Slides;
import com.example.nickpham.game2048.PlayBoardActivity;
import com.example.nickpham.game2048.R;

/**
 * Created by nickpham on 12/12/2016.
 */

public class DialogGameOver implements View.OnClickListener {

    Context context;

    long PlayScore = 0, BestScore = 0;

    Dialog Dialog_Game_Over;

    public LinearLayout CP_ShowBestScore, CP_showPlayScore;

    public TextView  Show_Message_Dialog, Show_Best_Score, Show_Play_Score;
    public Button    bt_ReplayGame;
    public ImageView Show_ImageView_AResult;

    public Button[][] Slides_Play;

    public DialogGameOver(Context context, int PlayScore, long BestScore, Button[][] Slides_Play)
    {
        this.context   = context;
        this.PlayScore = PlayScore;
        this.BestScore = BestScore;

        this.Slides_Play = Slides_Play;

        Setup_Dialog();
        Dialog_Game_Over.show();

    }


    public void Setup_Dialog()
    {

        this.Dialog_Game_Over = new Dialog(context);
        this.Dialog_Game_Over.setContentView(R.layout.layout_dialog_game_over);
        this.Dialog_Game_Over.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.Dialog_Game_Over.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        this.Dialog_Game_Over.setCancelable(false);

        Custom_Views_Dialog();

        Handling_ResultGame();

        bt_ReplayGame.setOnClickListener(this);

    }


    public void Handling_ResultGame()
    {
        String Message_Dialog = "";


        switch ((int) PlayScore)
        {
            case 0:

                Message_Dialog = "";
                bt_ReplayGame.setText("New Game");
                CP_showPlayScore.removeAllViews();

                break;

            default:

                if (PlayScore >= BestScore)
                {

                    Message_Dialog = "New Best ";
                    Show_ImageView_AResult.setImageResource(R.mipmap.vicory_icon);

                }else
                {

                    Message_Dialog = "";
                    Show_ImageView_AResult.setImageResource(R.mipmap.flash_icon);

                }

                break;

        }


        if (PlayScore > 0)
        {

            if (new Check_Move_Slides(context, Slides_Play, -1).Check_Win())
            {
                Message_Dialog = "Win !!!";
            }

        }


        Show_Message_Dialog.setText(Message_Dialog);
        Show_Best_Score.setText(String.valueOf(BestScore));
        Show_Play_Score.setText(String.valueOf(PlayScore));

    }


    public void Custom_Views_Dialog()
    {

        this.CP_ShowBestScore = (LinearLayout) Dialog_Game_Over.findViewById(R.id.DGOV_ViewGroup_Show_BestScore);
        this.CP_showPlayScore = (LinearLayout) Dialog_Game_Over.findViewById(R.id.DGOV_ViewGroup_Show_PlayScore);

        this.Show_Message_Dialog    = (TextView)  Dialog_Game_Over.findViewById(R.id.DGOV_Show_Message_Dialog);
        this.Show_Best_Score        = (TextView)  Dialog_Game_Over.findViewById(R.id.DGOV_Show_BestScore);
        this.Show_Play_Score        = (TextView)  Dialog_Game_Over.findViewById(R.id.DGOV_Show_ScorePlay);
        this.bt_ReplayGame          = (Button)    Dialog_Game_Over.findViewById(R.id.DGOV_ReplayGame);
        this.Show_ImageView_AResult = (ImageView) Dialog_Game_Over.findViewById(R.id.DGOV_ShowImage);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view)
    {

        if (view.getId() == R.id.DGOV_ReplayGame)
        {

            ReplayGame();

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void ReplayGame()
    {

        PlayBoardActivity.Comprise_Play_Board.removeAllViews();

        PlayBoardActivity.about_board_play = new About_Board_Play(context, PlayBoardActivity.Comprise_Play_Board);

        PlayBoardActivity.Play_Score = 0;
        PlayBoardActivity.Show_Play_Score.setText("0");

        Dialog_Game_Over.dismiss();

    }

}
