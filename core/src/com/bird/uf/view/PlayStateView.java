package com.bird.uf.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bird.uf.UFBird;
import com.bird.uf.controller.GameStateManager;
import com.bird.uf.controller.PlayStateController;
import com.bird.uf.model.Bird;
import com.bird.uf.model.Tube;


public class PlayStateView extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 5;
    private static final int GROUND_Y_OFFSET = -30;

    private BitmapFont somes;
    public static Bird bird;
    private Texture background;
    public Texture ground;
    public Vector2 groundPos1, groundPos2;
    public Array<Tube> tubes;
    public static int score = 0;
    private int save;
    PlayStateController check;

    public PlayStateView(GameStateManager gsm, int skin) {
        super(gsm);
        check = new  PlayStateController(gsm);
        bird = new Bird(60, 300, skin);
        somes = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        save = skin;
        camera.setToOrtho(false, UFBird.WIDTH / 2, UFBird.HEIGHT / 2);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Tube>();

        for (int i = 1; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }


        somes.setColor(Color.WHITE);
    }




    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);

        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        String score = getScore() + "";
        somes.draw(sb, getScore() + "", camera.position.x - score.length() * 10, 390);
        sb.end();
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        somes.dispose();
        for (Tube tube : tubes)
            tube.dispose();
    }


    @Override
    public void update(float dt) {
        check.birdJump(bird);
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;
        for (int i = 0; i < tubes.size; i++){

            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * (TUBE_COUNT - 1)));
            }

            if (tube.collides(bird.getBounds())){
                gsm.set(new GameOverView(gsm, save));
            }
            if (bird.getPosition().y < ground.getHeight() - 35){
                gsm.set(new GameOverView(gsm, save));
            }

            if (bird.getPosition().x > tube.getPosTopTube().x & !tube.isScored() & bird.getPosition().x < tube.getPosTopTube().x + 2){
                addScore(1);
                tube.setScored(true);
            }

            if (bird.getPosition().x > tube.getPosTopTube().x + Tube.TUBE_WIDTH)
                tube.setScored(false);

            if (bird.getPosition().y > 380){
                gsm.set(new GameOverView(gsm, save));
            }
        }

        camera.update();
    }

    public void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > this.groundPos1.x + ground.getWidth())
            this.groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }
}
