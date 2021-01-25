package com.pbo.movieBot.bot.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class StringInEmbedFactory {
    private Color color = Color.LIGHT_GRAY;

    public StringInEmbedFactory(Color color) {
        this.color = color;
    }

    public MessageEmbed embedWithText(String text) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setColor(color);
        builder.setDescription(text);

        return builder.build();
    }
}
