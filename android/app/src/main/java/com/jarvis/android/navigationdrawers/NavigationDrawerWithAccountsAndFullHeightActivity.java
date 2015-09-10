package com.jarvis.android.navigationdrawers;

import android.os.Bundle;
import android.view.View;

import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerStyleHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.models.Account;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.jarvis.android.R;
import com.jarvis.android.database.DatabaseManager;
import com.jarvis.android.model.PageFacebook;
import com.jarvis.android.model.PostComment;
import com.jarvis.android.model.PostPage;
import com.jarvis.android.ui.fragment.ListViewFragment;
import com.jarvis.android.utils.GsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerWithAccountsAndFullHeightActivity
        extends com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity {
    private List<PageFacebook> pageFacebooks;
//    private boolean check = true;

    @Override
    protected void onResume() {
        super.onResume();

        loadData(getNavigationDrawerAccountsHandler().getNavigationDrawerAccounts().get(1));
    }

    @Override
    public NavigationDrawerStyleHandler getNavigationDrawerStyleHandler() {
        return null;
    }

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        pageFacebooks = DatabaseManager.DB.getAllPage();
        NavigationDrawerAccountsHandler navigationDrawerAccountsHandler = new NavigationDrawerAccountsHandler(this);
        for (PageFacebook pageFacebook : pageFacebooks) {
            navigationDrawerAccountsHandler.addAccount(pageFacebook.getPageName(), pageFacebook.getPageId(), String.format("https://graph.facebook.com/%s/picture?access_token=%s", pageFacebook.getPageId(), AccessToken.getCurrentAccessToken().getToken()), R.color.hint_foreground_material_light);
        }

        return navigationDrawerAccountsHandler;

    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return new NavigationDrawerAccountsMenuHandler(this)
                .addManageAccounts(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                    }

                });
    }

    @Override
    public void onNavigationDrawerAccountChange(Account account) {
        loadData(account);

    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler(this)
                .addItem(R.string.post, new ListViewFragment());
    }

    @Override
    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return null;
    }

    @Override
    public boolean overlayActionBar() {
        return true;
    }

    @Override
    public boolean replaceActionBarTitleByNavigationDrawerItemTitle() {
        return true;
    }

    @Override
    public int defaultNavigationDrawerItemSelectedPosition() {
        return 1;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return false;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

    private void loadData(final Account account) {
        if (AccessToken.getCurrentAccessToken() != null) {
            int j = 0;
            while (j < 10000) {
                GraphRequest request = GraphRequest.newGraphPathRequest(
                        AccessToken.getCurrentAccessToken(), String.format("/%s/posts", account.getDescription()),
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                JSONObject result = response.getJSONObject();
                                JSONArray data = result != null ? result.optJSONArray("data") : null;
                                List<PostPage> postPages = new ArrayList<PostPage>();
                                if (data == null || data.length() == 0) {
                                    return;
                                }
                                for (int i = 0; i < data.length(); i++) {
                                    try {
                                        final PostPage postPage = GsonUtils.getGson().fromJson(data.get(i).toString(), PostPage.class);
                                        postPage.setPageId(account.getDescription());
                                        postPages.add(postPage);

                                        GraphRequest request = GraphRequest.newGraphPathRequest(
                                                AccessToken.getCurrentAccessToken(), "/comments",
                                                new GraphRequest.Callback() {
                                                    @Override
                                                    public void onCompleted(GraphResponse graphResponse) {
                                                        JSONObject result = graphResponse.getJSONObject();
                                                        JSONObject commentData = result.optJSONObject(postPage.getPostId());
                                                        JSONArray data = commentData != null ? commentData.optJSONArray("data") : null;
                                                        List<PostComment> postComments = new ArrayList<PostComment>();
                                                        if (data != null) {
                                                            for (int i = 0; i < data.length(); i++) {
                                                                try {
                                                                    final PostComment postComment = GsonUtils.getGson().fromJson(data.get(i).toString(), PostComment.class);
                                                                    postComment.setPostId(postPage.getPostId());
                                                                    postComments.add(postComment);
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                            if (!postComments.isEmpty()) {
                                                                DatabaseManager.DB.saveOrUpdateCommentArray(postComments);
                                                            }
                                                        }
                                                    }
                                                });

                                        Bundle parameters = new Bundle();
                                        parameters.putString("ids", postPage.getPostId());
                                        request.setParameters(parameters);
                                        request.executeAsync();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (!postPages.isEmpty()) {
                                    DatabaseManager.DB.saveOrUpdatePostArray(postPages);
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("limit", "100");
                parameters.putString("offset", String.valueOf(j));
                request.setParameters(parameters);
                request.executeAsync();
                j += 100;
            }
        }
    }
}
