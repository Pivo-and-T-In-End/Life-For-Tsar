package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;
import static ru.myitschool.lifefortsar.Screens.GameSreen.voinRadius;


import com.badlogic.gdx.math.Vector3;


public class OrthogrCameraControll {
    public float camPositionX, camPositionY=2880;
    public float posX, posY, poslastX, poslastY;

    public boolean inZone(Vector3 t, byte batlePhas) {
        boolean whatReturn = false;
        if (batlePhas == 0 && t.x > voinRadius && t.x < SCREEN_WIDTH - voinRadius &&
                t.y > SCREEN_HEIGHT + voinRadius + SCREEN_WIDTH / 6 && t.y < SCREEN_HEIGHT * 2 - voinRadius)
            whatReturn = true;

        if (batlePhas == 1 && t.x > voinRadius && t.x < SCREEN_WIDTH - voinRadius &&
                t.y > voinRadius + SCREEN_WIDTH / 6 && t.y < SCREEN_HEIGHT - voinRadius)
            whatReturn = true;

        return whatReturn;
    }//проверка на нахождение воина в зоне выставки воинов

    public byte changeTypeV(Vector3 t, byte nowType, byte batlPhasa) {
        byte whatReturn = 0;
        if (batlPhasa == 0) {
            if (t.y > SCREEN_HEIGHT && t.y < SCREEN_HEIGHT + SCREEN_WIDTH / 6 && t.x < SCREEN_WIDTH / 6 * 5)
                whatReturn = (byte) (t.x / 180);
            else whatReturn = nowType;
        }
        if (batlPhasa == 1) {
            if (t.y > 0 && t.y < SCREEN_WIDTH / 6 && t.x < SCREEN_WIDTH / 6 * 5)
                whatReturn = (byte) (t.x / 180);
            else whatReturn = nowType;
        }
        return whatReturn;

    }//меняет тип выставляемых воинов

    public  byte changeBatPhas(Vector3 t, byte batPhase){
        if (batPhase==0)
            if (t.y>SCREEN_HEIGHT && t.y<SCREEN_HEIGHT + SCREEN_WIDTH/6 && t.x > SCREEN_WIDTH / 6 * 5 && t.x<SCREEN_WIDTH) batPhase=4;

        if (batPhase==1)
            if (t.y>0 && t.y< SCREEN_WIDTH/6 && t.x > SCREEN_WIDTH / 6 * 5 && t.x<SCREEN_WIDTH) batPhase=3;

            return batPhase;
    }

    public byte  moveCamera(byte batPhas){

        return 0;
    }



     /*public void move(float tochY,float nowPosCamY,byte battlePhase){
        if (battlePhase==4) {
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
    }*/
}
