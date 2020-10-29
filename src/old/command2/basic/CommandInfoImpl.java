package com.pbo.movieBot.command2.basic;

import com.pbo.movieBot.command2.basic.base.CommandInfo;

public class CommandInfoImpl implements CommandInfo {
    private String syntaxExample = "N/A";
    private String shortInfo = "N/A";
    private String detailedInfo = "N/A";

    public CommandInfoImpl() {
    }

    @Override
    public String getSyntaxExample() {
        return syntaxExample;
    }

    public void setSyntaxExample(String syntaxExample) {
        this.syntaxExample = syntaxExample;
    }

    @Override
    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    @Override
    public String getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(String detailedInfo) {
        this.detailedInfo = detailedInfo;
    }
}
