package com.jarvis.android.database;

/**
 * All schema of database
 * <p/>
 */
public class Schema {

    private static String TAG = Schema.class.getName();

    //Table Page
    public static String PAGE_NAME_TABLE = "page";
    public static String PAGE_ID = "PageID";
    public static String PAGE_NAME = "PageName";
    public static String PAGE_ACCESS_TOKEN = "PageAccessToken";
    public static String CREATE_PAGE_TABLE = "CREATE TABLE page (PageID TEXT PRIMARY KEY UNIQUE, PageName TEXT, PageAccessToken TEXT)";
    //Table Post
    public static String POST_NAME_TABLE = "post";
    public static String POST_ID = "PostID";
    public static String Page_ID_POST = "PageIDPost";
    public static String POST_MESSAGE = "PostMessage";
    public static String CREATE_AT = "createdAt";
    public static String UPDATE_AT = "updatedAt";
    public static String CREATE_POST_TABLE = "CREATE TABLE post (PostID TEXT PRIMARY KEY UNIQUE, PostMessage TEXT, createdAt REAL,PageIDPost TEXT)";
    //Table Post
    public static String COMMENT_NAME_TABLE = "comment";
    public static String COMMENT_ID = "CommentID";
    public static String POST_ID_COMMENT = "PostIDComment";
    public static String COMMENT_MESSAGE = "CommentMessage";
    public static String FROM_COMMENT_ID = "FromCommentID";
    public static String FROM_COMMENT_NAME = "FromCommentName";
    public static String PARENT_COMMENT = "ParentComment";
    public static String CREATE_COMMENT_TABLE = "CREATE TABLE comment (CommentID TEXT PRIMARY KEY UNIQUE, CommentMessage TEXT, createdAt REAL,PostIDComment TEXT,FromCommentID TEXT,FromCommentName TEXT, ParentCommentID TEXT)";

}
