package com.bird.uf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bird.uf.model.Bird;
import com.bird.uf.view.State;


public class PlayStateController extends State {

    public PlayStateController(GameStateManager gsm) {
        super(gsm);
    }

    public void birdJump(final Bird bird) {
        if (Gdx.input.justTouched())
            bird.jump();
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                bird.jump();
                return true;
            }

        });
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() {
    }
}
