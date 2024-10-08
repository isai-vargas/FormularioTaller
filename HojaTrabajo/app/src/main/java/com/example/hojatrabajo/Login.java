package com.example.hojatrabajo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Login extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    private EditText emailEditText;
    private EditText passwordEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.d("Login", "Inicio de la actividad Login");

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Login", "Intentando iniciar sesión con email");
                signInWithEmail();
            }
        });

        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpWithEmail();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("608955879953-8aabpv5g04n4rddai4e00b5gvo1pp326.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Button googleSignInButton = findViewById(R.id.googleSignInButton);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Login", "Intentando registrar usuario");
                signInWithGoogle();
            }
        });
    }

    private boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com") && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void signInWithEmail() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Por favor ingrese un correo de Gmail válido", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Login", "Inicio de sesión exitoso");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Login.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                            // Redirigir a la actividad principal
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        } else {
                            Exception exception = task.getException();
                            Log.e("Login", "Error en el inicio de sesión: " + exception.getMessage());
                            handleSignInError(exception);
                        }
                    }
                });
    }

    private void signUpWithEmail() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Por favor ingrese un correo de Gmail válido", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Login.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        } else {
                            Exception exception = task.getException();
                            handleSignUpError(exception);
                        }
                    }
                });
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                int errorCode = e.getStatusCode();
                String errorMessage = "Error en el inicio de sesión: " + getErrorMessage(errorCode);
                Log.w("Login", errorMessage);
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getErrorMessage(int errorCode) {
        switch (errorCode) {
            case 10:
                return "Error: La configuración de la API es incorrecta. Verifica el ID de cliente.";
            case 12501:
                return "Error: El usuario canceló el inicio de sesión.";
            case 12500:
                return "Error: Se produjo un error interno.";
            default:
                return "Error desconocido. Código: " + errorCode;
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Login.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        } else {
                            Exception exception = task.getException();
                            handleSignInError(exception);
                        }
                    }
                });
    }

    private void handleSignInError(Exception exception) {
        if (exception instanceof FirebaseAuthInvalidUserException) {
            Toast.makeText(Login.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(Login.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Login.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void handleSignUpError(Exception exception) {
        if (exception instanceof FirebaseAuthUserCollisionException) {
            Toast.makeText(Login.this, "Este correo ya está registrado", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthWeakPasswordException) {
            Toast.makeText(Login.this, "La contraseña es demasiado débil", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Login.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
