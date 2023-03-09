package damz.quizioner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import damz.quizioner.bantuan.*;
import damz.quizioner.R;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Act_regist extends Activity implements View.OnClickListener {
    ProgressDialog damz_log;
    private static String url_simp_regis = "http://own-youth.com/damz_quiz/damz_simp_regis.php";
    //private static String url_lihat_lokasi  = "http://own-youth.com/damz_quiz/damz_cluster.php";
    private static String url_lihat_lokasi  = "http://minisiteoperation.com/json/nama-barang.php";
    private static String url_cek_telp = "http://own-youth.com/damz_quiz/damz_cek_telp.php";
    EditText edt_telpon, edt_pasw, edt_nalengk;
    private static boolean foreground = true;
    AutoCompleteTextView act_lokasi;
    Button bt_daftar;
    final List< String > list = new ArrayList < String > ();
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    JSONArray contacts = null;
    String var_nohp,var_nalengk,var_passw,var_lokasi;
    Act_set_get act_sens = new Act_set_get();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_regist);
        edt_telpon = findViewById(R.id.edt_telepon);
        edt_pasw = findViewById(R.id.edt_paswr);
        edt_nalengk = findViewById(R.id.edt_nmlengkap);
        act_lokasi = findViewById(R.id.act_cluster);
        bt_daftar = findViewById(R.id.btn_daftar);
        bt_daftar.setOnClickListener(this);
        try{
            new AmbilLokasi().execute();
        }
        catch (Exception e){
            Toast.makeText(Act_regist.this,"Error karena, "+e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(final View klik2) {
        cd = new ConnectionDetector(getApplicationContext());
        var_nohp = edt_telpon.getText().toString();
        var_nalengk = edt_nalengk.getText().toString();
        var_passw = edt_pasw.getText().toString();
        var_lokasi = act_lokasi.getText().toString

                ();
        isInternetPresent = cd.isConnectingToInternet();
        if(klik2 == bt_daftar){
            if ((var_nohp.length() > 0) && (var_nalengk.length() > 0) && (var_passw.length() > 0) && (var_lokasi.length() > 0)) {
                if (isInternetPresent) {
                    DialogInterface.OnClickListener dd_dialog = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    readWebpage(klik2);
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(Act_regist.this);
                    builder.setMessage("Do You want to Save?").setPositiveButton("No", dd_dialog).setNegativeButton("Yes", dd_dialog).show();
                }
                else{
                    inet_mati();
                }
            }
            else{
                field_kosong();
            }
        }
    }

    public void inet_mati() {
        AlertDialog dd_dialog = new AlertDialog.Builder(Act_regist.this).create();
        dd_dialog.setTitle("Warning");
        dd_dialog.setIcon(R.drawable.warning);
        dd_dialog.setMessage("Please Activate Your Connection Internet...");
        dd_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dd_dialog.show();
    }
    private void field_kosong(){
        AlertDialog dyam_dialog = new AlertDialog.Builder(Act_regist.this).create();
        dyam_dialog.setTitle("Warning");
        dyam_dialog.setIcon(R.drawable.warning);
        dyam_dialog.setMessage("Please Complete the field");
        dyam_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dyam_dialog.show();
    }
    public void onBackPressed() {
        DialogInterface.OnClickListener dd_dialog = new DialogInterface.OnClickListener() {@
                Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    damz_kembali();
                    break;
            }
        }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You sure").setPositiveButton("No", dd_dialog).setNegativeButton("Yes", dd_dialog).show();
    }
    public void damz_kembali(){
        finish();
    }
    public void readWebpage(View view) {
        CallWebPageTask task = new CallWebPageTask();
        task.applicationContext = Act_regist.this;
        String url = url_cek_telp + "?TELPX=" + var_nohp;
        task.execute(new String[] {
                url
        });
    }
    public String getRequest(String Url) {
        String sret;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Url);
        try {
            HttpResponse response = client.execute(request);
            sret = request(response);
        } catch (Exception ex) {
            sret = "Failed Connect to server!";
        }
        return sret;
    }

    public static String request(HttpResponse response) {
        String result = "";
        try {
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader( in ));
            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line + "\n");
            } in .close();
            result = str.toString();
        } catch (Exception ex) {
            result = "Error";
        }
        return result;
    }

    private class CallWebPageTask extends AsyncTask< String, Void, String > {
        private ProgressDialog dialog;
        protected Context applicationContext;@
                Override
        protected void onPreExecute() {
          this.dialog = ProgressDialog.show(applicationContext, "Checking Data", "Please Wait...", true);
        }

        @
                Override
        protected String doInBackground(String...urls) {
            String response = "";
            response = getRequest(urls[0]);
            return response;

        }

        @
                Override
        protected void onPostExecute(String result) {
            this.dialog.cancel();
            String u = result;
            if (u.substring(27, 29).trim().equals("ok")) {
                AlertDialog dyam_dialog = new AlertDialog.Builder(Act_regist.this).create();
                dyam_dialog.setTitle("Warning");
                dyam_dialog.setIcon(R.drawable.warning);
                dyam_dialog.setMessage("Sorry, Number Phone is Registed");
                dyam_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dyam_dialog.show();
            } else {
                simp_regis(var_nohp, var_nalengk, var_passw, var_lokasi);
            }
        }
    }

    private void simp_regis(String var_nohpx, String var_nalengkx, String var_passwx, String var_lokasix) {

        class SendPostReqAsyncTask extends AsyncTask< String, Void, String > {
            @
                    Override
            protected void onPreExecute() {
                super.onPreExecute();
                damz_log = new ProgressDialog(Act_regist.this);
                damz_log.setMessage("Saving Process");
                damz_log.setIndeterminate(false);
                damz_log.show();
            }
            @
                    Override
            protected String doInBackground(String...params) {

                String var_nohpy = params[0];
                String var_nalengky = params[1];
                String var_passwy = params[2];
                String var_lokasiy = params[3];
                List<NameValuePair> nameValuePairs = new ArrayList< NameValuePair >();
                nameValuePairs.add(new BasicNameValuePair("TELEPONX", var_nohpy));
                nameValuePairs.add(new BasicNameValuePair("NAMA_LENGKAPX", var_nalengky));
                nameValuePairs.add(new BasicNameValuePair("PASSWORDZX", var_passwy));
                nameValuePairs.add(new BasicNameValuePair("CLUSTERX", var_lokasiy));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url_simp_regis);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @
                    Override
            protected void onPostExecute(String result) {
                damz_log.dismiss();
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(var_nohp,var_nalengk,var_passw,var_lokasi);

    }

    public class AmbilLokasi extends AsyncTask< String, String, String > {
        ArrayList<HashMap< String,
                String >> contactList = new ArrayList < HashMap < String, String >> ();
        HashMap < String, String > map = new HashMap < String, String > ();
        String aaz;
        int countz = 0;
        String[] str1;@
                Override
        protected void onPreExecute() {
            super.onPreExecute();

                damz_log = new ProgressDialog(Act_regist.this);
                damz_log.setMessage("Loading Data ...");
                damz_log.setIndeterminate(false);
                damz_log.setCancelable(false);
                damz_log.show();

        }

        @
                Override
        protected String doInBackground(String...arg0) {
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.ambilURL(url_lihat_lokasi);
            try {
                contacts = json.getJSONArray("INVENTORY");
                str1 = new String[contacts.length()];
                for (int aai = 0; aai < contacts.length(); aai++) {
                    JSONObject c = contacts.getJSONObject(aai);
                    str1[aai] = c.getString("nama_barang");
                }
            } catch (JSONException e) {

            }
            return null;
        }
        @
                Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            damz_log.dismiss();
            for (int i = 0; i < str1.length; i++) {
                list.add(str1[i]);
            }
            Collections.sort(list);
            ArrayAdapter< String > dataAdapter = new ArrayAdapter < String >
                    (getApplicationContext(), android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            act_lokasi.setThreshold(1);
            act_lokasi.setAdapter(dataAdapter);
        }

    }
}