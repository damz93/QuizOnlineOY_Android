package damz.quizioner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import damz.quizioner.bantuan.ConnectionDetector;
import damz.quizioner.R;

public class Act_menu_utama extends Activity implements View.OnClickListener {
    Button bt_mulai, bt_tentang, bt_gant_pas, bt_keluar, bt_produk;
    Intent buka;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    private AdView JadVi;
    private InterstitialAd intersialADDDD;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_menu_utama);
        bt_mulai = findViewById(R.id.btn_m_quiz);
        bt_tentang = findViewById(R.id.btn_ttg_aplikasi);
        bt_gant_pas =findViewById(R.id.btn_ganti_pasw);
        bt_keluar = findViewById(R.id.btn_keluar);
        bt_produk = findViewById(R.id.btn_listProduk);
        bt_mulai.setOnClickListener(this);
        bt_tentang.setOnClickListener(this);
        bt_gant_pas.setOnClickListener(this);
        bt_keluar.setOnClickListener(this);
        bt_produk.setOnClickListener(this);


//all iklan
        JadVi = findViewById(R.id.adview);
        JadVi.loadAd(new AdRequest.Builder().build());

//menyiapkan iklan untuk intersial ID
        intersialADDDD = new InterstitialAd(Act_menu_utama.this);
//masukkan id iklan
        intersialADDDD.setAdUnitId(getString(R.string.id_iklanyaaa));
        AdRequest adreq = new AdRequest.Builder().build();

        //muat iklan interstiasal
        intersialADDDD.loadAd(adreq);

        //persiapan iklan interstial
        intersialADDDD.setAdListener(new AdListener(){
            public void onAdLoaded(){
                displayI();
            }
        });

    }

    @SuppressLint("ResourceType")
    @Override
        public void onClick(View v) {
            cd = new ConnectionDetector(getApplicationContext());
            isInternetPresent = cd.isConnectingToInternet();
        if(v == bt_mulai){
            final PopupMenu menu_quiz = new PopupMenu(Act_menu_utama.this, bt_mulai);
            try {
                menu_quiz.getMenuInflater().inflate(R.layout.popmenu_mulai, menu_quiz.getMenu());
                menu_quiz.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        if (title.equals("Quiz")) {
                            if (isInternetPresent) {
                                buka = new Intent(Act_menu_utama.this, Act_kuiz.class);
                                finish();
                                startActivity(buka);
                            } else {
                                inet_mati();
                            }
                        } else if (title.equals("Quizioner")) {
                            if (isInternetPresent) {
                                buka = new Intent(Act_menu_utama.this, Act_kuesioner.class);
                                finish();
                                startActivity(buka);
                            } else {
                                inet_mati();
                            }
                        }
                        return true;
                    }
                });

                menu_quiz.show();
            }
            catch (Exception e){
                Toast.makeText(Act_menu_utama.this,"Errornya: "+e,Toast.LENGTH_LONG).show();
            }
        }
        else if(v == bt_tentang){
            buka = new Intent(Act_menu_utama.this, Act_tentang.class);
            finish();
            startActivity(buka);
        }
        else if(v == bt_gant_pas){
            buka = new Intent(Act_menu_utama.this, Act_pengaturan.class);
            finish();
            startActivity(buka);
        }
        else if(v == bt_keluar){
            onBackPressed();
        }
        else if(v == bt_produk) {
            buka = new Intent(Act_menu_utama.this, Act_telkomsel.class);
            finish();
            startActivity(buka);
        }

    }
    public void inet_mati () {
        AlertDialog damz_dialog = new AlertDialog.Builder(Act_menu_utama.this).create();
        damz_dialog.setTitle("Warning");
        damz_dialog.setIcon(R.drawable.warning);
        damz_dialog.setMessage("Please Activate Your Connection Internet...");
        damz_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        damz_dialog.show();
    }
    public void onBackPressed(){
        DialogInterface.OnClickListener dd_dialog = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        dd_kembali();

                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you Sure?").setPositiveButton("No", dd_dialog).setNegativeButton("Yes", dd_dialog).show();
    }
    public void dd_kembali(){
        Intent n = new Intent(Act_menu_utama.this, Act_login.class);
        finish();
        startActivity(n);
    }

    public void displayI(){
        if (intersialADDDD.isLoaded()){
            intersialADDDD.show();
        }
    }
}