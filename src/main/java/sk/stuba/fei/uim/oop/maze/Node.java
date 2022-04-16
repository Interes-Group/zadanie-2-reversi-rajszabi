package sk.stuba.fei.uim.oop.maze;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.player.Player;

import java.awt.*;

public class Node {
    @Getter
    @Setter
    private Player player;
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    private final int parity;
    @Setter
    private boolean highlight;
    public static final int NODE_SIZE = 60;

    public Node(int x, int y) {
        this.x = x * NODE_SIZE;
        this.y = y * NODE_SIZE;
        this.parity = (x+y) % 2;
    }

    public void nodeSwitchPlayer(Player player) {
        if (this.player != null) {
            this.player.deleteNode(this);
        }
        this.player = player;
    }


    public void draw(Graphics g) {
        if (parity == 0) {
            g.setColor(new Color(0, 128, 0));
        } else {
            g.setColor(new Color(0,100,0));
        }
        g.fillRect(this.x, this.y, Node.NODE_SIZE, Node.NODE_SIZE);
    }

    public void drawPossible(Graphics g) {
        if (highlight) {
            g.setColor(Color.BLACK);
            this.highlight = false;
        } else {
            g.setColor(Color.GRAY);
        }
        g.fillOval(this.x + 4, this.y + 4, Node.NODE_SIZE - 10, Node.NODE_SIZE - 10);

        if (this.parity == 0) {
            g.setColor(new Color(0, 128, 0));
        } else {
            g.setColor(new Color(0, 100, 0));
        }
        g.fillOval(this.x + 8, this.y + 8, Node.NODE_SIZE - 18, Node.NODE_SIZE - 18);
    }
}
