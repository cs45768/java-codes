package behavioral.strategy;

import behavioral.strategy.fly.FlyNoWay;
import behavioral.strategy.fly.FlyWithWing;
import behavioral.strategy.quack.QuackGua;
import behavioral.strategy.quack.QuackZhi;

/**
 * 野鸭
 * Created by zhangchunhui on 2018/7/28.
 */
public class WildDuck extends Duck{

    public WildDuck(){
        this.flyBehavior = new FlyWithWing();
        this.quackBehavior = new QuackGua();
    }
    @Override
    public void swim() {
        System.out.println("用脚丫游泳");
    }

    public static void main(String[] args) {
        WildDuck wildDuck = new WildDuck();
        wildDuck.performFly();
        wildDuck.performQuack();
        wildDuck.setQuackBehavior(new QuackZhi());
        wildDuck.performQuack();
    }
}
