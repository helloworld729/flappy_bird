package main;

import java.util.ArrayList;
import java.util.List;

public class BarrierPool {
    public static List<Barrier> pool = new ArrayList<>();

    public static final int initCount = 16;
    public static final int maxCount = 20;

    static {
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());
        }
    }

    public static Barrier getPool(){
        int size = pool.size();
        if (size>0){
            System.out.println("get");
            return pool.remove(pool.size()-1);
        }else{
            return new Barrier();
        }
    }

    public static void setPool(Barrier br){
        int size = pool.size();
        if (size<maxCount){
            pool.add(0, br);
            System.out.println("set");
        }
    }
}
