package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.maze.Node;

import java.awt.*;
import java.util.Collections;
import java.util.Map;

public class CPU extends Player{

    public CPU(Color color) {
        super(color);
    }

    public void makeMove(Node[][] board, Node node) {
        Map<Node, Integer> validMoves = this.getValidMoves();
        Node n = Collections.max(validMoves.entrySet(), Map.Entry.comparingByValue()).getKey();
        int x = n.getX() / Node.NODE_SIZE;
        int y = n.getY() / Node.NODE_SIZE;
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
        this.addNode(n);
    }

    public void draw(Graphics g) {
        for (Node node : this.nodes) {
            g.setColor(this.color);
            g.fillOval(node.getX() + 4 , node.getY() + 4, Node.NODE_SIZE - 10, Node.NODE_SIZE - 10);
        }
    }
}
