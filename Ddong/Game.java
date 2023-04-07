package Ddong;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
    private int delay = 20;
    private long pretime;
    private int cnt;
    private int score;

    private Image player = new ImageIcon("src/images/bate.png").getImage();

    private int playerX, playerY;
    private int playerWidth = player.getWidth(null);
    private int playerHeight = player.getHeight(null);
    private int playerSpeed = 8;

    private boolean up, down, left, right;
    private boolean isOver;

    private ArrayList<Ddong> enemyList=new ArrayList<Ddong>();

    private Ddong enemy;
    @Override
    public void run() {

        reset();
        while (true) {
            while(!isOver) {
                pretime = System.currentTimeMillis();
                if (System.currentTimeMillis() - pretime < delay) {
                    try {
                        Thread.sleep(delay - System.currentTimeMillis() + pretime);
                        enemyAppearProcess();
                        enemyMoveProcess();
                        KeyProcess();
                        cnt++;
                        score++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void reset() {	//다시하기 기능
        isOver=false;
        cnt = 0;
        score=0;
        playerX = (Main.SCREEN_WIDTH - playerWidth) / 2;
        playerY = (Main.SCREEN_HEIGHT - playerHeight) - 10;

        enemyList.clear();

    }
    private void KeyProcess() {
        if (up && playerY - playerSpeed > 0)
            playerY -= playerSpeed;
        if (down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT)
            playerY += playerSpeed;
        if (left && playerX - playerSpeed > 0)
            playerX -= playerSpeed;
        if (right && playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH)
            playerX += playerSpeed;
    }

    private void enemyAppearProcess() {
        if(cnt%7==0) {
            enemy=new Ddong((int)(Math.random()*441), 0);
            enemyList.add(enemy);


        }
    }

    private void enemyMoveProcess() {
        for(int i=0;i<enemyList.size();i++) {
            enemy=enemyList.get(i);
            enemy.move();

			/*
			if (enemy.x<playerX+playerWidth&&enemy.x+enemy.width>playerX&&
					enemy.y<playerY+playerHeight&&enemy.y+enemy.height>playerY) //플레이어가 똥에 맞을시
			{
	            enemyList.remove(enemy); //똥삭제
	        }
			*/
            if (enemy.x+15<playerX+playerWidth&&enemy.x+enemy.width>playerX+15
                    &&enemy.y<playerY+playerHeight-40&&enemy.y+enemy.height>playerY) //플레이어가 똥에 맞을시
            {
                enemyList.remove(enemy); //똥삭제
                isOver=true;
            }


        }
    }


    public void gameDraw(Graphics g) {
        playerDraw(g);
        enemyDraw(g);
        infoDraw(g);
    }

    public void infoDraw(Graphics g) {	//점수표와 게임이 끝났을때 안내표
        g.setColor(Color.RED);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("SCORE : "+score,250,60);
        if(isOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial",Font.BOLD,40));
            g.drawString("Press Enter to restart",40,200);
        }
    }

    public void playerDraw(Graphics g) {
        g.drawImage(player, playerX, playerY, null);
    }

    public void enemyDraw(Graphics g) {
        for(int i=0;i<enemyList.size();i++) {
            enemy=enemyList.get(i);
            g.drawImage(enemy.image,enemy.x,enemy.y,null);
        }
    }

    /*	public void setUp(boolean up) {
            this.up = up;
        }

        public void setDown(boolean down) {
            this.down = down;
        }
    */
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isOver() {
        // TODO Auto-generated method stub
        return isOver;
    }

}