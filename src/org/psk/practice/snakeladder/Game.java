package org.psk.practice.snakeladder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Game {

    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;
    private Player winner = null;

    public Game(Board board, Dice dice, Player... players) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>();
        Collections.addAll(this.players, players);
    }

    public void play() {
        startGame();

        while (notOver()) {
            Player currentPlayer = players.poll();
            int roll = dice.roll();

            System.out.println(currentPlayer.getName() + " has rolled " + roll);

            currentPlayer.move(roll);

            players.offer(currentPlayer);

            if (currentPlayer.wins()) {
                winner = currentPlayer;
            }
        }

        System.out.println(winner.getName() + " has won...");
    }

    private boolean notOver() {
        return winner == null;
    }

    private void startGame() {
        placePlayersAtFirstSqaure();
        winner = null;
    }

    private void placePlayersAtFirstSqaure() {
        for (Player player : players) {
            board.firstSquare().enter(player);
        }
    }
}