package main;

import utils.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static utils.Constant.BARRIER_PATH;

public class BarrierLayer{
    private ArrayList<Barrier> barriers = new ArrayList<>();
    private Random random = new Random();
    private int height_up;
    private int height_down;
    private GameTime gameTime;
    private GameScore gameScore;

    // construct
    public BarrierLayer(){
        barriers = new ArrayList<>();
        gameTime = new GameTime();
        gameScore = new GameScore();
    }

    // draw
    public void draw(Graphics gh, Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier br = barriers.get(i);
//            System.out.println(br.getVisible());
            if (br.getVisible()){
                br.draw(gh);
            }else{
                BarrierPool.setPool(barriers.remove(i));
                i--;
            }
        }
        collapse(bird);
        checkCross(bird);
        logic();

        long gap = gameTime.diff();
        gh.setColor(Color.white);
        gh.setFont(new Font("微软雅黑", 1, 15));
        gh.drawString("insist: " + gap + "S", 10, 50);

        gh.setFont(new Font("微软雅黑", 1, 15));
        gh.drawString("score: " + gameScore.getScore() + "分", 100, 50);


    }

    public void logic(){
        if (barriers.size() == 0){
            ran();
            gameTime.begin();
            insert(0, 180+height_up);
            insert(1, -height_down);

        } else{
            Barrier last = barriers.get(barriers.size()-1);
            if (last.isInFrame()){
                ran();
                insert(0, 180+height_up);
                insert(1, -height_down);
            }
        }

    }

    // get from pool
    public void insert(int state, int height){
        Barrier top = BarrierPool.getPool();
        top.setBarrier_state(state);
        top.setBarrier_height(height);
        top.setBarrier_x(600);
        top.setVisible(true);
        top.setBeenCrossed(false);
        barriers.add(top);
    }



    public void ran(){
        height_up = random.nextInt(280) + 60;
        height_down = random.nextInt(280) + 60;

        if (height_up + height_down >= 340){
            ran();
        }
        if (height_up + height_down <= 240){
            ran();
        }
    }

    public boolean collapse(Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier br = barriers.get(i);
            if (br.getRect().intersects(bird.getRect())){
                System.out.println("collapse!!!");
                bird.setisAlive(false);
                return true;
            }
        }
        return false;
    }

    public void checkCross(Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier br = barriers.get(i);
            if(! br.getBeenCrossed()){
                //check logic:小鸟的右边界超过障碍物
                double birdBound = bird.getRect().getMaxX();
                double barrierBound = br.getRect().getMaxX();
                if (birdBound>barrierBound){
                    br.setBeenCrossed(true);
                    gameScore.addScore();
                }
            }

        }

    }

    public void clearBarrier(){
        barriers.clear();
        gameScore.clearScore();
    }

}
