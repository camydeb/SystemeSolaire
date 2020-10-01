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
        this.db.execSQL("INSERT INTO astre VALUES ('pluton','150','RED','0','pluton');");
        this.db.execSQL("INSERT INTO astre VALUES ('terre','80','BLUE','1','terre');");
        this.db.execSQL("INSERT INTO astre VALUES ('saturne','120','YELLOW','0','saturne');");
        this.db.execSQL("INSERT INTO astre VALUES ('mars','70','RED','0','mars');");
        this.db.execSQL("INSERT INTO astre VALUES ('soleil','220','BLUE','0','soleil');");
        this.db.execSQL("INSERT INTO astre VALUES ('mercure','200','RED','0','mercure');");
        this.db.execSQL("INSERT INTO astre VALUES ('lune','100','BLUE','0','lune');");
        this.db.execSQL("INSERT INTO astre VALUES ('deathstar','140','BLUE','1','deathstar');");
    }


    public void UpdateCouleur( String nom, String couleur) {

        this.db.execSQL("UPDATE astre SET  CouleurAstre = '"+"''GREEN''"+"'WHERE NomAstre ='"+nom+"';");
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
                listePlanetes.add(new AstreCeleste(cursor.getString(nomIndex),cursor.getInt(tailleIndex),cursor.getString(couleurIndex), cursor.getInt(statusIndex),
                        cursor.getString(imageIndex)));

            }
            while(cursor.moveToNext());

        }
        return listePlanetes;
    }


}
