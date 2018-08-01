## 行为型设计模式-策略模式（Strategy Pattern）
#### 把很简单的东西搞得那么复杂，一次性代码，设计模式优势的实例说明：（策略模式）
说明：模拟鸭子游戏的应用程序，要求：游戏中会出现各种颜色外形的鸭子，一边游泳戏水，一边呱呱叫。
* 第一种方法：（一次性代码）
直接编写出各种鸭子的类：MallardDuck//野鸭，RedheadDuck//红头鸭，  
各类有三个方法：  
quack()：叫的方法  
swim()：游水的方法  
display()：外形的方法  

* 第二种方法：运用继承的特性，将其中共同的部分提升出来，避免重复编程。  
即：设计一个鸭子的超类（Superclass）,并让各种鸭子继承这个超类。
```java
public abstract class Duck{
     public void quack(){  //呱呱叫
          System.out.println("呱呱叫");
      }
     public void swim(){   //游泳
          System.out.println("游泳");
      }   
     public  abstract void display(); /*因为外观不一样，让子类自己去决定了。*/
} 
```
对于它的子类只需简单的继承就可以了，并实现自己的display()方法。  
```java
//野鸭
 public class MallardDuck extends Duck{
     public void display(){
          System.out.println("野鸭的颜色...");
   }
 }
//红头鸭
 public class RedheadDuck extends Duck{
     public void display(){
          System.out.println("红头鸭的颜色...");
   }
}
```
不幸的是，现在客户又提出了新的需求，想让鸭子飞起来。这个对于我们OO程序员，在简单不过了，在超类中在加一
个方法就可以了。
```java
public abstract class Duck{
   public void quack(){  //呱呱叫
              System.out.println("呱呱叫");
      }
   public void swim(){   //游泳
            System.out.println(" 游泳");
    }   
   public abstract void display(); /*因为外观不一样，让子类自己去决定了。*/
   public void fly(){
        System.out.println("飞吧！鸭子");
  }
}
```
对于不能飞的鸭子，在子类中只需简单的覆盖。
```java
//残废鸭
 public class DisabledDuck extends Duck{
   public void display(){
          System.out.println("残废鸭的颜色...");
   }
   public void fly(){
    //覆盖，变成什么事都不做。
  }
}
```
其它会飞的鸭子不用覆盖。  
这样所有的继承这个超类的鸭子都会fly了。但是问题又出来了，客户又提出有的鸭子会飞，有的不能飞。  
>点评:对于上面的设计，你可能发现一些弊端，如果超类有新的特性，子类都必须变动，这是我们开发最不喜欢看到的，一个类变让另一个类也跟着变，这有点不符合OO设计了。这样很显然的耦合了一起。利用继承-->耦合度太高了.
* 第三种方法：用接口改进.  
我们把容易引起变化的部分提取出来并封装之，来应付以后的变法。虽然代码量加大了，但可用性提高了，耦合度也降低了。  
我们把Duck中的fly方法和quack提取出来。
```java
public interface Flyable{
   public void fly();
}
public interface Quackable{
   public void quack();
}
```  
最后Duck的设计成为：  
```java
public class Duck{
    public void swim(){   //游泳
            System.out.println(" 游泳");
    }   
    public  abstract void display(); /*因为外观不一样，让子类自 己去决定了。*/
}
```
而MallardDuck,RedheadDuck,DisabledDuck 就可以写成为：  
```java
//野鸭
 public class MallardDuck extends Duck implements Flyable,Quackable{
  public void display(){
          System.out.println("野鸭的颜色...");
   }
  public void fly(){
    //实现该方法
  }
  public void quack(){
    //实现该方法
  }
 }
//红头鸭
 public class RedheadDuck extends Duck implements Flyable,Quackable{
  public void display(){
          System.out.println("红头鸭的颜色...");
   }
  public void fly(){
    //实现该方法
  }
  public void quack(){
    //实现该方法
  }
}
//残废鸭 只实现Quackable（能叫不能飞）
 public class DisabledDuck extends Duck implements Quackable{
   public void display(){
          System.out.println("残废鸭的颜色...");
   }
   public void quack(){
    //实现该方法
  }
}
```
>点评:  
好处:这样已设计，我们的程序就降低了它们之间的耦合。  
不足:Flyable和 Quackable接口一开始似乎还挺不错的，解决了问题（只有会飞到鸭子才实现 Flyable），但是Java接口不具有实现代码，所以实现接口无法达到代码的复用。
* 第四种方法：
对上面各方式的总结:  
继承的好处:让共同部分,可以复用.避免重复编程.  
继承的不好:耦合性高.一旦超类添加一个新方法,子类都继承,拥有此方法,
    若子类相当部分不实现此方法,则要进行大批量修改.
    继承时,子类就不可继承其它类了.  
