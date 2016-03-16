/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Pablo
 */
public class Constants {

    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);
    
    public static final float GROUND_WIDTH = APP_WIDTH * 10.6f;
    public static final float GROUND_HEIGHT = 60f;
    public static final float GROUND_X = 0f;//(APP_WIDTH + GROUND_WIDTH) / (-2);
    public static final float GROUND_Y = APP_HEIGHT / 4f;
    public static final float GROUND_DENSITY = 0;

    public static final float RUNNER_X = 0;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 30f;
    public static final float RUNNER_HEIGHT = 10f;
    public static float RUNNER_DENSITY = 0.5f;
    public static final float RUNNER_GRAVITY_SCALE = 10f;
    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(10f, 13f);

}
