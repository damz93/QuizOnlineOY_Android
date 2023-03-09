package damz.quizioner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import damz.quizioner.bantuan.ConnectionDetector;
import damz.quizioner.R;

public class Act_daftar_paket_as extends Activity implements View.OnClickListener {
    Button bt_kmbli;
    final String st_perdanas = "http://telkomsel.com/kartu-perdana-kartu";
    final String st_combo = "http://telkomsel.com/kartu-combo";
    final String st_harga = "http://telkomsel.com/kartu-packages-0";
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    Button bt_perdanas, bt_combo, bt_harga;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_daftar_paket_as);

        bt_kmbli = findViewById(R.id.btn_ok_dftr_produkas);

        bt_perdanas = findViewById(R.id.btn_kartu_as_perdanaas);
        bt_combo = findViewById(R.id.btn_paket_comboas);
        bt_harga = findViewById(R.id.btn_harga_paketas);
        bt_kmbli.setOnClickListener(this);
        bt_perdanas.setOnClickListener(this);
        bt_combo.setOnClickListener(this);
        bt_harga.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if (v == bt_kmbli) {
            onBackPressed();
        }
        if (isInternetPresent) {
             if (v == bt_perdanas) {
                 Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                 intent.putExtra("link", st_perdanas);
                 intent.putExtra("caption", " Kartu As Perdana ");
                 startActivity(intent);
            }
            else if (v == bt_combo) {
                 Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                 intent.putExtra("link", st_combo);
                 intent.putExtra("caption", " Paket Combo kartu As ");
                 startActivity(intent);
            }
            else if (v == bt_harga) {
                 Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                 intent.putExtra("link", st_harga);
                 intent.putExtra("caption", " Harga Paket kartu As ");
                 startActivity(intent);
            }
        } else {
            inet_mati();
        }
    }

    public void inet_mati() {
        AlertDialog damz_dialog = new AlertDialog.Builder(Act_daftar_paket_as.this).create();
        damz_dialog.setTitle("Warning");
        damz_dialog.setIcon(R.drawable.warning);
        damz_dialog.setMessage("Please Activate Your Internet...");
        damz_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        damz_dialog.show();
    }

    public void onBackPressed(){
        Intent i = new Intent(Act_daftar_paket_as.this,Act_telkomsel.class);
        finish();
        startActivity(i);
    }
}