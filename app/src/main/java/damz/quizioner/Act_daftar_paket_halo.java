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

public class Act_daftar_paket_halo extends Activity implements View.OnClickListener {
    Button bt_kmbli;
    final String st_bulananhalo = "http://telkomsel.com/paket-kartuhalo";
    final String st_tambahanhalo = "http://my.telkomsel.com/?routeTo=%252Fapp%252Fpackages&search=%253F_ga%253D2.94773596.1360096475.1524718654-113927064.1519955249";
    final String st_kickhalo = "http://telkomsel.com/halo-kick";
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    Button bt_bulanan, bt_tambahan, bt_kick;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_daftar_paket_halo);


        bt_kmbli = findViewById(R.id.btn_ok_dftr_produkhalo);

        bt_bulanan = findViewById(R.id.btn_halo_bulanan);
        bt_tambahan = findViewById(R.id.btn_halo_tambahan);
        bt_kick = findViewById(R.id.btn_halo_kick);
        bt_kmbli.setOnClickListener(this);
        bt_bulanan.setOnClickListener(this);
        bt_tambahan.setOnClickListener(this);
        bt_kick.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if (v == bt_kmbli) {
            onBackPressed();
        }
        if (isInternetPresent) {
            if (v == bt_bulanan) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_bulananhalo);
                intent.putExtra("caption", " Paket Bulanan HALO ");
                startActivity(intent);
            }
            else if (v == bt_tambahan) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_tambahanhalo);
                intent.putExtra("caption", " Paket Tambahan HALO ");
                startActivity(intent);
            }
            else if (v == bt_kick) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_kickhalo);
                intent.putExtra("caption", " Halo Kick HALO ");
                startActivity(intent);
            }
        } else {
            inet_mati();
        }

    }
    public void inet_mati() {
        AlertDialog damz_dialog = new AlertDialog.Builder(Act_daftar_paket_halo.this).create();
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
        Intent i = new Intent(Act_daftar_paket_halo.this,Act_telkomsel.class);
        finish();
        startActivity(i);
    }
}