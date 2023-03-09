package damz.quizioner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import damz.quizioner.bantuan.Act_set_get;
import damz.quizioner.bantuan.ConnectionDetector;
import damz.quizioner.R;

public class Act_kuiz  extends Activity {
    Button bt_kirim;
    ProgressDialog damz_dialog = null;
    private static String url_quiz = "http://own-youth.com/damz_quiz/damz_isi_kuizz.php";
    private static String url_simp_KUIS = "http://own-youth.com/damz_quiz/damz_simp_kuiss.php";
    JSONArray contacts = null;
    ListView lv;
    private QuestionAdapterrrr adapter;
    private List<Question> feedsList;
    Context context;
    TextView tx_kategori;
    Integer jmlah_quiz;
    Boolean isInternetPresent = false;
    List<HashMap<String, String>> subjectsList;
    String[] pertanyaan;
    RadioGroup rdg;
    private RecyclerView recyclerViewQuestions;
    private List<Question> questions = new ArrayList<>();
    String ke1, ke2, ke3,ke4, ke5, ke6,ke7,ke8;
    RadioButton rdbb;
    ListView simpleList;
    ConnectionDetector cd;
    String us_telpon, kode_hasil, st_pertanyaan, st_jawaban, st_jawaban_benar, st_hasil;
    Integer jml_benar=0, jml_salah=0;
    Act_set_get asetget = new Act_set_get();
    String allhasil=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_list);
        tx_kategori = findViewById(R.id.tx_kategori);
        tx_kategori.setText("Kuis");
        us_telpon = asetget.getusnme();
        Date jam_skg = Calendar.getInstance().getTime();
        kode_hasil = us_telpon+"_"+String.valueOf(jam_skg);
        recyclerViewQuestions = (RecyclerView) findViewById(R.id.recycler_view_questions);
        recyclerViewQuestions.setHasFixedSize(true);
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));


        new DownloadTask().execute(url_quiz);
        initQuestionsAdapter();
        bt_kirim =  findViewById(R.id.btn_kirim);
        bt_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cd = new ConnectionDetector(getApplicationContext());
                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    DialogInterface.OnClickListener dd_dialog = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    ke1 = asetget.getke_1();
                                    ke2 = asetget.getke_2();
                                    ke3 = asetget.getke_3();
                                    ke4 = asetget.getke_4();
                                    ke5 = asetget.getke_5();

                                    try {
                                        if (jmlah_quiz == 5) {

                                            if ((((ke1!=null) && (ke2!=null) && (ke3!=null) && (ke4!=null)
                                                    && (ke5!=null)))) {
                                                //save =  ke+","+pert+","+jawb+","+jawb_benar+","+hasil;
                                                String[] k_11 = ke1.split("_");
                                                String pertanyaan = k_11[1];
                                                String jawaban = k_11[2];
                                                st_pertanyaan = pertanyaan;
                                                st_jawaban = jawaban;
                                                String jawbenar = k_11[3];
                                                String hasill = k_11[4];
                                                st_jawaban_benar = jawbenar;
                                                st_hasil =hasill;
                                                if(st_hasil.equals("benar")){
                                                    jml_benar = jml_benar + 1 ;
                                                }
                                                else{
                                                    jml_salah= jml_salah+1;
                                                }
                                                simp_db(us_telpon, kode_hasil, st_pertanyaan, st_jawaban,st_jawaban_benar, st_hasil);

                                                String[] k_22 = ke2.split("_");
                                                String pertanyaan22 = k_22[1];
                                                String jawaban22 = k_22[2];
                                                st_pertanyaan = pertanyaan22;
                                                st_jawaban = jawaban22;
                                                String jawbenar2 = k_22[3];
                                                String hasill2 = k_22[4];
                                                st_jawaban_benar = jawbenar2;
                                                st_hasil =hasill2;
                                                if(st_hasil.equals("benar")){
                                                    jml_benar = jml_benar + 1 ;
                                                }
                                                else{
                                                    jml_salah= jml_salah+1;
                                                }
                                                simp_db(us_telpon, kode_hasil, st_pertanyaan, st_jawaban,st_jawaban_benar, st_hasil);


                                                String[] k_33 = ke3.split("_");
                                                String pertanyaan33 = k_33[1];
                                                String jawaban33 = k_33[2];
                                                st_pertanyaan = pertanyaan33;
                                                st_jawaban = jawaban33;
                                                String jawbenar3 = k_33[3];
                                                String hasill3 = k_33[4];
                                                st_jawaban_benar = jawbenar3;
                                                st_hasil = hasill3;
                                                if(st_hasil.equals("benar")){
                                                    jml_benar = jml_benar + 1 ;
                                                }
                                                else{
                                                    jml_salah= jml_salah+1;
                                                }
                                                simp_db(us_telpon, kode_hasil, st_pertanyaan, st_jawaban,st_jawaban_benar, st_hasil);


                                                String[] k_44 = ke4.split("_");
                                                String pertanyaan44 = k_44[1];
                                                String jawaban44 = k_44[2];
                                                st_pertanyaan = pertanyaan44;
                                                st_jawaban = jawaban44;
                                                String jawbenar4 = k_44[3];
                                                String hasill4 = k_44[4];
                                                st_jawaban_benar = jawbenar4;
                                                st_hasil = hasill4;
                                                if(st_hasil.equals("benar")){
                                                    jml_benar = jml_benar + 1 ;
                                                }
                                                else{
                                                    jml_salah= jml_salah+1;
                                                }
                                                simp_db(us_telpon, kode_hasil, st_pertanyaan, st_jawaban,st_jawaban_benar, st_hasil);


                                                String[] k_55 = ke5.split("_");
                                                String pertanyaan55 = k_55[1];
                                                String jawaban55 = k_55[2];
                                                st_pertanyaan = pertanyaan55;
                                                st_jawaban = jawaban55;
                                                String jawbenar5 = k_55[3];
                                                String hasill5 = k_55[4];
                                                st_jawaban_benar = jawbenar5;
                                                st_hasil = hasill5;
                                                if(st_hasil.equals("benar")){
                                                    jml_benar = jml_benar + 1 ;
                                                }
                                                else{
                                                    jml_salah= jml_salah+1;
                                                }
                                                simp_dbfinal(us_telpon, kode_hasil, st_pertanyaan, st_jawaban,st_jawaban_benar, st_hasil);


                                            }
                                            else{
                                                lengkapi();
                                            }
                                        } else {

                                        }
                                    }
                                    catch (Exception e){
                                       // Toast.makeText(Act_kuiz.this,"Errornya: "+e,Toast.LENGTH_LONG).show();
                                    }


                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(Act_kuiz.this);
                    builder.setMessage("Do you want to save?").setPositiveButton("No", dd_dialog).setNegativeButton("Yes", dd_dialog).show();
                } else {
                    inet_mati();
                }
            }
        });

    }
    public void inet_mati() {
        AlertDialog dd_dialog = new AlertDialog.Builder(Act_kuiz.this).create();
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
    public void lengkapi() {
        AlertDialog dd_dialog = new AlertDialog.Builder(Act_kuiz.this).create();
        dd_dialog.setTitle("Warning");
        dd_dialog.setIcon(R.drawable.warning);
        dd_dialog.setMessage("Please Complete the field");
        dd_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dd_dialog.show();
    }

    public class DownloadTask extends AsyncTask<String, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();
            damz_dialog = new ProgressDialog(Act_kuiz.this);
            damz_dialog.setMessage("Loading Data ...");
            damz_dialog.setIndeterminate(false);
            damz_dialog.setCancelable(false);
            damz_dialog.show();
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            damz_dialog.dismiss();
            if (result == 1) {
                adapter = new QuestionAdapterrrr(Act_kuiz.this, feedsList);
                recyclerViewQuestions.setAdapter(adapter);
            } else {
                Toast.makeText(Act_kuiz.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("kuizz");
            feedsList = new ArrayList<>();

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                Question item = new Question();
                item.setpertanyaan(post.optString("SOAL"));
                item.setke1(post.optString("JAWABAN1"));
                item.setke2(post.optString("JAWABAN2"));
                item.setke3(post.optString("JAWABAN3"));
                item.setke4(post.optString("JAWABAN4"));
                item.setke5(post.optString("JAWABAN5"));
                item.setjawbenar(post.optString("JAWABAN_BENAR"));
                item.setJumlah(String.valueOf(i+1));
                feedsList.add(item);

                jmlah_quiz = i+1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void initQuestionsAdapter() {
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));
        QuestionAdapterrrr questionAdapter = new QuestionAdapterrrr(this, questions);
        recyclerViewQuestions.setAdapter(questionAdapter);

    }

    private void simp_db(String us_telponx, String kode_hasilx, String st_pertanyaanx, String st_jawabanx, String st_jawaban_benarx,
                         String st_hasilx) {
        class SendPostReqAsyncTask extends AsyncTask < String, Void, String > {

            @Override
            protected String doInBackground(String...params) {
                String us_telpony = params[0];
                String kode_hasily = params[1];
                String st_pertanyaany = params[2];
                String st_jawabany = params[3];
                String st_jawaban_benary = params[4];
                String st_hasily = params[5];
                List<NameValuePair> nameValuePairs = new ArrayList < NameValuePair > ();
                nameValuePairs.add(new BasicNameValuePair("TELEPONZX", us_telpony));
                nameValuePairs.add(new BasicNameValuePair("KODE_HASILX", kode_hasily));
                nameValuePairs.add(new BasicNameValuePair("PERTANYAANZX", st_pertanyaany));
                nameValuePairs.add(new BasicNameValuePair("JAWABANZX", st_jawabany));
                nameValuePairs.add(new BasicNameValuePair("JAWABAN_BENARZX", st_jawaban_benary));
                nameValuePairs.add(new BasicNameValuePair("HASILZX", st_hasily));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url_simp_KUIS);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(us_telpon, kode_hasil, st_pertanyaan, st_jawaban,st_jawaban_benar, st_hasil);

    }
    private void simp_dbfinal(String us_telponx, String kode_hasilx, String st_pertanyaanx, String st_jawabanx, String st_jawaban_benarx,
                         String st_hasilx) {
        class SendPostReqAsyncTask extends AsyncTask < String, Void, String > {

            @
                    Override
            protected void onPreExecute() {
                super.onPreExecute();
                damz_dialog = new ProgressDialog(Act_kuiz.this);
                damz_dialog.setMessage("Processing...");
                damz_dialog.setIndeterminate(false);
                damz_dialog.show();
            }
            @
                    Override
            protected String doInBackground(String...params) {
                String us_telpony = params[0];
                String kode_hasily = params[1];
                String st_pertanyaany = params[2];
                String st_jawabany = params[3];
                String st_jawaban_benary = params[4];
                String st_hasily = params[5];
                List<NameValuePair> nameValuePairs = new ArrayList < NameValuePair > ();
                nameValuePairs.add(new BasicNameValuePair("TELEPONZX", us_telpony));
                nameValuePairs.add(new BasicNameValuePair("KODE_HASILX", kode_hasily));
                nameValuePairs.add(new BasicNameValuePair("PERTANYAANZX", st_pertanyaany));
                nameValuePairs.add(new BasicNameValuePair("JAWABANZX", st_jawabany));
                nameValuePairs.add(new BasicNameValuePair("JAWABAN_BENARZX", st_jawaban_benary));
                nameValuePairs.add(new BasicNameValuePair("HASILZX", st_hasily));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url_simp_KUIS);
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
                damz_dialog.dismiss();
                super.onPostExecute(result);

                AlertDialog dd_dialog = new AlertDialog.Builder(Act_kuiz.this).create();
                   dd_dialog.setTitle("Information");
                    dd_dialog.setIcon(R.drawable.information);
                    dd_dialog.setMessage("--Your Result--\nCorrect Answer: "+jml_benar.toString()+"\nWrong Answer: "+jml_salah.toString());
                    dd_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent n = new Intent(Act_kuiz.this,Act_menu_utama.class);
                            finish();
                            startActivity(n);
                        }
                    });
                    dd_dialog.show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(us_telpon, kode_hasil, st_pertanyaan, st_jawaban,st_jawaban_benar, st_hasil);

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
        Intent n = new Intent(Act_kuiz.this, Act_menu_utama.class);
        finish();
        startActivity(n);
    }
}