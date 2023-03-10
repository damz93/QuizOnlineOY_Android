package damz.quizioner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import damz.quizioner.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Act_splash extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    /** Called when the activity is first created. */
    @
            Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        /* layout splashscreen dengan background gambar */
        try{
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.lay_splash);
            new Handler().postDelayed(new Runnable() {@
                                              Override
                                      public void run() {
                                          Intent mainIntent = null;

                                          mainIntent = new Intent(Act_splash.this, Act_login.class);

                                          Act_splash.this.startActivity(mainIntent);
                                          Act_splash.this.finish();
                                      }
                                      },
                    SPLASH_DISPLAY_LENGTH);
        }
        catch (Exception e){
            //           System.out.print("ini errornya boskuu: "+e);
            Log.d("a","Errornya ces "+e);
        }

    }

}