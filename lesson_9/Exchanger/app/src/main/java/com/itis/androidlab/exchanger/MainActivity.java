package com.itis.androidlab.exchanger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.itis.androidlab.exchanger.models.Currency;

public class MainActivity extends AppCompatActivity {

    private EditText mAmount;
    private Spinner mCurrency;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mAmount = (EditText) findViewById(R.id.amount);

        mCurrency = (Spinner) findViewById(R.id.currency);
        ArrayAdapter<?> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, Currency.getValuesArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCurrency.setAdapter(adapter);

        mResult = (TextView) findViewById(R.id.result);

        (findViewById(R.id.exchange)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer amount = Integer.valueOf(mAmount.getText().toString());
                String result = null;
                switch (Currency.values()[mCurrency.getSelectedItemPosition()]) {
                    case RUB:
                        result = String.format(getResources().getString(R.string.result_template),
                                amount * Constants.RUB_TO_EUR, Currency.EUR.toString(),
                                amount * Constants.RUB_TO_USD, Currency.USD.toString());
                        break;
                    case USD:
                        result = String.format(getResources().getString(R.string.result_template),
                                amount * Constants.USD_TO_RUB, Currency.RUB.toString(),
                                amount * Constants.USD_TO_EUR, Currency.EUR.toString());
                        break;
                    case EUR:
                        result = String.format(getResources().getString(R.string.result_template),
                                amount * Constants.EUR_TO_RUB, Currency.RUB.toString(),
                                amount * Constants.EUR_TO_USD, Currency.USD.toString());
                        break;
                }
                mResult.setText(result);
            }
        });
    }
}
