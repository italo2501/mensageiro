package com.github.welingtonveiga.mensageiro.views.status;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.welingtonveiga.mensageiro.R;
import com.github.welingtonveiga.mensageiro.domain.model.Status;
import com.github.welingtonveiga.mensageiro.util.RestClient;

import java.util.Date;

public class StatusActivity extends AppCompatActivity {

    private static final String TAG = "StatusActivity";
    private static final int TEXT_SIZE_LIMIT = 140;
    public static final String STATUSES = "https://service-api.herokuapp.com/statuses";

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

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(StatusActivity.this);
                final String username = prefs.getString("username", "Jhon Doe");

                new PostTask().execute(new Status(username, status, new Date()));
            }
        });

        editStatus.addTextChangedListener(new TextLimitTextWatcher(editStatus, textCount, TEXT_SIZE_LIMIT));
    }


    private final class PostTask extends AsyncTask<Status, Void, String> {

        @Override
        protected String doInBackground(com.github.welingtonveiga.mensageiro.domain.model.Status... statuses) {
            String message = "Successfully posted";

            try {
                RestClient rest = new RestClient();
                for (com.github.welingtonveiga.mensageiro.domain.model.Status status: statuses) {
                    rest.post(STATUSES, status, status.getClass());
                }
            } catch (Exception e) {
                Log.e(TAG, "Error posting status! " + e.getMessage() ,e);
                message = "Sorry... failed to post status!";
            }

            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }

}
