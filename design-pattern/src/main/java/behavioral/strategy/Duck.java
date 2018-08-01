package behavioral.strategy;

import behavioral.strategy.fly.FlyBehavior;
import behavioral.strategy.quack.QuackBehavior;

/**
 * 鸭子抽象类
 * Created by zhangchunhui on 2018/7/28.
 */
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck(){}
    public abstract void swim();

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
