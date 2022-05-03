package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class LoadingScreen implements Screen {
    final MyGame gme;

    OrthographicCamera camera;
    Vector3 touch;
    SpriteBatch batch;
    Texture img_Screensaver;

    // Отвечают за время, через которое экран загрузки сменится на экран игры
    long transitionTamer = 20000;
    long entryTimeForTamer;

    public LoadingScreen(MyGame gme) {
        this.gme = gme;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        touch = new Vector3();
        batch = new SpriteBatch();

        entryTimeForTamer = TimeUtils.millis();

        img_Screensaver = new Texture(Gdx.files.internal("scrennsResource/screensLoading/loadsav.jpg"));

    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // обработка касаний
        if(Gdx.input.isTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            gme.setScreen(gme.gameScreen);
        }

        if (TimeUtils.millis()>entryTimeForTamer+transitionTamer) gme.setScreen(gme.gameScreen);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(1,1,1,1);

       batch.begin();
       batch.draw(img_Screensaver, 0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
       batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }


    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        img_Screensaver.dispose();
        gme.dispose();
    }


}
