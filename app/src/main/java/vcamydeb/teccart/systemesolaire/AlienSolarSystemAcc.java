package vcamydeb.teccart.systemesolaire;
import android.annotation.SuppressLint;
import android.content.res.Resources;
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
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienSolarSystemAcc extends View implements SensorEventListener{

    private Random alea;
    private int ballX;
    private int bally;
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
    private ArrayList<AstreCeleste> liste;
    private Bitmap imageSelect;
    public float xVaisseau;
    public float yVaisseau;
    public float newX;
    public float newY;
    private Sensor accelerometer;
    public float diffX;
    public float diffY;




    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
    }

    public AlienSolarSystemAcc(Context context) {
        super(context);
        mcontext = context;
        fin = false;
        cnt = 0;

        float xMax;
        float yMax;
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        xVaisseau = screenW/2;
        yVaisseau = screenH/2;

        alea = new Random();
        ballX = alea.nextInt(800);
        bally = alea.nextInt(1000);


        myDbAdapter myDBAdapter = new myDbAdapter(mcontext);
        myDBAdapter.Open();
        liste = myDBAdapter.selectAstres();


        //reference au sensorManager
        SensorManager sm = (SensorManager)getContext().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        for(Sensor element:sensorList) {
            System.out.println("Sensor vendor:" + element.toString());
            System.out.println("");
        }

        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME);


        for (int i = 0; i <= 7; i++) {


            planetes temp = new planetes(mcontext);
            planetes[i] = temp;

        }


        if (cnt >= 7 && !fin) {
            Toast.makeText(mcontext, "La partie est terminee", Toast.LENGTH_LONG).show();
            fin = true;
        }
    }





    protected void onDraw(Canvas canvas) {
//         super.onDraw(canvas);


        //creer le background/////////////////////////////////////////
        space = BitmapFactory.decodeResource(getResources(), R.drawable.space);
        Bitmap resizedSpace = Bitmap.createScaledBitmap(space, screenW, screenH, true);
        canvas.drawBitmap(resizedSpace, 0, 0, null);


        //creer le vaisseau/////////////////////////////////////////

        VaisseauSpatial vaisseau = new VaisseauSpatial("vaisseau", 400, 320);

        int idImage = getResources().getIdentifier(vaisseau.getNomImage(), "drawable", getContext().getPackageName());
        imageSelect = BitmapFactory.decodeResource(getResources(), idImage);
        Bitmap petitVaisseau = Bitmap.createScaledBitmap(imageSelect, vaisseau.getWidth(), vaisseau.getHeight(), true);
        final Bitmap resizedImage = petitVaisseau;



        canvas.drawBitmap(resizedImage, xVaisseau, yVaisseau, null);





        //creer les planetes///////////////////////////////////////////
//        for (int i = 0; i <= 7; i++) {
//
//            nom = liste.get(i).getNomAstre();
//            taille = liste.get(i).getTailleAstre();
//            couleur = liste.get(i).getCouleurAstre();
//            type = liste.get(i).isStatusAstre();
//            nomImage = liste.get(i).getNomImageAstre();
//
//
//            planetes[i].onDraw(canvas, nom, taille, couleur, type, nomImage);
//        }


        if (cnt >= 7 && !fin) {
            Toast.makeText(mcontext, "La partie est terminee", Toast.LENGTH_LONG).show();
            fin = true;
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        float vectorLength;

        vectorLength = (float)Math.sqrt(Math.pow((double)(event.values[0]),2)+Math.pow((double)(event.values[1]),2)
                + Math.pow((double)(event.values[2]),2));


        newX =  xVaisseau + event.values[0] *(-1 * vectorLength);
        newY =  yVaisseau + event.values[1] * vectorLength;


        diffX = newX - xVaisseau;
        diffY = newY - yVaisseau;


            xVaisseau = newX;
            yVaisseau = newY;


        boolean limitL, limitR, LimitU, LimitD = false;

        int action = event.accuracy;


        switch (action) {

            case MotionEvent.ACTION_MOVE:


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

        if(diffX > 15 && diffY > 15){

            invalidate();
        }


    }





//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//
//        int action = event.getAction();
//
//
//        int touchX = (int) event.getX();
//        int touchY = (int) event.getY();
//
//
//        int diffX = touchX - ballX;
//        int diffY = touchY - bally;
//
//
//        boolean limitL, limitR, LimitU, LimitD = false;
//
//        switch (action) {
//
//            case MotionEvent.ACTION_MOVE:
//
//                ballX = touchX;
//                bally = touchY;
//
//                for (int i = 0; i < planetes.length; i++) {
//
//                    nom = liste.get(i).getNomAstre();
//                    taille = liste.get(i).getTailleAstre();
//                    couleur = liste.get(i).getCouleurAstre();
//                    type = liste.get(i).isStatusAstre();
//                    nomImage = liste.get(i).getNomImageAstre();
//
//
//                    limitL = ballX > (planetes[i].getPosX() - taille);
//                    limitR = ballX < (planetes[i].getPosX() + taille);
//                    LimitU = bally > (planetes[i].getPosY() - taille);
//                    LimitD = bally < (planetes[i].getPosY() + taille);
//
//                    if (limitL && limitR && LimitD && LimitU) {
//
//
//                        if (planetes[i].getStatus()) {
//
//                            planetes[i].setStatus(false, nom, couleur, taille, type);
//
//                            cnt++;
//                        }
//                        System.out.println(cnt);
//                    }
//                }
//                break;
//
//
//        }
//
//
//
//        return true;
//    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


