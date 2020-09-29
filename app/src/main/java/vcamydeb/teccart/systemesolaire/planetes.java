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

    private ArrayList<AstreCeleste> liste;
    private int posX;
    private int posY;
//    private Bitmap image;
    private Random alea;
    private int radius;
    private boolean status;
    private String image;
    private Bitmap imageSelect;
    private Context myContexte;

//    private static final int[] palette = {Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW};


    public planetes  (Context context) {
        super(context);
        myContexte = context;
        myDbAdapter myDBAdapter = new myDbAdapter(myContexte);
        myDBAdapter.Open();
        liste = myDBAdapter.selectAstres();

        status = true;
        alea = new Random();
        posX = alea.nextInt(500);
        posY = alea.nextInt(500);



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

        }
    }


    public int getPosX()
    {

        return this.posX;
    }

    public int getPosY()
    {
        return
                this.posY;
    }



    protected void onDraw(Canvas canvas) {

        for(int i = 0; i < liste.size(); i++)
        {
            image = liste.get(i).getNomImageAstre();
            radius = liste.get(i).getTailleAstre();

            int idImage= getResources().getIdentifier(image,"drawable", myContexte.getPackageName());

            imageSelect = BitmapFactory.decodeResource(getResources(),idImage);


            Bitmap resizedImage = Bitmap.createScaledBitmap(imageSelect,radius ,radius,true);


            canvas.drawBitmap(resizedImage, posX , posY, null);

        }



    }
}
