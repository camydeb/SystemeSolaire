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
    private int screenW;
    private int screenH;
    private Context myContexte;
    private static final int[] palette = {Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW};


//    private static final int[] palette = {Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW};


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
    }

    public planetes  (Context context) {
        super(context);
        myContexte = context;
//        myDbAdapter myDBAdapter = new myDbAdapter(myContexte);
//        myDBAdapter.Open();
//        liste = myDBAdapter.selectAstres();

        status = true;
        alea = new Random();
        posX = alea.nextInt(1500);
        posY = alea.nextInt(700);

        crayon = new Paint();
        crayon.setAntiAlias(true);
//        crayon.setColor(palette[alea.nextInt(3)]);




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
            //update couleur bdd to GREEN

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


    public void onDraw(Canvas canvas, String nom, String nomImage, int taille, boolean type, String couleur) {

        radius = taille+2;

        crayon.setColor(Color.parseColor(couleur));
        int idImage= getResources().getIdentifier(nomImage,"drawable", getContext().getPackageName());


        imageSelect = BitmapFactory.decodeResource(getResources(),idImage);


        Bitmap resizedImage = Bitmap.createScaledBitmap(imageSelect,taille ,taille,true);


        canvas.drawCircle(getPosX(), getPosY(), taille/2, crayon);
        canvas.drawBitmap(resizedImage, getPosX()-100 , getPosY()-110,null);

    }
}

