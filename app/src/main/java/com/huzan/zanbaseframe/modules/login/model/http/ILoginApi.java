package com.huzan.zanbaseframe.modules.login.model.http;

import com.huzan.zanbaseframe.base.data.BaseResponse;
import com.huzan.zanbaseframe.modules.mtest.model.entity.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ZAN on 2017/7/19.
 */

public interface ILoginApi {
	@GET("vinfo")
	Observable<BaseResponse<VersionBean>> mVersionAPI();
}
