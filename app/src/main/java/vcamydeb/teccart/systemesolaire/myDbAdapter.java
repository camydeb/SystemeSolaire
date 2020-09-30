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

    }

    public void insertBD() {
        this.db = this.dbHelper.getWritableDatabase();

        this.db.execSQL("DELETE FROM 'astre';");
        this.db.execSQL("INSERT INTO astre VALUES ('pluton','350','RED','false','pluton');");
        this.db.execSQL("INSERT INTO astre VALUES ('terre','200','BLUE','true','terre');");
        this.db.execSQL("INSERT INTO astre VALUES ('saturne','250','YELLOW','false','saturne');");
        this.db.execSQL("INSERT INTO astre VALUES ('mars','225','RED','false','mars');");
        this.db.execSQL("INSERT INTO astre VALUES ('soleil','400','YELLOW','false','soleil');");
        this.db.execSQL("INSERT INTO astre VALUES ('mercure','300','RED','false','mercure');");
        this.db.execSQL("INSERT INTO astre VALUES ('lune','200','BLUE','false','lune');");
        this.db.execSQL("INSERT INTO astre VALUES ('deathstar','250','BLUE','false','deathstar');");
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
