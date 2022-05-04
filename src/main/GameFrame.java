package main;

import static utils.Constant.*;  // static so do not have to add Constant as prefix
import main.GameBackGround;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameFrame extends Frame{
    // background property
    private GameBackGround gameBackGround;
    // bird property
    private Bird bird;
    // picture to rid chambling
    private BufferedImage buffimg = new BufferedImage(
            FRAME_WEIGHT, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
    // barrier
    private BarrierLayer barrierLayer;




    // 构造方法中初始化一些参数
    public GameFrame(){

        // 窗口是否可见
        setVisible(true);

        // 窗口的大小
        setSize(FRAME_WEIGHT, FRAME_HEIGHT);

        // 窗口的标题
        setTitle("flappy bird");

        // 窗口的初始化位置
        setLocation(LOCATION_X, LOCATION_Y);

        // 窗口的大小不可改变
        setResizable(false);

        // 窗口的关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        initGameBack();
        new gameStart().start();

        // key listener
        addKeyListener(new KeyAdapter() {
            @Override
            // 表示有键盘按下，而不是下键
            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                super.keyReleased(e);
                minu(e);
            }

        });


    }

    public void initGameBack(){
        gameBackGround = new GameBackGround();
        bird = new Bird();
        barrierLayer = new BarrierLayer();
    }

    class gameStart extends Thread{
        @Override
        public void run(){
            while(true){  // 持续的调用这个进程，否则画面是静止的
                repaint();
                try {
                    Thread.sleep(30);  // diaoyong / 30ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void update(Graphics g) {
        // question :刷新和进程的关系
        if (bird.getisAlive()){
            Graphics graphics = buffimg.getGraphics();
            gameBackGround.draw(graphics);// 本来是直接画到g上，为了消除抖动，这样处理
            bird.draw(graphics);
            barrierLayer.draw(graphics, bird);
            g.drawImage(buffimg, 0, 0, null);
        }else{
            String over = "Game Over!!!";
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", 1, 30));
            g.drawString(over, 200, 200);
        }

    }

    // press
    public void add(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                bird.fly(1);
                break;
            case KeyEvent.VK_SPACE:
                if (! bird.getisAlive()){
                    restart();
                }
                break;

        }
    }

    // release
    public void minu(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_DOWN:
                bird.fly(5);
                break;
        }
    }

    public void restart(){
        barrierLayer.clearBarrier();
        bird.reLive();
    }
}
