package com.app.cg.recipe.data;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.app.cg.recipe.R;

public class Tools {
    private static float getAPIVersion() {

        Float f = null;
        try {
            StringBuilder strBuild = new StringBuilder();
            strBuild.append ( android.os.Build.VERSION.RELEASE );
            f = Float.valueOf ( strBuild.toString () );
            System.out.println ( "android.os.build.VERSION" + f );
        } catch (NumberFormatException e) {
            Log.e ( "", "Error retrieving API" + e.getMessage () );
        }

        return f;
    }

    public static void systemBarLolipop(Activity act){
        if (getAPIVersion() >= 5.0) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}
