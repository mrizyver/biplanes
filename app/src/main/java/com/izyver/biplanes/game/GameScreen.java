package com.izyver.biplanes.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import androidx.annotation.Nullable;
import com.izyver.biplanes.engine.Game;

public class GameScreen extends TextureView implements Game, TextureView.SurfaceTextureListener {

    private static final String TAG = "GameScreen";
    private boolean isDrawing = false;
    private GameCanvas gameCanvas = new BiplaneGame();

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

    //------------------Game--------------------//


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        gameCanvas.setSize(width, height);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameCanvas.onTouch(event);
        return true;
    }

    @Override
    public void onStartGame() {
        gameCanvas.initialize();
    }

    private Canvas mCanvas;
    @Override
    public void render() {
        isDrawing = true;
        mCanvas = lockCanvas();
        if (mCanvas == null) {
            Log.w(TAG, "canvas must be non null, surface view is not ready");
            return;
        }
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

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
