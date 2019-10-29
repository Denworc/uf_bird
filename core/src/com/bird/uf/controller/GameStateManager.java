package com.bird.uf.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bird.uf.view.State;

import java.util.Stack;

public class GameStateManager {

    public Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
