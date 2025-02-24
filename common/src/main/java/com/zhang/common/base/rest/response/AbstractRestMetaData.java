package com.zhang.common.base.rest.response;

public class AbstractRestMetaData {
    private String uri;
    private String stats;

    public AbstractRestMetaData(String url, String stats) {
        this.uri = url;
        this.stats = stats;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
