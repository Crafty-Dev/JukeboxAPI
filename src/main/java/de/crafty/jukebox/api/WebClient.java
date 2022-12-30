package de.crafty.jukebox.api;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebClient extends WebSocketClient {


    private static WebClient instance;

    public WebClient(String host, int port) throws URISyntaxException {
        super(new URI("ws://" + host + ":" + port));
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        JukeboxAPI.callConnectionEvent();
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        JukeboxAPI.callDisconnectionEvent();

    }

    @Override
    public void onMessage(String message) {

        JSONObject json;
        try {
            json = new JSONObject(message);
        } catch (JSONException e) {
            return;
        }

        if (!json.has("responseHeader") || !json.has("responseLevel") || !json.has("responseMessage"))
            return;

        String header = json.getString("responseHeader");
        ResponseMessage.Level responseLevel = json.getEnum(ResponseMessage.Level.class, "responseLevel");
        String responseMessage = json.getString("responseMessage");

        String guild = json.isNull("guild") ? null : json.getString("guild");
        String guildId = json.isNull("guildId") ? null : json.getString("guildId");

        String channel = json.isNull("channel") ? null : json.getString("channel");
        String channelId = json.isNull("channelId") ? null : json.getString("channelId");

        String sender = json.isNull("sender") ? null : json.getString("sender");
        String senderId = json.isNull("senderId") ? null : json.getString("senderId");
        String source = json.isNull("source") ? null : json.getString("source");

        JukeboxAPI.onResponseReceived(new ResponseMessage(header, responseLevel, responseMessage, guild, guildId, channel, channelId, sender, senderId, source));
    }


    @Override
    public void onError(Exception ex) {

    }


    public void bind() {

        if (instance != null)
            throw new IllegalStateException("API is already bound");

        instance = this;
        this.connect();
    }

    public static WebClient getInstance() {
        return instance;
    }

    public static boolean isConnected() {
        return WebClient.getInstance() != null && WebClient.getInstance().isOpen();
    }
}
