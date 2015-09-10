package com.jarvis.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kingo_000 on 08/09/2015.
 */
public class PageFacebook {
    @SerializedName("id")
    private String pageId;
    @SerializedName("name")
    private String pageName;
    @SerializedName("access_token")
    private String pageAccessToken;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageAccessToken() {
        return pageAccessToken;
    }

    public void setPageAccessToken(String pageAccessToken) {
        this.pageAccessToken = pageAccessToken;
    }
}
