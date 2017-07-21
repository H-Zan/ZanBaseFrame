package com.huzan.zanbaseframe.app.module;

import android.content.Context;

import com.huzan.zanbaseframe.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author 张磊 (baronzhang[at]anjuke[dot]com)
 *         2016/11/30
 */
@Module
public class ApplicationModule {
	
	private Context context;
	
	public ApplicationModule(Context context) {
		this.context = context;
	}
	
	@Provides
	@Singleton
	App provideApplication() {
		return (App) context.getApplicationContext();
	}
	
	@Provides
	@Singleton
	Context provideContext() {
		return context;
	}
}
