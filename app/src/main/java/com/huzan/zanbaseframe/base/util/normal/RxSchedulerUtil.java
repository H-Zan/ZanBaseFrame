package com.huzan.zanbaseframe.base.util.normal;


import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZAN on 2017/7/18.
 * 
 */
public final class RxSchedulerUtil {

    /**
     * 在RxJava的使用过程中我们会频繁的调用subscribeOn()和observeOn(),通过Transformer结合
     * Observable.compose()我们可以复用这些代码
     *
     * @return Transformer
     */
    public static <T> ObservableTransformer<T, T> normalSchedulersTransformer() {

        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    
    
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                                         .unsubscribeOn(AndroidSchedulers.mainThread())
                                         .observeOn(AndroidSchedulers.mainThread());
    }
    
    /**
     * 可自定义线程
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper(Scheduler scheduler) {
        return tObservable -> tObservable.subscribeOn(scheduler)
                                         .unsubscribeOn(AndroidSchedulers.mainThread())
                                         .observeOn(AndroidSchedulers.mainThread());
    }
    
    //取消订阅
    public static void unsubscribe(Disposable... subscriptions) {
        if (subscriptions != null && subscriptions.length > 0) {
            for (Disposable subscription : subscriptions) {
                if (subscription != null && !subscription.isDisposed()) {
                    subscription.dispose();
                }
            }
        }
    }
}
