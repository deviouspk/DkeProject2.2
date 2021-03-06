package Menu;

import javax.swing.*;
import java.awt.*;

public class SplashScreenMain {

    SplashScreen screen;
    int progress = 3000;

    public SplashScreenMain() {
        // initialize the splash screen
        splashScreenInit();
        // do something here to simulate the program doing something that
        // is time consuming
        for (int i = 0; i <= progress; i++) {
            for (long j = 0; j < 40000; ++j) {
                String load = "" + (j + i);
            }
            // run either of these two -- not both
//            screen.setProgress("",i);  // progress bar with a message
//      screen.setProgress(i);           // progress bar with no message
        }
//    To cheat and make the player wait a bit so he can see the buetifuel loading screen
        final int pause = 0;
        final Runnable closerRunner = new Runnable() {
            public void run() {
                splashScreenDestruct();
                new StartMenu();
            }
        };
        Runnable waitRunner = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(pause);
                    SwingUtilities.invokeAndWait(closerRunner);
                } catch (Exception e) {
                    e.printStackTrace();
                    // can catch InvocationTargetException
                    // can catch InterruptedException
                }
            }
        };
        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
//    splashScreenDestruct();
//        new StartMenu();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new SplashScreenMain();
    }

    private void splashScreenDestruct() {
        screen.setScreenVisible(false);
    }

    private void splashScreenInit() {
//	  Image image = new ImageIcon("logo.gif").getImage();
//	  image.getScaledInstance(600, 600, image.SCALE_DEFAULT);
        ImageIcon myImage = new ImageIcon("logo.gif");
//    	ImageIcon myImage = new ImageIcon("Loading.jpg");
//	  ImageIcon myImage = new ImageIcon();
//	  myImage.setImage(image);
        screen = new SplashScreen(myImage);
        screen.setPreferredSize(new Dimension(600, 600));
        screen.setLocationRelativeTo(null);
        // screen.setProgressMax(progress);
        screen.setScreenVisible(true);
    }

}