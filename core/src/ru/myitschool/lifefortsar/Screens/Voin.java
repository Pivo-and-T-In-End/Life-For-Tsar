package ru.myitschool.lifefortsar.Screens;

import static ru.myitschool.lifefortsar.Screens.GameSreen.voinRadius;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class Voin extends Object {
    boolean isFreand;
    int sizeX = voinRadius * 2, sizeY = voinRadius * 2;
    private int typeVoin;
    int health, armor;
    private int damage;
    private int radiusdamage;
    int idAim;       //"id"цели в "вражеском" списке воинов
    float radius;
    float x, y;
    private float speed;

    Voin(float posX, float posY, int typVoi, boolean isFrean) {
        // 0 = меч 1 = щит 2 = щит и меч 3 = копьё 4 = копьё и щит
        switch (typVoi) {
            case 0:
                health = 5000;
                armor = 10;
                speed = 0.8f;
                damage = 20;
                radiusdamage = voinRadius * 2 + 10;
                break;
            case 1:
                health = 10000;
                armor = 50;
                speed = 0.6f;
                damage = 5;
                radiusdamage = voinRadius * 2 + 5;
                break;
            case 2:
                health = 10000;
                armor = 50;
                speed = 0.4f;
                damage = 20;
                radiusdamage = voinRadius * 2 + 7;
                break;
            case 3:
                health = 4000;
                armor = 10;
                speed = 1;
                damage = 15;
                radiusdamage = voinRadius * 2 + 20;
                break;
            case 4:
                health = 8000;
                armor = 35;
                speed = 0.6f;
                damage = 15;
                radiusdamage = voinRadius * 2 + 15;
                break;
        }

        isFreand = isFrean;
        radius = 20;
        typeVoin = typVoi;
        x = posX;
        y = posY;

    }

    public boolean isHit(float tx, float ty) {
        return (Math.pow(tx - x, 2) + Math.pow(ty - y, 2) <= Math.pow(radius * 2, 2));
    }

    //переставление воинов в списке


    // фокусировка на враге
    public void focus(ArrayList<Voin> voins) {
        float othX = 99999, othY = 99999;
        int idTarget = 0;
        // расстояние наименьшего радиуса до врага//
        if (isFreand) {
            for (int i = 0; i < voins.size(); i++) {
                if (!voins.get(i).isFreand && (Math.pow(x - voins.get(i).x, 2) + Math.pow(y - voins.get(i).y, 2))
                        < (Math.pow(x - othX, 2) + Math.pow(y - othY, 2))) {
                    othX = voins.get(i).x;
                    othY = voins.get(i).y;
                    idTarget = i;
                }
            }
        }

        if (!isFreand) {
            for (int i = 0; i < voins.size(); i++) {
                if (voins.get(i).isFreand && (Math.pow(x - voins.get(i).x, 2) + Math.pow(y - voins.get(i).y, 2))
                        < (Math.pow(x - othX, 2) + Math.pow(y - othY, 2))) {
                    othX = voins.get(i).x;
                    othY = voins.get(i).y;
                    idTarget = i;
                }
            }
        }

        idAim = idTarget;
    }

    // Движение к цели
    public void moveToAim(ArrayList<Voin> voins) {
        float possX = 0, possY = 0;
        float genNumber; // Общий знаменатель для выяснения точек, к которым надо двигаться
        boolean canmove = true;
        genNumber = (Math.abs(y - voins.get(idAim).y) + Math.abs(x - voins.get(idAim).x));
        if (x > voins.get(idAim).x + radiusdamage)
            possX = (speed / genNumber * (voins.get(idAim).x - x));
        if (x < voins.get(idAim).x - radiusdamage)
            possX = (speed / genNumber * (voins.get(idAim).x - x));
        if (x < voins.get(idAim).x + radiusdamage / 10 && x > voins.get(idAim).x - radiusdamage / 10)
            possX = 0;

        if (y > voins.get(idAim).y + radiusdamage)
            possY = (speed / genNumber * (voins.get(idAim).y - y));
        if (y < voins.get(idAim).y - radiusdamage)
            possY = (speed / genNumber * (voins.get(idAim).y - y));
        if (y < voins.get(idAim).y + radiusdamage && y > voins.get(idAim).y - radiusdamage)
            possY = 0;

        for (int i = 0; i < voins.size(); i++) {
            if (isFreand) {
                if (voins.get(i).y > y && Math.pow(y - voins.get(i).y, 2) + Math.pow(x - voins.get(i).x, 2) < Math.pow(radius * 2 + Math.abs(possX) + Math.abs(possY),2)) {
                    canmove = false;
                    break;
                }
            }
            if (!isFreand) {
                if (voins.get(i).y < y && Math.pow(y - voins.get(i).y, 2) + Math.pow(x - voins.get(i).x, 2) < Math.pow(radius * 2,2)) {
                    canmove = false;
                    break;
                }
            }
        }
        if (canmove) {
            if (possX != 0) x += possX;
            if (possY != 0) y += possY;
        }


    }//хрень - сделать нехренью

    // бой
    public void fightWithAim(ArrayList<Voin> voins){
        if (Math.pow(x - voins.get(idAim).x, 2) + Math.pow(y - voins.get(idAim).y, 2) < Math.pow(radiusdamage,2)){
            voins.get(idAim).health -= damage;
        }
    }


}