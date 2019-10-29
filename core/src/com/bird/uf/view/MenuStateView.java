package com.bird.uf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bird.uf.UFBird;
import com.bird.uf.controller.GameStateManager;
import com.bird.uf.controller.MenuStateController;

public class MenuStateView extends State {

    private Texture background;
    public static Sprite playBtn;
    public static Sprite btn1;
    public Sprite btn2;
    public Sprite btn3;
    private int i = 1;
    private MenuStateController check;
    private BitmapFont somes;

    public MenuStateView(GameStateManager gsm) {
        super(gsm);
        check = new MenuStateController(gsm);
        camera.setToOrtho(false, UFBird.WIDTH / 2, UFBird.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Sprite(new Texture("playbtn.png"));
        btn1 = new Sprite(new Texture("b1.png"));
        btn2 = new Sprite(new Texture("b2.png"));
        btn3 = new Sprite(new Texture("b3.png"));
        somes = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        somes.setColor(Color.WHITE);
    }

    @Override
    protected void handleInput() {

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
        sb.draw(btn1, camera.position.x - playBtn.getWidth() / 4 - btn1.getWidth(), camera.position.y - btn1.getHeight() * 2);
        sb.draw(btn2, camera.position.x - btn2.getWidth() / 2, camera.position.y - btn1.getHeight() * 2);
        sb.draw(btn3, camera.position.x + playBtn.getWidth() / 4, camera.position.y - btn1.getHeight() * 2);
        String chose = "Chose";
        String bird = "bird";
        String or = "or just ";
        String tap = "tap";
        somes.draw(sb, chose, camera.position.x - chose.length() * 14, 390);
        somes.draw(sb, bird , camera.position.x - bird.length() * 14, 340);
        somes.draw(sb, or, camera.position.x - or.length() * 14, 290);
        somes.draw(sb, tap , camera.position.x - tap.length() * 14, 240);

        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
