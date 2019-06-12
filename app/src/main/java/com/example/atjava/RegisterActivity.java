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

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText editLogin;
    EditText editPassword, editConfirm, editCPF;
    EditText editName;
    Button btnLoginRegister;
    ProgressDialog progressLogin;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        editLogin = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.senha);
        editConfirm = (EditText) findViewById(R.id.confirmSenha);
        editCPF = (EditText) findViewById(R.id.cpf);
        //mascara cpf
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(editCPF, smf);
        editCPF.addTextChangedListener(mtw);
        editName = (EditText) findViewById(R.id.name);
        btnLoginRegister = (Button) findViewById(R.id.email_sign_in_button);
        btnLoginRegister.setText(R.string.action_cadastro);
        progressLogin = new ProgressDialog(this);
        databaseUser = FirebaseDatabase.getInstance().getReference("Users");

    }

    private void registerUser(){
        String email = editLogin.getText().toString().trim();
        String senha  = editPassword.getText().toString().trim();
        String confirmSenha = editConfirm.getText().toString().trim();
        String cpf = editCPF.getText().toString().trim();
        String name = editName.getText().toString().trim();

        if( !name.isEmpty() & (isValidEmail(email)) & (senha.equals(confirmSenha)) & !cpf.isEmpty() ){

            progressLogin.setMessage(getString(R.string.registering_user));
            progressLogin.show();
            register(email, senha);
            salvarDatabase(name, senha, email, cpf);

        } else {
            Toast.makeText(this,"Formulário preenchido de maneira errada",Toast.LENGTH_LONG).show();

        }
    }

    private void salvarDatabase (String nome, String senha, String email, String cpf){

        String id = databaseUser.push().getKey();

        //crear objeto
        Acao acao = new Acao(nome, senha, email, cpf);

        //salvar as pastas
        databaseUser.child(id).setValue(acao);


    }

    private void register(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, R.string.successfully_registred,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, R.string.regristration_error,Toast.LENGTH_LONG).show();
                        }
                        progressLogin.dismiss();
                    }
                });
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void RegisterClick(View view) {
        registerUser();
    }
}