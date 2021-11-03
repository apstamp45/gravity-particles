package com.apstamp45.gravity_particles;

import com.apstamp45.beryl.render.Renderer;
import com.apstamp45.beryl.window.Window;

/**
 * The main class (duh).
 */
public class Main {

    /**
     * You shouldn't need documentation
     * for this function.
     * @param args ...
     */
    public static void main(String[] args) {
        Renderer.setDefaultShaderFile("resources/shaders/default.glsl");
        Window window = Window.get(800, 500, "Gravity Particles");
        window.run(new MainScene());
    }
}
