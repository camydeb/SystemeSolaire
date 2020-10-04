package vcamydeb.teccart.systemesolaire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

public class GameOn extends AppCompatActivity {

    private AlienSolarSystem AlienSolarSystem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int hPix = metrics.heightPixels;
        int wPix = metrics.widthPixels;

        AlienSolarSystem = new AlienSolarSystem(this);






        setContentView(AlienSolarSystem);


    }
}