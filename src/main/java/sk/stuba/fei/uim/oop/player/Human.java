package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.board.Node;

import java.awt.*;

public class Human extends Player{

    public Human(Color color) {
        super(color);
    }

    public void makeMove(Node[][] board, Node node, int size) {
        int x = node.getX() / node.getNodeSize();
        int y = node.getY() / node.getNodeSize();
        for (int i=-1; i<2; i++) {
            for (int j=-1; j<2; j++) {
                if (isDirectionValid(board, y, x, i, j, size, false) != 0) {
                    isDirectionValid(board, y, x, i, j, size, true);
                }
            }
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
