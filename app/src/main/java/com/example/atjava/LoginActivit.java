package com.example.atjava;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivit extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText editLogin;
    EditText editPassword;
    Button mBtnLogin;
    ProgressDialog progressLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        editLogin = (EditText) findViewById(R.id.loginEmail);
        editPassword = (EditText) findViewById(R.id.loginSenha);
        mBtnLogin = (Button) findViewById(R.id.login);
        progressLogin = new ProgressDialog(this);

    }

    private void login() {
        String email = editLogin.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, R.string.prompt_email, Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.prompt_password, Toast.LENGTH_LONG).show();
            return;
        }
        progressLogin.setMessage(getString(R.string.efetuando_login));
        progressLogin.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivit.this, R.string.login_efetuado, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), PosLogin.class);
                            intent.putExtra("nome", editLogin.getText().toString().trim());
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivit.this, R.string.login_error, Toast.LENGTH_LONG).show();
                        }
                        progressLogin.dismiss();
                    }
                });


    }


    public void loginClick(View view) {
        login();
    }

}