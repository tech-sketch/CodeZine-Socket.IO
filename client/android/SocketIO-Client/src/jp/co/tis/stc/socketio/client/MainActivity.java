package jp.co.tis.stc.socketio.client;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private static final String TAG = "SocketIO-Client";
    
    private SocketIO socketIO = null;
    
    private EditText msg = null;
    private Button send = null;
    private EditText msgArea = null;
    
    private Spinner spinner = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        
        msg = (EditText)findViewById(R.id.msg);
        msgArea = (EditText)findViewById(R.id.msgArea);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(onClickSend);
        
        spinner = new Spinner();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        spinner.show();
        connect();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        disconnect();
        super.onPause();
    }

    private IOCallback socketIOCallback = new IOCallback() {
        @Override
        public void onMessage(JSONObject json, IOAcknowledge ack) {
            throw new UnsupportedOperationException("onMessage is not supported");
        }
        @Override
        public void onMessage(String str, IOAcknowledge ack) {
            throw new UnsupportedOperationException("onMessage is not supported");
        }
        @Override
        public void onConnect() {
            Log.d(TAG, "onConnect");
            send.post(new Runnable() {
                @Override
                public void run() {
                    send.setEnabled(true);
                    spinner.hide();
                }
            });
        }
        @Override
        public void onDisconnect() {
            Log.d(TAG, "onDisconnect");
        }
        @Override
        public void onError(SocketIOException e) {
            Log.d(TAG, "onError");
            connect();
        }
        @Override
        public void on(String eventName, IOAcknowledge ack, Object... data) {
            Log.d(TAG, String.format("on %s, %s", eventName, data[0].toString()));
            if (Common.RECV_KEY.equals(eventName)) {
                final String received = data[0].toString();
                msgArea.post(new Runnable() {
                    @Override
                    public void run() {
                        msgArea.append(String.format("%s%n", received));
                    }
                });
            }
        }
    };
    
    private View.OnClickListener onClickSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String message = msg.getText().toString();
            if (message != null && message.length() != 0) {
                socketIO.emit(Common.SEND_KEY, message);
                msg.setText("");
            }
        }
    };
    
    private void connect() {
        try {
            socketIO = new SocketIO(Common.getURL(), socketIOCallback);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void disconnect() {
        socketIO.disconnect();
    }
    
    private class Spinner {
        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        public void show() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(MainActivity.this.getString(R.string.dialog));
            dialog.show();
        }
        public void hide() {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
