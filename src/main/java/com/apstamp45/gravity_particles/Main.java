package com.apstamp45.gravity_particles;

import com.apstamp45.beryl.render.Renderer;
import com.apstamp45.beryl.window.Window;

public class Main {
    public static void main(String[] args) {
        Renderer.setDefaultShaderFile("resources/shaders/default.glsl");
        Window window = Window.get(800, 500, "Gravity Particles");
        window.run(new MainScene());
    }
}
