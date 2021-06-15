package com.ishwar_arcore.pacebook.views.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ishwar_arcore.pacebook.R;
import com.ishwar_arcore.pacebook.views.mainactivity.MainActivity;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView userRegister,forgetPassword;
    private EditText editTextEmail, editTextPassword;
    private Button SignIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userRegister=(TextView)findViewById(R.id.createAccount);
        userRegister.setOnClickListener(this);

        SignIn=(Button)findViewById(R.id.loginText);
        SignIn.setOnClickListener(this);

        editTextEmail=(EditText)findViewById(R.id.emailText);
        editTextPassword=(EditText) findViewById(R.id.passwordText);

        progressBar=(ProgressBar) findViewById(R.id.progressbar1);
        mAuth=FirebaseAuth.getInstance();
        forgetPassword = (TextView) findViewById(R.id.forgettext);
        forgetPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createAccount:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.loginText:
                UserLogin();
                break;
            case R.id.forgettext:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
        }

    }

    private void UserLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("please enter valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPassword.setError("Min password length is 6 characters!");
            return;
        }
        progressBar.setVisibility(View.GONE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Faild to login please check your credentials", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    }
