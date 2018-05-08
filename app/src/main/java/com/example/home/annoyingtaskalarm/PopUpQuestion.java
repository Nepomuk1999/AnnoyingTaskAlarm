package com.example.home.annoyingtaskalarm;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class PopUpQuestion extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.popup_question, null))
                // Add action buttons
                .setPositiveButton(R.string.btnSaveAnswer, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // validate answer with alarmentitiy!!!
                    }
                });
        return builder.create();
    }
}
