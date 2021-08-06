package application;
	
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,700,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.onCloseRequestProperty().setValue(e -> Platform.exit());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame();
        frame.getContentPane().add(new ImgPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setVisible(true);
        TimeUnit.SECONDS.sleep(5);
        frame.setVisible(false);
		launch(args);
		System.exit(0);
	}
	
}

class ImgPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	BufferedImage image;
    public ImgPanel() {
        setOpaque(false);
        setLayout(new FlowLayout());
        try {
            image = ImageIO.read(new File(Main.class.getResource("icon.png").getFile()));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    
	    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
}
