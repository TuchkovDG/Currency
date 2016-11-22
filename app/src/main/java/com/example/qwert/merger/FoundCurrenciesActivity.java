package com.example.qwert.merger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.qwert.merger.MainActivity.SELECTED_CURRENCY;

/**
 * Created by samik on 10.11.2016.
 */

public class FoundCurrenciesActivity extends AppCompatActivity {

    private TextView name;
    private TextView full_name;
    protected static TextView date;
    private ImageView imageView;
    private Button search;

    private ListView listView;
    private CurrencyItemAdapter currencyItemAdapter;
    private Currency currencyLocal;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.found_activity);

        full_name = (TextView) findViewById(R.id.fa_fullName_text);
        name = (TextView) findViewById(R.id.fa_ABR_text);
        date = (TextView) findViewById(R.id.fa_edit_date);
        listView = (ListView) findViewById(R.id.fa_list_view);
        search = (Button) findViewById(R.id.fa_found_btn);
        imageView = (ImageView) findViewById(R.id.fa_icon);

//        long date = System.currentTimeMillis();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd");
//        String dateString = sdf.format(date);

        Intent intent = getIntent();
        int currIndex = intent.getIntExtra(SELECTED_CURRENCY, 0);

        setDataToScreen(currIndex);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = new DataPicker();
                picker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit.getCurrenciesByDate(date.toString(), name.toString(), new Callback<Currency>() {

                    @Override
                    public void success(Currency currency, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(FoundCurrenciesActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void setDataToScreen(int index) {
        currencyLocal = CurrenciesList.getInstance().get(index);

        name.setText(currencyLocal.base);
        full_name.setText(currencyLocal.sname);
        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                this.getResources().getIdentifier(currencyLocal.base.toLowerCase(), "drawable", "com.example.qwert.merger")));
        currencyItemAdapter = new CurrencyItemAdapter(this, currencyLocal.rates);

        listView.setAdapter(currencyItemAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Retrofit.getCurrenciesByDate("2016-01-03", "USD", new Callback<Currency>() {

            @Override
            public void success(Currency currency, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(FoundCurrenciesActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
