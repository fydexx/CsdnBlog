package com.free.blog.domain.config;

import android.content.Context;

import com.free.blog.domain.util.FileUtils;

import java.io.File;

/**
 * App缓存管理
 *
 * @author tangqi
 * @since 2015年8月8日上午11:35:33
 */

public class CacheManager {

    /**
     * 获取外部缓存目录
     */
    public static String getExternalCachePath(Context context) {
        return FileUtils.getExternalCacheDir(context);
    }

    /**
     * 获取博客收藏数据库目录
     */
    public static String getBloggerCollectDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "BlogCollect";
    }

    /**
     * 获取博主数据库目录
     */
    @SuppressWarnings("unused")
    public static String getBloggerDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "Blogger";
    }

    /**
     * 获取博客列表数据库目录
     */
    public static String getBlogListDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "BlogList";
    }

    /**
     * 获取博客内容数据库目录
     */
    public static String getBlogContentDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "BlogContent";
    }

    /**
     * 获取评论数据库目录
     */
    public static String getBlogCommentDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "CommentList";
    }

    /**
     * 获取某频道博主数据库目录
     */
    public static String getChannelBloggerDbPath(Context context) {
        return getExternalCachePath(context) + File.separator + "ChannelBlogger";
    }

    /**
     * 获取WebView缓存目录
     */
    public static String getAppCachePath(Context context) {
        return getExternalCachePath(context) + File.separator + "App" + File.separator + "Cache";
    }

    /**
     * 获取WebView数据库目录
     */
    public static String getAppDatabasePath(Context context) {
        return getExternalCachePath(context) + File.separator + "App" + File.separator + "DataBase";
    }
}
