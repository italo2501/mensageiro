package com.github.welingtonveiga.mensageiro.status;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.welingtonveiga.mensageiro.R;

public class StatusActivity extends AppCompatActivity {

    private static final String TAG = "StatusActivity";
    private EditText editStatus;
    private Button buttonTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        editStatus = (EditText) findViewById(R.id.editStatus);
        buttonTweet = (Button) findViewById(R.id.buttonTweet);

        buttonTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String status = editStatus.getText().toString();
                Log.d(TAG, "onClicked with status: " + status);

            }
        });
    }
}
