package com.codingwithgiap.a2812_demo_surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

class gamesurface extends SurfaceView implements SurfaceHolder.Callback {
    Object_box object_box;
    GameThread gamethread;
  public gamesurface(Context context){
       super(context);
       this.setFocusable(true);
       this.getHolder().addCallback(this);
   }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.chibi1);
        object_box = new Object_box(200,200,chibiBitmap1);
        gamethread = new GameThread(this,holder);
        gamethread.setRurning(true);
        gamethread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Boolean repply  = true;
        while (repply){
            this.gamethread.setRurning(false);
            try {
                this.gamethread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repply = true;
        }
    }

    public void update() {
      this.object_box.update();
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
      this.object_box.draw(canvas);
    }
}
