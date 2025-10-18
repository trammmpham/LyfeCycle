package com.example.lyfecycle;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements InputFragment.OnInputListener {


    DisplayFragment displayFragment;
    InputFragment inputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d( "MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputFragment = new InputFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, inputFragment)
                .commit();

        Button sendToInputFragmentButton = findViewById(R.id.sendToInputFragmentButton);
        sendToInputFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                }
                new Handler().postDelayed(() -> {
                    if (inputFragment != null) {
                        inputFragment.updateText("Data from Activity");
                    }
                }, 100);
            }
        });

    }

    @Override
    public void sendInput(String input) {
        // When receiving data from InputFragment → replace with DisplayFragment
        displayFragment = new DisplayFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, displayFragment)
                .addToBackStack(null)
                .commit();


        // Delay to ensure the fragment view is ready
        new Handler().postDelayed(() -> {
            if (displayFragment != null) {
                displayFragment.updateText("Data from Fragment: " + input);
            }
        }, 100);
    }



    @Override
    protected void onStart() {
        Log.d( "MainActivity", "onStart");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume");
        super.onResume();
    }
    @Override
    protected void onPause() {
        Log.d("MainActivity", "onPause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d("MainActivity", "onStop");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.d("MainActivity", "onDestroy");
        super.onDestroy();
    }
}