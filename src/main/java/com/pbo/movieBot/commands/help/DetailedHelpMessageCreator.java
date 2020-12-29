package com.pbo.movieBot.commands.help;

import com.pbo.movieBot.command.base.Command;
import com.pbo.movieBot.command.base.CommandHelp;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.List;

public class DetailedHelpMessageCreator {
    private static final Character DOT_CHARACTER = '\u2022';

    public DetailedHelpMessageCreator() {
    }

    public static Message getDetailedInfoFromCommand(Command command) {
        CommandHelp help = command.getHelp();

        EmbedBuilder builder = new EmbedBuilder();
        builder
                .setTitle(command.getName())
                .setDescription(help.getDetailedInfo())
                .setColor(Color.lightGray);

        appendList(builder, "Syntax", help.getSyntaxList());
        appendList(builder, "Examples", help.getUseExamples());

        return getMessageFromEmbed(builder.build());
    }

    private static void appendList(EmbedBuilder builder, String name, List<String> items) {
        StringBuilder listStringBuilder = new StringBuilder();

        for(String item : items) {
            listStringBuilder.append(getListItemFromString(item));
        }

        if(items.size() == 0) {
            listStringBuilder.append("-- none --");
        }

        builder.addField(name, listStringBuilder.toString(), false);
    }

    private static String getListItemFromString(String s) {
        return DOT_CHARACTER + " " + s + System.lineSeparator();
    }

    private static Message getMessageFromEmbed(MessageEmbed embed) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setEmbed(embed);
        return messageBuilder.build();
    }

}
