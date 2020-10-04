package vcamydeb.teccart.systemesolaire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class planetes extends View {

    private int posX;
    private int posY;
    private Random alea;
    private boolean status;
    private Paint crayon;
    private Color pinceau;
    private String image;
    private Bitmap imageSelect;
    private int radius;
    private int screenW;
    private int screenH;
    private Context myContexte;
    private ArrayList<AstreCeleste> liste;

//    private static final String[] palette = new String[8];


    public planetes  (Context context) {
        super(context);
        myContexte = context;


        status = true;
        alea = new Random();
        posX = alea.nextInt(1000);
        posY = alea.nextInt(1500);

        crayon = new Paint();
        crayon.setAntiAlias(true);


//        myDbAdapter myDBAdapter = new myDbAdapter(context);
//        myDBAdapter.Open();
//        liste =  myDBAdapter.selectAstres();

    }



    public boolean getStatus()
    {

        return this.status;
    }



    public void setStatus(Boolean state, String nom, String couleur, int taille, int type)
    {
        this.status = state;

        if (!this.status)
        {
            Toast.makeText(myContexte, "Nom : "+nom+"   Taille: "+taille+"", Toast.LENGTH_SHORT).show();

            if(type == 1)
            {
                crayon.setColor(Color.GREEN);

            }
        }
    }


    public int getPosX()
    {

        return this.posX;
    }

    public int getPosY()
    {
        return this.posY;
    }


    public void onDraw(Canvas canvas, String nom, int taille, String couleur, int type,String nomImage) {

        radius = (taille/2)+10;
        int x = getPosX();
        int y = getPosY();

        int idImage= getResources().getIdentifier(nomImage,"drawable", getContext().getPackageName());



        imageSelect = BitmapFactory.decodeResource(getResources(),idImage);



        Bitmap resizedImage = Bitmap.createScaledBitmap(imageSelect,taille ,taille,true);

        if(this.status)
        {
            crayon.setColor(Color.parseColor(couleur));
        }

        canvas.drawCircle(x, y, radius, crayon );

        canvas.drawBitmap(resizedImage, x-taille/2 , y-taille/2,null);
    }
}

