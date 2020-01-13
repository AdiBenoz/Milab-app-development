package com.example.stockmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText stockInput;
    private EditText userNameInput;
    private String userToken;
    private TextView showPrice;
    String stock;
    String user;
    private static final String TAG = "MainActivity";
    IntentFilter mFilter;
    static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getToken();

        submitButton = findViewById(R.id.submit);
        stockInput = findViewById(R.id.stockText);
        userNameInput = findViewById(R.id.userName);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stock = stockInput.getText().toString();
                user = userNameInput.getText().toString();
                fetchStockPrice(view);
            }
        });
        mFilter = new IntentFilter("StockMonitor.MESSAGE_IN");
        registerReceiver(myPriceReceiver, mFilter);
        progressDialog = new ProgressDialog(this);
    }


    public void fetchStockPrice(final View view) {
        //showPrice.setText(" ");
        progressDialog.setMessage("Fetching stock price for " + stock + "...");
        progressDialog.show();

        final stockPriceFetcher fetcher = new stockPriceFetcher(view.getContext());

        fetcher.dispatchRequest(stock, user, userToken, new stockPriceFetcher.stockResponseListener() {
            @Override
            public void onResponse(stockPriceFetcher.stockResponse response) {

                if (response.isError) {
                    Toast.makeText(view.getContext(), "Error while fetching weather", Toast.LENGTH_LONG);
                    return;
                }

                ((TextView) MainActivity.this.findViewById(R.id.showPrice)).setText(response.price);
            }
        });
    }


    public void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        userToken = token;

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private final BroadcastReceiver myPriceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            progressDialog.hide();
            if (intent.getAction().equalsIgnoreCase("StockMonitor.MESSAGE_IN")) {
                showPrice = (TextView) MainActivity.this.findViewById(R.id.showPrice);
                String priceToShow = intent.getStringExtra("StockMonitor.STOCK_PRICE");
                Log.d(TAG, priceToShow);
                showPrice.setText(priceToShow);
            }
        }
    };
}
