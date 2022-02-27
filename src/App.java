import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;

public class App {
    public static void main(String[] args) throws Exception {

        Game.info().setName("Scheiss Zelda like game");
        Game.info().setVersion("v.0.69");
        Game.info().setSubTitle("pre alpha crack whore becky version");
        Game.init(args);

        Game.graphics().setBaseRenderScale(4f);

        Resources.load("game.litidata");

        Game.screens().add(new MenuScreen());
        Game.start();
    }
}