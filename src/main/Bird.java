package main;

import utils.GameUtil;
import static utils.Constant.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird {
    // arrsy to put img files
    private BufferedImage[] images;
    public static final int BIRDCOUNT = 3;

    // bird state
    public int state=0;
    public static final int START_MID = 0;
    public static final int START_UP = 1;
    public static final int START_DOWN = 2;

    // postion
    private int pos_x = 200;
    private int pos_y = 200;
    // speed
    private int upDis = 30;
    private int downDis = 1;
    private float acc = 0;

    // direction
    private boolean up=false, down=false;

    // rectangle
    private Rectangle rect;

    // live
    private boolean isAlive = true;



    // construct
    public Bird(){
        images = new BufferedImage[BIRDCOUNT];
        for (int i = 0; i < BIRDCOUNT; i++) {
            images[i] =GameUtil.loadBufferedImg(BIRDIMAGES[i]);
        }
        int w = images[0].getWidth();
        int h = images[0].getHeight();
        rect = new Rectangle(w, h);
    }

    // draw
    public void draw(Graphics gh){
        // 就是输出状态 和位置，状态在监听按键的时候就会传过来，这里会计算位置，位置可以整合一下
        flypos();// 按键检测 控制位置，外层会感应状态
        gh.drawImage(images[state], pos_x, pos_y, null);

//        gh.setColor(Color.black);
//        gh.drawRect(pos_x, pos_y, (int)rect.getWidth(), rect.height);
        rect.setLocation(pos_x, pos_y);

    }

    //
    public void flypos(){
        if (up){
            pos_y -= upDis;

            acc = 0;

            if(pos_y < 30){
                pos_y = 30;
            }
        }else{
            pos_y += downDis;
            pos_y += acc;
            acc += 0.1;
            if (acc>=10){
                acc = 10;
            }

            if(pos_y >= 470){
                pos_y = 470;
            }
        }
        up = false;
        state = 2;

    }

    public void fly(int fly){
        if (fly==1){
            state = 1;
            up = true;
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean getisAlive() {
        return isAlive;
    }

    public void setisAlive(boolean alive) {
        isAlive = alive;
    }

    public void reLive(){
        isAlive = true;
        pos_x = pos_y = 200;
    }

}
