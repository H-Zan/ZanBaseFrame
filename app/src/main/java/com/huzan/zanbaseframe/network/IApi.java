package com.huzan.zanbaseframe.network;

import com.huzan.zanbaseframe.base.data.BaseResponse;
import com.huzan.zanbaseframe.modules.mtest.model.entity.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ZAN on 2017/7/19.
 * 
 */

public interface IApi {
	
	String HOST = "http://admin.admai.com/weather/";
	String HOST_TEST = "http://test-admin.admai.com/weather/";
	
	@GET("vinfo")
	Observable<BaseResponse<VersionBean>> mVersionAPI();
	
	/*
	@GET("http://192.168.80.56:8008/vinfo")
	Observable<VersionAPI> mVersionAPITest();
	
	@GET("moji/condition/{cityid}")
	Observable<MojiNowWeather> mMojiNowWeather(@Path("cityid") String cityid);
	
	@GET("moji/forecast24hours/{cityid}")
	Observable<MojiHours24> mMojiHours24(@Path("cityid") String cityid);
	
	@GET("moji/forecast15days/{cityid}")
	Observable<MojiDays15> mMojiDays15(@Path("cityid") String cityid);
	
	@GET("moji/index/{cityid}")
	Observable<MojiIndex> mMojiIndex(@Path("cityid") String cityid);
	
	@GET("moji/aqi/{cityid}")
	Observable<MojiAqi> mMojiAqi(@Path("cityid") String cityid);
	
	@GET("moji/alert/{cityid}")
	Observable<MojiAlert> mMojiAlert(@Path("cityid") String cityid);
	
	@GET("moji/aqiforecast5days/{cityid}")
	Observable<MojiAqi5> mMojiAqi5(@Path("cityid") String cityid);
	
	@GET("holiday")
	Observable<Holiday> mHoliday();
	
	@Headers("Authorization:APPCODE 102244875ca84381b4fcdcb53a76ce5c")
	@GET("http://jisuhlcx.market.alicloudapi.com/huangli/date")
	Observable<Calendar> mCalendar(@Query("day") String day, @Query("month") String month, @Query("year") String year);
	
	@GET("data")
	Observable<ExtraData> mExtraData();
	
	@GET
	Observable<ResponseBody> downloadPicFrom(@Url String picUrl);*/
}
