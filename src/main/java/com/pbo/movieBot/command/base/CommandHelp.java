package com.pbo.movieBot.command.base;

import java.util.List;

public interface CommandHelp {
    List<String> getSyntaxList();
    String getShortInfo();
    String getDetailedInfo();
    List<String> getUseExamples();
}
