package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.maze.Board;
import sk.stuba.fei.uim.oop.maze.Node;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter{
    private Board board;

    private Player player;
    private Player[] players;
    @Getter
    private JLabel chips1Label;
    @Getter
    private JLabel chips2Label;
    @Getter
    private JComboBox colorList;
    @Getter
    private Render render;

    public GameLogic() {
        Player[] players = new Player[2];
        players[1] = new Player(Color.WHITE);
        this.player = new Player(Color.BLACK);
        players[0] = this.player;

        this.board = new Board(players);
        this.render = new Render(this.board, players);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);

        this.chips1Label = new JLabel("BLACK chips: 0");
        this.chips2Label = new JLabel("WHITE chips: 0");
        this.colorList = new JComboBox(new String[]{ "BLACK", "WHITE" });
        colorList.addActionListener(this);
    }

    private void repaint() {
        this.render.repaint();
    }

    private void setPlayers() {
        this.players = new Player[2];
        String color = String.valueOf(colorList.getSelectedItem());
        if (color.equals("BLACK")) {
            this.players[0] = new Player(Color.BLACK);
            this.players[1] = new Player(Color.WHITE);
        } else {
            this.players[0] = new Player(Color.WHITE);
            this.players[1] = new Player(Color.BLACK);
        }



    }

//    public void gameWon() {
//        this.board.
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Node current = this.board.getNode(convertPosition(e.getX()), convertPosition(e.getY()));
        if (current == null) {
            return;
        }
        if (this.player.canPlayNode(current)) {
            this.player.makeMove(this.board.getBoard(), current);
            this.board.searchPlayerMoves();

            this.board.cpuTakesMove();
            this.board.searchPlayerMoves();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Node current = this.board.getNode(convertPosition(e.getX()), convertPosition(e.getY()));
        if (current == null) {
            return;
        }
        if (this.player.canPlayNode(current)) {
            current.setHighlight(true);
        }
        this.repaint();
    }

    private int convertPosition(int screenPosition) {
        return (screenPosition < 0) ? -1 : screenPosition / Node.NODE_SIZE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Reset")) {
            System.out.println("Reset Button");
        } else {
//            this.color = e.getSource()
            System.out.println("comboBoxChanged");
        }
    }
}
