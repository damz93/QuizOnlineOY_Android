package damz.quizioner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import damz.quizioner.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Act_tampil_list_tarif extends Activity {
    private WebView webView;
    TextView tx_caption;
    private AdView JadVi;
    private InterstitialAd intersialADDDD;
    Button bt_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_tampil_list_tarif);
        Bundle b = getIntent().getExtras();
        tx_caption = findViewById(R.id.tx_tampillistarif);
        bt_back = findViewById(R.id.btn_oktampil_list_tarif);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String URLz = b.getString("link");
        String Caption = b.getString("caption");
        tx_caption.setText(Caption);

        webView = (WebView) findViewById(R.id.wb_tampilistproduk);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URLz);

        //all iklan
        JadVi = findViewById(R.id.adview2);
        JadVi.loadAd(new AdRequest.Builder().build());

//menyiapkan iklan untuk intersial ID
        intersialADDDD = new InterstitialAd(Act_tampil_list_tarif.this);
//masukkan id iklan
        intersialADDDD.setAdUnitId(getString(R.string.id_iklanyaaa));
        AdRequest adreq = new AdRequest.Builder().build();

        //muat iklan interstiasal
        intersialADDDD.loadAd(adreq);

        //persiapan iklan interstial
        intersialADDDD.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayI();
            }
        });

    }

    public void displayI() {
        if (intersialADDDD.isLoaded()) {
            intersialADDDD.show();
        }
    }
}