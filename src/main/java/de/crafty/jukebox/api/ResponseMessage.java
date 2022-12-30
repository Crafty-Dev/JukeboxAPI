package de.crafty.jukebox.api;

public class ResponseMessage {


    private final String header;
    private final Level responseLevel;
    private final String responseMessage;

    private final String guild, guildId;
    private final String channel, channelId;
    private final String sender, senderId;
    private final String source;

    public ResponseMessage(String header, Level responseLevel, String message, String guild, String guildId, String channel, String channelId, String sender, String senderId, String source){
        this.header = header;
        this.responseLevel = responseLevel;
        this.responseMessage = message;

        this.guild = guild;
        this.guildId = guildId;

        this.channel = channel;
        this.channelId = channelId;

        this.sender = sender;
        this.senderId = senderId;
        this.source = source;

    }


    //Returns the messag
    public String getHeader() {
        return this.header;
    }


    //Returns the Response Type
    public Level getLevel() {
        return this.responseLevel;
    }

    //Returns the Response Message
    public String getResponseMessage() {
        return this.responseMessage;
    }


    //Returns the guild name
    public String getGuild() {
        return this.guild;
    }

    //Returns the guild id
    public String getGuildId() {
        return this.guildId;
    }


    //Returns the channel name
    public String getChannel() {
        return this.channel;
    }

    //Returns the channel id
    public String getChannelId() {
        return this.channelId;
    }


    //Returns the sender's name
    public String getSender() {
        return this.sender;
    }


    //Returns the sender's id
    public String getSenderId() {
        return this.senderId;
    }


    //Returns the location from where the sender has sent the message
    //Returns null if there is no sender to refer
    public String getSource() {
        return this.source;
    }

    public enum Level {

        IMPORTANT,
        INFO,
        FAIL,
        ERROR



    }
}
