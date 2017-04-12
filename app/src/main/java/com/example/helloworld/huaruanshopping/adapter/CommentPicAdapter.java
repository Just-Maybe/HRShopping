package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by helloworld on 2017/3/29.
 */

public class CommentPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int REQUEST_CODE_GALLERY = 1001;
    private static Context context;
    private List<String> imageViewList = new ArrayList<>();

    public List<String> getImageViewList() {
        return imageViewList;
    }

    public CommentPicAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemIiew = LayoutInflater.from(context).inflate(R.layout.imageview_layout, parent, false);
        return new itemHolder(itemIiew);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof itemHolder) {
            if (position == 0) {
                ((itemHolder) holder).imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.add_pic_128));
                ((itemHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (imageViewList.size() < 3) {
                            addPic();
                        }

                    }
                });
            } else {
                Glide.with(context).load(imageViewList.get(position - 1)).into(((itemHolder) holder).imageView);
                ((itemHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "!!!", Toast.LENGTH_SHORT).show();
                    }
                });
                ((itemHolder) holder).badge.setBadgeText("一").setGravityOffset(-3, -3, true);
                ((itemHolder) holder).badge.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (dragState == STATE_SUCCEED) {
//                            Toast.makeText(context, "asdd", Toast.LENGTH_SHORT).show();
//                            ((itemHolder) holder).badge.hide(false);
                            removePic(position - 1);
                        }
                    }
                });

            }
        }
    }

    private void removePic(int position) {
        imageViewList.remove(position);
        notifyDataSetChanged();

    }

    private void addPic() {
        FunctionConfig config = new FunctionConfig.Builder().setMutiSelectMaxSize(3).build();
        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, config, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                for (int i = 0; i < resultList.size(); i++) {
                    imageViewList.add(resultList.get(i).getPhotoPath());
                }
                notifyDataSetChanged();

            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return 1 + imageViewList.size();
    }

    static class addPicHolder extends RecyclerView.ViewHolder {

        public addPicHolder(View itemView) {
            super(itemView);
        }
    }

    static class itemHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        QBadgeView badge;

        public itemHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.commentPic);
            badge = (QBadgeView) new QBadgeView(context).bindTarget(imageView);
            badge.setBadgeGravity(Gravity.TOP | Gravity.END);
            badge.setBadgeTextSize(14, true);
            badge.setBadgePadding(10, true);
        }
    }
}
