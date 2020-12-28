package com.pbo.movieBot.command.base;

import java.util.List;

public interface CommandHelp {
    String getSyntax();
    String getShortInfo();
    String getDetailedInfo();
    List<String> getUseExamples();
}
