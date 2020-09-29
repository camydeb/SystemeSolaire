package vcamydeb.teccart.systemesolaire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameOn extends AppCompatActivity {

    private AlienSolarSystem AlienSolarSystem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlienSolarSystem = new AlienSolarSystem(this);





        setContentView(AlienSolarSystem);


    }
}