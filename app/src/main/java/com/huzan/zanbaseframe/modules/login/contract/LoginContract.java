package com.huzan.zanbaseframe.modules.login.contract;

import com.huzan.zanbaseframe.base.mvp.BasePresenter;
import com.huzan.zanbaseframe.base.mvp.BaseView;

/**
 * Created by ZAN on 2017/7/20.
 * 
 */

public interface LoginContract {
	interface View extends BaseView<Presenter>{
		void showLoading();
		void hideLoading();
		void showToast(String msg);
		void setLoginBtnEnable(boolean isEnable);
		void jump();
	}
	interface Presenter extends BasePresenter{
		void doLogin(String name, String pwd);
		void isLoginBtnEnable(boolean isEnable);
		void rememberPassword(String pwd);
	}
}
