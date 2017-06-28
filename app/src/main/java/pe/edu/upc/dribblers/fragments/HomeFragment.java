package pe.edu.upc.dribblers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.edu.upc.dribblers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.mth_home)
    PagerSlidingTabStrip mthHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    View homeView;
    HomeTrainingFragment homeTrainingFragment;
    HomeEventsFragment homeEventsFragment;
    HomePlansFragment mHomePlansFragment;
    HomeViewPagerAdapter homeViewPagerAdapter;

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeView =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, homeView);
        AssignViewPager();
        return homeView;
    }
    private void AssignViewPager() {
        homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        vpHome.setAdapter(homeViewPagerAdapter);
        vpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mthHome.setViewPager(vpHome);
    }


    private class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

        public HomeViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if(homeTrainingFragment == null){
                    homeTrainingFragment = HomeTrainingFragment.newInstance();
                }
                return homeTrainingFragment;
            } else {
                if(mHomePlansFragment == null){
                    mHomePlansFragment = HomePlansFragment.newInstance();
                }
                return mHomePlansFragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.home_training);
            } else {
                return "TRAINING PLANS";
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
