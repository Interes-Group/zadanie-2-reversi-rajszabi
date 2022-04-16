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
    @Getter
    private int nodeSize;

    public Node(int x, int y, int nodeSize) {
        this.x = x * nodeSize;
        this.y = y * nodeSize;
        this.nodeSize = nodeSize;
        this.parity = (x+y) % 2;
    }

    public void draw(Graphics g) {
        if (parity == 0) {
            g.setColor(new Color(0, 128, 0));
        } else {
            g.setColor(new Color(0,100,0));
        }
        g.fillRect(this.x, this.y, this.nodeSize, this.nodeSize);
    }

    public void drawPossible(Graphics g) {
        if (highlight) {
            g.setColor(Color.BLACK);
            this.highlight = false;
        } else {
            g.setColor(Color.GRAY);
        }
        g.fillOval(this.x + 4, this.y + 4, this.nodeSize - 10, this.nodeSize - 10);

        if (this.parity == 0) {
            g.setColor(new Color(0, 128, 0));
        } else {
            g.setColor(new Color(0, 100, 0));
        }
        g.fillOval(this.x + 8, this.y + 8, this.nodeSize - 18, this.nodeSize - 18);
    }
}
