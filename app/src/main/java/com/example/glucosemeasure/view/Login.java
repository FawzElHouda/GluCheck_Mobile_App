package com.example.glucosemeasure.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.glucosemeasure.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
        private EditText email;
        private EditText password;

        private Button login;
        private TextView toSign;

        private FirebaseAuth mAuth;
        private boolean isValidEmail(String email) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            return pattern.matcher(email).matches();}

        @Override
        public void onStart() {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                Intent intent =new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login_button);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        toSign = (TextView) findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        toSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Sign_up.class);
                startActivity(intent);
                finish();
            }
        });
        // Ajouter un auditeur (listener) au bouton
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Récupérer le contenu de l'email et du mot de passe
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                // Vérifier si l'email et le mot de passe ne sont pas vides
                if (userEmail.isEmpty() || userPassword.isEmpty()) {

                    Toast.makeText(Login.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!isValidEmail(userEmail))
                        Toast.makeText(Login.this, "Format d'email invalide.", Toast.LENGTH_SHORT).show();

                    else {


                        mAuth.signInWithEmailAndPassword(userEmail, userPassword ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, " Authentication is Successful ", Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(Login.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}

