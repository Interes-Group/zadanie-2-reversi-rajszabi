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
        this.addNode(n);
    }

    public void draw(Graphics g) {
        for (Node node : this.nodes) {
            g.setColor(this.color);
            g.fillOval(node.getX() + 4 , node.getY() + 4, node.getNodeSize() - 10, node.getNodeSize() - 10);
        }
    }
}
