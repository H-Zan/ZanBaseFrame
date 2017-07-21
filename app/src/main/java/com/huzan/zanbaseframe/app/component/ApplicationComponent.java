package com.huzan.zanbaseframe.app.component;

import android.content.Context;

import com.huzan.zanbaseframe.app.App;
import com.huzan.zanbaseframe.app.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ZAN on 2017/7/20.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
	
	App getApplication();
	
	Context getContext();
}
