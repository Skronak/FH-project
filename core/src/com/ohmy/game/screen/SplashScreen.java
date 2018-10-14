package com.ohmy.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.ohmy.game.Constants;

public class SplashScreen
{
    private Texture img;
    private float timer = 0.0F;
    private static final float BOUNCE_DUR = 1.2F;
    private static final float FADE_DUR = 3.0F;
    private static final float WAIT_DUR = 1.5F;
    private static final float FADE_OUT_DUR = 1.0F;
    private static final int W = 512;
    private static final int H = 512;
    private Color color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
    private Color bgColor = new Color(0.0F, 0.0F, 0.0F, 1.0F);
    private Color shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private Phase phase = Phase.INIT;
    public boolean isDone = false;
    private static final float OFFSET_Y = 8.0F * 1;
    private static final float OFFSET_X = 12.0F * 1;
    private float x = Constants.V_WIDTH / 2.0F;
    private float y = Constants.V_HEIGHT / 2.0F - OFFSET_Y;
    private float sX = Constants.V_WIDTH / 2.0F;
    private float sY = Constants.V_HEIGHT / 2.0F;
    private Color cream = Color.valueOf("ffffdbff");
    private Color bgBlue = Color.valueOf("074254ff");
    private boolean playSfx = false;
    private long sfxId = -1L;
    private String sfxKey = null;

    private static enum Phase
    {
        INIT,  BOUNCE,  FADE,  WAIT,  FADE_OUT;

        private Phase() {}
    }

    public SplashScreen()
    {
        this.img = new Texture (Gdx.files.internal("sprite/logo.png"));
    }

    public void update()
    {
        switch (this.phase)
        {
            case INIT:
                this.timer -= Gdx.graphics.getDeltaTime();
                if (this.timer < 0.0F)
                {
                    this.phase = Phase.BOUNCE;
                    this.timer = 1.2F;
                }
                break;
            case BOUNCE:
                this.timer -= Gdx.graphics.getDeltaTime();
                this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.timer / 1.2F);
                this.y = Interpolation.elasticIn.apply(Constants.V_HEIGHT / 2.0F, Constants.V_HEIGHT / 2.0F - 200.0F * 1, this.timer / 1.2F);
                if ((this.timer < 0.96000004F) && (!this.playSfx))
                {
                    this.playSfx = true;
                }
                if (this.timer < 0.0F)
                {
                    this.phase = Phase.FADE;
                    this.timer = 3.0F;
                }
                break;
            case FADE:
                this.timer -= Gdx.graphics.getDeltaTime();
                this.color.r = Interpolation.fade.apply(this.cream.r, 1.0F, this.timer / 3.0F);
                this.color.g = Interpolation.fade.apply(this.cream.g, 1.0F, this.timer / 3.0F);
                this.color.b = Interpolation.fade.apply(this.cream.b, 1.0F, this.timer / 3.0F);
                this.bgColor.r = Interpolation.fade.apply(this.bgBlue.r, 0.0F, this.timer / 3.0F);
                this.bgColor.g = Interpolation.fade.apply(this.bgBlue.g, 0.0F, this.timer / 3.0F);
                this.bgColor.b = Interpolation.fade.apply(this.bgBlue.b, 0.0F, this.timer / 3.0F);
                this.sX = Interpolation.exp5Out.apply(Constants.V_WIDTH / 2.0F + OFFSET_X, Constants.V_WIDTH / 2.0F, this.timer / 3.0F);
                this.sY = Interpolation.exp5Out.apply(Constants.V_HEIGHT / 2.0F - OFFSET_Y, Constants.V_HEIGHT / 2.0F, this.timer / 3.0F);
                if (this.timer < 0.0F)
                {
                    this.phase = Phase.WAIT;
                    this.timer = 1.5F;
                }
                break;
            case WAIT:
                this.timer -= Gdx.graphics.getDeltaTime();
                if (this.timer < 0.0F)
                {
                    this.phase = Phase.FADE_OUT;
                    this.timer = 1.0F;
                }
                break;
            case FADE_OUT:
                this.bgColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.timer / 1.0F);
                this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.timer / 1.0F);
                this.timer -= Gdx.graphics.getDeltaTime();
                if (this.timer < 0.0F)
                {
                    this.img.dispose();
                    this.isDone = true;
                }
                break;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.bgColor);
        sb.setColor(this.shadowColor);
        sb.draw(this.img, this.sX - 256.0F, this.sY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, 1, 1, 0.0F, 0, 0, 512, 512, false, false);

        Color s = new Color(0.0F, 0.0F, 0.0F, this.color.a / 5.0F);
        sb.setColor(s);
        sb.draw(this.img, this.x - 256.0F + 14.0F * 1, this.y - 256.0F - 14.0F * 1, 256.0F, 256.0F, 512.0F, 512.0F, 1, 1, 0.0F, 0, 0, 512, 512, false, false);

        sb.setColor(this.color);
        sb.draw(this.img, this.x - 256.0F, this.y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, 1, 1, 0.0F, 0, 0, 512, 512, false, false);
    }
}
