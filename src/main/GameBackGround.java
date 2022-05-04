package main;

import utils.GameUtil;
import  static utils.Constant.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameBackGround {
    // pic used in background
    private BufferedImage bkimg;

    // construct fun
    public GameBackGround(){
        bkimg = GameUtil.loadBufferedImg(BACK_PATH);
    }

    // draw pic to window
    public void draw(Graphics gh){
        // 背景色
        gh.setColor(BACK_COLOR);
        gh.fillRect(0, 0, FRAME_WEIGHT, FRAME_HEIGHT);

        // 岸基
        int height = bkimg.getHeight();
        int weight = bkimg.getWidth();

        int count = FRAME_WEIGHT / weight + 1;

        for (int i = 0; i < count; i++) {// base图片左上角的 定位坐标
            gh.drawImage(bkimg, weight*i, FRAME_HEIGHT-height, null);
        }
    }
}
