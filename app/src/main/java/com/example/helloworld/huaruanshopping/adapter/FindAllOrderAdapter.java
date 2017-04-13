package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.CommentActivity;
import com.example.helloworld.huaruanshopping.acitiviy.FindAllOrderActivity;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.presenter.ActivityFindAllOrderPresenter;
import com.example.helloworld.huaruanshopping.bean.orderList.DataBean;

import java.util.List;

/**
 * Created by helloworld on 2017/3/18.
 */

public class FindAllOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<DataBean> orderList;
    private final static String TAG = "111";
    ActivityFindAllOrderPresenter presenter = new ActivityFindAllOrderPresenter();
    private cancelItemListen listener;
    private String token = "532a8a18b75079da0c48414014600600d64737f36e330997";
    private int id = 1;
    int num = 0;

    public FindAllOrderAdapter(Context context, List<DataBean> orderList) {
        mContext = context;
        this.orderList = orderList;
        Log.d(TAG, "FindAllOrderAdapter: " + orderList.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.find_all_order_item, parent, false);
        return new OrderItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OrderItem) {

            ((OrderItem) holder).status.setText(orderList.get(position).getStatus().getStatus());
            ((OrderItem) holder).total.setText("￥ " + orderList.get(position).getTotal());
            ((OrderItem) holder).NO.setText(orderList.get(position).getId());
//            Log.d(TAG, "onBindViewHolder: id" + orderList.get(position).getStatus().getId() + "  " + position);
            if (orderList.get(position).getStatus().getId() == 2 || orderList.get(position).getStatus().getId() == 4) {
                ((OrderItem) holder).deleteOrder.setVisibility(View.VISIBLE);
                ((OrderItem) holder).cancelOrder.setVisibility(View.GONE);
                ((OrderItem) holder).orderImmediately.setVisibility(View.GONE);
            } else if (orderList.get(position).getStatus().getId() == 1) {
                ((OrderItem) holder).deleteOrder.setVisibility(View.GONE);
                ((OrderItem) holder).cancelOrder.setVisibility(View.VISIBLE);
                ((OrderItem) holder).orderImmediately.setVisibility(View.VISIBLE);
            }
            ((OrderItem) holder).deleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(v, position);
                }
            });
            ((OrderItem) holder).cancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelItem(position);
                }
            });
            DataBean.SorderSetBean Bean;
            for (int i = 0; i < orderList.get(position).getSorderSet().size(); i++) {
                Bean = orderList.get(position).getSorderSet().get(i);
//                Log.d(TAG, "onBindViewHolder:position " + position + " i " + i);
//                Log.d(TAG, "onBindViewHolder: " + Bean.getProtype().getName() + Bean.getProtype().getPic() + Bean.getPrice());
                //半段是否为空，空则添加子View 否则不需要添加
                if (((OrderItem) holder).contentLayout.getChildCount() < orderList.get(position).getSorderSet().size()) {

                    ((OrderItem) holder).contentLayout.addView(setItemView(((OrderItem) holder).contentLayout,
                            Bean.getProtype().getPic(),
                            Bean.getProtype().getProduct().getName(),
                            Bean.getNumber(), Bean.getPrice(),
                            Bean.getProtype().getName(),
                            Bean.getComm_flag()));
                    Log.d(TAG, "onBindViewHolder: " + orderList.get(position).getStatus().getId());
//                    Log.d(TAG, "onBindViewHolder: " + num++);
                }
            }
        }
    }

    /**
     * @param contentLayout 父布局
     * @param imgUrl        商品图片
     * @param Name          商品名称
     * @param Num           商品数量
     * @param Total         商品总价
     * @param sort          商品分类
     * @param getComm_flag  订单状态id
     * @return
     */
    private View setItemView(ViewGroup contentLayout, String imgUrl, String Name, int Num, double Total, String sort, int getComm_flag) {
        Log.d(TAG, "setItemView: " + getComm_flag);
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.order_item_layout, contentLayout, false);
        ImageView img = (ImageView) itemView.findViewById(R.id.orderItemImg);
        TextView itemName = (TextView) itemView.findViewById(R.id.orderItemName);
        TextView itemNum = (TextView) itemView.findViewById(R.id.orderItemNum);
        TextView itemTotal = (TextView) itemView.findViewById(R.id.orderItemTotal);
        TextView itemSort = (TextView) itemView.findViewById(R.id.orderItemSort);
        TextView orderComment = (TextView) itemView.findViewById(R.id.orderComment);
        if (getComm_flag == 1) {
            orderComment.setText("评论");
        } else if (getComm_flag == 2) {
            orderComment.setText("追评");
        } else {
            orderComment.setVisibility(View.GONE);
        }
        orderComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(), CommentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
//        Log.d(TAG, "setItemView: " + HttpMethods.BASE_URL + imgUrl);
        Glide.with(mContext).load(HttpMethods.BASE_URL + imgUrl).into(img);
        itemName.setText(Name);
        itemNum.setText("x " + Num);
        itemTotal.setText("￥ " + Total);
        itemSort.setText(sort);
        return itemView;
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    private class OrderItem extends RecyclerView.ViewHolder {
        TextView status;
        TextView total;
        LinearLayout contentLayout;
        TextView NO;
        TextView deleteOrder;
        TextView cancelOrder;
        TextView orderImmediately;

        private OrderItem(View itemView) {
            super(itemView);
            status = (TextView) itemView.findViewById(R.id.orderStatus);
            total = (TextView) itemView.findViewById(R.id.orderTotal);
            NO = (TextView) itemView.findViewById(R.id.orderNO);
            contentLayout = (LinearLayout) itemView.findViewById(R.id.orderContent);
            deleteOrder = (TextView) itemView.findViewById(R.id.deleteOrder);
            cancelOrder = (TextView) itemView.findViewById(R.id.cancelOrder);
            orderImmediately = (TextView) itemView.findViewById(R.id.orderImmediately);
        }
    }

    private void deleteItem(View view, int position) {
        presenter.deleteOrder(orderList.get(position).getId(), id, token);
        orderList.remove(position);
        notifyDataSetChanged();
        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
    }

    private void cancelItem(int position) {
        presenter.cancelOrder(orderList.get(position).getId(), id, token);
        listener.afterCancelListen();
        notifyDataSetChanged();
    }

    public void setOnCancelItemListener(cancelItemListen listener) {
        this.listener = listener;
    }

    public interface cancelItemListen {
        void afterCancelListen();
    }
}
