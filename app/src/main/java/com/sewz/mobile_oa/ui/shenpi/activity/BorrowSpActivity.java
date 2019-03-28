package com.sewz.mobile_oa.ui.shenpi.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseActivity;
import com.sewz.mobile_oa.ui.BaseFragment;
import com.sewz.mobile_oa.ui.shenpi.fragment.BorrowNoFragment;
import com.sewz.mobile_oa.ui.shenpi.fragment.BorrowOkFragment;
import com.sewz.mobile_oa.ui.shenpi.fragment.BorrowSendMeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BorrowSpActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.homePager)
    ViewPager homePager;

    @BindView(R.id.homeMenuMainRelative)
    RelativeLayout homeMenuMainRelative;

    @BindView(R.id.homeMainImg)
    ImageView homeMainImg;

    @BindView(R.id.homeMainTxt)
    TextView homeMainTxt;

    @BindView(R.id.homeMenuBatchRelative)
    RelativeLayout homeMenuBatchRelative;

    @BindView(R.id.homeBatchImg)
    ImageView homeBatchImg;

    @BindView(R.id.homeBatchTxt)
    TextView homeBatchTxt;

    @BindView(R.id.homeMenuMyRelative)
    RelativeLayout homeMenuMyRelative;

    @BindView(R.id.homeMyImg)
    ImageView homeMyImg;

    @BindView(R.id.homeMyTxt)
    TextView homeMyTxt;

    BorrowOkFragment borrowOkFragment;
    BorrowNoFragment borrowNoFragment;
    BorrowSendMeFragment borrowSendMeFragment;

    ArrayList<BaseFragment> fragmentList = new ArrayList<>();
    FragmentManager fragmentManager;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_borrow_sp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("物品借用审批");
        setLeftImgBg(R.mipmap.back_white);

        initClickListener();
        initView();
    }

    private void initClickListener() {
        homeMenuMainRelative.setOnClickListener(this);
        homeMenuBatchRelative.setOnClickListener(this);
        homeMenuMyRelative.setOnClickListener(this);

        borrowNoFragment = new BorrowNoFragment();
        borrowOkFragment = new BorrowOkFragment();
        borrowSendMeFragment = new BorrowSendMeFragment();

        fragmentList.add(borrowNoFragment);
        fragmentList.add(borrowOkFragment);
        fragmentList.add(borrowSendMeFragment);

        fragmentManager = getSupportFragmentManager();
        homePager.setAdapter(new  MyFragmentPagerAdapter(fragmentManager));
        homePager.setOffscreenPageLimit(3);
        homePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                homePager.setCurrentItem(position);
                switch (position) {
                    case 0:
                        clearSelect();
                        homeMainImg.setSelected(true);
                        homeMainTxt.setSelected(true);
                        break;
                    case 1:
                        clearSelect();
                        homeBatchImg.setSelected(true);
                        homeBatchTxt.setSelected(true);
                        break;
                    case 2:
                        clearSelect();
                        homeMyImg.setSelected(true);
                        homeMyTxt.setSelected(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void clearSelect() {
        homeMainImg.setSelected(false);
        homeMainTxt.setSelected(false);
        homeBatchImg.setSelected(false);
        homeBatchTxt.setSelected(false);
        homeMyImg.setSelected(false);
        homeMyTxt.setSelected(false);

    }

    private void initView() {
        homePager.setCurrentItem(0);
        homeMainImg.setSelected(true);
        homeMainTxt.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeMenuMainRelative://未审批
                clearSelect();
                homePager.setCurrentItem(0, false);
                break;

            case R.id.homeMenuBatchRelative://已审批
                clearSelect();
                homePager.setCurrentItem(1, false);
                break;
            case R.id.homeMenuMyRelative://抄送给我
                clearSelect();
                homePager.setCurrentItem(2, false);
                showVisi(View.VISIBLE, View.GONE);
                break;
        }
    }


    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }


    @Override
    protected void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
