package vcamydeb.teccart.systemesolaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



public class game_on_acc extends AppCompatActivity {

    private AlienSolarSystemAcc AlienSolarSystemAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AlienSolarSystemAcc = new AlienSolarSystemAcc(this);
        setContentView(AlienSolarSystemAcc);


    }
}

