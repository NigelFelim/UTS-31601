package id.ac.umn.uts_31601;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnConfirmLogin;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnConfirmLogin = findViewById(R.id.btnConfirmLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnConfirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Failed");
                builder.setMessage("Username atau Password salah !");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Silahkan coba lagi", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();

                Intent keHalamanListMusik = new Intent(LoginActivity.this, MainActivity2.class);

                if(username.equals("uasmobile") && password.equals("uasmobilegenap")){
                    startActivityForResult(keHalamanListMusik, 3);
                } else {
                    alertDialog.show();
                }
            }
        });
    }
}