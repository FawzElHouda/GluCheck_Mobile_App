package com.example.glucosemeasure.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.glucosemeasure.R;


public class Sign_up extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button Sign;
    private TextView tologin;
    private FirebaseAuth mAuth;

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    public static boolean isEquals(String password, String confirmPassword) {
        // Check if both password and confirm password are not null
        if (password != null && confirmPassword != null) {
            // Use equals method to compare the values
            return password.equals(confirmPassword);
        } else {
            // If either password or confirm password is null, they are not equal
            return false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(Sign_up.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Sign = (Button) findViewById(R.id.signup_button);
        email = (EditText) findViewById(R.id.signup_email);
        password = (EditText) findViewById(R.id.signup_password);
        confirmPassword= (EditText) findViewById(R.id.signup_confirm);
        mAuth = FirebaseAuth.getInstance();
        Sign = (Button) findViewById(R.id.signup_button);
        email = (EditText) findViewById(R.id.signup_email);
        password = (EditText) findViewById(R.id.signup_password);
        mAuth = FirebaseAuth.getInstance();
        tologin = (TextView) findViewById(R.id.bcktolog);
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_up.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_up.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
// Ajouter un auditeur (listener) au bouton
        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer le contenu de l'email et du mot de passe
                String userEmail = email.getText().toString().trim();
                String userPassword = password.getText().toString().trim();
                String confirmpassword = confirmPassword.getText().toString().trim();

                // Vérifier si l'email et le mot de passe ne sont pas vides
                if (userEmail.isEmpty() || userPassword.isEmpty())
                    Toast.makeText(Sign_up.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                else {
                    if (!isValidEmail(userEmail))
                        Toast.makeText(Sign_up.this, "Format d'email invalide.", Toast.LENGTH_SHORT).show();
                    else if (!isEquals(userPassword,confirmpassword)) {
                        Toast.makeText(Sign_up.this, "Check your password", Toast.LENGTH_SHORT).show();
                    } else {
                        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(Sign_up.this, "Authentication Successful.",Toast.LENGTH_SHORT).show();

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(Sign_up.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }
}