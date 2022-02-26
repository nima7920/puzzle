package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class MyKeyListener implements KeyListener {


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            Controller.getInstance().move("right");
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            Controller.getInstance().move("left");

        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            Controller.getInstance().move("up");
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            Controller.getInstance().move("down");
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
