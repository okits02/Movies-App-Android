package com.example.moviesapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.moviesapp.Adapter.SliderAdapter;
import com.example.moviesapp.Domain.SliderItem;
import com.example.moviesapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private Handler sliderHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        baners();
    }

    private void baners()
    {
        List<SliderItem> SliderItems=new ArrayList<>();
        SliderItems.add(new SliderItem(R.drawable.wide1));
        SliderItems.add(new SliderItem(R.drawable.wide));
        SliderItems.add(new SliderItem(R.drawable.wide3));
        viewPager2.setAdapter(new SliderAdapter(SliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(8).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(SliderRunable);
            }
        });
    }

    private Runnable SliderRunable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(SliderRunable);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sliderHandler.postDelayed(SliderRunable,2000);
    }

    private void initView()
    {
        viewPager2=findViewById(R.id.ViewPager1);
    }



}