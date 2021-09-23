package com.apstamp45.gravity_particles;

import com.apstamp45.beryl.render.Renderer;
import com.apstamp45.beryl.window.Scene;
import com.apstamp45.beryl.window.Window;

public class Main {
    public static void main(String[] args) {
        Window window = Window.get(800, 500, "Gravity Particles");
        Scene testScene = new Scene() {
            @Override
            public void init() {
                Renderer.setDefaultShaderFile("resources/shaders/default.glsl");
            }

            @Override
            public void update(float dt) {
                System.out.println("FPS: " + 1.0F / dt);
            }
        };
        window.run(testScene);
    }
}
