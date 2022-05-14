package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class ForBatchRender {

    Texture img_Feeld, img_Voin, img_Battons;
    TextureRegion[] imgBtn = new TextureRegion[12];

    ForBatchRender(){

        img_Feeld = new Texture(Gdx.files.internal("scrennsResource/screensGame/gamefeeld.png"));
        img_Voin = new Texture(Gdx.files.internal("scrennsResource/screensGame/knight.png"));
        img_Battons = new Texture(Gdx.files.internal("scrennsResource/screensGame/buttons/atlasBatton.png"));
        for (int i = 0; i < imgBtn.length; i++)
            imgBtn[i] = new TextureRegion(img_Battons, i * 180, 0, 180, 180);

    }

    void batchRender (SpriteBatch batch, ArrayList<Voin> voins,byte battlePhase,byte typeVoin){

        ScreenUtils.clear(0, 1, 1, 1);
        batch.begin();
        batch.draw(img_Feeld, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT * 2);
        for (Voin v : voins) {
            batch.draw(img_Voin, v.x - v.radius, v.y - v.radius, v.sizeX, v.sizeY);
        }

        if (battlePhase==0) batch.draw(imgBtn[10], SCREEN_WIDTH / 6 * 5, SCREEN_HEIGHT, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);
        if (battlePhase==1) batch.draw(imgBtn[11], SCREEN_WIDTH / 6 * 5, 0, SCREEN_WIDTH / 6, SCREEN_WIDTH / 6);

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

}
