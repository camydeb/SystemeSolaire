package vcamydeb.teccart.systemesolaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartScreen startScreen = new StartScreen(this);
        //startScreen.setKeepScreenOn(true);




        setContentView(startScreen);
    }
}