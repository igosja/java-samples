package simplePaint;

import javax.swing.*;
import java.awt.*;

public class SimplePaint extends JFrame {
    static final String CLEAR_COMMAND = "Clear";

    SimplePaint(String title) {
        super(title);

        Dimension size = new Dimension(500, 500);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setSize(size);
        add(scrollPane);

        SimplePaintComponent simplePaintComponent = new SimplePaintComponent(size);
        scrollPane.add(simplePaintComponent);

        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);

        JMenuItem jMenuItem = new JMenuItem(CLEAR_COMMAND);
        jMenuItem.addActionListener(simplePaintComponent);
        jMenuBar.add(jMenuItem);

        setSize(size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SimplePaint("Simple Paint");
    }
}

