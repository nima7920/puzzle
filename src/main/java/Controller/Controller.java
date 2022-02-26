package Controller;

import Models.Board;
import View.MyPanel;

import javax.swing.*;
import java.awt.*;

public class Controller {

    private JFrame frame;
    private MyPanel panel;
    private Board board;

    private int missingPiece;
    private String gameState = "#";

    private static Controller controller;

    private Controller() {
        frame = new JFrame();
        panel = new MyPanel();
        int screenWidth, screenHeight;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        panel.setSize(maxSize, maxSize);
        board = new Board(panel.getSize().width, panel.getSize().height);
        missingPiece = board.getMissingPiece();
        panel.setBoard(board);
        panel.setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
        frame.setSize(panel.getSize());
        frame.setLocation(panel.getLocation());
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(new MyKeyListener());
        panel.repaint();

        if(board.solvable()==false){
            gameState="not solvable";
            panel.showUnsolvableMessage();
        }
    }

    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;

    }

    void move(String direction) {
        if (gameState.equals("finished") || gameState.equals("not solvable"))
            return;
        if (direction.equals("up")) {
            moveUp();
        } else if (direction.equals("down")) {
            moveDown();
        } else if (direction.equals("left")) {
            moveLeft();
        } else if (direction.equals("right")) {
            moveRight();
        }
        if (board.gameFinished()) {
            gameState = "finished";
            panel.showFinishedMessage();
        }
    }

    private void moveUp() {
        if (missingPiece <= 2) {
            return;
        }
        board.swapPieces(missingPiece, missingPiece - 3);
        board.setMissingPiece(missingPiece - 3);
        missingPiece = missingPiece - 3;
        panel.repaint();
    }

    private void moveDown() {
        if (missingPiece >= 6) {
            return;
        }
        board.swapPieces(missingPiece, missingPiece + 3);
        board.setMissingPiece(missingPiece + 3);
        missingPiece = missingPiece + 3;
        panel.repaint();

    }


    private void moveLeft() {
        if (missingPiece % 3 == 0) {
            return;
        }
        board.swapPieces(missingPiece, missingPiece - 1);
        board.setMissingPiece(missingPiece - 1);
        missingPiece = missingPiece - 1;
        panel.repaint();
    }

    private void moveRight() {
        if (missingPiece % 3 == 2) {
            return;
        }
        board.swapPieces(missingPiece, missingPiece + 1);
        board.setMissingPiece(missingPiece + 1);
        missingPiece = missingPiece + 1;
        panel.repaint();
    }
}
