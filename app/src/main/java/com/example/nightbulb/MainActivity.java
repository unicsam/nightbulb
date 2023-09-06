package com.example.nightbulb;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private float initialBrightness = 1.0f; // Initial brightness value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View mainLayout = findViewById(R.id.mainLayout);

        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Save initial brightness
                        initialBrightness = getWindow().getAttributes().screenBrightness;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Calculate new brightness based on Y coordinate
                        float newBrightness = event.getY() / v.getHeight();
                        if (newBrightness > 1.0f) {
                            newBrightness = 1.0f;
                        } else if (newBrightness < 0.1f) {
                            newBrightness = 0.1f;
                        }

                        // Apply the new brightness
                        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                        layoutParams.screenBrightness = newBrightness;
                        getWindow().setAttributes(layoutParams);
                        break;
                }
                return true;
            }
        });
    }
}
