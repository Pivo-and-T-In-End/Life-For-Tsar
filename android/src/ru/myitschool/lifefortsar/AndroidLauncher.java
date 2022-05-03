package ru.myitschool.lifefortsar;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import ru.myitschool.lifefortsar.Screens.MyGame;

public class AndroidLauncher extends AndroidApplication {
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(new MyGame(), config);
		getWindow().setNavigationBarColor(getResources().getColor(R.color.ic_background_color));
	}
}
