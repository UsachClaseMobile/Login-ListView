package cl.rodrigoayala.ejemplo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.login_title);

        Button loginButton = (Button)findViewById(R.id.login_button);
        final EditText mailEditText = (EditText)findViewById(R.id.mail_edittext);
        final EditText passwordEditText = (EditText)findViewById(R.id.password_edittext);


        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String mail = mailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    if (mail.isEmpty()) {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Error")
                                .setMessage("El mail no puede estar vacío")
                                .setNegativeButton("Okey", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create().show();
                    } else if (password.isEmpty()){
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Error")
                                .setMessage("La contraseña no puede estar vacía")
                                .setNegativeButton("Okey", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create().show();
                    } else {
                        // Mail y password completados

                        if (mail.equals("mail") && password.equals("password")) {
                            // TODO: Ir a siguiente pantalla
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);

                            finish();

                        } else {
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Error")
                                    .setMessage("Usuario y clave incorrectos")
                                    .setNegativeButton("Okey", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).create().show();
                        }
                    }
                }
            });
        }
    }
}
