package Models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PuzzlePiece {
    private Image img;
    Location location;
    private Dimension pieceSize;
    private int pieceNumber;

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

 // class constructors
    public PuzzlePiece(Image img, Dimension pieceSize,int x,int y, int pieceNumber) {
        this.img = img;
        this.pieceSize = pieceSize;
        this.location=new Location(x,y);
        this.pieceNumber = pieceNumber;
    }

    private PuzzlePiece(Image img, Location location, int pieceIdentifier) {
        this.img = img;
        this.location = location;
        this.pieceNumber = pieceIdentifier;
    }


    public Image getImage() {
        return img;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Dimension getPieceSize() {
        return pieceSize;
    }

    public void setPieceSize(Dimension pieceSize) {
        this.pieceSize = pieceSize;
    }

    public PuzzlePiece getClone() {
        PuzzlePiece clone = new PuzzlePiece(img, location, pieceNumber);

        return clone;
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(img,location.getX(),location.getY(),pieceSize.width,pieceSize.height,null);
    }
}
