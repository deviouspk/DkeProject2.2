package org.panels;

import org.Editor.Panels.EditorPanel;
import org.Editor.Panels.GridEditorPanel;
import org.Game;
import org.panels.menu.MainMenu;
import org.panels.menu.PauseMenu;
import org.panels.menu.StartMenu;

import javax.swing.*;

/**
 * Created by s120619 on 19-6-2017.
 */
public class MainFrame extends JFrame {

    private BackgroundPanel bgp;
    private MainMenu mainMenu;
    private StartMenu startMenu;
    private PauseMenu pauseMenu;
    private EditorPanel editorPanel;
    private GridPanel gridPanel;
    private Game game;

    /**
     * Start a mainframe, automatically loads the backgroundpanel ontop of it.
     */
    public MainFrame(Game game) {
        this.game = game;
        startBackgroundPanel();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * gets called automatically since the main frame needs a background.
     */
    public void startBackgroundPanel() {
        ImageIcon background = new ImageIcon("game.jpg");
        bgp = new BackgroundPanel(background.getImage(), BackgroundPanel.SCALED, 0.0f, 0.0f);
        add(bgp);
    }

    /**
     * Start and add a Main Menu.
     */
    public void startMainMenu() {
        mainMenu = new MainMenu(this);
        bgp.add(mainMenu);
    }

    /**
     * Start and add a Start Menu.
     */
    public void startStartMenu() {
        startMenu = new StartMenu(game);
        bgp.add(startMenu);
    }

    /**
     * Sets existing pauseMenu visible, or creates new pausemenu
     */
    public void startPauseMenu() {
        if (pauseMenu != null) {
            pauseMenu.setVisible(true);
        } else {
            pauseMenu = new PauseMenu(game);
            bgp.add(pauseMenu);
        }
    }

    /**
     * Starts a GridPanel with the Game.grid loading
     */
    public void startGame() {
        gridPanel = new GridPanel(game.getGrid());
        bgp.add(gridPanel);
    }

    /**
     * Start and add a Editor Panel
     */
    public void startEditorPanel() {
        editorPanel = editorPanel == null ? new EditorPanel() : editorPanel;
        editorPanel.setVisible(true);
        bgp.add(editorPanel);
    }

    public BackgroundPanel getBackgroundPanel() {
        return bgp;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public EditorPanel getEditorPanel() {
        return editorPanel;
    }

    public PauseMenu getPauseMenu(){
        return pauseMenu;
    }

    public Game getGame() {
        return game;
    }
}