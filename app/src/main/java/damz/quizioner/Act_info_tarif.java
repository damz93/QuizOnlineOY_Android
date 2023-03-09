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

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Act_info_tarif extends Activity implements View.OnClickListener{
    Button bt_kmbli, bt_tarif_telp, bt_tarif_paket;
    final String st_tarif_telp = "http://loop.co.id/tarif";
    final String st_tarif_paket = "http://loop.co.id/info-tarif";
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
@Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.lay_info_tarif);

            bt_kmbli = findViewById(R.id.btn_ok_info_tarif);
            bt_tarif_telp = findViewById(R.id.btn_tarif_telp_sms);
            bt_tarif_paket= findViewById(R.id.btn_tarif_paket);

            bt_kmbli.setOnClickListener(this);
            bt_tarif_telp.setOnClickListener(this);
            bt_tarif_paket.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if(view == bt_kmbli){
            onBackPressed();
        }
        if (isInternetPresent) {
            if (view == bt_tarif_telp) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_tarif.class);
                intent.putExtra("link", st_tarif_telp);
                intent.putExtra("caption", " Tarif Telepon, SMS, dan Internet ");
                startActivity(intent);
            } else if (view == bt_tarif_paket) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_tarif.class);
                intent.putExtra("link", st_tarif_paket);
                intent.putExtra("caption", " Tarif Paket Loop ");
                startActivity(intent);
            }
        }
        else{
            inet_mati();
        }
    }
    public void inet_mati () {
        AlertDialog damz_dialog = new AlertDialog.Builder(Act_info_tarif.this).create();
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
        Intent i = new Intent(Act_info_tarif.this,Act_telkomsel.class);
        finish();
        startActivity(i);
    }
}