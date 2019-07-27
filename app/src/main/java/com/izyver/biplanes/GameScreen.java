package com.izyver.biplanes;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;
import androidx.annotation.Nullable;
import com.izyver.biplanes.engine.Game;
import com.izyver.biplanes.engine.Log;

public class GameScreen extends TextureView implements Game, TextureView.SurfaceTextureListener {
    public GameScreen(Context context) {
        super(context);
        super.setSurfaceTextureListener(this);
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        super.setSurfaceTextureListener(this);
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setSurfaceTextureListener(this);
    }

    public GameScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        super.setSurfaceTextureListener(this);
    }

    private boolean isDrawing = false;
    private boolean isSurfaceAvailable = false;
    private Canvas mCanvas;

    private GameCanvas gameCanvas = new BiplaneGame();

    //------------------Game--------------------//

    @Override
    public void onStartGame() {
    }

    @Override
    public void render() {
        if (!isSurfaceAvailable) {
            Log.w("Surface view is not available");
            return;
        }
        isDrawing = true;
        mCanvas = lockCanvas();
        if (mCanvas == null) throw new NullPointerException("canvas must be non null");
        gameCanvas.render(mCanvas);
        unlockCanvasAndPost(mCanvas);
        isDrawing = false;
    }

    @Override
    public void update(long deltaTime) {
        gameCanvas.update(deltaTime);
    }


    //-------SurfaceListener----------//

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        isSurfaceAvailable = true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        isSurfaceAvailable = false;
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
