/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Helper.AssetLoader;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameLibGDX;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public abstract class AbstractWindow {

    protected Stage stage;
    protected Group group;

    private boolean isVisible;

    protected float xPos, yPos;
    protected float width, height;

    private ScrollWindowThread swt;
    private Thread thread;

    private boolean isCreated;
    private boolean check;

    protected TextureRegion normalState = AssetLoader.btn, pressedState = AssetLoader.btnPress;

    public AbstractWindow(Stage stage) {
        isVisible = false;
        this.stage = stage;
        this.group = new Group();
        this.check = false;
        this.isCreated = false;
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public void createWindow(GameLibGDX game) {
        show();
        isCreated = true;
        xPos = stage.getCamera().position.x - width / 2;
        yPos = -height;
        initBackground();
        initText();
        initButtons(game);
        stage.addActor(group);
        scroll();
    }

    private void scroll() {
        swt = new ScrollWindowThread();
        thread = new Thread(swt);
        thread.start();
    }

    protected abstract void initText();

    protected abstract void initButtons(GameLibGDX game);

    private void initBackground() {
        Background background = new Background(AssetLoader.textureBtnNormal);
        background.setPosition(xPos, yPos);
        background.setSize(width, height);
        group.addActor(background);
    }

    public class ScrollWindowThread implements Runnable {

        public ScrollWindowThread() {
        }

        @Override
        public void run() {
            do {
                group.setY(group.getY() + 1);
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AbstractWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (group.getY() < stage.getHeight() * 5 / 6 && !check);
        }

    }

    public void checkClick(float x, float y) {
        if ((x < xPos || y < yPos
                || x > xPos + width || y > yPos + height) && isCreated) {
            check = true;
            group.setY(stage.getHeight() * 5 / 6);
        }
    }
}
