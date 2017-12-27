package com.less.tplayer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.less.tplayer.R;
import com.less.tplayer.base.adapter.BaseViewPagerAdapter;
import com.less.tplayer.base.fragment.BaseViewPagerFragment;
import com.less.tplayer.bean.PagerInfo;
import com.less.tplayer.interfaces.IFragmentReSelected;
import com.less.tplayer.mvp.movie.MovieFragment;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author deeper
 * @date 2017/11/22
 */

public class TabMoviePagerFragment extends BaseViewPagerFragment implements IFragmentReSelected {

    @Override
    protected int getToolBarIconRes() {
        return R.mipmap.btn_search_normal;
    }

    @Override
    protected int getToolBarTitleRes() {
        return R.string.main_tab_name_tweet;
    }

    @Override
    protected List<PagerInfo> getPagers() {
        List<PagerInfo> list = new LinkedList<>();
        list.add(new PagerInfo("最新动弹",MovieFragment.class,createFragArgs(MovieFragment.CATALOG_NEW)));
        list.add(new PagerInfo("热门动弹",MovieFragment.class,createFragArgs(MovieFragment.CATALOG_HOT)));
        list.add(new PagerInfo("我的动弹",MovieFragment.class,createFragArgs(MovieFragment.CATALOG_MYSELF)));
        return list;
    }

    private Bundle createFragArgs(int catalog) {
        Bundle bundle = new Bundle();
        bundle.putInt(MovieFragment.CATALOG_KEY, catalog);
        return bundle;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initBundle(Bundle bundle) {

    }

    @Override
    protected View.OnClickListener getToolbarClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "you click toolbar", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void callReSelect() {
        if (mBaseViewPager != null) {
            BaseViewPagerAdapter pagerAdapter = (BaseViewPagerAdapter) mBaseViewPager.getAdapter();
            Fragment fragment = pagerAdapter.getCurrentFrag();
            if (fragment != null && fragment instanceof IFragmentReSelected) {
                ((IFragmentReSelected) fragment).callReSelect();
            }
        }
    }
}
