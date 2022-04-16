package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.maze.Board;
import sk.stuba.fei.uim.oop.maze.Node;
import sk.stuba.fei.uim.oop.player.CPU;
import sk.stuba.fei.uim.oop.player.Human;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter{
    private Board board;
    private Player[] players;
    @Getter
    private JFrame frame;
    @Getter
    private JLabel chips1Label;
    @Getter
    private JLabel chips2Label;
    private JLabel mapSizeLabel;
    @Getter
    private JSlider slider;
    @Getter
    private Render render;

    public GameLogic() {
        this.frame = new JFrame("Reversi");
        this.slider = new JSlider(6, 12, 8);
        this.chips1Label = new JLabel("BLACK chips: 0");
        this.chips2Label = new JLabel("WHITE chips: 0");

        this.players = new Player[2];
        this.players[0] = new Human(Color.BLACK);
        this.players[1] = new CPU(Color.WHITE);
        this.board = new Board(this.players,  slider.getValue());
        this.render = new Render(this.board, this.players);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
    }

    private void repaint() {
        this.render.repaint();
    }

    private void restartGame() {
        this.board.setBoardSize(slider.getValue());
        this.board.generateBoard();
        this.repaint();
        this.updateChips();
    }

    private boolean checkEnd() {
        return players[0].getValidMoves().size() == 0 && players[1].getValidMoves().size() == 0;
    }

    private void playMoves(Node current) {
        // Step1: Human makes move
        this.players[0].makeMove(this.board.getBoard(), current);
        this.board.searchPlayerMoves();

        // Step2: Check whether game is over
        if (checkEnd()) {
            System.out.println("konec hry");
        }

        // Step3: Can CPU make a move if not pass
        if (players[1].getValidMoves().size() == 0) return;
        else {
            // Step4: CPU makes move
            this.players[1].makeMove(this.board.getBoard(), current);
            this.board.searchPlayerMoves();
            // Step5: Check whether game is over
            if (checkEnd()) {
                System.out.println("konec hry");
                return;
            }

            // Step6: CPU plays while human cannot take move or game is over
            while (players[0].getValidMoves().size() == 0) {
                this.players[1].makeMove(this.board.getBoard(), current);
                this.board.searchPlayerMoves();
                // Step7: Is game over?
                if (checkEnd()) {
                    System.out.println("konec hry");
                    return;
                }
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.restartGame();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Node current = this.board.getNode(convertPosition(e.getX()), convertPosition(e.getY()));
        if (current == null) {
            return;
        }
        if (this.players[0].canPlayNode(current)) {
//            this.players[0].makeMove(this.board.getBoard(), current);
//            this.board.searchPlayerMoves();
//
//            this.players[1].makeMove(this.board.getBoard(), current);
//            this.board.searchPlayerMoves();

            playMoves(current);
            updateChips();
        }

    }

    private void updateChips() {
        this.chips1Label.setText("BLACK chips: " + players[0].getNodes().size());
        this.chips2Label.setText("WHITE chips: " + players[1].getNodes().size());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Node current = this.board.getNode(convertPosition(e.getX()), convertPosition(e.getY()));
        if (current == null) {
            return;
        }
        if (this.players[0].canPlayNode(current)) {
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
            this.restartGame();
        } else {
//            this.color = e.getSource()
            System.out.println("comboBoxChanged");
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println(slider.getValue());
    }
}
