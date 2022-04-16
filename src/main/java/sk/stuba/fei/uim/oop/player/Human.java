package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.maze.Node;

import java.awt.*;

public class Human extends Player{

    public Human(Color color) {
        super(color);
    }

    public void makeMove(Node[][] board, Node node) {
        int x = node.getX() / Node.NODE_SIZE;
        int y = node.getY() / Node.NODE_SIZE;
        if (isDirectionValid(board, y, x, 0, 1, 8, false) != 0) {
            isDirectionValid(board, y, x, 0, 1, 8, true);
        }
        if (isDirectionValid(board, y, x, 0, -1, 8, false) != 0) {
            isDirectionValid(board, y, x, 0, -1, 8, true);
        }
        if (isDirectionValid(board, y, x, 1, 0, 8, false) != 0) {
            isDirectionValid(board, y, x, 1, 0, 8, true);
        }
        if (isDirectionValid(board, y, x, -1, 0, 8, true) != 0) {
            isDirectionValid(board, y, x, -1, 0, 8, true);
        }
        this.addNode(node);
    }

    public void draw(Graphics g) {
        for (Node node : this.nodes) {
            g.setColor(this.color);
            g.fillOval(node.getX() + 4 , node.getY() + 4, Node.NODE_SIZE - 10, Node.NODE_SIZE - 10);
        }
        drawPossibleMoves(g);
    }

    private void drawPossibleMoves(Graphics g) {
        for (Node node : this.validMoves.keySet()) {
            node.drawPossible(g);
        }
    }

}
