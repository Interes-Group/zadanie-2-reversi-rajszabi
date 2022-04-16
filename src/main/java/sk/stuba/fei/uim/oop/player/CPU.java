package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.maze.Node;

import java.awt.*;
import java.util.Collections;
import java.util.Map;

public class CPU extends Player{

    public CPU(Color color) {
        super(color);
    }

    public void makeMove(Node[][] board, Node node, int size) {
        Map<Node, Integer> validMoves = this.getValidMoves();
        Node n = Collections.max(validMoves.entrySet(), Map.Entry.comparingByValue()).getKey();
        int x = n.getX() / node.getNodeSize();
        int y = n.getY() / node.getNodeSize();
        for (int i=-1; i<2; i++) {
            for (int j=-1; j<2; j++) {
                if (isDirectionValid(board, y, x, i, j, size, false) != 0) {
                    isDirectionValid(board, y, x, i, j, size, true);
                }
            }
        }
        this.addNode(n);
    }

    public void draw(Graphics g) {
        for (Node node : this.nodes) {
            g.setColor(this.color);
            g.fillOval(node.getX() + 4 , node.getY() + 4, node.getNodeSize() - 10, node.getNodeSize() - 10);
        }
    }
}
