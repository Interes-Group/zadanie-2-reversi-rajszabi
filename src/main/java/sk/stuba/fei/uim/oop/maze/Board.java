package sk.stuba.fei.uim.oop.maze;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.player.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {
    @Setter
    @Getter
    private int boardSize;
    @Getter
    private Map<Integer, Integer> boardAlternatives;
    @Getter
    private Node[][] board;
    @Setter
    private Player[] players;

    public Board(Player[] players, int boardSize) {
        this.players = players;
        this.boardSize = boardSize;
        this.generateBoardAlternatives();
        this.generateBoard();
    }

    public void generateBoard() {
        this.initializeBoard();
        this.initializePlayers();
    }

    public void generateBoardAlternatives() {
        this.boardAlternatives = new HashMap<>();
        this.boardAlternatives.put(6, 80);
        this.boardAlternatives.put(8, 60);
        this.boardAlternatives.put(10, 48);
        this.boardAlternatives.put(12, 40);
    }

    private void initializeBoard() {
        this.board = new Node[boardSize][boardSize];

        for (int i = 0; i< boardSize; i++){
            for (int j = 0; j< boardSize; j++) {
                this.board[i][j] = new Node(j, i, this.boardAlternatives.get(this.boardSize));
            }
        }
    }

    private void initializePlayers() {
        this.players[0].clearAllNodes();
        this.players[1].clearAllNodes();
        this.players[0].addNode(board[boardSize /2-1][boardSize /2-1]);
        this.players[0].addNode(board[boardSize /2][boardSize /2]);
        this.players[1].addNode(board[boardSize /2-1][boardSize /2]);
        this.players[1].addNode(board[boardSize /2][boardSize /2-1]);

        searchPlayerMoves();
    }

    public Node getNode(int x, int y) {
        if (x >= boardSize || x < 0 || y >= boardSize || y < 0) {
            return null;
        }
        return this.board[y][x];
    }

    public void searchPlayerMoves() {
        this.players[0].searchMoves(this.board, this.boardSize);
        this.players[1].searchMoves(this.board, this.boardSize);
    }

    public void draw(Graphics g) {
        for (int i = 0; i< boardSize; i++){
            for (int j = 0; j< boardSize; j++) {
                this.board[i][j].draw(g);
            }
        }
    }
}
