package com.codingwithgiap.a2812_demo_surfaceview;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

class GameThread extends Thread{
    Boolean istunning;
    SurfaceHolder surfaceHolder;
    gamesurface vgamesurface;
    public GameThread(gamesurface vgamesurface, SurfaceHolder surfaceHolder)  {
        this.vgamesurface= vgamesurface;
        this.surfaceHolder= surfaceHolder;
    }
    @Override
    public void run() {

        while (istunning){
            Canvas canvas = null;
            try {
            canvas = this.surfaceHolder.lockCanvas();
            //update and draw
            synchronized (canvas){
              this.vgamesurface.update();
              this.vgamesurface.draw(canvas);
            }
            }catch(Exception e)  {
            // Không làm gì
            }
            finally {
                if (canvas != null) {
                    // Mở khóa cho Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                    Log.e("check", "run: final" );
                }
            }
            try {
                // Ngừng chương trình một chút.
                this.sleep(150);
                Log.e("check", "run: sleep" );
            } catch(InterruptedException e)  {

            }
        }
    }

    public void setRurning(boolean isrunning) {
        this.istunning = isrunning;
    }
}
