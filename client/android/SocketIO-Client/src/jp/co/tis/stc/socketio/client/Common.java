package jp.co.tis.stc.socketio.client;

public class Common {
    public static final String HOST = "xxxx.xxxx.dotcloud.com";
    public static final String PORT = "80";
    public static final String SEND_KEY = "message";
    public static final String RECV_KEY = "broadcast";
    
    public static String getURL() {
        return String.format("http://%s:%s", HOST, PORT);
    }
}
