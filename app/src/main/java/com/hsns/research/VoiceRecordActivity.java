package com.hsns.research;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * created by SENEY SEAN
 * since 12-01-2016
 */
public class VoiceRecordActivity extends AppCompatActivity implements AudioRecordManager.AudioRecordManagerListener {

    boolean mStartRecording = true;
    boolean mStartPlaying = true;
    private AudioRecordManager audioRecordManager;
    private Button buttonRecording, buttonPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_record);

        initViews();
        initListeners();
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    private void initListeners() {
        audioRecordManager.setAudioRecordManagerListener(this);
        buttonPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecordManager.onPlay(mStartPlaying);
                mStartPlaying = !mStartPlaying;
            }
        });

        buttonRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecordManager.onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });
    }

    private void initViews() {
        audioRecordManager = new AudioRecordManager();
        buttonPlaying = (Button) findViewById(R.id.buttonPlay);
        buttonRecording = (Button) findViewById(R.id.buttonRec);
    }

    @Override
    public void onStartRecording() {
        Toast.makeText(this, "Start Record", Toast.LENGTH_LONG).show();
        buttonRecording.setText("STOP RECORDING");
    }

    @Override
    public void onStopRecording() {
        Toast.makeText(this, "Stop Record", Toast.LENGTH_LONG).show();
        buttonRecording.setText("START RECORDING");
    }

    @Override
    public void onStartPlaying(String fileName) {
        Toast.makeText(this, "Start Playing : " + fileName, Toast.LENGTH_LONG).show();
        buttonPlaying.setText("STOP PLAYING");
    }

    @Override
    public void onStopPlaying(String fileName) {
        Toast.makeText(this, "Stop Playing : " + fileName, Toast.LENGTH_LONG).show();
        buttonPlaying.setText("START PLAYING");
    }

    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(500);
        // This view will not be affected by enter transition animation
//        enterTransition.excludeTarget(R.id.buttonRec, true);
        return enterTransition;
    }

    private Visibility buildReturnTransition() {
        Visibility enterTransition = new Slide();
        enterTransition.setDuration(500);
        return enterTransition;
    }
}
