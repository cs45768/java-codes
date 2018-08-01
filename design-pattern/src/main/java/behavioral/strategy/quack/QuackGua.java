package behavioral.strategy.quack;

/**
 * Created by zhangchunhui on 2018/7/28.
 */
public class QuackGua implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
