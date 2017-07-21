package com.huzan.zanbaseframe.modules.mtest.presenter;

import com.huzan.zanbaseframe.base.data.BaseResponse;
import com.huzan.zanbaseframe.modules.mtest.contract.MainContract;
import com.huzan.zanbaseframe.modules.mtest.model.entity.VersionBean;
import com.huzan.zanbaseframe.network.RetrofitClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZAN on 2017/7/19.
 */

public class MainPresenterImpl implements MainContract.Presenter {
	
	private MainContract.View mView;
	
	@Override
	public void subscribe() {
		
	}
	
	@Override
	public void unSubscribe() {
		
	}
	
	@Override
	public void checkVersion(String currentVersion) {
		RetrofitClient.get()
		              .getApiService()
		              .mVersionAPI()
		              .subscribeOn(Schedulers.io())
		              .observeOn(AndroidSchedulers.mainThread())
		              .subscribe(new Observer<BaseResponse<VersionBean>>() {
			              @Override
			              public void onSubscribe(@NonNull Disposable d) {
				              mView.showProgressDialog();
			              }
			
			              @Override
			              public void onNext(@NonNull BaseResponse<VersionBean> versionBeanBaseResponse) {
				              if (Integer.valueOf(currentVersion.replace(".", "")) < Integer.valueOf(
					              versionBeanBaseResponse.getData().getCode().replace(".", ""))) {
					
					              // mView.showUpdateDialog(versionBean);
					              //这里表示发现新版本
					              mView.ShowToast("发现最新版本");
				              } else {
					              //表示这就是最新版本
					              mView.ShowToast("已经是最新版本");
				              }
			              }
			
			              @Override
			              public void onError(@NonNull Throwable e) {
				              mView.DissProgressDialog();
				              mView.ShowToast("请求出错");
			              }
			
			              @Override
			              public void onComplete() {
				              mView.DissProgressDialog();
				
			              }
		              });
	}
}
