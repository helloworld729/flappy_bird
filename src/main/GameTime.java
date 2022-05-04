package main;

public class GameTime {
    private long beginTime;
    private long endTime;
    private long gap;

    public GameTime(){}

    public void begin(){
        beginTime = System.currentTimeMillis();
    }
    public long diff(){
        endTime = System.currentTimeMillis();
        return (endTime - beginTime) / 1000;
    }
}
