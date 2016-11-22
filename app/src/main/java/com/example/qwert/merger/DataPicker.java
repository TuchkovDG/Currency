package com.example.qwert.merger;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by AndAdm on 13.11.2016.
 */

public class DataPicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Dialog date_picker = new DatePickerDialog(getActivity(), this, year, month, day);
        date_picker.setTitle("test");

        return date_picker;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button set_data = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
        set_data.setText("Готово");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        FoundCurrenciesActivity.get_date.setText(year + "-" + month + "-" + day);
    }
}
