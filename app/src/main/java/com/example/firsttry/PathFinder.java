package com.example.firsttry;

public class PathFinder {

    private static int[] wayI, wayJ;
    private static int[] queueI, queueJ;
    private static int step = -1;
    private static int p;
    private static int problemo;
    private static int min;

    public static void FillBoard(){
        boolean stop = false;
        int visit = 0;
        queueI = new int[Board.sizeY * Board.sizeX * 2];
        queueJ = new int[Board.sizeY * Board.sizeX * 2];
        int playerY = Player.getPositionY();
        int playerX = Player.getPositionX();
        int[][] visited = new int[Board.sizeY][Board.sizeX];

        for (int _i = 0; _i < Board.sizeY; _i++)
            for (int _j = 0; _j < Board.sizeX; _j++)
                if(Board.field[_i][_j] != -2)
                    Board.field[_i][_j] = 0;

        p = 0;
        Board.field[playerY][playerX] = 1;
        while (!stop){
            FillBoardCheck(playerY, playerX, -1, 0);
            FillBoardCheck(playerY, playerX, 1, 0);
            FillBoardCheck(playerY, playerX, 0, -1);
            FillBoardCheck(playerY, playerX, 0, 1);
            visited[playerY][playerX] = 1;
            if (visit == p) stop = true;

            if (visited[queueI[visit]][queueJ[visit]] != 1){
                playerY = queueI[visit];
                playerX = queueJ[visit];
                visit++;
            }
        }
    }

    public static void FindPath(int destinationX, int destinationY){
        step = 0;
        boolean stop = false;
        wayI = new int[Board.sizeX * Board.sizeY];
        wayJ = new int[Board.sizeX * Board.sizeY];
        if (Board.field[destinationY][destinationX] != -2 &&
                Board.field[destinationY][destinationX] != 0){
            min = Board.field[destinationY][destinationX];
            int destY = destinationY;
            int destX = destinationX;
            wayI[step] = destY;
            wayJ[step] = destX;
            while (!stop){
                problemo = 0;
                step++;

                FindPathCheck(destY, destX, -1, 0);
                FindPathCheck(destY, destX, 1, 0);
                FindPathCheck(destY, destX, 0, -1);
                FindPathCheck(destY, destX, 0, 1);

                if (problemo == 4) stop = true;

                destY = wayI[step];
                destX = wayJ[step];
            }
        }
        step--;
    }

    private static void FillBoardCheck(int y, int x, int _y, int _x){
        if ((y + _y >= 0 && y + _y < Board.sizeY) &&
                (x + _x >= 0 && x + _x < Board.sizeX) &&
                Board.field[y + _y][x + _x] == 0)
        {
            Board.field[y + _y][x + _x] = 1 + Board.field[y][x];
            queueI[p] = y + _y;
            queueJ[p] = x + _x;
            p++;
        }
    }

    private static void FindPathCheck(int y, int x, int _y, int _x){
        if ((y + _y >= 0 && y + _y < Board.sizeY) &&
                (x + _x >= 0 && x + _x < Board.sizeX) &&
                Board.field[y + _y][x + _x] != -2){
            if (Board.field[y + _y][x + _x] < min){
                min = Board.field[y + _y][x + _x];
                wayI[step] = y + _y;
                wayJ[step] = x + _x;
            }
            else problemo++;
        }
        else problemo++;
    }

    public static int[] getWayI(){
        return wayI;
    }

    public static int[] getWayJ(){
        return wayJ;
    }

    public static int getStep(){
        return step;
    }

    public static void setStep(int num){
        step = num;
    }
}
