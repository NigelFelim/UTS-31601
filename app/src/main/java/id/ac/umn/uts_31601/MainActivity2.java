package id.ac.umn.uts_31601;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ArrayList<String> musicFilesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MusicListAdapter mAdapter;
    private SeekBar seekBar;
    private Button btnBackward, btnPausePlay, btnForward;

    private void addMusicFilesFrom(String dirPath) {
        final File musicDir = new File(dirPath);
        if(!musicDir.exists()) {
            musicDir.mkdir();
            return;
        }
        final File[] files = musicDir.listFiles();
        for(File file : files) {
            final String path = file.getAbsolutePath();
            if(path.endsWith(".mp3") || path.endsWith(".ogg")) {
                musicFilesList.add(path);
            }
        }
    }

    private void fillMusicList() {
        musicFilesList.clear();
        addMusicFilesFrom(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)));
        addMusicFilesFrom(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btnBackward = findViewById(R.id.btnBackward);
        btnPausePlay = findViewById(R.id.btnPausePlay);
        btnForward = findViewById(R.id.btnForward);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selamat Datang");
        builder.setMessage("Nigel Crispianius Felim - 00000031601");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Selamat mencoba aplikasi", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new MusicListAdapter(this, musicFilesList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fillMusicList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnProfile) {
            Intent keHalamanProfile = new Intent(MainActivity2.this, ProfileActivity.class);
            startActivityForResult(keHalamanProfile, 5);
        }

        if (id == R.id.btnLogout) {
            Intent keHalamanUtama = new Intent(MainActivity2.this, MainActivity.class);
            startActivityForResult(keHalamanUtama, 6);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}