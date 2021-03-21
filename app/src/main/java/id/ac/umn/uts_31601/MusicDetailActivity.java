package id.ac.umn.uts_31601;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicDetailActivity extends AppCompatActivity {
    private Button btnBackward, btnPausePlay, btnForward;
    private TextView tvJudul;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);

        btnBackward = findViewById(R.id.btnBackward);
        btnPausePlay = findViewById(R.id.btnPausePlay);
        btnForward = findViewById(R.id.btnForward);
        tvJudul = findViewById(R.id.tvJudul);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
    }
}