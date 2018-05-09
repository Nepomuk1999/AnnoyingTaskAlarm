package com.example.home.annoyingtaskalarm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PopupDialog extends AppCompatDialogFragment {

    private EditText editTextAnswer;
    private TextView showQuestion;
    private PopupDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.popup_dialog, null);

        // TODO: set question from db here
        //showQuestion.setText();

        builder.setView(view)
                .setTitle("Question time!!!")
                .setPositiveButton("Send answer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String answer = editTextAnswer.getText().toString();
                        listener.sendAnswer(answer);
                    }
                });

        editTextAnswer = view.findViewById(R.id.alarmAnswer);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (PopupDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement PopupDialogListener");
        }
    }

    public interface PopupDialogListener {
        void sendAnswer(String answer);
    }
}
