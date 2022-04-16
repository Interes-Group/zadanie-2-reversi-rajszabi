package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.maze.Board;
import sk.stuba.fei.uim.oop.maze.Node;
import sk.stuba.fei.uim.oop.player.CPU;
import sk.stuba.fei.uim.oop.player.Human;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
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
    private JLabel chipsLabel;
    @Getter
    private JLabel mapLabel;
    @Getter
    private JLabel queueLabel;
    @Getter
    private JSlider slider;
    @Getter
    private Render render;

    public GameLogic() {
        this.frame = new JFrame("Reversi");
        this.slider = new JSlider(6, 12, 8);
        this.queueLabel = new JLabel("ON TURN: BLACK");
        this.chipsLabel = new JLabel("HUMAN: 0 | CPU: 0");
        this.mapLabel = new JLabel("Map size: " + slider.getValue() + "x" + slider.getValue());

        this.players = new Player[2];
        this.players[0] = new Human(Color.BLACK);
        this.players[1] = new CPU(Color.WHITE);
        this.board = new Board(this.players,  slider.getValue());
        this.render = new Render(this.board, this.players);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
        this.updateChips();


    }

    private void repaint() {
        this.render.repaint();
    }

    private void restartGame() {
        this.board.setBoardSize(slider.getValue());
        this.board.generateBoard();
        this.repaint();
        this.updateChips();
        this.updateMapLabel();
    }

    private void updateMapLabel() {
        this.mapLabel.setText("Map size: " + this.slider.getValue() + "x" + this.slider.getValue());
    }

    private void updateChips() {
        this.chipsLabel.setText("PLAYER: " + players[0].getNodes().size() + "  |  CPU: " + players[1].getNodes().size());
    }

    private void updateOnTurn(Player player) {
        this.queueLabel.setText("ON TURN: " + ((player.getColor() == Color.BLACK) ? "BLACK" : "WHITE"));
    }

    private int convertPosition(int screenPosition) {
        return (screenPosition < 0) ? -1 : screenPosition / this.board.getBoardAlternatives().get(this.board.getBoardSize());
    }

    private boolean checkEnd() {
        return players[0].getValidMoves().size() == 0 && players[1].getValidMoves().size() == 0;
    }

    private void playMoves(Node current) {
        // Step1: Human makes move
        this.players[0].makeMove(this.board.getBoard(), current, this.board.getBoardSize());
        this.board.searchPlayerMoves();

        // Step2: Check whether game is over
        if (checkEnd()) {
            this.endMessage();
        }

        // Step3: Can CPU make a move if not pass
        if (players[1].getValidMoves().size() == 0) return;
        else {
            // Step4: CPU makes move
            this.players[1].makeMove(this.board.getBoard(), current, this.board.getBoardSize());
            this.board.searchPlayerMoves();
            this.updateOnTurn(players[0]);
            // Step5: Check whether game is over
            if (checkEnd()) {
                this.endMessage();
                return;
            }

            // Step6: CPU plays while human cannot take move or game is over
            while (players[0].getValidMoves().size() == 0) {
                this.players[1].makeMove(this.board.getBoard(), current, this.board.getBoardSize());
                this.board.searchPlayerMoves();
                this.updateOnTurn(players[0]);
                // Step7: Is game over?
                if (checkEnd()) {
                    this.endMessage();
                    return;
                }
            }
        }
    }

    private void endMessage() {
        this.queueLabel.setText("WINNER IS: " + ((players[0].getNodes().size() > players[1].getNodes().size()) ? "BLACK" : "WHITE"));
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
            playMoves(current);
            updateChips();
        }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Reset")) {
            this.restartGame();
        }
    }
}
