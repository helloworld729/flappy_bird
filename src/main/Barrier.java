package main;

import utils.GameUtil;
import utils.ImageOperate;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.BARRIER_PATH;


public class Barrier {
    // arrsy to put img files
    private BufferedImage[] images;
    private int barrier_x = 600;
    private final int speed = 8;
    private int barrier_state;
    private int barrier_height;
    private boolean isVisible = true;
    private boolean beenCrossed = false;

    private Rectangle rect;


    // construct
    public Barrier(){
        images = new BufferedImage[2];  // 上面是声明，这里是初始化
        images[0] = GameUtil.loadBufferedImg(BARRIER_PATH);
        images[1] = ImageOperate.rotateimage(GameUtil.loadBufferedImg(BARRIER_PATH), 180);
//        HEIGHT = images[0].getHeight();  //320
//        WEIGHT = images[0].getWidth();   // 52
        int w = images[0].getWidth()+2;
        int h = images[1].getHeight();
        rect = new Rectangle(w, h);
    }

    // draw
    public void draw(Graphics gh){
        gh.drawImage(images[barrier_state], barrier_x, barrier_height, null);
        barrier_x -= speed;
        if (barrier_x < -60){
            isVisible = false;
        }

//        gh.setColor(Color.black);
//        gh.drawRect(barrier_x, barrier_height, rect.width, rect.height);
        rect.setLocation(barrier_x, barrier_height);
    }

    public boolean isInFrame(){
        if (barrier_x < 400){
            return true;
        }
        return false;
    }
    //  youjian generate
    public int getBarrier_state() {
        return barrier_state;
    }

    public int getBarrier_x() {
        return barrier_x;
    }

    public void setBarrier_x(int barrier_x) {
        this.barrier_x = barrier_x;
    }

    public void setBarrier_state(int barrier_state) {
        this.barrier_state = barrier_state;
    }

    public int getBarrier_height() {
        return barrier_height;
    }

    public void setBarrier_height(int barrier_height) {
        this.barrier_height = barrier_height;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean getBeenCrossed() {
        return beenCrossed;
    }

    public void setBeenCrossed(boolean beenCrossed) {
        this.beenCrossed = beenCrossed;
    }
}
