package com.huzan.zanbaseframe.base.data;

import com.annimon.stream.function.Function;
import io.reactivex.Observable;

/**
 * Created by ZAN on 2017/7/19.
 * Rxjava对象变换
 */

/* apiService.login(username,password)
                 .flatMap(new BaseResponseFunc<User>()) //这里使用
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new ExceptionSubscriber<User>(simpleCallback,application));
*/
public class BaseResponseFunc<T> implements Function<BaseResponse<T>, Observable<T>> {
	
	@Override
	public Observable<T> apply(BaseResponse<T> tBaseResponse) {
		//遇到非200错误统一处理,将BaseResponse转换成您想要的对象
		if (tBaseResponse.getCode() != 200) {
			return Observable.error(new Throwable(tBaseResponse.getMessage()));
		}else{
			return Observable.just(tBaseResponse.getData());
		}
	}
}