package com.free.blog.model.remote;

import com.free.blog.library.config.Config;

import java.util.HashMap;

import rx.Observable;

/**
 * @author tangqi on 17-3-14.
 */
public class NetEngine {

    private final IBlogApi mBlogApi;
    private static final NetEngine sInstance = new NetEngine();

    public static NetEngine getInstance() {
        return sInstance;
    }

    private NetEngine() {
        mBlogApi = RetrofitClient.getInstance().getNetApi();
    }

    public Observable<String> getBloggerInfo(String userId) {
        return mBlogApi.getBloggerInfo(userId);
    }

    public Observable<String> getBlogList(String userId, int page) {
        return mBlogApi.getBlogList(userId, page);
    }

    public Observable<String> getCategoryBlogList(String category, int page) {
        return mBlogApi.getCategoryBlogList(category, page);
    }

    public Observable<String> getHtml(String url) {
        return mBlogApi.getHtml(url);
    }

    public Observable<String> getHtmlByPage(String url, int page) {
        return mBlogApi.getHtmlByPage(url, page);
    }

    public Observable<String> getBlogComment(String blogId, int page) {
        return mBlogApi.getBlogComment(blogId, page);
    }

    public Observable<String> getBlogRank() {
        return mBlogApi.getBlogRank();
    }

    public Observable<String> getSearchBlog(String keywords) {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("q", keywords);
        queryMap.put("t", "blog");
        queryMap.put("l", "");
        return mBlogApi.getSearchBlog(queryMap);
    }

    public String getBlogPkUrl() {
        return Config.BLOG_HOST + "PK.html";
    }

    public String getBlogDaily() {
        return Config.BLOG_HOST + "column/details/14549.html";
    }
}