接口的好处:解决了继承耦合性高的问题.  
    且可让实现类,继承或实现其它类或接口.
接口的不好:不能真正实现代码的复用.可用以下的策略模式来解决.  
 
#### ------------------------- Strategy Pattern(策略模式) -------------------------  
我们有一个设计原则：  
找出应用中相同之处，且不容易发生变化的东西，把它们抽取到抽象类中，让子类去继承它们；  
找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起。 -->important.  
现在，为了要分开“变化和不变化的部分”，我们准备建立两组类（完全远离Duck类），一个是"fly"相关的，另一个
是“quack”相关的，每一组类将实现各自的动作。  
比方说，我们可能有一个类实现“呱呱叫”，另一个类实现“吱吱叫”，还有一个类实现“安静”。  
首先写两个接口。FlyBehavior(飞行行为)和QuackBehavior（叫的行为）.  
```java
public interface FlyBehavior{
     public void fly();    
 }
 public interface QuackBehavior{
     public void quack();
 }
```
 我们在定义一些针对FlyBehavior的具体实现。  
 ```java
 public class FlyWithWings implements FlyBehavior{
    public void  fly(){
     //实现了所有有翅膀的鸭子飞行行为。
   }
 }
public class FlyNoWay implements FlyBehavior{
    public void  fly(){
      //什么都不做，不会飞
    }
 }  
```
针对QuackBehavior的几种具体实现。  
```java
public class Quack implements QuackBehavior{
    public void quack(){
      //实现呱呱叫的鸭子
  }
}

public class Squeak implements QuackBehavior{
    public void quack(){
      //实现吱吱叫的鸭子
  }
}

public class MuteQuack implements QuackBehavior{
    public void quack(){
      //什么都不做，不会叫
  }
}
```

> 点评一:
这样的设计，可以让飞行和呱呱叫的动作被其他的对象复用，因为这些行为已经与鸭子类无关了。  
而我们增加一些新的行为，不会影响到既有的行为类，也不会影响“使用”到飞行行为的鸭子类。

最后我们看看Duck 如何设计。
```java
public abstract class Duck{       
      //--------->在抽象类中,声明各接口,定义各接口对应的方法.
    FlyBehavior flyBehavior;//接口
    QuackBehavior quackBehavior;//接口
    public Duck(){}
    public abstract void display();
    public void swim(){
        //实现游泳的行为
    }
     public void performFly(){
        flyBehavior.fly(); // -->由于是接口,会根据继承类实现的方式,而调用相应的方法.
     }
     public void performQuack(){
          quackBehavior.quack();
    }
 }
```   
看看MallardDuck如何实现。  
----->通过构造方法,生成'飞','叫'具体实现类的实例,从而指定'飞','叫'的具体属性   
```java
 public class MallardDuck extends Duck{
    public MallardDuck(){      
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }
      //因为MallardDuck 继承了Duck，所有具有flyBehavior 与quackBehavior 实例变量}
    public void display(){
     //实现
   }
 }
```
这样就满足了即可以飞，又可以叫，同时展现自己的颜色了。  
这样的设计我们可以看到是把flyBehavior ，quackBehavior 的实例化写在子类了。我们还可以动态的来决定。  
我们只需在Duck中加上两个方法。  

在构造方法中对属性进行赋值与用属性的setter的区别：  
构造方法中对属性进行赋值：固定，不可变；  
用属性的setter，可以在实例化对象后，动态的变化，比较灵活。  
```java
public class Duck{
    FlyBehavior flyBehavior;//接口
    QuackBehavior quackBehavior;//接口
    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior = flyBehavior;
    }
    public void setQuackBehavior(QuackBehavior quackBehavior  {
            this.quackBehavior= quackBehavior;
     }
 }
```
  