package com.huzan.zanbaseframe.base.util.rx;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by ZAN on 2017/7/18.
 * 
 * RxBus
 * 静态内部类
 */

public class RxBus {
	
	private final Subject<Object> bus;
	
	private RxBus() {
		//rx2的改变
		bus = PublishSubject.create().toSerialized();
	}
	
	public static RxBus get() {
		return RxBusHolder.sInstance;
	}
	
	private static class RxBusHolder {
		private static final RxBus sInstance = new RxBus();
	}
	
	// 发送
	public void post(final Object event) {
		bus.onNext(event);                                       //48             
	}
	
	//接收
	// 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
	public <T> Observable<T> toObserverable(Class<T> eventType) {
		return bus.ofType(eventType);
	}
	
	public boolean hasObservers() {
		return bus.hasObservers();
	}
	
}