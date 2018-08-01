package behavioral.strategy.fly;

/**
 * Created by zhangchunhui on 2018/7/28.
 */
public class FlyWithWing implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly with wing");
    }
}
