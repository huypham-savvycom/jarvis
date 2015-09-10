package com.jarvis.android.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kingo_000 on 08/09/2015.
 */
public class PostComment {
//    "data":[
//
//    {
//        "from":{
//        "name":"Sâu Xinh Xắn",
//                "id":"1512460295709630"
//    },
//        "message":"kg gj cả",
//            "created_time":"2015-05-12T10:52:00+0000",
//            "id":"568528856623447_568584329951233"
//    }
//
//    ],
//
//    public static String COMMENT_ID = "CommentID";
//    public static String POST_ID_COMMENT = "PostIDComment";
//    public static String COMMENT_MESSAGE = "CommentMessage";
//    public static String FROM_COMMENT_ID = "FromCommentID";
//    public static String FROM_COMMENT_NAME = "FromCommentName";
//    public static String PARENT_COMMENT = "ParentComment";
    @SerializedName("id")
    private String commentId;
    @SerializedName("message")
    private String message;
    @SerializedName("created_time")
    private UnixTime created;
    private String postId;
    private String parentComment;
    @SerializedName("from")
    private FromUserComment fromUserComment;

   public class FromUserComment {
        @SerializedName("id")
        private String userId;
        @SerializedName("name")
        private String name;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getParentComment() {
        return parentComment;
    }

    public void setParentComment(String parentComment) {
        this.parentComment = parentComment;
    }

    public FromUserComment getFromUserComment() {
        return fromUserComment;
    }

    public void setFromUserComment(FromUserComment fromUserComment) {
        this.fromUserComment = fromUserComment;
    }
}
