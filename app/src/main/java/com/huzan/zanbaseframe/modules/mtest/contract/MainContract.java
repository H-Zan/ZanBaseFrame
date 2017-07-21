package com.huzan.zanbaseframe.modules.mtest.contract;

import com.huzan.zanbaseframe.base.mvp.BasePresenter;
import com.huzan.zanbaseframe.base.mvp.BaseView;
import com.huzan.zanbaseframe.modules.mtest.model.entity.VersionBean;

/**
 * Created by ZAN on 2017/7/19.
 */

public interface MainContract {
	
	interface View extends BaseView<Presenter> {
		//View效果就是展示下载进度框
		void showUpdateDialog(VersionBean bean);
		
		void showProgressDialog();
		
		void DissProgressDialog();
		
		void ShowToast(String message);
	}
	
	interface Presenter extends BasePresenter {
		//一般在首页我们会进行一个版本的更新（功能）
		void checkVersion(String currentVersion);
	}
	
}
