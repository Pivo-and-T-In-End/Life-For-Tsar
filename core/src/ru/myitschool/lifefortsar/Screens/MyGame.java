package ru.myitschool.lifefortsar.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;



import ru.myitschool.lifefortsar.Screens.GameSreen;
import ru.myitschool.lifefortsar.Screens.LoadingScreen;
import ru.myitschool.lifefortsar.Screens.MenuScreen;

public class MyGame extends Game {

	public static final int SCREEN_HEIGHT = 1920, SCREEN_WIDTH = 1080;
	public SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;

	LoadingScreen loadingScreen;
	MenuScreen menuScreen;
	GameSreen gameScreen;


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		touch = new Vector3();

		//loadingScreen = new LoadingScreen(this);//
		menuScreen = new MenuScreen(this);
		gameScreen = new GameSreen(this);

		setScreen(gameScreen);

	}


	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		loadingScreen.dispose();
		menuScreen.dispose();
		gameScreen.dispose();
	}

}

