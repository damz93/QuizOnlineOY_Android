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

public class Act_daftar_paket extends Activity implements View.OnClickListener{
    Button bt_kmbli;
    final String st_inharian = "http://loop.co.id/belipaket";
    final String st_inmingguan = "http://loop.co.id/belipaket";
    final String st_inbulanan = "http://loop.co.id/belipaket";
    final String st_loopomb = "http://loop.co.id/belipaket";
    final String st_loopcash = "http://loop.co.id/loopcash";
    final String st_loopgame = "http://loop.co.id/game";
    final String st_loopmusik = "http://loop.co.id/musik";
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    Button bt_inharian, bt_inmingguan, bt_inbulanan, bt_loopcomb, bt_loopcash, bt_loopgame, bt_loopmusik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_daftar_paket);

        //Uri uri = Uri.parse("https://www.example.com");
        //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //startActivity(intent);
        bt_kmbli = findViewById(R.id.btn_ok_dftr_produk);

        bt_inharian = findViewById(R.id.btn_inet_harian);
        bt_inmingguan = findViewById(R.id.btn_inet_minggu);
        bt_inbulanan = findViewById(R.id.btn_inet_bulan);
        bt_loopcomb = findViewById(R.id.btn_loop_combo);
        bt_loopcash = findViewById(R.id.btn_loop_cash);
        bt_loopgame= findViewById(R.id.btn_loop_game);
        bt_loopmusik= findViewById(R.id.btn_loop_musik);

        bt_kmbli.setOnClickListener(this);
        bt_inharian.setOnClickListener(this);
        bt_inmingguan.setOnClickListener(this);
        bt_inbulanan.setOnClickListener(this);
        bt_loopcomb.setOnClickListener(this);
        bt_loopcash.setOnClickListener(this);
        bt_loopgame.setOnClickListener(this);
        bt_loopmusik.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //Intent intent = new Intent(getApplicationContext(),Act_tampil_list_produk.class);
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if (view == bt_kmbli){
            onBackPressed();
        }
        if (isInternetPresent) {
            if (view == bt_inharian) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_inharian);
                intent.putExtra("caption", " Paket Harian LOOP ");
                startActivity(intent);


            } else if (view == bt_inmingguan) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_inmingguan);
                intent.putExtra("caption", " Paket Mingguan LOOP ");
                startActivity(intent);
            } else if (view == bt_inbulanan) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_inbulanan);
                intent.putExtra("caption", " Paket Bulanan LOOP ");
                startActivity(intent);
            } else if (view == bt_loopcomb) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_loopomb);
                intent.putExtra("caption", " Paket LOOP Combo ");
                startActivity(intent);
            } else if (view == bt_loopcash) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_loopcash);
                intent.putExtra("caption", " Paket LOOP Cash ");
                startActivity(intent);
            } else if (view == bt_loopgame) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_loopgame);
                intent.putExtra("caption", " Paket LOOP Game ");
                startActivity(intent);
            } else if (view == bt_loopmusik) {
                Intent intent = new Intent(getApplicationContext(), Act_tampil_list_paket.class);
                intent.putExtra("link", st_loopmusik);
                intent.putExtra("caption", " Paket LOOP Musik ");
                startActivity(intent);

            }
        } else{
            inet_mati();
        }
    }
    public void inet_mati () {
        AlertDialog damz_dialog = new AlertDialog.Builder(Act_daftar_paket.this).create();
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
        Intent i = new Intent(Act_daftar_paket.this,Act_telkomsel.class);
        finish();
        startActivity(i);
    }
}