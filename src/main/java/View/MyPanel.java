package View;

import Models.Board;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private Board board;

    public MyPanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = addRenderingHint(g);
        board.setSize(getSize().width, getSize().height);

        board.render(g2d);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private Graphics2D addRenderingHint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        return g2d;
    }

    public void showFinishedMessage() {
        JOptionPane.showMessageDialog(null, " You finished.Congratulations!", "Finished", JOptionPane.INFORMATION_MESSAGE);

    }

    public void showUnsolvableMessage() {
        JOptionPane.showMessageDialog(null, "This board is not solvable,please change your config file", "Not solvable", JOptionPane.OK_OPTION);

    }
}
