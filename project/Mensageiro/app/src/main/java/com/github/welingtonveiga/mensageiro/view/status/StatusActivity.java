package com.github.welingtonveiga.mensageiro.view.status;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.welingtonveiga.mensageiro.R;

public class StatusActivity extends AppCompatActivity {

    private static final String TAG = "StatusActivity";
    private static final int TEXT_SIZE_LIMIT = 140;
    private EditText editStatus;
    private Button buttonTweet;
    private TextView textCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        editStatus = (EditText) findViewById(R.id.editStatus);
        buttonTweet = (Button) findViewById(R.id.buttonTweet);
        textCount = (TextView) findViewById(R.id.textCount);

        buttonTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String status = editStatus.getText().toString();
                Log.d(TAG, "onClicked with status: " + status);

            }
        });

        editStatus.addTextChangedListener(new TextLimitTextWatcher(editStatus, textCount, TEXT_SIZE_LIMIT));
    }
}
