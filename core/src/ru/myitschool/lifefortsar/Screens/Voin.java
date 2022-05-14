package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.GameSreen.voinRadius;

import java.util.ArrayList;

public class Voin  {
    boolean isFreand;
    int sizeX= voinRadius*2,sizeY= voinRadius*2;
    int typeVoin;
    int health, armor;
    int damage;
    int radiusdamage;
    int iAim;       //"id"цели в "вражеском" списке воинов
    float radius;
    float x, y;
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
        x = posX;
        y = posY;

    }

    void move(){
        y +=speed;
    }

    boolean isHit(float tx, float ty){
        return (Math.pow(tx - x, 2) + Math.pow(ty - y, 2) <= Math.pow(radius*2, 2));
    }

    // фокусировка на враге
    void focus(ArrayList<Voin> anim){
        // расстояние наименьшего радиуса до врага
        double rG = Math.sqrt(Math.pow(x -anim.get(0).x,2)+Math.pow(y -anim.get(0).y,2));
        for (int i=0; i<anim.size();i++){
            if (rG>= Math.sqrt(Math.pow(x -anim.get(i).x,2)+Math.pow(y -anim.get(i).y,2)) && anim.get(i).health>0) {
                rG = Math.sqrt(Math.pow(x - anim.get(i).x, 2) + Math.pow(y - anim.get(i).y, 2));
                iAim = i;
            }
        }

    }

}
