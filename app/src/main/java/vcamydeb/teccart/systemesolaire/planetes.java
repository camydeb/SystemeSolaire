package vcamydeb.teccart.systemesolaire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    private static final int[] palette = {Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW};


//    private static final int[] palette = {Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW};






    public planetes  (Context context) {
        super(context);
        myContexte = context;


        status = true;
        alea = new Random();
        posX = alea.nextInt(1000);
        posY = alea.nextInt(1500);

        crayon = new Paint();
        crayon.setAntiAlias(true);
//        crayon.setColor(Color.parseColor(couleur));




    }

    public boolean getStatus(Boolean type)
    {

        return this.status;
    }



    public void setStatus(Boolean state, String nom, int taille, Boolean type)
    {
        this.status = state;

        if (!this.status)
        {

            Toast.makeText(myContexte, "Nom : "+nom+"   Taille: "+taille+"", Toast.LENGTH_SHORT).show();


            if(type == true)
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


    public void onDraw(Canvas canvas, String nom, int taille, String couleur, boolean type,String nomImage) {

        radius = (taille/2)+10;
        int x = getPosX();
        int y = getPosY();

        crayon.setColor(Color.parseColor(couleur));
        int idImage= getResources().getIdentifier(nomImage,"drawable", getContext().getPackageName());


        imageSelect = BitmapFactory.decodeResource(getResources(),idImage);


        Bitmap resizedImage = Bitmap.createScaledBitmap(imageSelect,taille ,taille,true);


        canvas.drawCircle(x, y, radius, crayon);

        canvas.drawBitmap(resizedImage, x-taille/2 , y-taille/2,null);
    }
}

