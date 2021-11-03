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

    /**
     * Stores all the MainScene's Particle/s.
     */
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
                int type = (int) Math.ceil(Math.random() * 4);
                BasicParticle particle = new BasicParticle(loopNum, this,
                            new Vector4f(getColor(colorVal, 0),
                                    getColor(colorVal, (float) (2.0 * Math.PI / 3.0)),
                                    getColor(colorVal, (float) (4.0 * Math.PI / 3.0)), 1), 4, 10,
                                    spacingSize * i, spacingSize * j);
                switch (type) {
                    case 1:
                        particle.setMass(1);
                        particle.setScale(1, 1);
                        break;
                    case 2:
                        particle.setMass(2);
                        particle.setScale(2, 2);
                        break;
                    case 3:
                        particle.setMass(5);
                        particle.setScale(3, 3);
                        break;
                    case 4:
                        particle.setMass(10);
                        particle.setScale(4, 4);
                        break;
                }
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
                    setMass(1000);
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

    /**
     * Returns a channel value based on what rad is.
     * offset is used to add multiple channels. Example:
     * for an rgb cycle, supply the rad value, and use
     * 0 for the first offset (for red channel), (2 * pi / 3)
     * for the second (green), and (4 * pi / 3) for the third (blue).
     * @param rad The value on which to change the hue.
     * @param offset The offset to use to get the correct channel.
     * @return The channel value.
     */
    private float getColor(float rad, float offset) {
        return (float) (2 * (Math.sin(rad + offset) + 1) / 3);
    }

    /**
     * Maps a value to a new one based on its range, and the
     * range of the new value.
     * @param val The value to map.
     * @param min The minimum value of the current range.
     * @param max The maximum value of the current range.
     * @param toMin The minimum value of the new range.
     * @param toMax The maximum value of the new range.
     * @return The new mapped value.
     */
    private float map(float val, float min, float max, float toMin, float toMax) {
        return toMin + (val - min) * (toMax - toMin) / (max - min);
    }
}
