package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;


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

    public void move(float tochX, float tochY,float nowPosCamY,byte battlePhase){
        if (battlePhase==2) {
            poslastY = posY;
            posY = tochY;
            camPositionY = nowPosCamY;
            if (posY > poslastY + 5) camPositionY += 20;
            if (posY < poslastY - 5) camPositionY -= 20;

            //(posY - poslastY)/1;
            if (camPositionY < 960) camPositionY = 960;
            if (camPositionY > 2880) camPositionY = 2880;
            poslastY = camPositionY;
        }

        if (battlePhase == 0) camPositionY = 2880;
        if (battlePhase == 1) camPositionY = 960;
    }
}
