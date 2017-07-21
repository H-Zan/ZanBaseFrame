package com.huzan.zanbaseframe.network;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.huzan.zanbaseframe.app.App;
import com.huzan.zanbaseframe.app.C;
import com.huzan.zanbaseframe.base.util.normal.NetworkUtil;
import com.huzan.zanbaseframe.network.gson.ItemTypeAdapterFactory;
import com.huzan.zanbaseframe.base.util.normal.FileUtil;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZAN on 2017/7/19.
 */

public class RetrofitClient {
	
	private static IApi sApiService = null;
	private static Retrofit sRetrofit = null;
	private static OkHttpClient sOkHttpClient = null;
	//
	private static boolean sIsDebug = false;
	private static Context sAppContext = App.get().mAppContext;
	
	private RetrofitClient() {
		init();
	}
	
	private void init() {
		initOkHttp();
		initRetrofit();
		sApiService = sRetrofit.create(IApi.class);
	}
	
	public static RetrofitClient get() {
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder {
		private static final RetrofitClient INSTANCE = new RetrofitClient();
	}
	
	private static void initOkHttp() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		if (sIsDebug) {
			// https://drakeet.me/retrofit-2-0-okhttp-3-0-config
			HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
			builder.addInterceptor(loggingInterceptor);
		}
		// 缓存 http://www.jianshu.com/p/93153b34310e
		//		File cacheFile = new File(App.getFromDisk().cacheDir, "/NetCache");
		File cacheFile = FileUtil.isFileExist(C.File.CACHE_PATH_NET);
		Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
		
		Interceptor cacheInterceptor = chain -> {
			Request request = chain.request();
			if (!NetworkUtil.isNetworkConnected(sAppContext)) {
				request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
			}
			Response response = chain.proceed(request);
			if (NetworkUtil.isNetworkConnected(sAppContext)) {
				int maxAge = 0;
				// 有网络时 设置缓存超时时间0个小时
				response.newBuilder().header("Cache-Control", "public, max-age=" + maxAge).build();
			} else {
				// 无网络时，设置超时为4周
				int maxStale = 60 * 60 * 24 * 28;
				//				int maxStale = 0;
				response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale).build();
			}
			return response;
		};
		//错误拦截
		//		builder.addInterceptor(new ErrorInterceptor());
		//缓存
		builder.cache(cache).addInterceptor(cacheInterceptor);
		//设置超时
		builder.connectTimeout(3, TimeUnit.SECONDS);
		builder.readTimeout(3, TimeUnit.SECONDS);
		builder.writeTimeout(3, TimeUnit.SECONDS);
		//错误重连
		builder.retryOnConnectionFailure(true);
		sOkHttpClient = builder.build();
	}
	
	private static void initRetrofit() {
		sRetrofit = new Retrofit.Builder().baseUrl(sIsDebug ? IApi.HOST_TEST : IApi.HOST).client(sOkHttpClient).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory()).create())).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
	}
	
	public IApi getApiService() {
		return sApiService;
	}
}
