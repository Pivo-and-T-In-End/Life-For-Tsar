package ru.myitschool.lifefortsar.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    final MyGame gme;

    SpriteBatch batch;
    Texture img;

    public MenuScreen(MyGame gme) {
        this.gme = gme;
        batch = new SpriteBatch();
        //img = new Texture("loadScrnsav.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,1,1);
        batch.begin();
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
        img.dispose();
        gme.dispose();
        batch.dispose();
    }
}
