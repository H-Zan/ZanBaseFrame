package com.huzan.zanbaseframe.modules.login.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.huzan.zanbaseframe.R;
import com.huzan.zanbaseframe.base.view.BaseFragment;
import com.huzan.zanbaseframe.modules.login.contract.LoginContract;
import com.huzan.zanbaseframe.modules.login.view.widget.ClearEditText;
import com.huzan.zanbaseframe.modules.mtest.acrtivity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ZAN on 2017/7/20.
 *
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {
	
	@BindView(R.id.user)
	ClearEditText mEtName;
	@BindView(R.id.pass)
	ClearEditText mEtPwd;
	@BindView(R.id.register)
	Button mBtnRegister;
	@BindView(R.id.login)
	Button mBtnLogin;
	@BindView(R.id.forget)
	TextView mTvForgotPwd;
	
	private Unbinder mBind;
	
	LoginContract.Presenter mPresenter;
	
	public LoginFragment() {
	}

	public static LoginFragment newInstance() {
		LoginFragment fragment = new LoginFragment();
		/*Bundle args = new Bundle();
		args.putString(ARG_PARAM1, "");
		args.putString(ARG_PARAM2, "");
		fragment.setArguments(args);*/
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getArguments() != null) {

		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_login, container, false);
		mBind = ButterKnife.bind(this, rootView);
		mPresenter.subscribe();
		
		return rootView;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mBind.unbind();
		mPresenter.unSubscribe();
		
	}
	
	@OnClick({R.id.register, R.id.login})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.register:
				
				break;
			case R.id.login:
				mPresenter.doLogin(mEtName.getText().toString(), mEtPwd.getText().toString());
				break;
		}
	}
	
	@Override
	public void showError(String msg) {
		
	}
	
	@Override
	public void setPresenter(LoginContract.Presenter presenter) {
		mPresenter = presenter;
	}
	
	@Override
	public void showLoading() {
		Toast.makeText(getContext(),"正在登录",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void hideLoading() {
		Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void showToast(String msg) {
		Toast.makeText(getContext(),"msg",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void setLoginBtnEnable(boolean isEnable) {
		
	}
	
	
	@Override
	public void jump() {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		startActivity(intent);
	}
}
