package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.maze.Board;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel {
    private Board board;
    private Player[] players;

    public Render(Board maze, Player[] players) {
        this.board = maze;
        this.players = players;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.board.draw(g);
        this.players[0].draw(g);
        this.players[1].draw(g);
    }
}
