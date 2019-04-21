package com.sda5.walletdroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sda5.walletdroid.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private SignInButton mSignInButton;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.txt_login_email);
        etPassword = findViewById(R.id.txt_login_password);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(this, ServiceActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
        }

        //for google sign in
        mSignInButton = findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SigninGoogle.class));
            }
        });
    }

    /**
     *
     * @param view
     */
    public void login(View view) {

        // Check if user fills all fields.
        if(etEmail.getText().toString().trim().isEmpty() || etPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(MainActivity.this, ServiceActivity.class));

                            } else {
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    /**
     * Sign up activity
     * @param
     */
    public void goToSignUpPage(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


    public void resetPasswordViaEmail(View view) {
        String email = etEmail.getText().toString();
        // Check if there email is empty or not
        if (!email.trim().isEmpty()) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Password reset email has sent your mail. Check your email!", Toast.LENGTH_SHORT).show();

                            } else {
                                // ...
                            }
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();

        }
    }
}
