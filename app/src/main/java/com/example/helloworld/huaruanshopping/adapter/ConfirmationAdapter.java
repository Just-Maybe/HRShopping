package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.ProductDescribeActivity;
import com.example.helloworld.huaruanshopping.acitiviy.updateInfoActivity;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.CartBean;
import com.example.helloworld.huaruanshopping.bean.OrderJsonBean;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by helloworld on 2017/2/20.
 */

public class ConfirmationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NOORDER = Integer.MAX_VALUE - 1;
    private static final int SHOPPINGTO = Integer.MAX_VALUE - 2;
    private static final int ORDER = Integer.MAX_VALUE - 3;
    private static final int COUNT = Integer.MAX_VALUE - 4;
    private List<CartBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    OrderJsonBean jsonBean;
    boolean isZhifubao = true;

    public boolean isZhifubao() {
        return isZhifubao;
    }

    public void setZhifubao(boolean zhifubao) {
        isZhifubao = zhifubao;
    }


    public ConfirmationAdapter(OrderJsonBean jsonBean, Context Context) {
        this.jsonBean = jsonBean;
        this.mList = jsonBean.getCart();
        this.mContext = Context;
        Log.d("11", "ConfirmationAdapter: " + mList.size());
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewNoOrder = LayoutInflater.from(mContext).inflate(R.layout.confirm_no_order, parent, false);
        View viewShoppingTo = LayoutInflater.from(mContext).inflate(R.layout.confirm_shopping_to, parent, false);
        View viewOrderItem = LayoutInflater.from(mContext).inflate(R.layout.confirm_order_item, parent, false);
        View viewCount = LayoutInflater.from(mContext).inflate(R.layout.confirm_count, parent, false);
        Log.d("adapter", "onCreateViewHolder: " + getItemCount());
        switch (viewType) {
            case NOORDER:
                return new NoOrderViewHolder(viewNoOrder);
            case SHOPPINGTO:
                return new ShoppingToViewHolder(viewShoppingTo);
            case ORDER:
                return new OrderItemViewHolder(viewOrderItem);
            case COUNT:
                return new CountViewHolder(viewCount);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShoppingToViewHolder) {
            if (!jsonBean.getName().equals("") || !jsonBean.getAddress().equals("") || !jsonBean.getPhone().equals("")) {
                ((ShoppingToViewHolder) holder).name.setText(jsonBean.getName());
                ((ShoppingToViewHolder) holder).address.setText(jsonBean.getAddress());
                ((ShoppingToViewHolder) holder).tel.setText(jsonBean.getPhone());

            }
            ((ShoppingToViewHolder) holder).changeAddressBen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), updateInfoActivity.class);
                    intent.putExtra("info", "managerAddress");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        } else if (holder instanceof OrderItemViewHolder) {
            Glide.with(mContext).load(HttpMethods.BASE_URL + mList.get(position - 1).getProtype().getPic()).into(((OrderItemViewHolder) holder).orderItemPic);
            Log.d(TAG, "onBindViewHolder: " + HttpMethods.BASE_URL + mList.get(position - 1).getProtype().getPic());
            ((OrderItemViewHolder) holder).orderItemName.setText(mList.get(position - 1).getProtype().getProduct().getName());
            ((OrderItemViewHolder) holder).orderItemSort.setText(mList.get(position - 1).getProtype().getName());
            ((OrderItemViewHolder) holder).orderItemTotal.setText("￥" + (mList.get(position - 1).getProtype().getProduct().getPrice() * mList.get(position - 1).getNumber()));
            ((OrderItemViewHolder) holder).orderItemCount.setText("x " + mList.get(position - 1).getNumber());
        } else if (holder instanceof CountViewHolder) {
            ((CountViewHolder) holder).confirmTotal.setText("￥" + getTheTotal());
//            ((CountViewHolder) holder).confirmPayment.setText("在线支付");
            ((CountViewHolder) holder).zhifubao.setChecked(isZhifubao);
            ((CountViewHolder) holder).zhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        ((CountViewHolder) holder).zhifubao.setChecked(true);
                        setZhifubao(true);
                    } else {
                        ((CountViewHolder) holder).weixin.setChecked(true);
                        setZhifubao(false);
                    }
                    Log.d(TAG, "onCheckedChanged: "+isZhifubao());
                }
            });
        }
    }

    private double getTheTotal() {
        double total = 0;
        for (int i = 0; i < mList.size(); i++) {
            total += (mList.get(i).getNumber() * mList.get(i).getProtype().getProduct().getPrice());
        }
        return total;
    }

    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 1 : mList.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() > 1) {
            if (position == 0) {
                return SHOPPINGTO;
            } else if (position == getItemCount() - 1) {
                return COUNT;
            } else {
                return ORDER;
            }
        } else {
            return NOORDER;
        }
    }


    class NoOrderViewHolder extends RecyclerView.ViewHolder {

        public NoOrderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ShoppingToViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private TextView tel;
        private ImageView changeAddressBen;

        public ShoppingToViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.ConfirmName);
            address = (TextView) itemView.findViewById(R.id.ConfirmAddress);
            tel = (TextView) itemView.findViewById(R.id.ConfirmTel);
            changeAddressBen = (ImageView) itemView.findViewById(R.id.changeAddressBen);
        }
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder {
        private TextView orderItemName;
        private TextView orderItemSort;
        private TextView orderItemCount;
        private TextView orderItemTotal;
        private RoundedImageView orderItemPic;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            orderItemPic = (RoundedImageView) itemView.findViewById(R.id.carItemImg);
            orderItemName = (TextView) itemView.findViewById(R.id.ConfirmItemName);
            orderItemSort = (TextView) itemView.findViewById(R.id.ConfirmItemSort);
            orderItemCount = (TextView) itemView.findViewById(R.id.ConfirmItemCount);
            orderItemTotal = (TextView) itemView.findViewById(R.id.ConfirmItemPrice);
        }
    }

    class CountViewHolder extends RecyclerView.ViewHolder {
        TextView confirmPayment;
        TextView confirmTotal;
        RadioButton weixin;
        RadioButton zhifubao;

        public CountViewHolder(View itemView) {
            super(itemView);
            confirmTotal = (TextView) itemView.findViewById(R.id.ConfirmOrderTotal);
            weixin = (RadioButton) itemView.findViewById(R.id.weixin);
            zhifubao = (RadioButton) itemView.findViewById(R.id.zhifubao);
            zhifubao.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            weixin.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

}
