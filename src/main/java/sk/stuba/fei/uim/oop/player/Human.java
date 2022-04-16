package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.maze.Node;

import java.awt.*;

public class Human extends Player{

    public Human(Color color) {
        super(color);
    }

    public void makeMove(Node[][] board, Node node, int size) {
        int x = node.getX() / node.getNodeSize();
        int y = node.getY() / node.getNodeSize();
        if (isDirectionValid(board, y, x, 0, 1, size, false) != 0) {
            isDirectionValid(board, y, x, 0, 1, size, true);
        }
        if (isDirectionValid(board, y, x, 0, -1, size, false) != 0) {
            isDirectionValid(board, y, x, 0, -1, size, true);
        }
        if (isDirectionValid(board, y, x, 1, 0, size, false) != 0) {
            isDirectionValid(board, y, x, 1, 0, size, true);
        }
        if (isDirectionValid(board, y, x, -1, 0, size, true) != 0) {
            isDirectionValid(board, y, x, -1, 0, size, true);
        }
        this.addNode(node);
    }

    public void draw(Graphics g) {
        for (Node node : this.nodes) {
            g.setColor(this.color);
            g.fillOval(node.getX() + 4 , node.getY() + 4, node.getNodeSize() - 10, node.getNodeSize() - 10);
        }
        drawPossibleMoves(g);
    }

    private void drawPossibleMoves(Graphics g) {
        for (Node node : this.validMoves.keySet()) {
            node.drawPossible(g);
        }
    }

}
