package Ddong;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DdongGame extends JFrame{
    private Image bufferImage;
    private Graphics screenGraphic;

    private Image mainScreen = new ImageIcon("src/images/background.jpg").getImage();

    private Game game=new Game();

    public DdongGame(){
        setTitle("똥 피하기 게임"); //타이틀 이름
        //setUndecorated(true);
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);	//스크린 크기 설정
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        addKeyListener(new KeyListener());
        game.start();
    }
    public void paint(Graphics g) {
        bufferImage=createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        screenGraphic=bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage,0,0,null);
    }

    public void screenDraw(Graphics g) {
        g.drawImage(mainScreen,0,0,null);
        game.gameDraw(g);
        this.repaint();
    }

    class KeyListener extends KeyAdapter{

        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
			/*	case KeyEvent.VK_W:
					game.setUp(true);
					break;
				case KeyEvent.VK_S:
					game.setDown(true);
					break;
			*/
                case KeyEvent.VK_A:
                    game.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                    game.setRight(true);
                    break;
                case KeyEvent.VK_LEFT:
                    game.setLeft(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    game.setRight(true);
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                case KeyEvent.VK_ENTER:
                    if(game.isOver()) game.reset();
                    break;
            }
        }
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
			/*
			 * case KeyEvent.VK_W:
					game.setUp(false);
					break;
				case KeyEvent.VK_S:
					game.setDown(false);
					break;
			*/
                case KeyEvent.VK_A:
                    game.setLeft(false);
                    break;
                case KeyEvent.VK_D:
                    game.setRight(false);
                    break;
                case KeyEvent.VK_LEFT:
                    game.setLeft(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    game.setRight(false);
                    break;

            }
        }
    }
}