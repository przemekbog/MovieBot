package com.pbo.movieBot.bot.options;

class ConfigurationHolder {
    private String movieAPIKey = "not set";
    private String discordAPIKey = "not set";
    private String statusMessage = "";
    private long defaultChannelId;

    public String getMovieAPIKey() {
        return movieAPIKey;
    }

    public String getDiscordAPIKey() {
        return discordAPIKey;
    }

    public long getDefaultChannelId() {
        return defaultChannelId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setDefaultChannelId(long defaultChannelId) {
        this.defaultChannelId = defaultChannelId;
    }
}
