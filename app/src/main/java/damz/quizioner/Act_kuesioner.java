package damz.quizioner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import damz.quizioner.bantuan.*;
import damz.quizioner.R;

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

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Act_kuesioner extends Activity {
    String st_isiquiz, st_jw1, st_jw2, st_jw3, st_jw4, st_jw5, st_jw6, st_jw7, st_jw8;
    Button bt_kirim;
    ProgressDialog damz_dialog = null;
    private static String url_quizioner = "http://own-youth.com/damz_quiz/damz_isi_quizioner.php";
    private static String url_simp_quiz = "http://own-youth.com/damz_quiz/damz_simp_quiz.php";
    JSONArray contacts = null;

    private QuestionAdapter adapter;
    private List<Question> feedsList;
    ListAdapter adapter2;

    TextView tx_kategori;
    Integer jmlah_quiz;
    Boolean isInternetPresent = false;
    List<HashMap<String, String>> subjectsList;
    String[] pertanyaan;
    RadioGroup rdg;
    private RecyclerView recyclerViewQuestions;
    private List<Question> questions = new ArrayList<>();
    String ke1, ke2, ke3,ke4, ke5, ke6,ke7,ke8;
    ConnectionDetector cd;
    String us_telpon, kode_hasil, st_pertanyaan, st_jawaban;
    Act_set_get asetget = new Act_set_get();
String allhasil=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lay_list);
        tx_kategori = findViewById(R.id.tx_kategori);
        tx_kategori.setText("Kuesioner");
        us_telpon = asetget.getusnme();
        Date jam_skg = Calendar.getInstance().getTime();
        kode_hasil = us_telpon+"_"+String.valueOf(jam_skg);
        recyclerViewQuestions = (RecyclerView) findViewById(R.id.recycler_view_questions);
        recyclerViewQuestions.setHasFixedSize(true);
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));
     //   prepareQuestions();
        new DownloadTask().execute(url_quizioner);
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
                           //         Toast.makeText(Act_kuesioner.this,ke1+ke2+ke3+ke4+ke5+ke6+ke7+ke8, Toast.LENGTH_LONG).show();
                                   try {
                                        if (jmlah_quiz == 5) {

                                            if ((((ke1!=null) && (ke2!=null) && (ke3!=null) && (ke4!=null)
                                                    && (ke5!=null)))) {

                                                String[] k_11 = ke1.split(",");
                                                String pertanyaan = k_11[1];
                                                String jawaban = k_11[2];
                                                st_pertanyaan = pertanyaan;
                                                st_jawaban = jawaban;
                                                simp_db(us_telpon, kode_hasil, st_pertanyaan, st_jawaban);

                                                String[] k_22 = ke2.split(",");
                                                String pertanyaan22 = k_22[1];
                                                String jawaban22 = k_22[2];
                                                st_pertanyaan = pertanyaan22;
                                                st_jawaban = jawaban22;
                                                simp_db(us_telpon, kode_hasil, st_pertanyaan, st_jawaban);


                                                String[] k_33 = ke3.split(",");
                                                String pertanyaan33 = k_33[1];
                                                String jawaban33 = k_33[2];
                                                st_pertanyaan = pertanyaan33;
                                                st_jawaban = jawaban33;
                                                simp_db(us_telpon, kode_hasil, st_pertanyaan, st_jawaban);


                                                String[] k_44 = ke4.split(",");
                                                String pertanyaan44 = k_44[1];
                                                String jawaban44 = k_44[2];
                                                st_pertanyaan = pertanyaan44;
                                                st_jawaban = jawaban44;
                                                simp_db(us_telpon, kode_hasil, st_pertanyaan, st_jawaban);


                                                String[] k_55 = ke5.split(",");
                                                String pertanyaan55 = k_55[1];
                                                String jawaban55 = k_55[2];
                                                st_pertanyaan = pertanyaan55;
                                                st_jawaban = jawaban55;
                                                simp_dbfinal(us_telpon, kode_hasil, st_pertanyaan, st_jawaban);

                                            }
                                            else{
                                                lengkapi();
                                            }
                                        } else {

                                        }
                                    }
                                    catch (Exception e){
                                      //  Toast.makeText(Act_kuesioner.this,"Errornya: "+e,Toast.LENGTH_LONG).show();
                                    }


                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(Act_kuesioner.this);
                    builder.setMessage("Do You want to save?").setPositiveButton("No", dd_dialog).setNegativeButton("Yes", dd_dialog).show();
                } else {
                    inet_mati();
                }
            }
        });

    }
    private void initQuestionsAdapter() {
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));
        QuestionAdapter questionAdapter = new QuestionAdapter(this, questions);
        recyclerViewQuestions.setAdapter(questionAdapter);

    }

    public void inet_mati() {
        AlertDialog dd_dialog = new AlertDialog.Builder(Act_kuesioner.this).create();
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
        AlertDialog dd_dialog = new AlertDialog.Builder(Act_kuesioner.this).create();
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


    public class AmbilData extends AsyncTask<String, String, String> {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        List<Question> questionslessst;


        @
                Override
        protected void onPreExecute() {
            super.onPreExecute();
            damz_dialog = new ProgressDialog(Act_kuesioner.this);
            damz_dialog.setMessage("Loading Data ...");
            damz_dialog.setIndeterminate(false);
            damz_dialog.setCancelable(false);
            damz_dialog.show();
        }

        @
                Override
        protected String doInBackground(String... arg0) {
            questionslessst = new ArrayList<>();
            JSONParser jParser = new JSONParser();
            int i;
            JSONObject json = jParser.ambilURL(url_quizioner);
            try {
                contacts = json.getJSONArray("izi");
                for (i = 0; i < contacts.length(); i++) {
//                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject c = contacts.getJSONObject(i);
                    st_isiquiz = c.getString("ISI_KUESIONER").trim();
                    st_jw1 = c.getString("JAWKUE1Z").trim();
                    st_jw2 = c.getString("JAWKUE2Z").trim();
                    st_jw3 = c.getString("JAWKUE3Z").trim();
                    st_jw4 = c.getString("JAWKUE4Z").trim();
                    st_jw5 = c.getString("JAWKUE5Z").trim();
                    st_jw6 = c.getString("JAWKUE6Z").trim();
                    st_jw7 = c.getString("JAWKUE7Z").trim();
                    st_jw8 = c.getString("JAWKUE8Z").trim();
                    int ke = i+1;
                    jmlah_quiz = ke;
                    map.put("ke", String.valueOf(ke));
                    map.put("st_isiquiz_", st_isiquiz);
                    map.put("st_jw1_", st_jw1);
                    map.put("st_jw2_", st_jw2);
                    map.put("st_jw3_", st_jw3);
                    map.put("st_jw4_", st_jw4);
                    map.put("st_jw5_", st_jw5);
                    map.put("st_jw6_", st_jw6);
                    map.put("st_jw7_", st_jw7);
                    map.put("st_jw8_", st_jw8);
                    contactList.add(map);
                }
            }
            catch (JSONException e) {

            }
            return null;
        }

        @
                Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            damz_dialog.dismiss();
            adapter2 = new SimpleAdapter(getApplicationContext(),
                    contactList, R.layout.lay_kuesioner, new String[]{"ke","st_isiquiz_", "st_jw1_", "st_jw2_","st_jw3_","st_jw4_",
                    "st_jw5_", "st_jw6_", "st_jw7_","st_jw8_",},
                    new int[]{R.id.txt_ke_pert,R.id.tx_pertanyaan, R.id.jawaban1, R.id.jawaban2, R.id.jawaban3, R.id.jawaban4, R.id.jawaban5,
                            R.id.jawaban6, R.id.jawaban7, R.id.jawaban8});


                try {
                    recyclerViewQuestions.setAdapter((RecyclerView.Adapter) adapter2);
                }
                catch (Exception e){
                    AlertDialog dd_dialog = new AlertDialog.Builder(Act_kuesioner.this).create();
                    dd_dialog.setTitle("Peringatan");
                    dd_dialog.setIcon(R.drawable.warning);
                    dd_dialog.setMessage("Errornya: "+e);
                    dd_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dd_dialog.show();
                }
        }


    }


    public class DownloadTask extends AsyncTask<String, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();
            damz_dialog = new ProgressDialog(Act_kuesioner.this);
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
                adapter = new QuestionAdapter(Act_kuesioner.this, feedsList);
                recyclerViewQuestions.setAdapter(adapter);
            } else {
                Toast.makeText(Act_kuesioner.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("izi");
            feedsList = new ArrayList<>();

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                Question item = new Question();
                item.setpertanyaan(post.optString("ISI_KUESIONER"));
                item.setke1(post.optString("JAWKUE1Z"));
                item.setke2(post.optString("JAWKUE2Z"));
                item.setke3(post.optString("JAWKUE3Z"));
                item.setke4(post.optString("JAWKUE4Z"));
                item.setke5(post.optString("JAWKUE5Z"));
                item.setke6(post.optString("JAWKUE6Z"));
                item.setke7(post.optString("JAWKUE7Z"));
                item.setke8(post.optString("JAWKUE8Z"));
                item.setJumlah(String.valueOf(i+1));
                feedsList.add(item);

                jmlah_quiz = i+1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void simp_db(String us_telponx, String kode_hasilx, String st_pertanyaanx, String st_jawabanx) {

        class SendPostReqAsyncTask extends AsyncTask < String, Void, String > {

            @Override
            protected String doInBackground(String...params) {
                String us_telpony = params[0];
                String kode_hasily = params[1];
                String st_pertanyaany = params[2];
                String st_jawabany = params[3];
                List<NameValuePair> nameValuePairs = new ArrayList < NameValuePair > ();
                nameValuePairs.add(new BasicNameValuePair("TELEPONZX", us_telpony));
                nameValuePairs.add(new BasicNameValuePair("KODE_HASILX", kode_hasily));
                nameValuePairs.add(new BasicNameValuePair("PERTANYAANZX", st_pertanyaany));
                nameValuePairs.add(new BasicNameValuePair("JAWABANZ", st_jawabany));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url_simp_quiz);
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
        sendPostReqAsyncTask.execute(us_telpon, kode_hasil, st_pertanyaan, st_jawaban);

    }

    private void simp_dbfinal(String us_telponx, String kode_hasilx, String st_pertanyaanx, String st_jawabanx) {

        class SendPostReqAsyncTask extends AsyncTask < String, Void, String > {
            @
                    Override
            protected void onPreExecute() {
                super.onPreExecute();
                damz_dialog = new ProgressDialog(Act_kuesioner.this);
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
                List<NameValuePair> nameValuePairs = new ArrayList < NameValuePair > ();
                nameValuePairs.add(new BasicNameValuePair("TELEPONZX", us_telpony));
                nameValuePairs.add(new BasicNameValuePair("KODE_HASILX", kode_hasily));
                nameValuePairs.add(new BasicNameValuePair("PERTANYAANZX", st_pertanyaany));
                nameValuePairs.add(new BasicNameValuePair("JAWABANZ", st_jawabany));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url_simp_quiz);
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
                AlertDialog dd_dialog = new AlertDialog.Builder(Act_kuesioner.this).create();
                dd_dialog.setTitle("Information");
                dd_dialog.setIcon(R.drawable.information);
                dd_dialog.setMessage("Thanks For Your Participation, "+asetget.getnama().toString());
                dd_dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent n = new Intent(Act_kuesioner.this,Act_menu_utama.class);
                        finish();
                        startActivity(n);
                    }
                });
                dd_dialog.show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(us_telpon, kode_hasil, st_pertanyaan, st_jawaban);

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
        Intent n = new Intent(Act_kuesioner.this, Act_menu_utama.class);
        finish();
        startActivity(n);
    }

}