package de.crafty.jukebox.api;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JukeboxAPI {


    private static final List<EventListener> LISTENERS = new ArrayList<>();

    public static void bind(String host, int port) {

        try {
            new WebClient(host, port).bind();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to bind");
        }

    }

    public static void disconnect() {
        if (!WebClient.isConnected())
            throw new IllegalStateException("API is not connected to a Jukebox");

        WebClient.getInstance().close();

    }


    public static void executeCommand(String apiKey, String command, String source) {
        if (!WebClient.isConnected())
            throw new IllegalStateException("API is not connected to a Jukebox");

        JSONObject json = new JSONObject();
        json.put("request", "command");
        json.put("key", apiKey);
        json.put("content", command);

        if(source != null)
            json.put("source", source);

        WebClient.getInstance().send(json.toString());
    }

    public static void executeCommand(String apiKey, String command) {
        executeCommand(apiKey, command, null);
    }


    public static void registerListener(EventListener listener) {
        LISTENERS.add(listener);
    }


    protected static void callConnectionEvent() {
        LISTENERS.forEach(EventListener::onConnect);
    }

    protected static void callDisconnectionEvent() {
        LISTENERS.forEach(EventListener::onDisconnect);
    }

    protected static void onResponseReceived(ResponseMessage responseMessage) {
        LISTENERS.forEach(listener -> listener.onResponseReceived(responseMessage));
    }


}
