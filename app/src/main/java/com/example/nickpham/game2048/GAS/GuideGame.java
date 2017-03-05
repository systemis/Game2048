package com.example.nickpham.game2048.GAS;

/**
 * Created by nickpham on 13/12/2016.
 */

public class GuideGame
{

    String _GuideGame = "2048 is played on a gray 4×4 grid, with numbered tiles that slide smoothly when a player moves them using the four arrow keys. Every turn, a new tile will randomly appear in an empty spot on the board with a value of either 2 or 4. Tiles slide as far as possible in the chosen direction until they are stopped by either another tile or the edge of the grid. If two tiles of the same number collide while moving, they will merge into a tile with the total value of the two tiles that collided.  The resulting tile cannot merge with another tile again in the same move. Higher-scoring tiles emit a soft glow. n" +
            "\n" +
            "A scoreboard on the upper-right keeps track of the user's score. The user's score starts at zero, and is incremented whenever two tiles combine, by the value of the new tile. As with many arcade games, the user's best score is shown alongside the current score.\n" +
            "\n" +
            "\n" +
            "A game of 2048 in progress\n" +
            "The game is won when a tile with a value of 2048 appears on the board, hence the name of the game. After reaching the 2048 tile, players can continue to play (beyond the 2048 tile) to reach higher scores.  When the player has no legal moves (there are no empty spaces and no adjacent tiles with the same value), the game ends.\n" +
            "\n" +
            "The simple gameplay mechanics (just four directions) allowed it to be used in a promo video for the Myo gesture control armband,  the availability of the code underneath allowed it to be used as a teaching aid for programming,  and the second-place winner of a coding contest at Matlab Central Exchange was an AI system that would play 2048 on its own."
            ;


    public String Get_GuideGame()
    {

        return this._GuideGame;

    }

}

