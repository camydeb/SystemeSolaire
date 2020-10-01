package vcamydeb.teccart.systemesolaire;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class StartScreen extends View {


    private int screenW;
    private int screenH;
    private Bitmap startPageLogo;
    private Bitmap btnPlayUp;
    private Bitmap btnPlayDown;
    private Bitmap btnAccStartUp;
    private Bitmap btnAccStartDown;
    private boolean playBtnState;
    private Context MyContext;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
    }


    public StartScreen(Context context) {
        super(context);
        MyContext = context;
        startPageLogo = BitmapFactory.decodeResource(getResources(),R.drawable.start_page);
        btnPlayUp = BitmapFactory.decodeResource(getResources(),R.drawable.play_up);
        btnPlayDown = BitmapFactory.decodeResource(getResources(),R.drawable.play_down);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap resizedBtnUp = Bitmap.createScaledBitmap(btnPlayUp, 300,300,true);
        Bitmap resizedBtnDown = Bitmap.createScaledBitmap(btnPlayDown, 300,300,true);


        canvas.drawBitmap(startPageLogo, ((int)screenW-startPageLogo.getWidth())/2,((int)(-50)),null);
        if(playBtnState){canvas.drawBitmap(resizedBtnDown, (screenW-resizedBtnDown.getWidth())/2,(int)(screenH*0.75),null);
        }
        else
        {
            canvas.drawBitmap(resizedBtnUp,(screenW-resizedBtnUp.getWidth())/2,(int)(screenH*0.75),null);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // return super.onTouchEvent(event);
        int action = event.getAction();
        int touchX = (int)event.getX();
        int touchY = (int)event.getY();


        switch (action)
        {

            case MotionEvent.ACTION_DOWN:
                if ((touchX > (screenW-btnPlayUp.getWidth())/2 &&
                        touchX< ((screenW-btnPlayUp.getWidth())/2) +
                                btnPlayUp.getWidth())&& ((touchY > (int)(screenH*0.75)) &&
                        (touchY < ((int)(screenH*0.75) +
                                btnPlayUp.getHeight())))) {
                    playBtnState = true;
                }

                break;

            case MotionEvent.ACTION_UP:
                if(playBtnState)
                {
                    myDbAdapter adapter = new myDbAdapter(getContext());
                    adapter.insertBD();
                    Intent gameIntent = new Intent(MyContext,GameOn.class);
                    MyContext.startActivity(gameIntent);

                }
                playBtnState = false;

                break;

            case MotionEvent.ACTION_MOVE:

                break;


        }
        invalidate();
        return true;
    }

}
