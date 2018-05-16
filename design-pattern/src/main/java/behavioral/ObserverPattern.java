package behavioral;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.attachment.AttachmentMarshaller;

/**
 * Created by 张春辉 on 2018年5月16日.
 * <p>
 * 观察者模式定义了一种一对多的依赖关系，让多个观察者对象
 * 同时监听某一个主题对象。这个主题对象在状态发生变化是，
 * 会通知所有观察者对象，使他们能够自动更新自己。
 * <p>大白话：
　*其实就是发布订阅模式，发布者发布信息，订阅者获取信息，订阅了就能收到信息，没订阅就收不到信息。
 */
public class ObserverPattern {
	/***
	 * 抽象被观察者接口
	 * 声明了添加、删除、通知观察者方法
	 */
	interface Subject{
		public void attach(Observer observer);
		public void deatch(Observer observer);
		public void notifyObserver();
	}
	
	/***
	 * 抽象观察者
	 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
	 */
	interface Observer{
		public void update(String message);
	}
	
	
	
	
	static class ConsumerSubject implements Subject{
		
		//注意到这个List集合的泛型参数为Observer接口，设计原则：面向接口编程而不是面向实现编程
	    private List<Observer> list;
	    private String message;
	    
	    public ConsumerSubject() {
	        list = new ArrayList<Observer>();
	    }
	    
		
		@Override
		public void attach(Observer observer) {
			list.add(observer);
		}

		@Override
		public void deatch(Observer observer) {
			if(observer != null)
			list.remove(observer);
		}

		@Override
		public void notifyObserver() {
			 for(int i = 0; i < list.size(); i++) {
	            Observer oserver = list.get(i);
	            oserver.update(message);
	        }
		}
		
		public void setInfomation(String s) {
	        this.message = s;
	        System.out.println("更新消息： " + s);
	        //消息更新，通知所有观察者
	        notifyObserver();
	    }
		
	}
	
	static class ConsumerObserver implements Observer{
		
		String name;
		String message;

		public ConsumerObserver(String name){
			this.name = name;
		}
		
		@Override
		public void update(String message) {
			this.message = message;
			 read();
	    }
	    
	    public void read() {
	        System.out.println(name + " 收到推送消息： " + message);
	    }
		
	}

	public static void main(String[] args) {
		ConsumerSubject subject = new ConsumerSubject();
		subject.attach(new ConsumerObserver("小红帽"));
		subject.attach(new ConsumerObserver("白雪公主"));
		subject.setInfomation("开始通知");
	}
	
}
