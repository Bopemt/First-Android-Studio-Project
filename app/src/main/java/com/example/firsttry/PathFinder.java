package com.example.firsttry;

public class PathFinder {
    private Player player;
    private Board board;

    private int[] wayI;
    private int[] wayJ;
    private int[] queueI;
    private int[] queueJ;
    private int step = -1;
    private int p;
    private int problemo;
    private int min;

    public PathFinder(Player player, Board board){
        this.player = player;
        this.board = board;
    }

    public void FillBoard(){
        boolean stop = false;
        int visit = 0;
        queueI = new int[board.sizeY * board.sizeX * 2];
        queueJ = new int[board.sizeY * board.sizeX * 2];
        int playerY = player.getPositionY();
        int playerX = player.getPositionX();
        int[][] visited = new int[board.sizeY][board.sizeX];

        for (int _i = 0; _i < board.sizeY; _i++)
            for (int _j = 0; _j < board.sizeX; _j++)
                if(board.field[_i][_j] != -2)
                    board.field[_i][_j] = 0;

        p = 0;
        board.field[playerY][playerX] = 1;
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

        for (int _i = 0; _i < board.sizeY; _i++){
            for (int _j = 0; _j < board.sizeX; _j++){
                System.out.print(board.field[_i][_j] + "  ");
            }
            System.out.println('\n');
        }
    }

    public void FindPath(int destinationX, int destinationY){
        step = 0;
        boolean stop = false;
        wayI = new int[board.sizeX * board.sizeY];
        wayJ = new int[board.sizeX * board.sizeY];
        if (board.field[destinationY][destinationX] != -2 &&
                board.field[destinationY][destinationX] != 0){
            min = board.field[destinationY][destinationX];
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
            for(int k = 0; k<step; k++){
                System.out.print(wayI[k]);
            }
            System.out.println("J");
            for(int k = 0; k<step; k++){
                System.out.print(wayJ[k]);
            }
            System.out.println("J");
        }
        step--;
    }

    private void FillBoardCheck(int y, int x, int _y, int _x){
        if ((y + _y >= 0 && y + _y < board.sizeY) &&
                (x + _x >= 0 && x + _x < board.sizeX) &&
                board.field[y + _y][x + _x] == 0)
        {
            board.field[y + _y][x + _x] = 1 + board.field[y][x];
            queueI[p] = y + _y;
            queueJ[p] = x + _x;
            p++;
        }
    }

    private void FindPathCheck(int y, int x, int _y, int _x){
        if ((y + _y >= 0 && y + _y < board.sizeY) &&
                (x + _x >= 0 && x + _x < board.sizeX) &&
                board.field[y + _y][x + _x] != -2){
            if (board.field[y + _y][x + _x] < min){
                min = board.field[y + _y][x + _x];
                wayI[step] = y + _y;
                wayJ[step] = x + _x;
            }
            else problemo++;
        }
        else problemo++;
    }

    public int[] getWayI(){
        return wayI;
    }

    public int[] getWayJ(){
        return wayJ;
    }

    public int getStep(){
        return step;
    }

    public void setStep(int num){
        step = num;
    }
}
