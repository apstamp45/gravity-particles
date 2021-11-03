package com.apstamp45.gravity_particles.game;

import com.apstamp45.beryl.game.GameObject;
import com.apstamp45.beryl.game.components.SpriteRenderer;
import com.apstamp45.gravity_particles.MainScene;

import org.joml.Vector2f;
import org.joml.Vector4f;

/**
 * A Particle is a point that either orbits
 * other Particles, has Particles orbit around
 * it, or both.
 */
public class Particle extends GameObject {

    /**
     * Used to calculate the force between particles.
     */
    public static final double GRAVITY_CONSTANT = 1;

    /**
     * The Particle's mass.
     */
    private float mass;

    /**
     * This Particle's velocity.
     */
    public Vector2f velocity;//TODO

    /**
     * This Particle's acceleration.
     */
    public Vector2f acceleration;// TODO

    /**
     * This Particle's color.
     */
    private Vector4f color;

    /**
     * The instance of the MainScene that this
     * Particle is in.
     */
    public MainScene mainScene;// TODO

    /**
     * Creates a Particle with the name
     * "ParticleX", where X is the given
     * integer.
     * @param particleNumber The number of the
     *                       Particle.
     * @param mainScene The MainScene that this Particle
     *                  is in.
     * @param color The Particle's color.
     */
    public Particle(int particleNumber, MainScene mainScene, Vector4f color) {
        super("Particle" + particleNumber);
        this.mainScene = mainScene;
        velocity = new Vector2f();
        acceleration = new Vector2f();
        this.color = color;
        SpriteRenderer spriteRenderer = new SpriteRenderer(color);
        addComponent(spriteRenderer);
    }

    /**
     * Creates a Particle with the name
     * "GravityPointX", where x is the given
     * integer.
     * @param particleNumber The number of the
     * Particle.
     * @param mainScene The MainScene that this Particle
     *                  is in.
     */
    public Particle(int particleNumber, MainScene mainScene) {
        super("Particle" + particleNumber);
        this.mainScene = mainScene;
        velocity = new Vector2f();
        acceleration = new Vector2f();
        SpriteRenderer spriteRenderer = new SpriteRenderer(color);
        addComponent(spriteRenderer);
    }

    /**
     * Updates the Particle's position based on its
     * velocity and time since last update.
     * @param dt The time since last update.
     */
    public void updatePosition(float dt) {
        transform.position.x += velocity.x * dt;
        transform.position.y += velocity.y * dt;
    }

    /**
     * Updates the Particle's velocity based on its
     * acceleration and time since last update.
     * @param dt The time since last update.
     */
    public void updateVelocity(float dt) {
        velocity.x += acceleration.x * dt;
        velocity.y += acceleration.y * dt;
    }

    /**
     * Updates the Particle's acceleration based on
     * the other Particles in the MainScene.
     * @param dt The time since the last update.
     * @param other The Particle to evaluate.
     */
    public void updateAcceleration(float dt, Particle other) {
        float dx = other.transform.position.x - transform.position.x;
        float dy = other.transform.position.y - transform.position.y;
        float r = (float) Math.hypot(dx, dy);
        double force = GRAVITY_CONSTANT * ((this.mass * other.mass) / (r * r));
        double forceX = force * dx;
        double forceY = force * dy;
        acceleration.x = (float) (dt * forceX / this.mass);
        acceleration.y = (float) (dt * forceY / this.mass);
    }

    /**
     * Updates this Particle's acceleration,
     * velocity, and position based on
     * other's position and mass.
     * @param dt The change in time since last update.
     * @param other The other Particle to compare.
     */
    public void updateAVP(float dt, Particle other) {
        updateAcceleration(dt, other);
        updateVelocity(dt);
        updatePosition(dt);
    }

    /**
     * Sets this Particle's mass.
     * @param mass The new mass value.
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

    /**
     * Sets this Particle's scale.
     * @param width The Particle's new width.
     * @param height The Particle's new height.
     */
    public void setScale(float width, float height) {
        this.transform.scale.x = width;
        this.transform.scale.y = height;
    }

    /**
     * Sets this Particle's position.
     * @param xPos The Particle's new x position.
     * @param yPos The Particle's new y position.
     */
    public void setPosition(float xPos, float yPos) {
        this.transform.position.x = xPos;
        this.transform.position.y = yPos;
    }

    /**
     * Sets this Particle's velocity.
     * @param xVelocity The Particle's new velocity
     *                  in the x direction.
     * @param yVelocity The Particle's new velocity
     *                  in the y direction.
     */
    public void setVelocity(float xVelocity, float yVelocity) {
        this.velocity.x = xVelocity;
        this.velocity.y = yVelocity;
    }

    /**
     * Sets this Particle's acceleration.
     * @param xAcceleration The Particle's new acceleration in
     *                      the x direction.
     * @param yAcceleration The Particle's new acceleration in
     *                      the y direction.
     */
    public void setAcceleration(float xAcceleration, float yAcceleration) {
        this.acceleration.x = xAcceleration;
        this.acceleration.y = yAcceleration;
    }
}
