package com.apstamp45.gravity_particles.game;

import com.apstamp45.gravity_particles.MainScene;
import org.joml.Vector4f;

/**
 * This class defines a standard Particle.
 */
public class BasicParticle extends Particle {

    /**
     * Creates a BasicParticle.
     * @param particleNumber The number of this particle (not important).
     * @param mainScene The MainScene that this Particle was created in.
     * @param color The Particle's color.
     * @param size The Particle's size (in pixels). The Particle will
     *             be a square.
     * @param mass The Particle's mass.
     * @param xPos The Particle's x position.
     * @param yPos The Particle's y position.
     */
    public BasicParticle(int particleNumber, MainScene mainScene, Vector4f color,
                         float size, float mass, float xPos, float yPos) {
        super(particleNumber, mainScene, color);
        setMass(mass);
        setScale(size, size);
        setPosition(xPos, yPos);
    }

    @Override
    public void update(float dt) {
        for (Particle other : mainScene.particles) {
            if (other == this) {
                continue;
            }
            updateAVP(dt, other);
        }
    }
}
