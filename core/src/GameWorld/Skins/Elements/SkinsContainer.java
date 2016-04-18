package GameWorld.Skins.Elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.*;

import GameWorld.Skins.SkinsWorld;
import Helper.Constants;

/**
 * Created by broff on 16.04.2016.
 */
public class SkinsContainer {

    private List<BuySkin> skins = new LinkedList<BuySkin>();
    private float xCamera;
    private int activeButton = 0;
    private float maxX;
    private SkinsWorld world;

    public SkinsContainer(List<Skin> list, float cameraCenter, Stage mainStage, SkinsWorld world){
        this.world = world;
        float w = Constants.RUNNER_WIDTH * 4;
        float h = Constants.RUNNER_HEIGHT * 4;
        for(Skin skin : list){
            skins.add(new BuySkin(skin.getID(), skin.getTexture(), 0, 0, w, h, skin.getCost(), true, skin.getName()));
        }
        this.xCamera = cameraCenter;
        int i = -activeButton;
        for(BuySkin s: skins){
            s.setX(xCamera + i * Constants.APP_WIDTH / 2 - w / 2);
            s.setY(Constants.APP_HEIGHT / 4*3);
            mainStage.addActor(s);
            i++;
            updateScale(s);
        }

        maxX = (skins.size()+1) * (Constants.APP_WIDTH / 2 - w / 2);
    }

    public void moveX(float dX){
        int i = 0;
        int activeBtn=0;
        float dlt = Constants.APP_WIDTH*200;
        float delta;
        if((skins.get(0).getX() + dX) >= maxX){
            world.setInertion(0);
        } else if((skins.get(skins.size() - 1).getX() + dX) <= -maxX){
            world.setInertion(0);
        } else {
            for(BuySkin s: skins){
                s.setX(s.getX() + dX);
                delta = Math.abs(s.getX() - xCamera);
                updateScale(s);
                if(delta < dlt){
                    activeBtn = i;
                    dlt = delta;
                }
                i++;
            }
        }

        activeButton = activeBtn;
    }

    private void updateScale(BuySkin s){
        float delta = Math.abs(s.getX() - xCamera);
        if (delta >= Constants.APP_WIDTH / 2) {
            s.setScale(0.5f);
        } else {
            s.setScale(calcScale(delta));
        }

    }

    private float calcScale(float delta){
        float s = 1f - delta / Constants.APP_WIDTH ;
        return s;
    }

    public int getActiveButton(){
        return activeButton;
    }

    public List<BuySkin> getSkins(){
        return skins;
    }

    public String getSkinName(){
        return skins.get(activeButton).getName();
    }

    public int getSkinCoast(){
        return skins.get(activeButton).getCost();
    }
}
