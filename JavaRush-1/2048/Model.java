package com.javarush.task.task35.task3513;


import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by Zotov Mikhail on 22.03.2018.
 */
public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles= new Tile[FIELD_WIDTH][FIELD_WIDTH];

    private Stack<Integer> previousScores = new Stack<Integer>();
    private Stack<Tile[][]> previousStates = new Stack<Tile[][]>();
    private boolean isSaveNeeded = true;

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    int score=0;
    int maxTile = 0;

    public Model() {
        resetGameTiles();

    }

    public boolean canMove(){

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                if (gameTiles[i][j].value == 0)
                    return true;
                if (i != 0 && gameTiles[i - 1][j].value == gameTiles[i][j].value)
                    return true;
                if (j != 0 && gameTiles[i][j - 1].value == gameTiles[i][j].value)
                    return true;
            }
        }
        return false;
    }

    public ArrayList<Tile> getEmptyTiles() {
        ArrayList<Tile> list = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    list.add(gameTiles[i][j]);
                }
            }
        }
        return list;
    }

    public void addTile() {
        Tile newTile = getEmptyTiles().get((int) (getEmptyTiles().size() * (Math.random())));
        newTile.value = (Math.random() < 0.9 ? 2 : 4);
    }

    public void resetGameTiles() {
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {

        boolean anyChange = false;
        for (int i = 0; i < tiles.length; i ++) {

            if (tiles[i].value == 0) {
                outer:
                for (int j = i+1; j < tiles.length; j++) {
                    if (tiles[j].value != 0) {
                        tiles[i].value = tiles[j].value;
                        tiles[j].value = 0;
                        anyChange = true;
                        break outer;
                    }
                }
            }
        }

        return anyChange;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean change = false;

        for (int i = 0; i < tiles.length; i++) {
            try {

                if (tiles[i].value > 0 && tiles[i].value == tiles[i + 1].value) {

                    tiles[i].value += tiles[i + 1].value;
// Если выполняется условие слияния плиток, проверяем является ли новое значения больше максимального
// и при необходимости меняем значение поля maxTile.
                    if (tiles[i].value > maxTile) {
                        maxTile = tiles[i].value;
                    }
//Увеличиваем значение поля score на величину веса плитки образовавшейся в результате слияния.
                    score += tiles[i].value;
                    change = true;

                    for (int j = i + 1; j < tiles.length; j++) {
                        if (j != tiles.length - 1) {
                            tiles[j].value = tiles[j + 1].value;
                        } else tiles[j].value = 0;
                    }
                }
            }catch (ArrayIndexOutOfBoundsException ex) {
                continue;
            }
        }
        return change;
    }

   public void left(){
       if (isSaveNeeded) {
           saveState(gameTiles);
       }
       boolean isChange = false;
       for (int i = 0; i < FIELD_WIDTH; i++) {
           if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
               isChange = true;
       }

       if (isChange) {
           addTile();
       }
       isSaveNeeded = true;

   }

    private void rotateClockwise(){
        int [][] newField = new int[FIELD_WIDTH][FIELD_WIDTH];
        for (int x = 0; x< FIELD_WIDTH; x++)
            for (int y = 0; y< FIELD_WIDTH; y++)
                newField[x][y] = gameTiles[FIELD_WIDTH-y-1][x].value;
        for (int x = 0; x< FIELD_WIDTH; x++)
            for (int y = 0; y< FIELD_WIDTH; y++)
                gameTiles[x][y].value = newField[x][y];
    }

    public void right() {
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
    }

    public void up() {
        saveState(gameTiles);
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
    }

    public void down() {
        saveState(gameTiles);
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void saveState(Tile[][] tilesMatrix) {
        tilesMatrix = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tilesMatrix[i][j] = new Tile(gameTiles[i][j].value);
            }
        }
        previousStates.push(tilesMatrix);
        previousScores.push(score);
        isSaveNeeded = false;
    }


    public void rollback(){
        if(!previousScores.isEmpty()&&!previousStates.isEmpty()) {
            gameTiles = previousStates.pop();
            score=previousScores.pop();
        }
    }

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
       if(n==0){
           left();
       }
        if(n==1){
            up();
        }
        if(n==2){
            right();
        }
        if(n==3){
            down();
        }

    }

    public  boolean hasBoardChanged(){
        boolean isDifferent = false;
        int sumNow = 0;
        int sumPrevious = 0;
        Tile[][] tmp = previousStates.peek();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                sumNow += gameTiles[i][j].value;
                sumPrevious += tmp[i][j].value;
            }
        }
        return sumNow != sumPrevious;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();
        return moveEfficiency;

    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue(4, Collections.reverseOrder());
        queue.add(getMoveEfficiency(this::left));
        queue.add(getMoveEfficiency(this::right));
        queue.add(getMoveEfficiency(this::up));
        queue.add(getMoveEfficiency(this::down));
        Move move = queue.peek().getMove();
        move.move();
    }


}
