package com.example.aplicatieipbuna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inițializează instanța Firebase
        mAuth = FirebaseAuth.getInstance();

        // Inițializează elementele UI
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button btnLogin = findViewById(R.id.buttonLogin);

        // Setează un ascultător pentru butonul de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obține adresa de email și parola introduse
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Verifică dacă adresa de email și parola sunt nevide
                if (!email.isEmpty() && !password.isEmpty()) {
                    // Autentificare utilizator cu Firebase
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Autentificare reușită
                                        Toast.makeText(SignIn.this, "Autentificare reușită!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignIn.this, Aplicatia.class);
                                        startActivity(intent);
                                        finish();
                                        // Adaugă aici codul pentru a naviga către activitatea principală sau altă activitate
                                    } else {
                                        // Autentificare eșuată
                                        Toast.makeText(SignIn.this, "Autentificare eșuată. Verificați adresa de email și parola.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Afișează un mesaj dacă adresa de email sau parola sunt goale
                    Toast.makeText(SignIn.this, "Introduceți adresa de email și parola.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
