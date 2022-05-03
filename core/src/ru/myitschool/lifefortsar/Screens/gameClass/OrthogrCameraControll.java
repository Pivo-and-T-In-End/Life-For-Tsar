package ru.myitschool.lifefortsar.Screens.gameClass;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class OrthogrCameraControll implements ApplicationListener {


    float camViewHeight, camViewWidth;
    public float camPositionX, camPositionY;
    public float posX,posY,poslastX,poslastY;
    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public void move(float cameraposX, float cameraposY){
        camPositionX =cameraposX;
        camPositionY =cameraposY;
        camPositionY += (posY - poslastY)/2;
        if (camPositionY <960) camPositionY = 960;
        if (camPositionY > 2880) camPositionY = 2880;
    }
}
