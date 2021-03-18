package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

    private static final int WINNING_TILE = 2048;

    public View getView() {
        return view;
    }

    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public void resetGame(){
        model.resetGameTiles();
        model.score=0;
        view.isGameWon = false;
        view.isGameLost=false;
    }

    public Tile[][] getGameTiles(){
      return model.getGameTiles();
    }

    public int getScore(){
        return model.score;
    }

    public void keyPressed(KeyEvent event){
        if(event.getKeyCode()==KeyEvent.VK_ESCAPE){
            resetGame();
        }

        if(model.canMove()==false){
            view.isGameLost=true;
        }

        if(view.isGameLost==false && view.isGameWon==false){
            if(event.getKeyCode()==KeyEvent.VK_LEFT){
                model.left();
            }
            if(event.getKeyCode()==KeyEvent.VK_RIGHT){
                model.right();
            }
            if(event.getKeyCode()==KeyEvent.VK_UP){
                model.up();
            }
            if(event.getKeyCode()==KeyEvent.VK_DOWN){
                model.down();
            }
            if(event.getKeyCode() == KeyEvent.VK_Z){
                model.rollback();
            }
            if(event.getKeyCode()==KeyEvent.VK_R){
                model.randomMove();
            }
            if(event.getKeyCode()==KeyEvent.VK_A){
                model.autoMove();
            }
        }

        if(model.maxTile==WINNING_TILE)
        {
            view.isGameWon=true;
        }
        view.repaint();

    }

}
