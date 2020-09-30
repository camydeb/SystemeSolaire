package vcamydeb.teccart.systemesolaire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

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

    private Context myContexte;
    private static final int[] palette = {Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW};


//    private static final int[] palette = {Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW};


    public planetes  (Context context) {
        super(context);
        myContexte = context;
//        myDbAdapter myDBAdapter = new myDbAdapter(myContexte);
//        myDBAdapter.Open();
//        liste = myDBAdapter.selectAstres();

        status = true;
        alea = new Random();
        posX = alea.nextInt(500);
        posY = alea.nextInt(500);

        crayon = new Paint();
        crayon.setAntiAlias(true);
        crayon.setColor(palette[alea.nextInt(3)]);

        radius = 30;


    }

    public boolean getStatus()
    {
        return this.status;
    }



    public void setStatus(Boolean state)
    {
        this.status = state;

        if (!this.status)
        {
            crayon.setColor(Color.GREEN);

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

//    protected void onDraw(Canvas canvas) {
//

//
//    }

    public void onDraw(Canvas canvas, String nom, String nomImage, int taille, boolean type, String couleur) {
//        vaisseau = BitmapFactory.decodeResource(getResources(),R.drawable.vaisseau);
//        Bitmap resizedVaisseau = Bitmap.createScaledBitmap(vaisseau,300 ,400,true);
//        canvas.drawBitmap(resizedVaisseau, ballX, bally, null);

//
        int idImage= getResources().getIdentifier(nomImage,"drawable", getContext().getPackageName());

//
        imageSelect = BitmapFactory.decodeResource(getResources(),idImage);


        Bitmap resizedImage = Bitmap.createScaledBitmap(imageSelect,taille ,taille,true);
//
//
        canvas.drawBitmap(resizedImage, posX , posY, null);
        canvas.drawCircle(posX, posY, taille+20, crayon);

    }
}

