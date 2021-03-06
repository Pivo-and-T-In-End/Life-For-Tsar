package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;
import static ru.myitschool.lifefortsar.Screens.GameSreen.voinRadius;


import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;


public class OrthogrCameraControll {
    public float camPositionX, camPositionY = 2880;
    public float posX, posY, poslastX, poslastY;
    private long startIsTouchd, firstIsTouchd;

    OrthogrCameraControll(){
        startIsTouchd = TimeUtils.millis();
    }

    public boolean inZone(Vector3 t, byte batlePhas) {
        boolean whatReturn = false;
        if (batlePhas == 0 && t.x > voinRadius && t.x < SCREEN_WIDTH - voinRadius &&
                t.y > SCREEN_HEIGHT + voinRadius + SCREEN_WIDTH / 6 && t.y < SCREEN_HEIGHT * 2 - voinRadius)
            whatReturn = true;

        if (batlePhas == 1 && t.x > voinRadius && t.x < SCREEN_WIDTH - voinRadius &&
                t.y > voinRadius + SCREEN_WIDTH / 6 && t.y < SCREEN_HEIGHT - voinRadius)
            whatReturn = true;

        return whatReturn;
    }//проверка на нахождение воина в зоне выставки воинов//

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

    public byte changeBatPhas(Vector3 t, byte batPhase) {
        if (batPhase == 0)
            if (t.y > SCREEN_HEIGHT && t.y < SCREEN_HEIGHT + SCREEN_WIDTH / 6 && t.x > SCREEN_WIDTH / 6 * 5 && t.x < SCREEN_WIDTH)
                batPhase = 3;

        if (batPhase == 1)
            if (t.y > 0 && t.y < SCREEN_WIDTH / 6 && t.x > SCREEN_WIDTH / 6 * 5 && t.x < SCREEN_WIDTH)
                batPhase = 2;

        return batPhase;
    }// меняет фазу боя

    public byte moveCamera(byte batPhas) {
        if (batPhas == 3) {
            if (camPositionY > SCREEN_HEIGHT / 2) camPositionY -= 10;
            else {
                camPositionY = SCREEN_HEIGHT / 2;
                batPhas = 1;
            }
        }
        if (batPhas == 2) {
            if (camPositionY < SCREEN_HEIGHT + SCREEN_HEIGHT / 2) camPositionY += 10;
            else {
                camPositionY = SCREEN_HEIGHT + SCREEN_HEIGHT / 2;
                batPhas = 0;
            }
        }
        if (batPhas == 4) {
            camPositionY = SCREEN_HEIGHT;
        }

        return batPhas;
    }//2- к врагу 3- к друзьям

    public byte startFight(Vector3 touch, byte battlePhase) {
        byte whatReturn = battlePhase;
        if (battlePhase == 0 && touch.y > SCREEN_HEIGHT && touch.y < SCREEN_HEIGHT + SCREEN_WIDTH / 6 &&
                touch.x > SCREEN_WIDTH / 6 * 5 && touch.x < SCREEN_WIDTH) {
            if (firstIsTouchd < TimeUtils.millis() - 3100) firstIsTouchd = TimeUtils.millis();
            if (TimeUtils.millis() >= firstIsTouchd + 3000)
                whatReturn =  4;
        }
        else if (battlePhase == 1 && touch.y > 0 && touch.y < SCREEN_WIDTH / 6 &&
                touch.x > SCREEN_WIDTH / 6 * 5 && touch.x < SCREEN_WIDTH) {
            if (firstIsTouchd < TimeUtils.millis() - 3100) firstIsTouchd = TimeUtils.millis();
            if (TimeUtils.millis() >= firstIsTouchd + 3000)
                whatReturn = 4;
        }
        return whatReturn;
    } // нажатие кнопки для начала боя
}