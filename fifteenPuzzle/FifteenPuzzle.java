package fifteenPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FifteenPuzzle extends JFrame implements MouseListener {
    private static Integer[][] items;
    private static JPanel mainPanel;

    FifteenPuzzle(String header) {
        super(header);

        Integer[] shuffledItems = new Integer[16];
        for (int i = 0; i <= 15; i++) {
            shuffledItems[i] = i;
        }

        List<Integer> itemsList = Arrays.asList(shuffledItems);
        Collections.shuffle(itemsList);
        itemsList.toArray(shuffledItems);

        items = new Integer[4][4];
        for (int i = 0; i < shuffledItems.length; i++) {
            items[(int) Math.floor(i / 4)][i - (int) Math.floor(i / 4) * 4] = shuffledItems[i];
        }

        mainPanel = new JPanel();
        add(mainPanel);
        setDefaultLookAndFeelDecorated(false);

        mainPanel.setLayout(new GridLayout(4, 4, 2, 2));

        redraw();

        setSize(300, 300);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FifteenPuzzle("15 Puzzle");
    }

    private void redraw() {
        mainPanel.removeAll();

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                String buttonText = String.valueOf(items[i][j]);
                if (0 == items[i][j]) {
                    buttonText = "";
                }
                JButton button = new JButton(buttonText);
                mainPanel.add(button);

                button.addMouseListener(this);
            }
        }

        mainPanel.revalidate();
    }

    public void mouseClicked(MouseEvent e) {
        JButton eventButton = (JButton) e.getSource();
        String eventButtonText = eventButton.getText();

        if (eventButtonText.isEmpty()) {
            return;
        }

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                String buttonText = String.valueOf(items[i][j]);
                if (eventButtonText.equals(buttonText)) {
                    int leftIndex = j - 1;
                    int rightIndex = j + 1;
                    int topIndex = i - 1;
                    int bottomIndex = i + 1;

                    if (leftIndex >= 0 && items[i][leftIndex].equals(0)) {
                        items[i][leftIndex] = items[i][j];
                        items[i][j] = 0;
                    } else if (rightIndex <= 3 && items[i][rightIndex].equals(0)) {
                        items[i][rightIndex] = items[i][j];
                        items[i][j] = 0;
                    } else if (topIndex >= 0 && items[topIndex][j].equals(0)) {
                        items[topIndex][j] = items[i][j];
                        items[i][j] = 0;
                    } else if (bottomIndex <= 3 && items[bottomIndex][j].equals(0)) {
                        items[bottomIndex][j] = items[i][j];
                        items[i][j] = 0;
                    }
                    redraw();
                    return;
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
