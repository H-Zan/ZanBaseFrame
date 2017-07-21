package com.huzan.zanbaseframe.app;

import android.app.Application;
import android.content.Context;

import com.huzan.zanbaseframe.BuildConfig;
import com.huzan.zanbaseframe.app.component.ApplicationComponent;
import com.huzan.zanbaseframe.app.component.DaggerApplicationComponent;
import com.huzan.zanbaseframe.app.module.ApplicationModule;
import com.huzan.zanbaseframe.base.util.normal.AppUtil;

/**
 * Created by ZAN on 2017/7/19.
 * Application
 */

public class App extends Application {
	
	private static App mApp = null;
	
	public boolean mIsDebug = BuildConfig.DEBUG;
	public Context mAppContext = null;
	public int versionCode;
	public String cacheDir;
	public String externalCacheDir;
	private ApplicationComponent applicationComponent;
	
	public static App get() {
		return mApp;
	}
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mAppContext = getApplicationContext();
		mApp = this;
		versionCode = AppUtil.getVersionCode(mAppContext);
		cacheDir = getCacheDir().toString();
		
		if (getExternalCacheDir() != null && AppUtil.isSDCardExist()) {
			externalCacheDir = getExternalCacheDir().toString();
		} else {
			externalCacheDir = cacheDir;
		}
		
		applicationComponent = DaggerApplicationComponent.builder()
		                                                 .applicationModule(new ApplicationModule(this))
		                                                 .build();
	}
	
	public ApplicationComponent getApplicationComponent() {
		
		return applicationComponent;
	}
	
}
