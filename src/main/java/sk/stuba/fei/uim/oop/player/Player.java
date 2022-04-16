package sk.stuba.fei.uim.oop.player;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Player {
    @Getter
    protected Color color;
    @Getter
    protected List<Node> nodes;
    @Getter
    protected Map<Node, Integer> validMoves;

    public Player(Color color) {
        this.color = color;
        this.nodes = new ArrayList<>();
        this.validMoves = new HashMap<>();
    }

    public abstract void makeMove(Node[][] board, Node node, int size);

    public abstract void draw(Graphics g);

    public void searchMoves(Node[][] board, int size) {
        this.validMoves.clear();
        int moveValue;
        int moveValueSum;
        boolean playable;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                moveValueSum = 0;
                playable = false;
                if (board[i][j].getPlayer() == null) {
                    for (int k=-1; k<2; k++) {
                        for (int l=-1; l<2; l++) {
                            moveValue = isDirectionValid(board, i, j, k, l, size, false);
                            if (moveValue != 0) {
                                playable = true;
                                moveValueSum += moveValue;
                            }
                        }
                    }
                    if (playable) {
                        validMoves.put(board[i][j], moveValueSum);
                    }
                }
            }
        }
    }

    protected int isDirectionValid(Node[][] board, int row, int col, int rowDir, int colDir, int size, boolean takeMove) {
        int value = 0;
        boolean goodEndig = false;
        int currentRow = row + rowDir;
        int currentCol = col + colDir;

        if (currentRow == size || currentRow < 0 || currentCol == size || currentCol < 0) {
            return 0;
        }

        while (board[currentRow][currentCol].getPlayer() != null) {
            if (board[currentRow][currentCol].getPlayer() == this) {
                goodEndig = true;
                break;
            } else {
                if (takeMove) {
                    board[currentRow][currentCol].getPlayer().deleteNode(board[currentRow][currentCol]);
                    this.addNode(board[currentRow][currentCol]);
                } else {
                    value++;
                }
                currentRow=currentRow + rowDir;
                currentCol=currentCol + colDir;
            }
            if (currentRow<0 || currentCol<0 || currentRow==size || currentCol==size) {
                break;
            }
        }
        return goodEndig ? value : 0;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
        node.setPlayer(this);
    }

    public void deleteNode(Node node) {
        this.nodes.remove(node);
    }

    public void clearAllNodes() {
        this.getValidMoves().clear();
        this.getNodes().clear();
    }

    public boolean canPlayNode(Node node) {
        return this.validMoves.containsKey(node);
    }
}
