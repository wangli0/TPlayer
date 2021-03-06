package com.less.tplayer.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.less.tplayer.util.LogUtils;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2017/8/14
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected View mRoot;
    protected Bundle mBundle;
    protected LayoutInflater mInflater;

    /**
     * 懒加载
     */
    private boolean init = false;

    private boolean viewInit = false;

    private boolean visibleToUser = false;

    /**
     * 该方法只在ViewPager中有效
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
        if (isVisibleToUser && !init && viewInit) {
            lazyLoadData();
            init = true;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewInit = true;
        // 修复setUserVisibleHint调用时机比onViewCreated还早的issue,所以这里主动再调用一次.
        setUserVisibleHint(visibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null) {
                parent.removeView(mRoot);
            }
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false);
            mInflater = inflater;

            if (savedInstanceState != null) {
                onRestartInstance(savedInstanceState);
            }
            inflateViewStubInCreateView(mRoot);
            initView(mRoot);
            initData();
        }
        return mRoot;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBundle = null;
    }

    protected <T extends View> T findView(int viewId) {
        return (T) mRoot.findViewById(viewId);
    }

    protected <T extends Serializable> T getBundleSerializable(String key) {
        if (mBundle == null) {
            return null;
        }
        return (T) mBundle.getSerializable(key);
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * Fragment onSaveInstanceState() 被调用情况下:savedInstanceState != null
     *
     * @param savedInstanceState
     */
    protected void onRestartInstance(Bundle savedInstanceState){

    }

    /**
     * initView
     *
     * @param mRoot
     */
    protected abstract void initView(View mRoot);

    /**
     * inflate ViewStub in onCreateView(可选)
     *
     * 如果子类没有用到ViewStub 则无需重写此方法
     */
    protected void inflateViewStubInCreateView(View root){
        // nothing to do
    }

    /**
     * Fragment的布局Id
     *
     * @return layoutId
     */
    protected abstract int getLayoutId();

    /**
     * 初始化Fragment的Arguments参数
     *
     * @param bundle
     * @return null
     */
    protected abstract void initBundle(Bundle bundle);

    /**
     * 网络请求
     */
    protected void lazyLoadData(){
        LogUtils.d("====> lazy load <====");
    }
}