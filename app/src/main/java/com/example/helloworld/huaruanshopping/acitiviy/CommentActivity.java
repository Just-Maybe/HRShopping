package com.example.helloworld.huaruanshopping.acitiviy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.CommentPicAdapter;
import com.example.helloworld.huaruanshopping.bean.CommentProduct.DataBean;
import com.example.helloworld.huaruanshopping.presenter.ActivityCommentPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityComment;

import java.util.ArrayList;
import java.util.List;

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
    List<String> imageViewList = new ArrayList<>();
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
    }

    @OnClick({R.id.commentBtn, R.id.productDescribeBtn})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.commentBtn:
//                presenter.postComment(1, 17, 4, getCommentContent(), adapter.getImageViewList());
                presenter.postAppendComment(16, 1, 17, "asdasdasdasd",adapter.getImageViewList());
                break;
            case R.id.productDescribeBtn:

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
    }

    @Override
    public void onFailedPostComment() {

    }

    @Override
    public void getCommentProduct(DataBean dataBean) {
        if (dataBean != null) {
            if (dataBean.getSorder() != null) {
                Glide.with(getApplicationContext()).load(dataBean.getSorder().getProtype().getPic()).into(productImg);
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
