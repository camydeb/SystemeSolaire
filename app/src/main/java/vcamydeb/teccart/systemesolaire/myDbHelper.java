package vcamydeb.teccart.systemesolaire;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDbHelper extends SQLiteOpenHelper {
    public myDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS astre( NomAstre varchar(50), TailleAstre int, CouleurAstre varchar(50), StatusAstre boolean, NomImageAstre varchar(50));";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS astre;";
        db.execSQL(query);
        onCreate(db);
    }
}
