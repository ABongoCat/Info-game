package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.Menu;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;

public class MenuScreen extends Screen implements IUpdateable {

    private static final BufferedImage background = Imaging.scale(Resources.images().get("res/background.png"),
            Game.window().getWidth(), Game.window().getHeight());
    private static final BufferedImage logo = Imaging.scale(Resources.images().get("res/logo.png"),
            0.8);

    public static final String[] buttons = { "Play", "Options", "About", "Exit" };
    public static final int BUTTON_WIDTH = (int) (Game.window().getWidth() * 0.8);
    public static final int BUTTON_HEIGHT = (int) ((Game.window().getHeight() - logo.getHeight() * 2)
            / (buttons.length + 1));

    private Menu mainMenu;

    public MenuScreen() {
        super("MENU");
    }

    public void render(Graphics2D g) {
        ImageRenderer.render(g, background, 0, 0);
        ImageRenderer.render(g, logo, Game.window().getCenter().getX() - (logo.getWidth() / 2), 100);

        super.render(g);
    }

    protected void initializeComponents() {
        super.initializeComponents();

        this.mainMenu = new Menu(Game.window().getCenter().getX() - (BUTTON_WIDTH / 2),
                logo.getHeight() * 2.5,
                BUTTON_WIDTH, BUTTON_HEIGHT * buttons.length, buttons);

        Input.keyboard().onKeyReleased(e -> {
            if (this.isSuspended())
                return;
            System.out.println(e.getKeyChar());
        });

        this.getComponents().add(this.mainMenu);
    }

    public void prepare() {
        super.prepare();
        Game.loop().attach(this);
        Game.window().getRenderComponent().setBackground(Color.BLUE);
    }

    private void startGame() {
        this.mainMenu.setEnabled(false);
        Game.window().getRenderComponent().fadeOut(1500);

        Game.loop().perform(1500, () -> {
            Game.window().getRenderComponent().fadeIn(1500);
            // Game.screens().display("GAME");
        });
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
        Game.audio().stopMusic();
    }
}
