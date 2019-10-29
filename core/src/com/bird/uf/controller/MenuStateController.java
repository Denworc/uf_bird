package com.bird.uf.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bird.uf.UFBird;
import com.bird.uf.view.MenuStateView;
import com.bird.uf.view.PlayStateView;
import com.bird.uf.view.State;

public class MenuStateController extends State {

    private int i = 1;


    public MenuStateController(GameStateManager gsm) {
        super(gsm);
    }


    public void handleInput() {
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if ((screenX / 3 > (UFBird.WIDTH / 2.0 - 140))
                        & (screenX / 3 < (UFBird.WIDTH / 2.0 - 100))
                        & (screenY / 3 > (UFBird.HEIGHT / 2 + (UFBird.HEIGHT / 2 - 425)))
                        & (screenY / 3 < (UFBird.HEIGHT / 2 + UFBird.HEIGHT / 2 - 400))
                )
                    gsm.set(new PlayStateView(gsm, 1));
                else if (screenX / 3 > UFBird.WIDTH / 2.0 - 80
                        & screenX / 3 < UFBird.WIDTH / 2.0 - 20
                        & screenY / 3 > UFBird.HEIGHT / 2 + (UFBird.HEIGHT / 2 - 425)
                        & screenY / 3 < UFBird.HEIGHT / 2 + UFBird.HEIGHT / 2 - 400
                )
                    gsm.set(new PlayStateView(gsm, 2));
                else if (screenX / 3 > UFBird.WIDTH / 2.0
                        & screenX / 3 < UFBird.WIDTH / 2.0 + 60
                        & screenY / 3 > UFBird.HEIGHT / 2 + (UFBird.HEIGHT / 2 - 425)
                        & screenY / 3 < UFBird.HEIGHT / 2 + UFBird.HEIGHT / 2 - 400
                )
                    gsm.set(new PlayStateView(gsm, 3));
                else
                    gsm.set(new PlayStateView(gsm, 1));
                return true;
            }

        });
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
