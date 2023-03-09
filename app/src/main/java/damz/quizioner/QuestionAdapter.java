package damz.quizioner;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.List;

import damz.quizioner.bantuan.Act_set_get;
import damz.quizioner.R;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private static final String TAG = "QuestionAdapter";
    private List<Question> questions;
    private Context mContext;
    private RadioButton lastCheckedRB = null;
    private RadioGroup lastCheckedRG = null;
    private LayoutInflater inflater;

    public QuestionAdapter(Context context, List<Question> questions) {
        //this.questions = questions;
        //this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.questions = questions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_kuesioner, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;*/


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_kuesioner, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Question current = questions.get(position);
        holder.setQuestion(current.pertanyaan);
        holder.setke1(current.ke1);
        holder.setke2(current.ke2);
        holder.setke3(current.ke3);
        holder.setke4(current.ke4);
        holder.setke5(current.ke5);
        holder.setke6(current.ke6);
        holder.setke7(current.ke7);
        holder.setke8(current.ke8);
        holder.setberapa(current.jumlah);

    }

    @Override
    public int getItemCount() {
        if (questions == null) {
            return 0;
        } else {
            return questions.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayoutContainer;
        private TextView textViewQuestion, textViewAnswer,txke;
        private RadioGroup radioGroupOptions;
        private RadioButton radioButtonOption1, radioButtonOption2, radioButtonOption3, radioButtonOption4, radioButtonOption5,
        radioButtonOption6, radioButtonOption7, radioButtonOption8;


        public ViewHolder(final View itemView) {
            super(itemView);
            linearLayoutContainer = (LinearLayout) itemView.findViewById(R.id.linear_layout_container);
            textViewQuestion =  itemView.findViewById(R.id.tx_pertanyaan);
            textViewAnswer =  itemView.findViewById(R.id.text_view_answer);
            radioGroupOptions = (RadioGroup) itemView.findViewById(R.id.rdgrp_jwban);
            radioButtonOption1 = (RadioButton) itemView.findViewById(R.id.jawaban1);
            radioButtonOption2 = (RadioButton) itemView.findViewById(R.id.jawaban2);
            radioButtonOption3 = (RadioButton) itemView.findViewById(R.id.jawaban3);
            radioButtonOption4 = (RadioButton) itemView.findViewById(R.id.jawaban4);
            radioButtonOption5 = (RadioButton) itemView.findViewById(R.id.jawaban5);
            radioButtonOption6 = (RadioButton) itemView.findViewById(R.id.jawaban6);
            radioButtonOption7 = (RadioButton) itemView.findViewById(R.id.jawaban7);
            radioButtonOption8 = (RadioButton) itemView.findViewById(R.id.jawaban8);
            txke = itemView.findViewById(R.id.txt_ke_pert);
            radioGroupOptions.clearCheck();


            radioGroupOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    try{
                        //int selectedId = radioGroupOptions.getCheckedRadioButtonId();
                        //checkedId = group.getCheckedRadioButtonId();
                        checkedId = group.getCheckedRadioButtonId();
                        RadioButton tercek = (RadioButton) group.findViewById(checkedId);

                        String ke,pert,jawb, save;
                        ke = txke.getText().toString();
                        pert = textViewQuestion.getText().toString();
                        jawb = tercek.getText().toString();
                        save =  ke+","+pert+","+jawb;
                        if (save==null){
                            save="kosong";
                        }
                        Act_set_get a = new Act_set_get();

                        if (ke.equals("1")){
                             a.set_ke1(save);
                        }
                        else if (ke.equals("2")){
                            a.set_ke2(save);
                        }
                        else if (ke.equals("3")){
                            a.set_ke3(save);
                        }
                        else if (ke.equals("4")){
                            a.set_ke4(save);
                        }
                        else if (ke.equals("5")){
                            a.set_ke5(save);
                        }
                        else if (ke.equals("6")){
                            a.set_ke6(save);
                        }
                        else if (ke.equals("7")){
                            a.set_ke7(save);
                        }
                        else if (ke.equals("8")){
                            a.set_ke8(save);
                        }

                        Log.e(">>>", "Tersimpan ke "+txke.getText().toString()+"\ndengan isi:  "+save);

//                        group.clearCheck();
 //                       textViewAnswer.setText(jawb);

                    }
                    catch (Exception e){
                        Log.e(">>>", "errornya "+e );
                    }

                }

            });

        }

        public void setberapa(String berapa) {
            txke.setText(berapa);
        }

        public void setQuestion(String question) {
            textViewQuestion.setText(question);
        }

        public void setke1(String ke1) {
            radioButtonOption1.setText(ke1);
        }
        public void setke2(String ke2) {
            radioButtonOption2.setText(ke2);
        }

        public void setke3(String ke3) {
            radioButtonOption3.setText(ke3);
        }
        public void setke4(String ke4) {
            radioButtonOption4.setText(ke4);
        }
        public void setke5(String ke5) {
            radioButtonOption5.setText(ke5);
        }
        public void setke6(String ke6) {
            radioButtonOption6.setText(ke6);
        }
        public void setke7(String ke7) {
            radioButtonOption7.setText(ke7);
        }
        public void setke8(String ke8) {
            radioButtonOption8.setText(ke8);
        }

    }

}