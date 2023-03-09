package damz.quizioner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;

import damz.quizioner.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Act_telkomsel extends Activity implements View.OnClickListener{
    Button bt_loop, bt_as, bt_halo, bt_simpati, bt_kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_telkomsel);
        bt_as = findViewById(R.id.btn_as);
        bt_loop = findViewById(R.id.btn_loop);
        bt_halo = findViewById(R.id.btn_halo);
        bt_simpati = findViewById(R.id.btn_simpati);
        bt_kembali = findViewById(R.id.btn_ok_telkomsel);
        bt_as.setOnClickListener(this);
        bt_loop.setOnClickListener(this);
        bt_halo.setOnClickListener(this);
        bt_simpati.setOnClickListener(this);
        bt_kembali.setOnClickListener(this);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        if(v == bt_kembali){
            onBackPressed();
        }
        else if (v == bt_as){
            try{
                final PopupMenu menu_produk = new PopupMenu(Act_telkomsel.this, bt_as);
                menu_produk.getMenuInflater().inflate(R.layout.popmenu_info, menu_produk.getMenu());
                menu_produk.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mnpaket:
                                Intent intent = new Intent(getApplicationContext(), Act_daftar_paket_as.class);
                                startActivity(intent);
                                return true;
                            case R.id.mninfotarif:
                                Intent intenzt = new Intent(getApplicationContext(), Act_tampil_list_tarif.class);
                                intenzt.putExtra("link", "http://telkomsel.com/kartu-as?utm_source=tsel-portal&utm_medium=home_page&utm_campaign=kartuas&utm_content=main_menu");
                                intenzt.putExtra("caption", " Info Tarif Paket kartu AS ");
                                startActivity(intenzt);
                                return true;
                            default:
                                return Act_telkomsel.super.onOptionsItemSelected(item);
                        }
                    }
                });
                menu_produk.show();}
            catch (Exception e){
            }
        }
        else if (v == bt_loop){
            try{
                final PopupMenu menu_produk = new PopupMenu(Act_telkomsel.this, bt_loop);
                menu_produk.getMenuInflater().inflate(R.layout.popmenu_info, menu_produk.getMenu());
                menu_produk.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mnpaket:
                                Intent intent = new Intent(getApplicationContext(), Act_daftar_paket.class);
                                startActivity(intent);
                                return true;
                            case R.id.mninfotarif:
                                Intent intenta = new Intent(getApplicationContext(), Act_info_tarif.class);
                                startActivity(intenta);
                                return true;
                            default:
                                return Act_telkomsel.super.onOptionsItemSelected(item);
                        }
                    }
                });
                menu_produk.show();}
            catch (Exception e){
            }
        }
        else if (v == bt_halo){
            try{
            final PopupMenu menu_produk = new PopupMenu(Act_telkomsel.this, bt_halo);
            menu_produk.getMenuInflater().inflate(R.layout.popmenu_info, menu_produk.getMenu());
            menu_produk.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.mnpaket:
                            Intent intent = new Intent(getApplicationContext(), Act_daftar_paket_halo.class);
                            startActivity(intent);
                            return true;
                        case R.id.mninfotarif:
                            Intent intenzt = new Intent(getApplicationContext(), Act_tampil_list_tarif.class);
                            intenzt.putExtra("link", "http://www.telkomsel.com/kartuhalo");
                            intenzt.putExtra("caption", " Info Tarif Paket kartu HALO ");
                            startActivity(intenzt);
                            return true;
                        default:
                            return Act_telkomsel.super.onOptionsItemSelected(item);
                    }
                }
            });
            menu_produk.show();}
            catch (Exception e){
            }

        }
        else if (v == bt_simpati){
            try{
                final PopupMenu menu_produk = new PopupMenu(Act_telkomsel.this, bt_simpati);
                menu_produk.getMenuInflater().inflate(R.layout.popmenu_info, menu_produk.getMenu());
                menu_produk.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mnpaket:
                                Intent intent = new Intent(getApplicationContext(), Act_daftar_paket_simpati.class);
                                startActivity(intent);
                                return true;
                            case R.id.mninfotarif:
                                Intent intenzt = new Intent(getApplicationContext(), Act_tampil_list_tarif.class);
                                intenzt.putExtra("link", "https://www.telkomsel.com/simpati?utm_source=tsel-portal&utm_medium=home_page&utm_campaign=simpati&utm_content=main_menu");
                                intenzt.putExtra("caption", " Info Tarif Paket simPATI ");
                                startActivity(intenzt);
                                return true;
                            default:
                                return Act_telkomsel.super.onOptionsItemSelected(item);
                        }
                    }
                });
                menu_produk.show();}
            catch (Exception e){
            }
        }

    }
    private void under(){
        AlertDialog damz_dialog = new AlertDialog.Builder(Act_telkomsel.this).create();
        damz_dialog.setTitle("Information");
        damz_dialog.setIcon(R.drawable.information);
        damz_dialog.setMessage("Under Construction");
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
        Intent i = new Intent(Act_telkomsel.this,Act_menu_utama.class);
        finish();
        startActivity(i);
    }
}