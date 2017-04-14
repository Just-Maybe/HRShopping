package com.example.helloworld.huaruanshopping.acitiviy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/4/12.
 */

public class PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    List<String> imageUrls;
    String TAG = "111";
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        imageUrls = intent.getStringArrayListExtra("imageUrlsList");
        position = intent.getIntExtra("ListPosition", 0);
        if (imageUrls.size() > 0) {
            Log.d(TAG, "initData: " + imageUrls.size());
            SamplePagerAdapter adapter = new SamplePagerAdapter(imageUrls);
            viewpager.setAdapter(adapter);
            viewpager.setCurrentItem(position);
        }
    }

    class SamplePagerAdapter extends PagerAdapter {

        List<String> imageUrls;

        public SamplePagerAdapter(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                photoView.setTransitionName("image");
            }
            container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
            Glide.with(getApplicationContext()).load(imageUrls.get(position)).into(photoView);
            photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView imageView, float v, float v1) {
                    finish();
                }
            });
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
