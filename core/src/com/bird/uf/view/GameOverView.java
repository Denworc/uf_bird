package com.bird.uf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bird.uf.UFBird;
import com.bird.uf.controller.GameOverController;
import com.bird.uf.controller.GameStateManager;

public class GameOverView extends State {

    private Texture background;
    private Texture gameover;
    private int save;
    private BitmapFont somes;
    GameOverController check;

    public GameOverView(GameStateManager gsm, int skin) {
        super(gsm);
        check = new GameOverController(gsm, skin);
        save = skin;
        somes = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        camera.setToOrtho(false, UFBird.WIDTH / 2, UFBird.HEIGHT / 2);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
        somes.setColor(Color.WHITE);
    }


    @Override
    public void update(float dt) {
        check.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(gameover, camera.position.x - gameover.getWidth() / 2, camera.position.y);
        String score = PlayStateView.score + "";
        somes.draw(sb, PlayStateView.score + "", camera.position.x - score.length() * 10, 100);
        sb.end();

    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
    }
}
