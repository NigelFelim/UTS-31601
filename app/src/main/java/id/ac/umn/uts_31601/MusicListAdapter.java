package id.ac.umn.uts_31601;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicListHolder> {
    private final ArrayList<String> musicFilesList;
    private LayoutInflater mInflater;
    private SeekBar seekBar;
    MediaPlayer mp = new MediaPlayer();

    MusicListAdapter(Context context, ArrayList<String> daftarMusik) {
        mInflater = LayoutInflater.from(context);
        musicFilesList = daftarMusik;
    }

    private void playMusicFile(String path) {
        try {
            if(!mp.isPlaying()) {
                mp.setDataSource(path);
                mp.prepare();
                mp.start();
                seekBar.setProgress(0);
                seekBar.setMax(mp.getDuration());
                seekBar.setProgress(mp.getCurrentPosition());
            } else {
                mp.stop();
                mp.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public MusicListAdapter.MusicListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View musicItemView = mInflater.inflate(R.layout.item, parent, false);
        return new MusicListHolder(musicItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicListAdapter.MusicListHolder holder, int position) {
        String mCurrent = musicFilesList.get(position);
        holder.MusicItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return musicFilesList.size();
    }

    class MusicListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView MusicItemView;
        final MusicListAdapter musicListAdapter;
        int mPosition;
        final String musicFilePath = musicFilesList.get(mPosition);

        public MusicListHolder(@NonNull View musicItemView, MusicListAdapter adapter) {
            super(musicItemView);
            MusicItemView = itemView.findViewById(R.id.myMusic);
            this.musicListAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            final String musicFilePath = musicFilesList.get(mPosition);
            playMusicFile(musicFilePath);
        }
    }
}


