package com.twoghadimoj.easynewsnavigatorforyt;

public class Channel {
    private int channelId;
    private Language language;
    private String ChannelYTID;

    public Channel(int channelId, Language language, String channelID) {
        this.channelId = channelId;
        this.language = language;
        ChannelYTID = channelID;
    }

    public int getChannelId() {
        return channelId;
    }

    public Language getLanguage() {
        return language;
    }

    public String getChannelYTID() {
        return ChannelYTID;
    }
    public String getChannelLiveURL(){
        return "https://www.youtube.com/channel/"+ChannelYTID+"/live";
    }
}
