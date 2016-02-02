package com.remote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.remote.utilities.ApiRequestInterceptor;
import com.remote.utilities.ServerRequest;
import com.remote.utilities.User;

import java.io.FileOutputStream;


public class LoginActivity extends ActionBarActivity {
    // UI references.
    private EditText mPasswordView;
    private Gson gson = new Gson();
    private ServerRequest svreq = ServerRequest.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        final EditText password = (EditText) findViewById(R.id.password);
        final AutoCompleteTextView username = (AutoCompleteTextView) findViewById(R.id.username);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(
                            getResources().getString(R.string.resource_file),
                            Context.MODE_PRIVATE);
                    User user = new User(username.getText().toString(),
                            password.getText().toString());
                    fos.write(gson.toJson(user).toString().getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                svreq.loadUser(getApplicationContext());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

