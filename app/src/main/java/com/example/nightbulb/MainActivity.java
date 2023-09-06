package com.example.nightbulb;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
    private float initialBrightness = 1.0f; // Initial brightness value

    ProgressBar brightnessProgressBar; // Declare the ProgressBar variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brightnessProgressBar = findViewById(R.id.brightnessProgressBar);


        final View mainLayout = findViewById(R.id.mainLayout);

        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Save initial brightness
                        initialBrightness = getWindow().getAttributes().screenBrightness;
                        brightnessProgressBar.setVisibility(View.VISIBLE); //// Show the ProgressBar when touched
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Calculate new brightness based on Y coordinate
                        float newBrightness = event.getY() / v.getHeight();
                        if (newBrightness > 1.0f) {
                            newBrightness = 1.0f;
                        } else if (newBrightness < 0.0f) {
                            newBrightness = 0.0f;
                        }

                        // Update the ProgressBar
                        int progress = (int) (newBrightness * 100); // Scale to 0-100range
                        brightnessProgressBar.setProgress(progress);

                        Log.d("Brightness", "Progress: " + progress);


                        // Apply the new brightness
                        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                        layoutParams.screenBrightness = newBrightness;
                        getWindow().setAttributes(layoutParams);

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:

                        // Hide the ProgressBar when touch event ends
                        try {
                            Thread.sleep(300); // Sleep for 0.3 second (300 milliseconds)
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        brightnessProgressBar.setVisibility(View.GONE);
                        break;
                }
                return true;
            }
        });
    }
}
