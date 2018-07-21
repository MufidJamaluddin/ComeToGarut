package id.ac.polban.jtk.cometogarut.mvp.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.lang.ref.WeakReference;

import id.ac.polban.jtk.cometogarut.R;

/**
 * Melayani Detail Wisata : Induk Fragment
 * @author Mufid Jamaluddin
 */
public class DetailActivity extends AppCompatActivity implements BaseFragmentActivity
{
    private ProgressWheel progressWheel;
    private ActionBar actionBar;

    private ViewPager viewPager;

    private Integer place_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.progressWheel = findViewById(R.id.progress_wheel);
//        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        this.viewPager = findViewById(R.id.pager);

        this.actionBar = super.getSupportActionBar();

        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(this, super.getSupportFragmentManager());

        this.viewPager.setAdapter(sectionsPageAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(this.viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        this.place_id = super.getIntent().getIntExtra("place_id", 1);
    }

    /**
     * Menampilkan Judul di ActionBar
     * @param title : judul
     */
    @Override
    public void showTitle(String title)
    {
        if(this.actionBar != null)
            this.actionBar.setTitle(title);
        else
        {
            Toolbar toolbar = super.findViewById(R.id.toolbar);
            toolbar.setTitle(title);
        }
    }

    /**
     * Menampilkan Loading
     */
    @Override
    public void showLoading()
    {
        //this.appBarLayout.setVisibility(View.GONE);
        this.viewPager.setVisibility(View.GONE);

        this.progressWheel.setVisibility(View.VISIBLE);
        this.progressWheel.spin();

        ValueAnimator progressFadeInAnim = ObjectAnimator.ofFloat(this.progressWheel, "alpha", 0, 1, 1);
        progressFadeInAnim.start();
    }

    /**
     * Menghentikan Loading
     */
    @Override
    public void hideLoading()
    {
        this.progressWheel.stopSpinning();
        this.progressWheel.setVisibility(View.GONE);

        //this.appBarLayout.setVisibility(View.VISIBLE);
        this.viewPager.setVisibility(View.VISIBLE);

        ValueAnimator progressFadeInAnim = ObjectAnimator.ofFloat(progressWheel, "alpha", 1, 0, 0);
        progressFadeInAnim.start();
    }

    /**
     * Mendapatkan ID Place
     * @return ID Place
     */
    @Override
    public Integer getPlace_id()
    {
        return this.place_id;
    }

    /**
     * Adapter
     */
    private static class SectionsPageAdapter extends FragmentPagerAdapter
    {
        // konteks ini
        private WeakReference<Context> refContext;

        /**
         * Konstruktor
         * @param context konteks
         * @param fm fragment manager
         */
        SectionsPageAdapter(Context context, FragmentManager fm)
        {
            super(fm);
            this.refContext = new WeakReference<>(context);
        }

        /**
         *
         * @param position posisi fragmenr
         * @return instance fragment
         */
        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new DetailFragment();
                case 1:
                    return new GalleryPlaceFragment();
                case 2:
                    return new ReviewFragment();
                case 3:
                    return new SendReviewFragment();
                case 4:
                    return new SuggestionFragment();
                case 5:
                    return new SendSuggestionFragment();
            }
            return null;
        }

        /**
         *
         * @param position posisi fragment
         * @return judul fragment
         */
        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            Context context = this.refContext.get();

            switch (position)
            {
                case 0:
                    return context.getResources().getString(R.string.title_detail);
                case 1:
                    return context.getResources().getString(R.string.sh_galleries);
                case 2:
                    return context.getResources().getString(R.string.sh_review);
                case 3:
                    return context.getResources().getString(R.string.send_review);
                case 4:
                    return context.getResources().getString(R.string.sh_suggestions);
                case 5:
                    return context.getResources().getString(R.string.send_suggestions);
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return 6;
        }
    }

}
