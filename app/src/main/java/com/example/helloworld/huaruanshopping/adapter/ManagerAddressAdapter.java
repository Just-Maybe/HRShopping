package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.updateInfoActivity;
import com.example.helloworld.huaruanshopping.bean.ListAddress;
import com.example.helloworld.huaruanshopping.bean.address;
import com.example.helloworld.huaruanshopping.util.SharedPreferencesUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by helloworld on 2017/3/22.
 */

public class ManagerAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public List<address> addressList;
    public final static int ADDADDRESSLAYOUT = Integer.MAX_VALUE - 1;
    public final static int ADDRESSLAYOUT = Integer.MAX_VALUE - 2;
    private ListAddress listAddres = new ListAddress();
    private Gson gson = null;
    address address = new address();
    String TAG = "111";
    public HashMap<Integer, Boolean> isSelected = null;


    public ManagerAddressAdapter(Context context, List<address> addressList) {
        this.mContext = context;
        this.addressList = addressList;
        gson = new Gson();
        initHashMap();
    }

    private void initHashMap() {
        if (addressList.size() > 0) {
            isSelected = new HashMap<>();
            for (int i = 0; i < addressList.size(); i++) {
                if (i == 0) {
                    isSelected.put(i, true);
                } else {

                    isSelected.put(i, false);
                }
            }
        }
    }

    public HashMap<Integer, Boolean> getIsChecked() {
        return isSelected;
    }

    public void setIsChecked(HashMap<Integer, Boolean> isChecked) {
        this.isSelected = isChecked;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View addressLayout = LayoutInflater.from(mContext).inflate(R.layout.manager_address_item, parent, false);
        View addAdressLayout = LayoutInflater.from(mContext).inflate(R.layout.add_address_layout, parent, false);
        if (viewType == ADDADDRESSLAYOUT) {
            return new addAdressViewHolder(addAdressLayout);
        } else {
            return new ManagerAddressViewHolder(addressLayout);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ManagerAddressViewHolder) {
            ((ManagerAddressViewHolder) holder).name.setText(addressList.get(position).getName());
            ((ManagerAddressViewHolder) holder).phone.setText(addressList.get(position).getPhone());
            ((ManagerAddressViewHolder) holder).address.setText(addressList.get(position).getAddress());
            ((ManagerAddressViewHolder) holder).checkBox.setChecked(getIsChecked().get(position));
            ((ManagerAddressViewHolder) holder).editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    address.setAddress(addressList.get(position).getAddress());
                    address.setName(addressList.get(position).getName());
                    address.setPhone(addressList.get(position).getPhone());
                    Intent intent = new Intent(mContext.getApplicationContext(), updateInfoActivity.class);
                    intent.putExtra("info", "addAddress");
                    intent.putExtra("address", address);
                    intent.putExtra("position", position);
                    Log.d(TAG, "onClick: " + position);
                    mContext.startActivity(intent);
                }
            });
            ((ManagerAddressViewHolder) holder).deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteAddress(position);
                }
            });
            ((ManagerAddressViewHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < getIsChecked().size(); i++) {
                        getIsChecked().put(i, false);
                        if (i == 0) {
                            getIsChecked().put(i, true);
                        }
                    }
                    changeAddressPosition(position);
//                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return ADDADDRESSLAYOUT;
        } else {
            return ADDRESSLAYOUT;
        }
    }

    @Override
    public int getItemCount() {
        return addressList.size() + 1;
    }

    class ManagerAddressViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        TextView name;
        TextView phone;
        ImageView editBtn;
        ImageView deleteBtn;
        AppCompatCheckBox checkBox;

        public ManagerAddressViewHolder(View itemView) {
            super(itemView);
            address = (TextView) itemView.findViewById(R.id.address);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            editBtn = (ImageView) itemView.findViewById(R.id.editBtn);
            deleteBtn = (ImageView) itemView.findViewById(R.id.deleteBtn);
            checkBox = (AppCompatCheckBox) itemView.findViewById(R.id.selectAddress);
        }
    }

    class addAdressViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;

        public addAdressViewHolder(View itemView) {
            super(itemView);
            layout = (RelativeLayout) itemView.findViewById(R.id.addAddressLayout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), updateInfoActivity.class);
                    intent.putExtra("info", "addAddress");
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public void addTheAddress(address address) {
        addressList.add(address);
        listAddres.setAddressList(addressList);
        String addressListGson = gson.toJson(listAddres);
        SharedPreferencesUtils.setParam(mContext, "addressList", addressListGson);
    }

    public void deleteAddress(int position) {
        addressList.remove(position);
        listAddres.setAddressList(addressList);
        String addressListGson = gson.toJson(listAddres);
        SharedPreferencesUtils.setParam(mContext, "addressList", addressListGson);
        notifyDataSetChanged();
    }

    public void changeAddressPosition(int position) {
        address tempAddress = new address();
        tempAddress.setAddress(addressList.get(position));
        addressList.get(position).setAddress(addressList.get(0));
        addressList.get(0).setAddress(tempAddress);

        listAddres.setAddressList(addressList);
        String addressListGson = gson.toJson(listAddres);
        SharedPreferencesUtils.setParam(mContext, "addressList", addressListGson);
        notifyDataSetChanged();
    }
}
