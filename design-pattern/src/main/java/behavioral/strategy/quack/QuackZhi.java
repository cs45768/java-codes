package behavioral.strategy.quack;

/**
 * Created by zhangchunhui on 2018/7/28.
 */
public class QuackZhi implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}
