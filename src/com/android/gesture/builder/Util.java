package com.android.gesture.builder;

import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureStore;
import android.os.Environment;

import java.io.File;

public class Util {
    
    static File mStoreFile;
    static GestureLibrary sStore; 
    static GestureLibrary getGestureLibraries(){
        if(sStore == null){
            sStore = GestureLibraries.fromFile(getStoreFile());
            sStore.setSequenceType(GestureStore.SEQUENCE_INVARIANT);
        }
        return sStore;
    }
    
    static File getStoreFile(){
        if(mStoreFile == null){
            mStoreFile = new File(Environment.getExternalStorageDirectory(), "gestures");
        }
        return mStoreFile;
    }
}
