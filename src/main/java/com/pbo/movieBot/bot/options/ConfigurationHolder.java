package com.pbo.movieBot.bot.options;

class ConfigurationHolder {
    private String movieAPIKey = "not set";
    private String discordAPIKey = "not set";
    private String statusMessage = "";
    private long announcementChannelId;
    private long reminderChannelId;

    public String getMovieAPIKey() {
        return movieAPIKey;
    }

    public String getDiscordAPIKey() {
        return discordAPIKey;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public long getAnnouncementChannelId() {
        return announcementChannelId;
    }

    public void setAnnouncementChannelId(long announcementChannelId) {
        this.announcementChannelId = announcementChannelId;
    }
}
