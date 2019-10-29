package com.bird.uf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bird.uf.UFBird;
import com.bird.uf.view.MenuStateView;
import com.bird.uf.view.PlayStateView;
import com.bird.uf.view.State;

public class GameOverController extends State {

    private int save;

    public GameOverController(GameStateManager gsm, int skin) {
        super(gsm);
        save = skin;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            PlayStateView.score = 0;
            gsm.set(new MenuStateView(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() {
    }
}
