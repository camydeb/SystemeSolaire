package vcamydeb.teccart.systemesolaire;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class AlienSolarSystem extends View {

    private Random alea;
    private int ballX;
    private int bally;
//    private float ballRadius;
    private planetes[] planetes = new planetes[8];
    private int cnt;
    private Context mcontext;
    private boolean fin;
    private String image;
    private Bitmap imageSelect;
    private int radius;

    private ArrayList<AstreCeleste> liste;


    public AlienSolarSystem(Context context) {
        super(context);
        mcontext = context;


        myDbAdapter myDBAdapter = new myDbAdapter(mcontext);
        myDBAdapter.Open();
        liste = myDBAdapter.selectAstres();

        fin = false;
        cnt =0;
        alea = new Random();

        ballX = alea.nextInt(1000);
        bally = alea.nextInt(1000);

        for (int i=0;i<planetes.length;i++)
        {
            planetes temp = new planetes(mcontext);
            planetes[i] = temp;

        }

        if(cnt>=planetes.length && !fin)
        {
            Toast.makeText(mcontext,"La partie est terminee",Toast.LENGTH_LONG).show();
            fin = true;
        }


    }
    
    @Override
    protected void onDraw(Canvas canvas)
    {
        // super.onDraw(canvas);

        Bitmap vaisseau = BitmapFactory.decodeResource(getResources(),R.drawable.vaisseau);

        for (int i=0;i<planetes.length;i++)
        {

            planetes[i].onDraw(canvas);
        }

        if(cnt>=planetes.length && !fin)
        {
            Toast.makeText(mcontext,"La partie est terminee",Toast.LENGTH_LONG).show();
            fin = true;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        int touchX = (int)event.getX();
        int touchY = (int)event.getY();

        boolean limitL,limitR,LimitU,LimitD = false;

        switch (action)
        {

            case MotionEvent.ACTION_MOVE:
                ballX = touchX;
                bally = touchY;

                for(int i =0;i<planetes.length;i++)
                {
                    limitL = ballX > (planetes[i].getPosX()-30);
                    limitR =  ballX < (planetes[i].getPosX()+30);
                    LimitU =  bally > (planetes[i].getPosY()-30);
                    LimitD =  bally < (planetes[i].getPosY()+30);

                    if(limitL && limitR && LimitD && LimitU )
                    {
                        if(planetes[i].getStatus())
                        {
                            planetes[i].setStatus(false);
                            cnt++;
                        }


                        System.out.println(cnt);

                    }

                }

                break;


        }
        invalidate();
        return true;
    }

}
