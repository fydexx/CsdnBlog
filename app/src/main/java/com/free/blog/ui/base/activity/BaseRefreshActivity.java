package com.free.blog.ui.base.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.free.blog.R;
import com.free.blog.library.view.recyclerview.DividerItemDecoration;
import com.free.blog.library.view.refresh.PullRefreshHandler;
import com.free.blog.library.view.refresh.PullRefreshLayout;
import com.free.blog.ui.base.adapter.BaseViewAdapter;
import com.free.blog.ui.base.vp.refresh.IRefreshPresenter;
import com.free.blog.ui.base.vp.refresh.IRefreshView;
import com.free.blog.ui.base.vp.refresh.RefreshPresenter;

import java.util.List;

/**
 * @author studiotang on 17/3/18
 */
public abstract class BaseRefreshActivity<T> extends BaseActivity implements
        IRefreshView<T, IRefreshPresenter>, IBaseRefresh,
        BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener,
        BaseQuickAdapter.OnItemClickListener {

    protected RefreshPresenter mPresenter;
    protected PullRefreshLayout mPtrFrameLayout;
    protected RecyclerView mRecyclerView;
    protected BaseViewAdapter mAdapter;
    protected TextView mTvTitle;

    protected abstract String getActionBarTitle();

    protected abstract void beforeInitView();

    protected abstract BaseViewAdapter onCreateAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_base_refresh);

        beforeInitView();
        initView();
    }

    protected void initView() {
        initActionBar();

        mPtrFrameLayout = (PullRefreshLayout) findViewById(R.id.base_ptr_frame);
        mRecyclerView = (RecyclerView) findViewById(R.id.base_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAnimation(null);
        //noinspection deprecation
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL, getResources().getColor(R.color.line)));

        mAdapter = onCreateAdapter();
        mAdapter.setOnItemClickListener(this);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);

        mPtrFrameLayout.setPtrHandler(new PullRefreshHandler() {

            @Override
            protected void onRefresh() {
                loadInitData();
            }
        });

        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                doRefresh();
            }
        }, 0);
    }

    private void initActionBar() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        ImageView mBackBtn = (ImageView) findViewById(R.id.btn_back);
        ImageView mMenuBtn = (ImageView) findViewById(R.id.btn_menu);
        mBackBtn.setOnClickListener(this);
        mMenuBtn.setOnClickListener(this);
        mMenuBtn.setVisibility(isShowMenu() ? View.VISIBLE : View.GONE);
        mMenuBtn.setImageResource(R.drawable.ic_menu);

        setActionBarTitle(getActionBarTitle());
    }

    protected void setActionBarTitle(String title) {
        mTvTitle.setText(title);
    }

    protected boolean isShowMenu() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
        mPtrFrameLayout.refreshComplete();
    }

    @Override
    public IRefreshPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(IRefreshPresenter presenter) {
        mPresenter = (RefreshPresenter) presenter;
        mPresenter.subscribe();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMoreData();
    }

    @Override
    public void doRefresh() {
        mPtrFrameLayout.autoRefresh(false);
        mAdapter.isUseEmpty(false);
    }

    @Override
    public void loadInitData() {
        mPresenter.loadRefreshData();
    }

    @Override
    public void loadMoreData() {
        mPresenter.loadMoreData();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onRefreshUI(T data) {
        onRefreshComplete();

        List<T> list = (List<T>) data;
        mAdapter.setNewData(list);
        mAdapter.setEnableLoadMore(hasMore(data));
    }

    @Override
    public void onRefreshFailure(int errNo) {
        onRefreshComplete();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onMoreUI(T data) {
        List<T> list = (List<T>) data;
        if (list != null) {
            mAdapter.addData(list);
            mAdapter.loadMoreComplete();
            mAdapter.setEnableLoadMore(hasMore(data));
        } else {
            mAdapter.loadMoreFail();
            mAdapter.setEnableLoadMore(false);
        }
    }

    @Override
    public void onMoreFailure(int errNo) {
        mAdapter.loadMoreFail();
    }

    @SuppressWarnings("unchecked")
    protected boolean hasMore(T data) {
        List<T> list = (List<T>) data;
        return mPresenter.hasMore(list != null ? list.size() : 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_menu:
                showMenu(view);
                break;

            case R.id.iv_reload:
                doRefresh();
                break;

            default:
                break;
        }
    }

    protected void onRefreshComplete() {
        mPtrFrameLayout.refreshComplete();

        if (mAdapter.getItemCount() == 0) {
            if (mAdapter.getEmptyView() == null) {
                setEmptyView();
            }

            mAdapter.isUseEmpty(true);
            mAdapter.notifyDataSetChanged();
        }

    }

    protected void setEmptyView() {
        mAdapter.setEmptyView(R.layout.empty_view_list);
        mAdapter.getEmptyView().findViewById(R.id.iv_reload).setOnClickListener(this);
    }

    protected void showMenu(View view) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
