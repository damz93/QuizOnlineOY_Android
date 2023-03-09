package damz.quizioner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import damz.quizioner.bantuan.JSONParser;
import damz.quizioner.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Act_produk  extends Activity {
    private static String url_produk = "http://own-youth.com/damz_quiz/damz_produk.php";
    Button bt_ok;
    JSONArray contacts = null;
    String url;
    Integer ke_produk;
    String stnm_produk, stktrngan, stcara_prduk, st_aktif;
    ProgressDialog damz_dialog = null;
    ListView lv_produk;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_list2);
        lv_produk = findViewById(R.id.list_produk);
        new AmbilData().execute();
        bt_ok = findViewById(R.id.btn_ok_produk);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Act_produk.this, Act_menu_utama.class);
                finish();
                startActivity(i);
            }
        });

    }

    public class AmbilData extends AsyncTask < String, String, String > {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

        @
                Override
        protected void onPreExecute() {
            super.onPreExecute();
            damz_dialog = new ProgressDialog(Act_produk.this);
            damz_dialog.setMessage("Loading Data ...");
            damz_dialog.setIndeterminate(false);
            damz_dialog.setCancelable(false);
            damz_dialog.show();
        }

        @
                Override
        protected String doInBackground(String... arg0) {
//            url_produk;

            JSONParser jParser = new JSONParser();
            int i;
            JSONObject json = jParser.ambilURL(url_produk);

            try {
                contacts = json.getJSONArray("PRODUKZ");
                for (i = 0; i < contacts.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject c = contacts.getJSONObject(i);
                    stnm_produk = c.getString("NAMA_PRODUK").trim();
                    stktrngan= c.getString("KETERANGAN").trim();
                    stcara_prduk = c.getString("CARA").trim();
                    map.put("s_stnm_produk", stnm_produk);
                    map.put("s_stktrngan", stktrngan);
                    map.put("s_stcara_prduk", stcara_prduk);
                    int ke = i+1;
                    ke_produk = ke;
                    map.put("ke", String.valueOf(ke_produk));

                    contactList.add(map);
                }
            } catch (JSONException e) {

            }
            if (contacts.length() == 0) {
                AlertDialog damz_dialog = new AlertDialog.Builder(Act_produk.this).create();
                damz_dialog.setTitle("Warning");
                damz_dialog.setIcon(R.drawable.warning);
                damz_dialog.setMessage("Maaf, tidak ada produk untuk saat ini");
                damz_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                damz_dialog.show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            damz_dialog.dismiss();
            ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(),
                    contactList, R.layout.lay_produk, new String[]{
                    "ke", "s_stnm_produk", "s_stktrngan", "s_stcara_prduk"
            },
                    new int[]{
                            R.id.txt_ke_produk, R.id.tx_nproduk, R.id.tx_ketproduk, R.id.tx_cara
                    });
            lv_produk.setAdapter(adapter2);

        }
    }
}
