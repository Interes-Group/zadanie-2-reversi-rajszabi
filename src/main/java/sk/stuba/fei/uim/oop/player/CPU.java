package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.maze.Node;

import java.awt.*;

public class CPU extends Player{

    public CPU(Color color) {
        super(color);
    }

    public void draw(Graphics g) {
        for (Node node : this.nodes) {
            g.setColor(this.color);
            g.fillOval(node.getX() + 4 , node.getY() + 4, Node.NODE_SIZE - 10, Node.NODE_SIZE - 10);
        }
    }
}
