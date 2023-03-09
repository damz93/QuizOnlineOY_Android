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

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Act_daftar_paket_simpati extends Activity implements View.OnClickListener {
    Button bt_kmbli;
    final String st_perdanasimp = "http://www.telkomsel.com/paket-perdana-simpati";
    final String st_combosimp = "http://www.telkomsel.com/simpati-combo";
    final String st_infopaket = "http://www.telkomsel.com/paket-simpati";
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    Button bt_perdanasimp, bt_combosimp, bt_infopaket;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_daftar_paket_simpati);

        bt_kmbli = findViewById(R.id.btn_ok_dftr_produksimp);

        bt_perdanasimp = findViewById(R.id.btn_pket_perdansimp);
        bt_combosimp = findViewById(R.id.btn_pket_combsimp);
        bt_infopaket = findViewById(R.id.btn_pket_infopketsimp);
        bt_kmbli.setOnClickListener(this);
        bt_perdanasimp.setOnClickListener(this);
        bt_combosimp.setOnClickListener(this);
        bt_infopaket.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if (v == bt_kmbli) {
            onBackPressed();
        }if (isInternetPresent) {
            if (v == bt_perdanasimp) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_perdanasimp);
                intent.putExtra("caption", " Paket Perdana simPATI ");
                startActivity(intent);
            }
            else if (v == bt_combosimp) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_combosimp);
                intent.putExtra("caption", " Paket simPATI combo ");
                startActivity(intent);
            }
            else if (v == bt_infopaket) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_infopaket);
                intent.putExtra("caption", " Info Paket simPATI ");
                startActivity(intent);
            }
        } else {
            inet_mati();
        }

    }
    public void inet_mati() {
        AlertDialog damz_dialog = new AlertDialog.Builder(Act_daftar_paket_simpati.this).create();
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
        Intent i = new Intent(Act_daftar_paket_simpati.this,Act_telkomsel.class);
        finish();
        startActivity(i);
    }
}