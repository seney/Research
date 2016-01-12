package com.hsns.research;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

/**
 * Created by SENEY SEAN on 1/12/16.
 */
public class AudioRecordManager {
    private static final String LOG_TAG = "AudioRecordManager";

    private String fileName;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    private AudioRecordManagerListener audioRecordManagerListener;

    public AudioRecordManager() {
        fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/voice_profile.3gp";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MediaRecorder getMediaRecorder() {
        return mediaRecorder;
    }

    public void setMediaRecorder(MediaRecorder mediaRecorder) {
        this.mediaRecorder = mediaRecorder;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    public void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    public void startPlaying() {
        mediaPlayer = new MediaPlayer();
        try {
            audioRecordManagerListener.onStartPlaying(fileName);
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Log.i(LOG_TAG, fileName);
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void stopPlaying() {
        audioRecordManagerListener.onStopPlaying(fileName);
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void startRecording() {
        audioRecordManagerListener.onStartRecording();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(fileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mediaRecorder.start();
    }

    public void stopRecording() {
        audioRecordManagerListener.onStopRecording();
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    public AudioRecordManagerListener getAudioRecordManagerListener() {
        return audioRecordManagerListener;
    }

    public void setAudioRecordManagerListener(AudioRecordManagerListener audioRecordManagerListener) {
        this.audioRecordManagerListener = audioRecordManagerListener;
    }

    public interface AudioRecordManagerListener {
        void onStartRecording();
        void onStopRecording();
        void onStartPlaying(String fileName);
        void onStopPlaying(String fileName);
    }
}
