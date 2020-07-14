package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class Crud extends JFrame {
    static final String ADD_NEW_COMMAND = "Add";
    static EditFrame editFrame;
    static Vector<Vector<Object>> userData = new Vector<>();
    static JScrollPane scrollPane;

    Crud(String title) {
        super(title);
        setLayout(new FlowLayout());

        scrollPane = new JScrollPane();
        add(scrollPane);

        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);

        JMenuItem addNew = new JMenuItem(ADD_NEW_COMMAND);
        jMenuBar.add(addNew);

        addNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        addNew.addActionListener(e -> editFrame = new EditFrame("New entity"));

        redraw();

        setSize(getPreferredSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    static void redraw() {
        Vector<String> userCols = new Vector<>();

        userCols.addElement("Name");
        userCols.addElement("Sex");
        userCols.addElement("Is Active");
        userCols.addElement("Role");

        JTable table = new JTable(userData, userCols);
        scrollPane.setViewportView(table);
    }

    public static void main(String[] args) {
        new Crud("Simple crud");
    }
}

class EditFrame extends JFrame implements ActionListener {
    private static final String ACTION_SAVE = "Save";
    private static final String ACTION_CANCEL = "Cancel";

    private JTextField fieldName;
    private JRadioButton sexMale;
    private ButtonGroup sexGroup;
    private JRadioButton sexFemale;
    private JCheckBox fieldActive;
    private JComboBox<String> fieldRole;

    EditFrame(String title) {
        super(title);

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        add(mainPanel, BorderLayout.CENTER);

        JLabel labelName = new JLabel("Name");
        mainPanel.add(labelName);

        fieldName = new JTextField(20);
        mainPanel.add(fieldName);

        JLabel labelSex = new JLabel("Sex");
        mainPanel.add(labelSex);

        sexGroup = new ButtonGroup();

        sexMale = new JRadioButton("Male");
        sexGroup.add(sexMale);
        mainPanel.add(sexMale);

        sexFemale = new JRadioButton("Female");
        sexGroup.add(sexFemale);
        mainPanel.add(sexFemale);

        JLabel labelActive = new JLabel("Is active");
        mainPanel.add(labelActive);

        fieldActive = new JCheckBox();
        mainPanel.add(fieldActive);

        JLabel labelRole = new JLabel("Role");
        mainPanel.add(labelRole);

        String[] roleData = {"Admin", "Moderator", "User"};
        fieldRole = new JComboBox<>(roleData);
        mainPanel.add(fieldRole);

        JPanel buttonPanel = new JPanel();

        JButton saveButton = new JButton(ACTION_SAVE);
        buttonPanel.add(saveButton);

        saveButton.addActionListener(this);

        JButton cancelButton = new JButton(ACTION_CANCEL);
        buttonPanel.add(cancelButton);

        cancelButton.addActionListener(e -> Crud.editFrame.dispose());

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(getPreferredSize());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (ACTION_SAVE.equals(e.getActionCommand())){
            Vector<Object> user = new Vector<>();
            user.addElement(this.fieldName.getText());
            user.addElement(this.sexGroup.getSelection().getActionCommand());
            user.addElement(this.fieldActive.isSelected());
            user.addElement(String.valueOf(this.fieldRole.getSelectedItem()));
            Crud.userData.add(user);
            Crud.redraw();
        }
        this.dispose();
    }
}