
package com.android.gesture.builder;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;

public class GestureCheckActivity extends Activity implements OnGesturePerformedListener {

    //static final float LENGTH_THRESHOLD = 120.0f;

    static final String TAG = "GestureCheckActivity";

    static GestureLibrary mStore;

    static final int MENU_ID_LIST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_gesture);
        mStore = Util.getGestureLibraries();
        GestureOverlayView overlay = (GestureOverlayView) findViewById(R.id.gestures_overlay);
        overlay.addOnGesturePerformedListener(this);
        //overlay.addOnGestureListener(new GesturesProcessor());
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        Log.d(TAG, "onGesturePerformed:");
        ArrayList<Prediction> predictions = mStore.recognize(gesture);
        for (Prediction itPrediction : predictions) {
            Log.d(TAG, "itPrediction.name:" + itPrediction.name + ",itPrediction.score:"
                    + itPrediction.score);
        }
        // We want at least one prediction
        if (predictions.size() > 0) {
            Prediction prediction = predictions.get(0);
            // We want at least some confidence in the result
            if (prediction.score > 1.0) {
                // Show the spell
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean rt = super.onCreateOptionsMenu(menu);
        menu.add(R.string.gestures_list);
        return rt;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ID_LIST:
                Intent intent = new Intent(this, GestureBuilderActivity.class);
                this.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
/***
    private class GesturesProcessor implements GestureOverlayView.OnGestureListener {
        Gesture mGesture;

        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
            Log.d(TAG, "onGestureStarted");
            mGesture = null;
        }

        public void onGesture(GestureOverlayView overlay, MotionEvent event) {
            Log.d(TAG, "onGesture");
        }

        public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
            mGesture = overlay.getGesture();
            Log.d(TAG, "onGestureEnded:" + mGesture);
            if (mGesture.getLength() < LENGTH_THRESHOLD) {
                overlay.clear(false);
            }
        }

        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
        }
    }
    ***/
}
