import GUI.AboutScreen;
import GUI.InGameScreen;
import GUI.MenuScreen;
import GUI.OptionScreen;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;

public class App {
    public static void main(String[] args) throws Exception {

        Game.info().setName("Scheiss Zelda like game");
        Game.info().setVersion("v.0.69");
        Game.info().setSubTitle("pre alpha crack whore becky version");
        Game.init(args);

        Game.graphics().setBaseRenderScale(4f);

        GameManager.init();

        Resources.load("game.litidata");

        Game.screens().add(new MenuScreen());
        Game.screens().add(new OptionScreen());
        Game.screens().add(new AboutScreen());
        Game.screens().add(new InGameScreen());

        Game.start();
    }
}