package com.huzan.zanbaseframe.base.mvp;

/**
 * Created by ZAN on 2017/7/19.
 * 
 */

public interface BaseView<T extends IPresenter> extends IView<T>{
	
	void showError(String msg);
}
