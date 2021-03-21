package id.ac.umn.uts_31601;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnProfil, btnLogin;

    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int REQUEST_PERMISSION = 12345;
    private static final int PERMISSION_COUNT = 1;

    @SuppressLint("NewApi")
    private boolean arePermissionsDenied() {
        for(int i = 0; i < PERMISSION_COUNT; i++) {
            if(checkSelfPermission(PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(arePermissionsDenied()) {
            ((ActivityManager) (this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
            recreate();
        } else {
            onResume();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && arePermissionsDenied()) {
            requestPermissions(PERMISSIONS, REQUEST_PERMISSION);
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProfil = findViewById(R.id.btnProfil);
        btnLogin = findViewById(R.id.btnLogin);

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keHalamanProfil = new Intent(MainActivity.this, ProfileActivity.class);
                startActivityForResult(keHalamanProfil, 1);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keHalamanLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(keHalamanLogin, 2);
            }
        });
    }
}