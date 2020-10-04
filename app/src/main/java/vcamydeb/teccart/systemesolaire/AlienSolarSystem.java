package vcamydeb.teccart.systemesolaire;
import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
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
    private Bitmap vaisseau;
    private String nom;
    private int taille;
    private String couleur;
    private int type;
    private String nomImage;
    private Bitmap space;
    private int screenW;
    private int screenH;
    private Paint ballPaint;
    private ArrayList<AstreCeleste> liste;
    private Bitmap imageSelect;


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
    }

    public AlienSolarSystem(Context context) {
        super(context);
        mcontext = context;


        myDbAdapter myDBAdapter = new myDbAdapter(mcontext);
        myDBAdapter.Open();
        liste = myDBAdapter.selectAstres();

        fin = false;
        cnt = 0;
        alea = new Random();

        ballX = alea.nextInt(1000);
        bally = alea.nextInt(1500);


        for (int i = 0; i <= 7; i++) {


            planetes temp = new planetes(mcontext);
            planetes[i] = temp;

        }

        if (cnt >= 7 && !fin) {
            Toast.makeText(mcontext, "La partie est terminee", Toast.LENGTH_LONG).show();
            fin = true;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

//        VaisseauSpatial vaisseau = new VaisseauSpatial()

        //creer le background
        space = BitmapFactory.decodeResource(getResources(), R.drawable.space);
        Bitmap resizedSpace = Bitmap.createScaledBitmap(space, screenW, screenH, true);
        canvas.drawBitmap(resizedSpace, 0, 0, null);


        //creer le vaisseau
//        canvas.drawCircle(ballX, bally, 50, ballPaint);

        VaisseauSpatial vaisseau = new VaisseauSpatial("vaisseau", 400, 320);

        int idImage = getResources().getIdentifier(vaisseau.getNomImage(), "drawable", getContext().getPackageName());
        imageSelect = BitmapFactory.decodeResource(getResources(), idImage);
        Bitmap resizedImage = Bitmap.createScaledBitmap(imageSelect, vaisseau.getWidth(), vaisseau.getHeight(), true);

        canvas.drawBitmap(resizedImage, ballX - vaisseau.getWidth() / 2, bally - vaisseau.getHeight() / 2, null);


        //creer les planetes
        for (int i = 0; i <= 7; i++) {


            nom = liste.get(i).getNomAstre();
            taille = liste.get(i).getTailleAstre();
            couleur = liste.get(i).getCouleurAstre();
            type = liste.get(i).isStatusAstre();
            nomImage = liste.get(i).getNomImageAstre();


            planetes[i].onDraw(canvas, nom, taille, couleur, type, nomImage);
        }


        if (cnt >= 7 && !fin) {
            Toast.makeText(mcontext, "La partie est terminee", Toast.LENGTH_LONG).show();
            fin = true;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action = event.getAction();


        int touchX = (int) event.getX();
        int touchY = (int) event.getY();


        int diffX = touchX - ballX;
        int diffY = touchY - bally;


        boolean limitL, limitR, LimitU, LimitD = false;

        switch (action) {

            case MotionEvent.ACTION_MOVE:

                ballX = touchX;
                bally = touchY;

                for (int i = 0; i < planetes.length; i++) {

                    nom = liste.get(i).getNomAstre();
                    taille = liste.get(i).getTailleAstre();
                    couleur = liste.get(i).getCouleurAstre();
                    type = liste.get(i).isStatusAstre();
                    nomImage = liste.get(i).getNomImageAstre();


                    limitL = ballX > (planetes[i].getPosX() - taille);
                    limitR = ballX < (planetes[i].getPosX() + taille);
                    LimitU = bally > (planetes[i].getPosY() - taille);
                    LimitD = bally < (planetes[i].getPosY() + taille);

                    if (limitL && limitR && LimitD && LimitU) {


                        if (planetes[i].getStatus()) {


                            planetes[i].setStatus(false, nom, couleur, taille, type);


                            cnt++;
                        }


                        System.out.println(cnt);

                    }

                }

                break;


        }
        if (diffX > 15 && diffY > 15) {

            invalidate();
        }

        return true;
    }
}


