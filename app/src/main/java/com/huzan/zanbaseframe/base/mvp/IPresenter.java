package com.huzan.zanbaseframe.base.mvp;

/**
 * Created by ZAN on 2017/7/18.
 * 
 * presenter interface,所有Presenter必须实现此接口
 */
public interface IPresenter {

    void subscribe();

    void unSubscribe();
}
