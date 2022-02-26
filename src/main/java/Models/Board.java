package Models;

import Util.ConfigReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Board {
    private int boardWidth, boardHeight;
    private ArrayList<PuzzlePiece> puzzlePieces;
    private int missingPiece;
    private Configuration boardConfiguration;

    private ConfigReader configReader;

    public Board(int boardWidth, int boardHeight) {

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        puzzlePieces = new ArrayList<>();

        configReader = ConfigReader.getInstance();
        configReader.loadInfo();
        boardConfiguration = configReader.getConfiguration().getClone();
        System.out.println(boardConfiguration.toString());
        Dimension size = new Dimension(boardWidth / 3, boardHeight / 3);
        for (int i = 0; i < 9; i++) {
            int a = boardConfiguration.getPieceNumber(i);

            if (a == 0)
                missingPiece = i;

            try {
                Image img = ImageIO.read(new File(configReader.getImagePath(a)));
                PuzzlePiece puzzlePiece = new PuzzlePiece(img, size, (i % 3) * size.width, (i / 3) * size.height, a);
                puzzlePieces.add(puzzlePiece.getClone());

            } catch (IOException e) {
                System.out.println("image not found");
            }
        }
    }

    private void updateSize() {
        Dimension size = new Dimension(boardWidth / 3, boardHeight / 3);
        for (int i = 0; i < puzzlePieces.size(); i++) {
            puzzlePieces.get(i).setLocation(new Location((i % 3) * size.width, (i / 3) * size.height));
            puzzlePieces.get(i).setPieceSize(size);
        }

    }


    public void render(Graphics2D g2d) {
        updateSize();
        for (int i = 0; i < puzzlePieces.size(); i++) {
            puzzlePieces.get(i).render(g2d);
        }

    }

    public void swapPieces(int i, int j) {
        PuzzlePiece copy = this.puzzlePieces.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).getImage());
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).getPieceNumber());
        puzzlePieces.get(j).setImage(copy.getImage());
        puzzlePieces.get(j).setPieceNumber(copy.getPieceNumber());
        boardConfiguration.swap(i, j);
    }

    public boolean gameFinished() {
        ArrayList<Integer> piecesOrder=boardConfiguration.getPieceNumbers();
        for (int i = 0; i <8 ; i++) {
            if (piecesOrder.get(i) != (i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean solvable() {
        ArrayList<Integer> piecesOrder = (ArrayList) boardConfiguration.getPieceNumbers().clone();
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }

        int parity = inversionCount % 2;

        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));

        parity ^= (distanceOfMissingPiece % 2);
        if (parity == 0) {
            return true;
        }
        return false;
    }

    public void setSize(int width, int height) {
        boardHeight = height;
        boardWidth = width;
    }

    public int getMissingPiece() {
        return missingPiece;
    }

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }
}
