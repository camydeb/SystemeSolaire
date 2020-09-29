package vcamydeb.teccart.systemesolaire;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class myDbAdapter {

    private Context c;
    private final String DATABASE_NAME = "SolarSystemBD";
    private myDbHelper dbHelper;
    private final int DATABASE_VERSION= 1;
    private SQLiteDatabase db;

    public myDbAdapter(Context context){
        this.c = context;
        this.dbHelper = new myDbHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void Open(){
        this.db = this.dbHelper.getWritableDatabase();
        this.db.execSQL("INSERT INTO astre VALUES ('pluton','25','jaune','false','pluton.png');");
        this.db.execSQL("INSERT INTO astre VALUES ('terre','30','bleu','true','terre.png');");
        this.db.execSQL("INSERT INTO astre VALUES ('saturne','40','jaune','false','saturne.png');");
        this.db.execSQL("INSERT INTO astre VALUES ('mars','20','rouge','false','mars.png');");
        this.db.execSQL("INSERT INTO astre VALUES ('mercure','15','rouge','false','mercure.png');");
        this.db.execSQL("INSERT INTO astre VALUES ('lune','10','bleu','false','lune.png');");
        this.db.execSQL("INSERT INTO astre VALUES ('soleil','100','jaune','false','soleil.png');");
        this.db.execSQL("INSERT INTO astre VALUES ('deathstar','20','bleu','false','deathstar.png');");
    }

    public ArrayList<AstreCeleste> selectAstres()
    {

        ArrayList<AstreCeleste> listePlanetes = new ArrayList<AstreCeleste>();

        Cursor cursor = this.db.rawQuery("select * from astre ",null);

        int nomIndex = cursor.getColumnIndex("NomAstre");
        int tailleIndex = cursor.getColumnIndex("TailleAstre");
        int couleurIndex = cursor.getColumnIndex("CouleurAstre");
        int statusIndex = cursor.getColumnIndex("StatusAstre");
        int imageIndex = cursor.getColumnIndex("NomImageAstre");



        if((cursor != null) && cursor.moveToFirst())
        {
            do{
                listePlanetes.add(new AstreCeleste(cursor.getString(nomIndex),cursor.getInt(tailleIndex),cursor.getString(couleurIndex), cursor.getInt(statusIndex)!=0,
                        cursor.getString(imageIndex)));

            }
            while(cursor.moveToNext());

        }
        return listePlanetes;
    }
}
