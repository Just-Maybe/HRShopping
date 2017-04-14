package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.PhotoViewActivity;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Comment.DataBean;
import com.example.helloworld.huaruanshopping.util.DensityUtil;

import java.util.List;

/**
 * Created by helloworld on 2017/4/6.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<DataBean> mCommentList;
    private static final String TAG = CommentAdapter.class.getSimpleName();

    public CommentAdapter(Context context, List<DataBean> commentList) {
        this.mContext = context;
        this.mCommentList = commentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item_layout, parent, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentHolder) {
            ((CommentHolder) holder).commentName.setText(mCommentList.get(position).getUser().getUsername());
            ((CommentHolder) holder).commentDate.setText(mCommentList.get(position).getCreate_date() + "");
            ((CommentHolder) holder).commentContent.setText(mCommentList.get(position).getComment());
            ((CommentHolder) holder).commentStar.setNumStars(mCommentList.get(position).getStar());
            ImageView imageView;
            LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams(DensityUtil.dip2px(mContext, 80), DensityUtil.dip2px(mContext, 80));
            if (mCommentList.get(position).getComment_pic_Set().size() > 0) {
                for (int i = 0; i < mCommentList.get(position).getComment_pic_Set().size(); i++) {
                    if (((CommentHolder) holder).commentPicLayout.getChildCount() == 0) {

                        if (mCommentList.get(position).getComment_pic_Set().get(i) != null) {
                            imageView = new ImageView(mContext);
//                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            ((CommentHolder) holder).commentPicLayout.addView(imageView, lp);
                            Glide.with(mContext).load(HttpMethods.BASE_URL + "comment-img/" +mCommentList.get(position).getComment_pic_Set().get(i).getPic()).into(imageView);

//                            Log.d(TAG, "onBindViewHolder: " + HttpMethods.BASE_URL + "comment-img/" + mCommentList.get(position).getComment_pic_Set().get(i).getPic());
                        }
                    }
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

   private static class CommentHolder extends RecyclerView.ViewHolder {
        TextView commentName;
        TextView commentDate;
        RatingBar commentStar;
        TextView commentContent;
        LinearLayout commentPicLayout;

        public CommentHolder(View itemView) {
            super(itemView);
            commentName = (TextView) itemView.findViewById(R.id.commentName);
            commentDate = (TextView) itemView.findViewById(R.id.commentDate);
            commentStar = (RatingBar) itemView.findViewById(R.id.commentStar);
            commentContent = (TextView) itemView.findViewById(R.id.commentContent);
            commentPicLayout = (LinearLayout) itemView.findViewById(R.id.commentPicLayout);
        }
    }
}
