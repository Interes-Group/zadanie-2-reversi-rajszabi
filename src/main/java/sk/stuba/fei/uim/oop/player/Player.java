package sk.stuba.fei.uim.oop.player;

import lombok.Getter;
import sk.stuba.fei.uim.oop.maze.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
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
                    moveValue = isDirectionValid(board, i, j, 0, 1, size, false);
                    if (moveValue != 0) {
                        playable = true;
                        moveValueSum += moveValue;
                    }

                    moveValue = isDirectionValid(board, i, j, 0, -1, size, false);
                    if (moveValue != 0) {
                        playable = true;
                        moveValueSum += moveValue;
                    }

                    moveValue = isDirectionValid(board, i, j, 1, 0, size, false);
                    if (moveValue != 0) {
                        playable = true;
                        moveValueSum += moveValue;
                    }

                    moveValue = isDirectionValid(board, i, j, -1, 0, size, false);
                    if (moveValue != 0) {
                        playable = true;
                        moveValueSum += moveValue;
                    }

                    if (playable) {
                        validMoves.put(board[i][j], moveValueSum);
                    }
                }
            }
        }
    }

    private int isDirectionValid(Node[][] board, int row, int col, int rowDir, int colDir, int size, boolean takeMove) {
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
            if (currentRow<0 || currentCol<0 || currentRow==8 || currentCol==8) {
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

    public boolean canPlayNode(Node node) {
        return this.validMoves.containsKey(node);
    }


    public void draw(Graphics g) {
        for (Node node : this.nodes) {
            g.setColor(this.color);
            g.fillOval(node.getX() + 4 , node.getY() + 4, Node.NODE_SIZE - 10, Node.NODE_SIZE - 10);
        }
        drawPossibleMoves(g);
    }

    // TODO: 16. 4. 2022 Nem jo a color, csak feketere irja ki
    private void drawPossibleMoves(Graphics g) {
        if (this.color == Color.BLACK) {
            for (Node node : validMoves.keySet()) {
                node.drawPossible(g);
            }
        }
    }

}
