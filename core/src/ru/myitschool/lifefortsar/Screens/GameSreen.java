package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;


public class GameSreen implements Screen {
    final MyGame gme;


    OrthographicCamera camera;
    OrthogrCameraControll orgrcamcon;
    SpriteBatch batch;
    Vector3 touch;
    public ArrayList<Voin> voins = new ArrayList<>();

    Texture img_Feeld, img_Voin;

    // 0- растановка войск врага,  1 - растановка наших войск, 2 - камера движется;
    byte battlePhase;
    // тип воина, такой же как в классе voin
    byte typeVoin;
    public final static int voinsizeX = 50, voinsizeY = 50;


    public GameSreen(MyGame gme) {
        this.gme = gme;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        camera.position.y = 2880;
        orgrcamcon = new OrthogrCameraControll();
        batch = new SpriteBatch();
        touch = new Vector3();

        img_Feeld = new Texture(Gdx.files.internal("scrennsResource/screensGame/gamefeeld.png"));
        img_Voin = new Texture(Gdx.files.internal("scrennsResource/screensGame/knight.png"));

        battlePhase = 0;

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            orgrcamcon.move(touch.x, touch.y, camera.position.y, battlePhase);
            camera.position.y = orgrcamcon.camPositionY;
            camera.unproject(touch);
        }
        if (Gdx.input.justTouched()) {
            //Добавление врагов
            if (battlePhase == 0 && touch.y - voinsizeY / 2 <= SCREEN_HEIGHT * 2 - voinsizeY / 2 && touch.y + voinsizeY / 2 >= SCREEN_HEIGHT + SCREEN_WIDTH / 6 &&
                    (touch.x - voinsizeX / 2) >= 0 && touch.x + voinsizeX / 2 <= SCREEN_WIDTH) {
                boolean canPut=true;
                for (Voin v : voins) {
                    //проверка на спорикосновение ставящегося воина с уже существующим
                    if (Math.pow(touch.x - (v.positX+v.sizeX/2), 2) + Math.pow(touch.y - (v.positY+v.sizeY/2), 2) <= Math.pow(v.radius*2, 2)) canPut=false;
                }
                if (canPut == true) {
                    boolean issDrug = false;
                    if (battlePhase == 1) issDrug = true;
                    voins.add(new Voin(touch.x - voinsizeX / 2, touch.y - voinsizeY / 2, typeVoin, true));
                }
            }
            //добавление друзей

            //Смена кнопок

        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 1, 1, 1);
        batch.begin();
        batch.draw(img_Feeld, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT * 2);
        for (Voin v : voins) {
            if (battlePhase == 2) v.move();
            batch.draw(img_Voin, v.positX, v.positY, v.sizeX, v.sizeY);

        }

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
        orgrcamcon.dispose();

        img_Feeld.dispose();
        img_Voin.dispose();
        batch.dispose();

    }
}
