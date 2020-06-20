package simplePaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SimplePaintComponent extends JComponent implements ActionListener, MouseMotionListener, MouseListener {
    static Integer startX, startY, newX, newY;

    SimplePaintComponent(Dimension size) {
        setSize(size);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(SimplePaint.CLEAR_COMMAND)) {
            repaint();
        }
    }

    public void mouseDragged(MouseEvent e) {
        newX = e.getX();
        newY = e.getY();

        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(startX, startY, newX, newY);

        startX = newX;
        startY = newY;
    }

    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
