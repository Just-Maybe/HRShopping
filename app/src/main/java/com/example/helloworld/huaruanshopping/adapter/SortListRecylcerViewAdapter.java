package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.ProductDescribeActivity;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.bean.ProductListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/1/18.
 */

public class SortListRecylcerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductBean.DataBean> mProductList = new ArrayList<>();
    private View view;

    public SortListRecylcerViewAdapter(Context context, List<ProductBean.DataBean> list) {
        this.context = context;
        this.mProductList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.sort_list_item, parent, false);
        return new GoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof GoodViewHolder) {
            Glide.with(context).load(HttpMethods.BASE_URL + mProductList.get(position).getProtypeSet().get(0).getPic()).into(((GoodViewHolder) holder).img);
            ((GoodViewHolder) holder).goodName.setText(mProductList.get(position).getName());
            ((GoodViewHolder) holder).goodPrice.setText("￥" + mProductList.get(position).getPrice());
            ((GoodViewHolder) holder).goodSale.setText(mProductList.get(position).getSales()+" ");
            ((GoodViewHolder) holder).goodLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = mProductList.get(position).getId();
                    Intent intent = new Intent(context.getApplicationContext(), ProductDescribeActivity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class GoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView goodName;
        private TextView goodPrice;
        private TextView goodSale;
        private CardView goodLayout;

        public GoodViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.GoodPicUrl4);
            goodName = (TextView) itemView.findViewById(R.id.GoodName4);
            goodPrice = (TextView) itemView.findViewById(R.id.GoodPrice4);
            goodSale = (TextView) itemView.findViewById(R.id.GoodSale4);
            goodLayout = (CardView) itemView.findViewById(R.id.goodLayout);
        }
    }

    public void addMoreData(List<ProductBean.DataBean> moreList) {
        mProductList.addAll(moreList);
        notifyItemInserted(getItemCount() - 1);
        notifyDataSetChanged();
    }
}
