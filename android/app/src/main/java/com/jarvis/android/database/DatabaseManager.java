package com.jarvis.android.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jarvis.android.model.PageFacebook;
import com.jarvis.android.model.PostComment;
import com.jarvis.android.model.PostPage;

import java.util.ArrayList;
import java.util.List;

/**
 * All SQL with local database will be called from here.
 * <p>
 */
public enum DatabaseManager {
    DB;
    private static String TAG = DatabaseManager.class.getName();
    private AppDb.JarvisDb mDbHelper;

    /**
     * This must be called when application starts.
     *
     * @param mDbHelper
     */
    public static void init(AppDb.JarvisDb mDbHelper) {
        DB.mDbHelper = mDbHelper;
    }

    public SQLiteDatabase getReadableDatabase() {
        return mDbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return mDbHelper.getWritableDatabase();
    }

    public void clearAndStopData() {
        mDbHelper.getWritableDatabase().delete(Schema.PAGE_NAME_TABLE, null, null);
        mDbHelper.getWritableDatabase().delete(Schema.POST_NAME_TABLE, null, null);
        mDbHelper.getWritableDatabase().delete(Schema.COMMENT_NAME_TABLE, null, null);

    }

    public void saveOrUpdatePageArray(List<PageFacebook> pageFacebooks) {

        for (PageFacebook pageFacebook : pageFacebooks) {
            saveOrUpdatePage(pageFacebook);
        }
    }

    public void saveOrUpdatePage(PageFacebook page) {
        String selection = "PageID = ?";
        String selectionArgs[] = new String[]{page.getPageId()};
        Cursor result = null;
        try {
            result = this.mDbHelper.getReadableDatabase().query(Schema.PAGE_NAME_TABLE, null, selection, selectionArgs, null, null, null);
            ContentValues values = new ContentValues();
            values.put(Schema.PAGE_NAME, page.getPageName());
            values.put(Schema.PAGE_ACCESS_TOKEN, page.getPageAccessToken());
            if (result == null || result.getCount() == 0) {
                values.put(Schema.PAGE_ID, page.getPageId());
                this.mDbHelper.getWritableDatabase().insert(Schema.PAGE_NAME_TABLE, null, values);
            } else {
                this.mDbHelper.getReadableDatabase().update(Schema.PAGE_NAME_TABLE, values, selection, selectionArgs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null)
                result.close();
        }
    }

    public List<PageFacebook> getAllPage() {
        List<PageFacebook> pageFacebooks = new ArrayList<PageFacebook>();
        String selectQuery = "SELECT * FROM page";
        SQLiteDatabase db = this.mDbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                PageFacebook pageFacebook = new PageFacebook();
                pageFacebook.setPageId(cursor.getString(cursor.getColumnIndex(Schema.PAGE_ID)));
                pageFacebook.setPageName(cursor.getString(cursor.getColumnIndex(Schema.PAGE_NAME)));
                pageFacebook.setPageAccessToken(cursor.getString(cursor.getColumnIndex(Schema.PAGE_ACCESS_TOKEN)));
                pageFacebooks.add(pageFacebook);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return pageFacebooks;
    }

    public void saveOrUpdatePostArray(List<PostPage> postPages) {

        for (PostPage postPage : postPages) {
            saveOrUpdatePost(postPage);
        }
    }

    public void saveOrUpdatePost(PostPage postPage) {
        String selection = "PostID = ?";
        String selectionArgs[] = new String[]{postPage.getPostId()};
        Cursor result = null;
        try {
            result = this.mDbHelper.getReadableDatabase().query(Schema.POST_NAME_TABLE, null, selection, selectionArgs, null, null, null);
            ContentValues values = new ContentValues();
            values.put(Schema.POST_MESSAGE, postPage.getMessage());
            values.put(Schema.Page_ID_POST, postPage.getPageId());
            values.put(Schema.CREATE_AT, postPage.getCreated().getTime());
            if (result == null || result.getCount() == 0) {
                values.put(Schema.POST_ID, postPage.getPostId());
                this.mDbHelper.getWritableDatabase().insert(Schema.POST_NAME_TABLE, null, values);
            } else {
                this.mDbHelper.getReadableDatabase().update(Schema.POST_NAME_TABLE, values, selection, selectionArgs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null)
                result.close();
        }
    }


    public void saveOrUpdateCommentArray(List<PostComment> postComments) {

        for (PostComment postComment : postComments) {
            saveOrUpdateComment(postComment);
        }
    }

    public void saveOrUpdateComment(PostComment postComment) {
        String selection = "CommentID = ?";
        String selectionArgs[] = new String[]{postComment.getCommentId()};
        Cursor result = null;
        try {
            result = this.mDbHelper.getReadableDatabase().query(Schema.COMMENT_NAME_TABLE, null, selection, selectionArgs, null, null, null);
            ContentValues values = new ContentValues();
            values.put(Schema.COMMENT_MESSAGE, postComment.getMessage());
            values.put(Schema.CREATE_AT, postComment.getCreated().getTime());
            values.put(Schema.FROM_COMMENT_ID, postComment.getFromUserComment().getUserId());
            values.put(Schema.FROM_COMMENT_NAME, postComment.getFromUserComment().getName());
            values.put(Schema.POST_ID_COMMENT, postComment.getPostId());
            if (postComment.getParentComment() != null)
                values.put(Schema.PARENT_COMMENT, postComment.getParentComment());
            if (result == null || result.getCount() == 0) {
                values.put(Schema.COMMENT_ID, postComment.getCommentId());
                this.mDbHelper.getWritableDatabase().insert(Schema.COMMENT_NAME_TABLE, null, values);
            } else {
                this.mDbHelper.getReadableDatabase().update(Schema.COMMENT_NAME_TABLE, values, selection, selectionArgs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result != null)
                result.close();
        }
    }
}