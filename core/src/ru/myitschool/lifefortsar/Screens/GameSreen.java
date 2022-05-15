package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class GameSreen implements Screen {
    final MyGame gme;
    public static final boolean DRUG = true, VRAG = false;
    public final static int voinRadius = 25;
    OrthographicCamera camera;
    OrthogrCameraControll ortCamCon;
    SpriteBatch batch;
    ForBatchRender forBatchRender;
    Vector3 touch;
    public ArrayList<Voin> voins = new ArrayList<>();


    // 0- растановка войск врага,  1 - растановка наших войск, 2 - камера движется к "врагу" 3 - камера движется к "друзьяшкам" 4 - камера в позиции боя ;
    byte battlePhase = 0;
    // тип воина, такой же как в классе voin
    byte typeVoin;


    public GameSreen(MyGame gme) {
        this.gme = gme;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        ortCamCon = new OrthogrCameraControll();
        camera.position.y = ortCamCon.camPositionY;
        batch = new SpriteBatch();
        forBatchRender = new ForBatchRender();
        touch = new Vector3();



    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            battlePhase = ortCamCon.startFight(touch,battlePhase);
        }

        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            typeVoin = ortCamCon.changeTypeV(touch, typeVoin, battlePhase);//Смена типа раставляемых воинов
            battlePhase = ortCamCon.changeBatPhas(touch,battlePhase);
            //Добавление врагов
            if ((battlePhase == 0 || battlePhase == 1) && ortCamCon.inZone(touch, battlePhase)) {
                boolean isPlaceFree = true;

                for (int i = voins.size() - 1; i >= 0; i--) {
                    //проверка на спорикосновение ставящегося воина с уже существующим//
                    if (voins.get(i).isHit(touch.x, touch.y)) {
                        voins.remove(i);
                        isPlaceFree = false;
                    }
                }
                if (isPlaceFree) {
                    if (battlePhase == 0) voins.add(new Voin(touch.x, touch.y, typeVoin, VRAG));
                    if (battlePhase == 1) voins.add(new Voin(touch.x, touch.y, typeVoin, DRUG));
                }
            }
        }

        battlePhase = ortCamCon.moveCamera(battlePhase);
        camera.position.y=ortCamCon.camPositionY;


        // отрисовка
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        forBatchRender.batchRender(batch,voins,battlePhase,typeVoin);

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

        forBatchRender.img_Feeld.dispose();
        forBatchRender.img_Voin.dispose();
        forBatchRender.img_Battons.dispose();
        batch.dispose();

    }

}