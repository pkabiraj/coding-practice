package org.psk.practice.snakeladder;

public class GameTest {

    public static void main(String[] args) {
        Board board = new Board();
        Dice dice = new Dice();
        Player pl1 = new Player("Pinaki");
        Player pl2 = new Player("Rimi");
        Game game = new Game(board, dice, pl1, pl2);
        game.play();
    }
}