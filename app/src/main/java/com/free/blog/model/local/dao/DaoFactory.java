package com.free.blog.model.local.dao;

import android.content.Context;

import com.free.blog.model.local.dao.impl.BlogCollectDaoImpl;
import com.free.blog.model.local.dao.impl.BlogCommentDaoImpl;
import com.free.blog.model.local.dao.impl.BlogContentDaoImpl;
import com.free.blog.model.local.dao.impl.BlogHistoryDaoImpl;
import com.free.blog.model.local.dao.impl.BlogItemDaoImpl;
import com.free.blog.model.local.dao.impl.BloggerDaoImpl;
import com.free.blog.model.local.dao.impl.ChannelBloggerDaoImpl;


/**
 * 数据库工厂类
 * 
 * @author Frank
 * @since 2015年8月22日上午10:26:06
 */

public class DaoFactory {

	private static DaoFactory mInstance = null;

	/**
	 * 获取DaoFactory的实例
	 */
	public static DaoFactory create() {
		if (mInstance == null) {
			synchronized (DaoFactory.class) {
				if (mInstance == null) {
					mInstance = new DaoFactory();
				}
			}
		}
		return mInstance;
	}

	/**
	 * 获取博主数据库
	 */
	public BloggerDao getBloggerDao(Context context, String type) {
		return new BloggerDaoImpl(context, type);
	}

	/**
	 * 获取博客列表数据库
	 */
	public BlogItemDao getBlogItemDao(Context context, String userId) {
		return new BlogItemDaoImpl(context, userId);
	}

	/**
	 * 获取博客内容数据库
	 */
	public BlogContentDao getBlogContentDao(Context context) {
		return new BlogContentDaoImpl(context);
	}

	/**
	 * 获取博客收藏数据库
	 */
	public BlogCollectDao getBlogCollectDao(Context context) {
		return new BlogCollectDaoImpl(context);
	}

	/**
	 * 获取博客收藏数据库
	 */
	public BlogHistoryDao getBlogHistoryDao(Context context) {
		return new BlogHistoryDaoImpl(context);
	}

	/**
	 * 获取博客评论数据库
	 */
	public BlogCommentDao getBlogCommentDao(Context context, String blogId) {
		return new BlogCommentDaoImpl(context, blogId);
	}

	/**
	 * 获取某频道-博客专家数据库
	 */
	@SuppressWarnings("unused")
	public ChannelBloggerDao getChannelBloggerDao(Context context, String channelName) {
		return new ChannelBloggerDaoImpl(context, channelName);
	}
}
