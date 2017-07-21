package com.huzan.zanbaseframe.modules.login.acrtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.huzan.zanbaseframe.R;
import com.huzan.zanbaseframe.base.util.normal.ActivityUtil;
import com.huzan.zanbaseframe.modules.login.presenter.LoginPresenter;
import com.huzan.zanbaseframe.modules.login.view.fragment.LoginFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {
	
	@BindView(R.id.toolbar)
	Toolbar mToolbar;
	
	
	private Unbinder mBind;
	
	private LoginPresenter mLoginPresenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mBind = ButterKnife.bind(this);
		
		setSupportActionBar(mToolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
		}
		
		LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fl_login_fragment_container);
		if (loginFragment == null) {
			// Create the fragment
			loginFragment = LoginFragment.newInstance();
			ActivityUtil.addFragmentToActivity(
				getSupportFragmentManager(), loginFragment, R.id.fl_login_fragment_container);
		}
		
		mLoginPresenter = new LoginPresenter(loginFragment);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mBind.unbind();
	}
}
