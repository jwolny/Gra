package com.mygdx.game.model.MapTools;
// metoda, ktora sprawdza czy jestesmy na tych obiektach wygenerowanych z mapy
public class MapCollision {
    public static boolean[][] collision(boolean[][] map){
        // boxy
        for (int i = 5; i <= 23 ; i += 6)
            for (int j = 5; j <= 23 ; j += 6) {
                map[i][j] = false;
                map[i+1][j] = false;
                map[i][j+1] = false;
                map[i+1][j+1] = false;
            }

        // brzeg
        for (int i = 0; i < 30; i++) {
            map[i][0] = false;
            map[0][i] = false;
            map[29][i] = false;
            map[i][29] = false;
        }

        // miejsce dla graczy
        map[3][2] = false;
        map[2][2] = false;
        map[3][1] = false;
        map[2][1] = false;

        map[3][28] = false;
        map[2][28] = false;
        map[3][27] = false;
        map[2][27] = false;


        return map;
    }
}
