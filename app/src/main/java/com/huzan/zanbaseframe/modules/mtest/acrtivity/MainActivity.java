package com.huzan.zanbaseframe.modules.mtest.acrtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.huzan.zanbaseframe.R;
import com.huzan.zanbaseframe.modules.login.acrtivity.LoginActivity;

public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void toLogin(View view) {
		startActivity(new Intent(this, LoginActivity.class));
	}
}
