package com.jarvis.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kingo_000 on 08/09/2015.
 */
public class PostPage {

    @SerializedName("id")
    private String postId;
    @SerializedName("message")
    private String message;
    @SerializedName("created_time")
    private UnixTime created;
    private String pageId;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UnixTime getCreated() {
        return created;
    }

    public void setCreated(UnixTime created) {
        this.created = created;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
