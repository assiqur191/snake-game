package Snakegame;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;



import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener  {


    static final int SCREEN_WIDTH =600;
    static final int SCREEN_HEIGHT =600;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY= 75;
    final int x[]= new int[GAME_UNITS];
    final int y[]= new int[GAME_UNITS];
    int bodyparts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction= 'R';
    boolean running =false;
    Timer timer;
    Random random;

    
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.green);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdepter());
        startGame();



    }
    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();



    }
    public void paintComponent(Graphics g){
         super.paintComponent(g);
         draw(g);


    }
    public void draw(Graphics g){

            if(running){
                       for (int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++);{
                      g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                      g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
                       }
                     g.setColor(color.red);
                     g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE );
            
                     for(int i = 0; i< bodyparts; i++ ){
                          if (i == 0){
                         g.setColor(Color.yellow);
                           g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                     }
                      else {
                    g.setcolor(new color(45,180.0));
                           g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
         }

        }
        else {
            gameOvert(g);
        }
        g.setColor(Color.red);
        g.setFont(new Font("int Free",Font.BOLD,40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("SCORE:"+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("SCORE:"+applesEaten))/2 , SCREEN_HEIGHT/2,g.getFont().getSize());


    }
    public void newApple(){
        appleX =random.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE
        appleY =random.nextInt((int) (SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE


    }
    public void move(){
        for (int i= bodyparts;i>0;i--){
            x[i]= x[i-1];
            y[i]= y[i-1];
        }
        switch(direction){
            case'U'
            y[0] =y[0] - UNIT_SIZE;
            break;
            case'D'
            y[0] =y[0] + UNIT_SIZE;
            break;
            case'L'
            y[0] =y[0] - UNIT_SIZE;
            break;
            case'R'
            y[0] =y[0] + UNIT_SIZE;
            break;
        }


    }
    public void checkApple(){
        if ((x[0] == appleX)&& (y[0]== appleY)){
            bodyparts ++;
            applesEaten++;
        }


    }
    public void cheakCollisions(){
        //this cheak if head collides with body
        for (int i= bodyparts;i>0;i--){
            if ((x[0]== x[i])&&(y[0] == y[i])){
                running= false;
            }
        }
        // this will chack if the head left the border
        if(x[0]<0){
            running= false;

        }
        if(x[0]<SCREEN_WIDTH){
            running= false;
            
        }
        // this cheak if the head touch the top border
        if (y[0]<0){
            running=false;

        }

        if (y[0]< SCREEN_HEIGHT){
            running=false;
            
        }
        if (!running){
            timer.stop();
        }



    }
    public void gameOvert(Graphics g){
        // this will print gameover text
        g.setColor(Color.red);
        g.setFont(new Font("int Free",Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("GAME OVER",(SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2 , SCREEN_HEIGHT/2);


    }

    public viod actionperformed(ActionEvent e){
        if (running){
            move();
            checkApple();
            checkCollisions();

        }
        repaint();
        
    }
    public class MyKeyAdepter extends KeyAdepter{
        @Override
        public void keyPressed(KeyEvent e){
             switch(e.getKeyCode()){
                 case KeyEvent.VK_LEFT;
                 if (direction!='R'){
                     direction='L';

                 }
                 break;
                 case KeyEvent.VK_RIGHT;
                 if (direction!='L'){
                     direction='R';

                 }
                 break;
                 case KeyEvent.VK_UP;
                 if (direction!='D'){
                     direction='U';

                 }
                 break;
                 case KeyEvent.VK_DOWN;
                 if (direction!='U'){
                     direction='D';

                 }
                 break;

             }

        }
    }
}
