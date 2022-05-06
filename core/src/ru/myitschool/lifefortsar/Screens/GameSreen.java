package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;


public class GameSreen implements Screen {
    final MyGame gme;
    public static final boolean DRUG = true, VRAG = false;
    public final static int voinRadius = 25;
    OrthographicCamera camera;
    OrthogrCameraControll ortCamCon;
    SpriteBatch batch;
    Vector3 touch;
    public ArrayList<Voin> voins = new ArrayList<>();

    Texture img_Feeld, img_Voin, img_Battons;
    TextureRegion[] imgBtn = new TextureRegion[12];

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
        touch = new Vector3();

        img_Feeld = new Texture(Gdx.files.internal("scrennsResource/screensGame/gamefeeld.png"));
        img_Voin = new Texture(Gdx.files.internal("scrennsResource/screensGame/knight.png"));
        img_Battons = new Texture(Gdx.files.internal("scrennsResource/screensGame/buttons/atlasBatton.png"));
        for (int i = 0; i < imgBtn.length; i++)
            imgBtn[i] = new TextureRegion(img_Battons, i * 180, 0, 180, 180);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
        }

        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            camera.position.y=ortCamCon.camPositionY;
            typeVoin = ortCamCon.changeTypeV(touch, typeVoin, battlePhase);//Смена типа раставляемых воинов
            battlePhase = ortCamCon.changeBatPhas(touch,battlePhase);
            //Добавление врагов
            if ((battlePhase == 0 || battlePhase == 1) && ortCamCon.inZone(touch, battlePhase)) {
                boolean isPlaceFree = true;

                for (int i = voins.size() - 1; i >= 0; i--) {
                    //проверка на спорикосновение ставящегося воина с уже существующим
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



        // отрисовка
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 1, 1, 1);
        batch.begin();
        batch.draw(img_Feeld, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT * 2);
        for (Voin v : voins) {
            if (battlePhase == 2) v.move();
            batch.draw(img_Voin, v.x - v.radius, v.y - v.radius, v.sizeX, v.sizeY);
        }

        batch.draw(imgBtn[10], SCREEN_WIDTH / 6 * 5, SCREEN_HEIGHT, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);
        for (int i = 0; i < 5; i++) {
            if (i == typeVoin) {
                if (i == 0) {
                    if (battlePhase == 0)
                        batch.draw(imgBtn[1], i * SCREEN_WIDTH / 6, SCREEN_HEIGHT, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);
                    if (battlePhase == 1)
                        batch.draw(imgBtn[1], i * SCREEN_WIDTH / 6, 0, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);
                }
                if (i != 0) {
                    if (battlePhase == 0)
                        batch.draw(imgBtn[i * 2 + 1], i * SCREEN_WIDTH / 6, SCREEN_HEIGHT, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);
                    if (battlePhase == 1)
                        batch.draw(imgBtn[i * 2 + 1], i * SCREEN_WIDTH / 6, 0, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);

                }
            } else {
                if (battlePhase == 0)
                    batch.draw(imgBtn[i * 2], i * SCREEN_WIDTH / 6, SCREEN_HEIGHT, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);
                if (battlePhase == 1)
                    batch.draw(imgBtn[i * 2], i * SCREEN_WIDTH / 6, 0, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);
            }
        } // отрисовка кнопок
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

        img_Feeld.dispose();
        img_Voin.dispose();
        batch.dispose();

    }

}