package com.apstamp45.gravity_particles.game;

import com.apstamp45.gravity_particles.MainScene;
import org.joml.Vector4f;

public class BasicParticle extends Particle {

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
