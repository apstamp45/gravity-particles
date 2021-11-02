package com.apstamp45.gravity_particles;

import com.apstamp45.beryl.event.MouseEvent;
import com.apstamp45.beryl.game.GameObject;
import com.apstamp45.beryl.render.Camera;
import com.apstamp45.beryl.render.Renderer;
import com.apstamp45.beryl.window.Scene;
import com.apstamp45.beryl.window.Window;
import com.apstamp45.gravity_particles.game.BasicParticle;
import com.apstamp45.gravity_particles.game.Particle;

import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;

/**
 * The Gravity Particle main Scene.
 */
public class MainScene extends Scene {

    private static float MAX = 1000;

    private static float DEFAULT_PARTICLE_MASS = 1;

    public ArrayList<Particle> particles = new ArrayList<>();

    @Override
    public void init() {
        Renderer.setDefaultShaderFile("resources/shaders/default.glsl");
        renderer = new Renderer();
        camera = new Camera(new Vector2f(0.0f, 0.0f));
        int squareSize = 16;
        int spacingSize = 16;
        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                int loopNum = i * squareSize + j;
                float colorVal = map(loopNum, 0, squareSize * squareSize,
                              0, (float) (2.0 * Math.PI));
                BasicParticle particle = new BasicParticle(loopNum, this,
                        new Vector4f(getColor(colorVal, 0),
                                     getColor(colorVal, (float) (2.0 * Math.PI / 3.0)),
                                     getColor(colorVal, (float) (4.0 * Math.PI / 3.0)), 1), 4, DEFAULT_PARTICLE_MASS,
                        spacingSize * i, spacingSize * j);
                particles.add(particle);
            }
        }
        Particle mouseNommerParticle = new Particle(0, this,
                                                    new Vector4f(0.0f, 0.5f, 0.0f, 1.0f)) {
            @Override
            public void update(float dt) {
                float x = (float) MouseEvent.getX();
                float y = (float) MouseEvent.getY();
                if (x < 10 || x > Window.getWidth() - 10 ||
                    y < 10 || y > Window.getHeight() - 10)  {
                    setMass(0);
                } else {
                    setMass(MAX - DEFAULT_PARTICLE_MASS);
                }
                for (Particle other : particles) {
                    updateAcceleration(dt, other);
                }
                setPosition(x, y);
            }
        };
        particles.add(mouseNommerParticle);
        for (Particle particle : particles) {
            addGameObject(particle);
        }
    }

    @Override
    public void update(float dt) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }
        renderer.render();
    }

    private float getColor(float rad, float offset) {
        return (float) (2 * (Math.sin(rad + offset) + 1) / 3);
    }

    private float map(float val, float min, float max, float toMin, float toMax) {
        return toMin + (val - min) * (toMax - toMin) / (max - min);
    }
}
