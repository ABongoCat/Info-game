package GUI;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;

public class InGameScreen extends GameScreen {

    private final Hud hud = new Hud();

    public void render(Graphics2D g) {
        super.render(g);
        this.hud.render(g);
    }

    public void prepare() {
        super.prepare();
    }
}
