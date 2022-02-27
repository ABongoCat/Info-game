package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiProperties;
import de.gurkenlabs.litiengine.gui.ImageComponent;
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

        GuiProperties.setDefaultFont(Fonts.MENU);
    }

    public void render(Graphics2D g) {
        ImageRenderer.render(g, background, 0, 0);
        ImageRenderer.render(g, logo, Game.window().getCenter().getX() - (logo.getWidth() / 2), 100);

        g.setFont(Fonts.MENU);
        g.setColor(Color.GREEN);
        TextRenderer.render(g, "an info lk 2022 media project", Align.CENTER, Valign.TOP, 0, 20);

        super.render(g);
    }

    protected void initializeComponents() {
        super.initializeComponents();

        this.mainMenu = new Menu(Game.window().getCenter().getX() - (BUTTON_WIDTH / 2),
                logo.getHeight() * 2.5,
                BUTTON_WIDTH, BUTTON_HEIGHT * buttons.length, buttons);

        Input.keyboard().onKeyReleased(event -> {
            if (this.isSuspended()) {
                return;
            }

            if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_W) {
                this.mainMenu.setCurrentSelection(Math.max(0, this.mainMenu.getCurrentSelection() - 1));
                for (ImageComponent comp : this.mainMenu.getCellComponents()) {
                    comp.setHovered(false);
                }
                this.mainMenu.getCellComponents().get(this.mainMenu.getCurrentSelection()).setHovered(true);

            }

            if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyCode() == KeyEvent.VK_S) {
                this.mainMenu
                        .setCurrentSelection(Math.min(buttons.length - 1, this.mainMenu.getCurrentSelection() + 1));
                for (ImageComponent comp : this.mainMenu.getCellComponents()) {
                    comp.setHovered(false);
                }
                this.mainMenu.getCellComponents().get(this.mainMenu.getCurrentSelection()).setHovered(true);

            }

            if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_SPACE) {

                switch (this.mainMenu.getCurrentSelection()) {
                    case 0:
                        this.changeScreen("GAME");
                        break;

                    case 1:
                        this.changeScreen("OPTIONS");
                        break;

                    case 2:
                        this.changeScreen("ABOUT");
                        break;

                    case 3:
                        System.exit(0);
                        break;

                }

            }
        });

        this.getComponents().add(this.mainMenu);
    }

    public void prepare() {
        super.prepare();
        Game.loop().attach(this);
        Game.window().getRenderComponent().setBackground(Color.black);

        this.mainMenu.setForwardMouseEvents(false);

        this.mainMenu.getCellComponents().forEach(comp -> {
            comp.setFont(Fonts.MENU);
            // comp.setSpriteSheet(Resources.spritesheets().get("button-background"));
            comp.getAppearance().setForeColor(Color.green);
            comp.getAppearanceHovered().setForeColor(Color.red);
            comp.setForwardMouseEvents(false);
        });

        this.mainMenu.setEnabled(true);
        this.mainMenu.getCellComponents().get(0).setHovered(true);
    }

    public void changeScreen(String screenName) {
        this.mainMenu.setEnabled(false);
        Game.window().getRenderComponent().fadeOut(1500);

        Game.loop().perform(1500, () -> {
            Game.window().getRenderComponent().fadeIn(1500);
            Game.screens().display(screenName);
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
