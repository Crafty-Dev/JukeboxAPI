package de.crafty.jukebox.api;

public interface EventListener {


    void onConnect();
    void onDisconnect();


    void onResponseReceived(ResponseMessage response);

}
