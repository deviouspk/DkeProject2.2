package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Popup extends JWindow {

    private BackgroundPanel background;
    private ImageIcon imageIcon;
    private JLabel imageLabel;
    private JPanel southPanel;
    private StartMenu menu;

    public Popup(StartMenu menu) {
        this.menu = menu;
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception {
//    	imageLabel = new JLabel();
//    	ImageIcon imageIcon = new ImageIcon("popup.png");
//        imageLabel.setIcon(imageIcon);


        java.awt.Image image = new ImageIcon("popup7.png").getImage();

        background = new BackgroundPanel(image, BackgroundPanel.SCALED, 0.0f, 0.0f);
        background.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15));
        this.getContentPane().setLayout(new BorderLayout());

        background.setLayout(new FlowLayout());
        JPanel tmp = new JPanel();
//		tmp.setPreferredSize(new Dimension((int)(this),400));
        tmp.setOpaque(false);
        tmp.setLayout(new GridLayout(7, 10, 0, 25));

        JLabel message = new JLabel();
        message.setText("PLAYER " + menu.game.winner + " WON THE GAME WITH " + menu.game.winnerTurns + " TURN(S)!");
//    	message.setFont(new Font("Calibri",Font.PLAIN, 40));
//    	message.setFont(new Font("Agent Orange",Font.PLAIN, 19));
        message.setFont(new Font("Calibri", Font.PLAIN, 28));
        message.setForeground(Color.BLACK);
        message.setHorizontalAlignment(SwingConstants.CENTER);

        JButton nextLevel = new JButton("NEXT LEVEL");
        nextLevel.setBackground(new Color(0, 0, 0, 200));
        nextLevel.setForeground(Color.WHITE);
        nextLevel.setFont(new Font("Calibri", Font.PLAIN, 30));
        nextLevel.setUI(new StyledButtonUI());
        nextLevel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton mainMenu = new JButton("MAIN MENU");
        mainMenu.setBackground(new Color(0, 0, 0, 200));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setFont(new Font("Calibri", Font.PLAIN, 30));
        mainMenu.setUI(new StyledButtonUI());
        mainMenu.setHorizontalAlignment(SwingConstants.CENTER);

        JButton exit = new JButton("EXIT");
        exit.setBackground(new Color(0, 0, 0, 200));
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Calibri", Font.PLAIN, 30));
        exit.setUI(new StyledButtonUI());
        exit.setHorizontalAlignment(SwingConstants.CENTER);


//
        tmp.add(message);
        tmp.add(Box.createRigidArea(new Dimension(0, HEIGHT)));
        tmp.add(nextLevel);
        tmp.add(mainMenu);
        tmp.add(exit);
        background.add(Box.createRigidArea(new Dimension(0, 900)));
        background.add(tmp);

//        this.getContentPane().add(imageLabel, BorderLayout.CENTER);
        this.getContentPane().add(background, BorderLayout.SOUTH);
        this.pack();

        class ButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == nextLevel) {
                    if (menu.game.slot != 4) {
                        int nextSlot = menu.game.slot + 1;
                        menu.createGame(nextSlot, "Slot" + nextSlot + ".txt");
                    } else {
                        menu.gamePanel.setVisible(false);
                        menu.createMainMenu();
                    }
                    setVisible(false);
                }
                if (e.getSource() == mainMenu) {
                    menu.gamePanel.setVisible(false);
                    menu.createMainMenu();
                    setVisible(false);
                }

                if (e.getSource() == exit)
                    System.exit(0);
            }
        }

        ButtonListener listener = new ButtonListener();
        nextLevel.addActionListener(listener);
        mainMenu.addActionListener(listener);
        exit.addActionListener(listener);
    }
//
//	public static void main(String[] args){
//		Popup pop = new Popup();
//		pop.setLocationRelativeTo(null);
//        pop.setVisible(true);
//	}
}