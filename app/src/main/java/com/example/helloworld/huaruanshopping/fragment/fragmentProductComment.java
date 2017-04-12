package com.example.helloworld.huaruanshopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.CommentAdapter;
import com.example.helloworld.huaruanshopping.bean.Comment;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.presenter.ActivityProductDescribePresenter;
import com.example.helloworld.huaruanshopping.presenter.FragmentCommentPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityProductDescribe;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentComment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/1/22.
 */

public class fragmentProductComment extends Fragment implements IFragmentComment {

    @BindView(R.id.commentRecyclerView)
    RecyclerView commentRecyclerView;
    FragmentCommentPresenter presenter;
    CommentAdapter adapter;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_product_comment, container, false);
        ButterKnife.bind(this, view);
        presenter = new FragmentCommentPresenter(this);
        presenter.getComment(1, 1);
        return view;
    }


    @Override
    public void getComment(List<Comment.DataBean> dataBeanList) {
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new CommentAdapter(getActivity(), dataBeanList);
        commentRecyclerView.setLayoutManager(layoutManager);
        commentRecyclerView.setAdapter(adapter);
    }
}
