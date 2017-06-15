package com.example.helloworld.huaruanshopping.acitiviy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.CommentPicAdapter;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.CommentProduct.DataBean;
import com.example.helloworld.huaruanshopping.presenter.ActivityCommentPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityComment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helloworld on 2017/3/27.
 */

public class CommentActivity extends AppCompatActivity implements IActivityComment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.commentEdit)
    EditText commentEdit;
    @BindView(R.id.picLayout)
    LinearLayout picLayout;
    @BindView(R.id.commentBtn)
    TextView commentBtn;
    String TAG = "111";
    CommentPicAdapter adapter;
    LinearLayoutManager manager;
    @BindView(R.id.picRecylcerView)
    RecyclerView picRecylcerView;
    ActivityCommentPresenter presenter;
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.productImg)
    ImageView productImg;
    @BindView(R.id.productRemark)
    TextView productRemark;
    @BindView(R.id.productPrice)
    TextView productPrice;
    @BindView(R.id.productDescribeBtn)
    ImageView productDescribeBtn;
    @BindView(R.id.commentName)
    TextView commentName;
    @BindView(R.id.commentDate)
    TextView commentDate;
    @BindView(R.id.commentStar)
    RatingBar commentStar;
    @BindView(R.id.commentContent)
    TextView commentContent;
    @BindView(R.id.commentLayout)
    RelativeLayout commentLayout;
    @BindView(R.id.addPicTv)
    TextView addPicTv;
    @BindView(R.id.addPicTips)
    TextView addPicTips;
    @BindView(R.id.orderContent)
    RelativeLayout orderContent;
    int flag;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        setSupportActionBar(toolbar);
        presenter = new ActivityCommentPresenter(this);
        adapter = new CommentPicAdapter(this);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        picRecylcerView.setLayoutManager(manager);
        picRecylcerView.setAdapter(adapter);
        presenter.getCommentProduct(1, 1);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                layout.setFocusable(true);
                layout.setFocusableInTouchMode(true);
                layout.requestFocus();
                closeInputMethod();
                return false;
            }
        });
        flag = getIntent().getIntExtra("flag", 0);
        id = getIntent().getIntExtra("id", 0);
        if (flag == 1) {
            commentLayout.setVisibility(View.GONE);
        }
        Log.d(TAG, "onCreate: flag" + flag);
    }

    @OnClick({R.id.commentBtn, R.id.productDescribeBtn})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.commentBtn:
                if (flag == 1) {
                    presenter.postComment(1, 17, 4, getCommentContent(), adapter.getImageViewList());
                } else if (flag == 2) {
                    presenter.postAppendComment(16, 1, 17, getCommentContent(), adapter.getImageViewList());
                }
                break;
            case R.id.productDescribeBtn:
                Intent intent = new Intent(CommentActivity.this, ProductDescribeActivity.class);
                intent.putExtra("id", id);
                Log.d(TAG, "onClick: " + id);
                startActivity(intent);
                break;
        }
    }

    private void closeInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
            imm.hideSoftInputFromWindow(commentEdit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public String getCommentContent() {
        return commentEdit.getText().toString();
    }

    @Override
    public void onSuccessPostComment() {
//        Snackbar.make()
        Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailedPostComment() {

    }

    @Override
    public void getCommentProduct(DataBean dataBean) {
        if (dataBean != null) {
            if (dataBean.getSorder() != null) {
                Glide.with(getApplicationContext()).load(HttpMethods.BASE_URL + dataBean.getSorder().getProtype().getPic()).into(productImg);
                Log.d(TAG, "getCommentProduct: " + dataBean.getSorder().getProtype().getPic());
                productPrice.setText("￥ " + dataBean.getSorder().getPrice());
                productRemark.setText(dataBean.getSorder().getProtype().getProduct().getRemark());
            }
            if (dataBean.getComment() != null) {
                commentName.setText(dataBean.getComment().getUser().getUsername());
                commentDate.setText(dataBean.getComment().getCreate_date() + "");
                commentStar.setNumStars(dataBean.getComment().getStar());
                commentContent.setText(dataBean.getComment().getComment());
//                picLayout.setVisibility(View.GONE);
//                addPicTv.setVisibility(View.GONE);
//                addPicTips.setVisibility(View.GONE);
            } else {
                commentLayout.setVisibility(View.GONE);
            }
        }
    }

}
