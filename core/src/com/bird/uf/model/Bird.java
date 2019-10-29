package com.bird.uf.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.bird.uf.sprites.Animation;

public class Bird {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Sound flap;
    private Texture texture;

    public Bird(int x, int y, int i){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        if (i == 1)
            texture = new Texture("spr_b1_strip4.png");
        else if (i == 2)
            texture = new Texture("spr_b2_strip4.png");
        else
            texture = new Texture("spr_b3_strip4.png");
        birdAnimation = new Animation(new TextureRegion(texture), 4, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 4, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update((dt));
        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 0)
            position.y = 0;
        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    public void jump(){
        velocity.y = 250;
        flap.play();
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return velocity.y > 70;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
