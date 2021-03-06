package org.editor.panels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Tony on 17/06/2017.
 */
public class LoadSavePanel extends JPanel {

    private JButton saveButton;
    private JButton loadButton;

    public LoadSavePanel() {
        this.initLoadButton();
        this.initSaveButton();
        setBorder(new LineBorder(Color.BLACK));
        this.add(getLoadButton());
        this.add(getSaveButton());
    }

    private void initLoadButton() {
        this.loadButton = new JButton("LOAD");
        this.loadButton.setBackground(Color.lightGray);
        this.loadButton.setForeground(Color.darkGray);
        this.loadButton.setBorderPainted(false);
        this.loadButton.setFont(new Font("Century Gothic", Font.BOLD, 30));
        this.loadButton.setFocusable(false);
    }

    private void initSaveButton() {
        this.saveButton = new JButton("SAVE");
        this.saveButton.setBackground(Color.lightGray);
        this.saveButton.setForeground(Color.darkGray);
        this.saveButton.setBorderPainted(false);
        this.saveButton.setFont(new Font("Century Gothic", Font.BOLD, 30));
        this.saveButton.setSize(new Dimension(20, 20));
        this.saveButton.setFocusable(false);
    }


    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

}
