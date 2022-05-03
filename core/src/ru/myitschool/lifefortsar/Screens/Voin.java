package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.GameSreen.voinsizeX;
import static ru.myitschool.lifefortsar.Screens.GameSreen.voinsizeY;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_HEIGHT;
import static ru.myitschool.lifefortsar.Screens.MyGame.SCREEN_WIDTH;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class Voin  {
    boolean isFreand;
    int sizeX=voinsizeX,sizeY=voinsizeY;
    int typeVoin;
    int health, armor;
    int damage;
    int radiusdamage;
    //"id"цели в "вражеском" списке воинов
    int iAim;
    double radius;
    float positX, positY;
    float speed;

    Voin(float posX,float posY,int typVoi,boolean isDrug){
        // 0 = меч 1 = щит 2 = щит и меч 3 = копьё 4 = копьё и щит
        switch (typVoi){
            case 0:
                health = 50;
                armor = 10;
                speed = 0.8f;
                damage = 20;
                radiusdamage=10;
                break;
            case 1:
                health=100;
                armor=50;
                speed=0.6f;
                damage=5;
                radiusdamage=5;
                break;
            case 2:
                health=100;
                armor=50;
                speed=0.4f;
                damage=20;
                radiusdamage=7;
                break;
            case 3:
                health=40;
                armor=10;
                speed=1;
                damage=15;
                radiusdamage=20;
                break;
            case 4:
                health=80;
                armor=35;
                speed=0.6f;
                damage=15;
                radiusdamage=15;
                break;
        }

        isFreand = isDrug;
        radius=20;
        typeVoin = typVoi;
        positX=posX;
        positY = posY;

    }
    void move(){
        positY+=speed;
    }

    // фокусировка на враге
    void focus(ArrayList<Voin> anim){
        // расстояние наименьшего радиуса до врага
        double rG = Math.sqrt(Math.pow(positX-anim.get(0).positX,2)+Math.pow(positY-anim.get(0).positY,2));
        for (int i=0; i<anim.size();i++){
            if (rG>= Math.sqrt(Math.pow(positX-anim.get(i).positX,2)+Math.pow(positY-anim.get(i).positY,2)) && anim.get(i).health>0) {
                rG = Math.sqrt(Math.pow(positX - anim.get(i).positX, 2) + Math.pow(positY - anim.get(i).positY, 2));
                iAim = i;
            }
        }

    }

}
