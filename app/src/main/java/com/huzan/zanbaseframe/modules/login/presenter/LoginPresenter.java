package com.huzan.zanbaseframe.modules.login.presenter;

import com.huzan.zanbaseframe.base.data.BaseResponse;
import com.huzan.zanbaseframe.modules.login.contract.LoginContract;
import com.huzan.zanbaseframe.modules.mtest.model.entity.VersionBean;
import com.huzan.zanbaseframe.network.RetrofitClient;
import com.huzan.zanbaseframe.base.util.normal.CheckUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ZAN on 2017/7/20.
 * LoginPresenter
 */

public class LoginPresenter implements LoginContract.Presenter {
	
	private LoginContract.View mLoginView;
	private CompositeDisposable mCompositeDisposable;
	
	public LoginPresenter(LoginContract.View loginView) {
		mLoginView = loginView;
		//csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
		if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
			mCompositeDisposable = new CompositeDisposable();
		}
		mLoginView.setPresenter(this);
	}
	
	@Override
	public void subscribe() {
		
	}
	
	@Override
	public void unSubscribe() {
		dispose();
	}
	
	@Override
	public void doLogin(String phone, String pwd) {
		if (!CheckUtil.isMobileNumber(phone)) {
			mLoginView.showToast("请输入正确的手机号!");
			return;
		}
		mLoginView.showLoading();
		RetrofitClient.get().getApiService().mVersionAPI().subscribe(new Observer<BaseResponse<VersionBean>>() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {
				// dispose();
				mCompositeDisposable.add(d);
			}
			
			@Override
			public void onNext(@NonNull BaseResponse<VersionBean> versionBeanBaseResponse) {
				
			}
			
			@Override
			public void onError(@NonNull Throwable e) {
				mLoginView.hideLoading();
			}
			
			@Override
			public void onComplete() {
				mLoginView.hideLoading();
			}
		});
	}
	
	@Override
	public void isLoginBtnEnable(boolean isEnable) {
		mLoginView.setLoginBtnEnable(isEnable);
	}
	
	@Override
	public void rememberPassword(String pwd) {
		
	}
	
	protected void addDisposable(Disposable subscription) {
		if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) { //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
			mCompositeDisposable = new CompositeDisposable();
		}
		mCompositeDisposable.add(subscription);
	}
	
	//在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
	private void dispose() {
		if (mCompositeDisposable != null) {
			mCompositeDisposable.dispose();
		}
	}
}
