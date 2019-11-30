package com.example.twinsrobo_apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText emailid, password;
    Button btnSignup;
    TextView tvSignin;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignup = findViewById(R.id.button2);
        tvSignin = findViewById(R.id.textView);

        //sign up button
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(login.this,"Field Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (email.isEmpty()) {
                    emailid.setError("Please enter email id");
                    emailid.requestFocus();
                }  else if (pwd.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                }   else if ((!email.isEmpty() && !pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(login.this,"SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(login.this,HomeActivity.class));
                            }
                        }
                    });
                }else {
                    Toast.makeText(login.this,"Error Occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,SigninActivity.class);
                startActivity(i);
            }
        });

    }
}
