package com.hsns.research;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class ChatActivity extends AppCompatActivity {

    private EditText mEdtSMS;
    private Button mBtnSend;
    private LinearLayout mLnSMS;
    private ScrollView mScrollView;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://chat.socket.io");
        } catch (URISyntaxException e) {
            Toast.makeText(ChatActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }

                    // add the message to view
                    addMessage(username, message);
                }
            });
        }
    };

    private void addMessage(String username, String message) {
        TextView textView = new TextView(this);
        textView.setText(username + " : " + message);
        textView.setGravity(Gravity.LEFT);
        mLnSMS.addView(textView);
        mScrollView.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();
        initEvents();
        mSocket.on("new message", onNewMessage);
        mSocket.connect();
    }

    private void attemptSend() {
        String message = mEdtSMS.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            return;
        }

        TextView textView = new TextView(this);
        textView.setText(message + " :Me");
        textView.setGravity(Gravity.RIGHT);
        mLnSMS.addView(textView);
        mScrollView.fullScroll(View.FOCUS_DOWN);

        mEdtSMS.setText("");
        mSocket.emit("new message", message);
    }

    private void initEvents() {
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSend();
            }
        });
    }

    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mLnSMS = (LinearLayout) findViewById(R.id.lnSMS);
        mEdtSMS = (EditText) findViewById(R.id.edtSMS);
        mBtnSend = (Button) findViewById(R.id.btnSend);
    }
}
