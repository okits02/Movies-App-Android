package com.example.moviesapp.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesapp.Domain.SliderItem;
import com.example.moviesapp.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>
{
    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;

    public SliderAdapter(List<SliderItem> sliderItem, ViewPager2 viewPager2) {
        this.sliderItems= sliderItem;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slider_item_layout,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
       holder.setImageView1(sliderItems.get(position));
        if(position==sliderItems.size()-2)
        {
            viewPager2.post(runnable);
        }
    }


    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView1;
        public SliderViewHolder(@NonNull View imageView) {
            super(imageView);
            imageView1=imageView.findViewById(R.id.imageSlider);
        }
        void setImageView1(SliderItem sliderItem)
        {
            RequestOptions requestOptions=new RequestOptions();
            requestOptions=requestOptions.transform(new CenterCrop(),new RoundedCorners(60));

            Glide.with(context)
                    .load(sliderItem.getImage())
                    .apply(requestOptions)
                    .into(imageView1);
        }
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
