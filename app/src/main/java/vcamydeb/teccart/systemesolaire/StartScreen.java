package vcamydeb.teccart.systemesolaire;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.SensorEventListener;
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
    private String playBtnState;
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
        btnAccStartUp = BitmapFactory.decodeResource(getResources(),R.drawable.accelerometre_up);
        btnAccStartDown = BitmapFactory.decodeResource(getResources(),R.drawable.accelerometre_down);
        playBtnState = "";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap resizedBtnUp = Bitmap.createScaledBitmap(btnPlayUp, screenW/4,400,true);
        Bitmap resizedBtnDown = Bitmap.createScaledBitmap(btnPlayDown, screenW/4,400,true);

        Bitmap resizedBtnAccUp = Bitmap.createScaledBitmap(btnAccStartUp, screenW/4, 400,true);
        Bitmap resizedBtnAccDown = Bitmap.createScaledBitmap(btnAccStartDown, screenW/4, 400,true);

//bouton touchscreen
        canvas.drawBitmap(startPageLogo, ((int)screenW-startPageLogo.getWidth())/2,((int)(-50)),null);
        if(playBtnState == "touch"){canvas.drawBitmap(resizedBtnDown, ((screenW/3)-resizedBtnDown.getWidth()/2),(int)(screenH*0.65),null);
        }
        else if(playBtnState == "")
            {
                canvas.drawBitmap(resizedBtnUp,((screenW/3)-resizedBtnUp.getWidth()/2),(int)(screenH*0.65),null);
            }

//bouton accelerometre
        if(playBtnState == "accelerometre") {
            canvas.drawBitmap(resizedBtnAccDown, (((screenW/3)+resizedBtnAccDown.getWidth())), (int)(screenH*0.65), null);
        }
        else if(playBtnState == "")
            {
                canvas.drawBitmap(resizedBtnAccUp, (((screenW/3)+resizedBtnAccUp.getWidth())), (int)(screenH*0.65), null);
            }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Bitmap resizedBtnUp = Bitmap.createScaledBitmap(btnPlayUp, screenW/4,400,true);
        Bitmap resizedBtnDown = Bitmap.createScaledBitmap(btnPlayDown, screenW/4,400,true);

        Bitmap resizedBtnAccUp = Bitmap.createScaledBitmap(btnAccStartUp, screenW/4, 400,true);
        Bitmap resizedBtnAccDown = Bitmap.createScaledBitmap(btnAccStartDown, screenW/4, 400,true);
        // return super.onTouchEvent(event);
        int action = event.getAction();
        int touchX = (int)event.getX();
        int touchY = (int)event.getY();


        switch (action)
        {

            case MotionEvent.ACTION_DOWN:
                if ((touchX > ((screenW/3)-resizedBtnUp.getWidth()/2) &&
                        touchX< ((screenW/3)-resizedBtnUp.getWidth()/2) +
                                resizedBtnUp.getWidth())&& ((touchY > (int)(screenH*0.65)) &&
                        (touchY < ((int)(screenH*0.65) +
                                resizedBtnUp.getHeight())))) {
                    playBtnState = "touch";
                }
                else if ((touchX > ((screenW/3)+resizedBtnAccUp.getWidth()) &&
                            touchX < ((screenW/3)+resizedBtnAccUp.getWidth()) + resizedBtnAccUp.getWidth()) && ((touchY >(int)(screenH*0.65))
                        && (touchY < ((int)(screenH*0.65) + resizedBtnAccUp.getHeight())))) {
                    playBtnState = "accelerometre";
                }

                break;





            case MotionEvent.ACTION_UP:
                if(playBtnState == "touch")
                {
                    myDbAdapter adapter = new myDbAdapter(getContext());
                    adapter.insertBD();
                    Intent gameIntent = new Intent(MyContext,GameOn.class);
                    MyContext.startActivity(gameIntent);

                }
                else if(playBtnState == "accelerometre")
                {
                    myDbAdapter adapter = new myDbAdapter(getContext());
                    adapter.insertBD();
                    Intent gameIntent = new Intent(MyContext,game_on_acc.class);
                    MyContext.startActivity(gameIntent);
                }

                playBtnState = "";

                break;

            case MotionEvent.ACTION_MOVE:

                break;


        }
        invalidate();
        return true;
    }

}
