package sk.stuba.fei.uim.oop.maze;

import lombok.Getter;
import sk.stuba.fei.uim.oop.player.Player;

import java.awt.*;
import java.util.Collections;
import java.util.Map;

public class Board {
    public static final int MAZE_SIZE = 8;
    @Getter
    private Node[][] board;
    private Player[] players;

    public Board(Player[] players) {
        this.players = players;
        this.initializeBoard();
        this.initializePlayers();
    }

    public Node getNode(int x, int y) {
        if (x >= MAZE_SIZE || x < 0 || y >= MAZE_SIZE || y < 0) {
            return null;
        }
        return this.board[y][x];
    }

    private void initializeBoard() {
        this.board = new Node[MAZE_SIZE][MAZE_SIZE];

        for (int i=0; i<MAZE_SIZE; i++){
            for (int j=0; j<MAZE_SIZE; j++) {
                this.board[i][j] = new Node(j, i);
            }
        }
    }

    private void initializePlayers() {
        this.players[0].addNode(board[MAZE_SIZE/2-1][MAZE_SIZE/2-1]);
        this.players[0].addNode(board[MAZE_SIZE/2][MAZE_SIZE/2]);
        this.players[1].addNode(board[MAZE_SIZE/2-1][MAZE_SIZE/2]);
        this.players[1].addNode(board[MAZE_SIZE/2][MAZE_SIZE/2-1]);

        searchPlayerMoves();
    }

    public void searchPlayerMoves() {
        this.players[0].searchMoves(board, MAZE_SIZE);
        this.players[1].searchMoves(board, MAZE_SIZE);
    }

    public void cpuTakesMove() {
        Map<Node, Integer> validMoves = players[1].getValidMoves();
        Node node = Collections.max(validMoves.entrySet(), Map.Entry.comparingByValue()).getKey();
        players[1].makeMove(this.board, node);
    }

    public void draw(Graphics g) {
        for (int i=0; i<MAZE_SIZE; i++){
            for (int j=0; j<MAZE_SIZE; j++) {
                this.board[i][j].draw(g);
            }
        }
    }
}
