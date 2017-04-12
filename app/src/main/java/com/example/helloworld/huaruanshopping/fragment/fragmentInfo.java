package com.example.helloworld.huaruanshopping.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.CommentActivity;
import com.example.helloworld.huaruanshopping.acitiviy.FindAllOrderActivity;
import com.example.helloworld.huaruanshopping.acitiviy.updateInfoActivity;
import com.example.helloworld.huaruanshopping.presenter.FragmentInfoPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentUpdateInfo;
import com.example.helloworld.huaruanshopping.util.SharedPreferencesUtils;
import com.example.helloworld.huaruanshopping.util.createBitmapUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

import static android.os.Environment.DIRECTORY_PICTURES;

/**
 * Created by helloworld on 2017/3/21.
 */

public class fragmentInfo extends Fragment implements IFragmentUpdateInfo{
    @BindView(R.id.headImg)
    RoundedImageView headImg;

    Button btnTakePhoto;
    Button btnPickPhoto;
    Button btnCancel;
    @BindView(R.id.findAllOrderLayout)
    RelativeLayout findAllOrderLayout;
    @BindView(R.id.headImgLayout)
    RelativeLayout headImgLayout;
    @BindView(R.id.headimgArrow)
    ImageView headimgArrow;
    @BindView(R.id.updateNameLayout)
    RelativeLayout updateNameLayout;
    @BindView(R.id.updatePasswordLayout)
    RelativeLayout updatePasswordLayout;
    @BindView(R.id.updateAddressLayout)
    RelativeLayout updateAddressLayout;
    @BindView(R.id.userName)
    TextView userName;
    //    @BindView(R.id.testImg)
//    ImageView testImg;
    private Bitmap image = null;
    FragmentInfoPresenter presenter;
    String userNameTv;
    private static final String TAG = "111";

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userNameTv = (String) SharedPreferencesUtils.getParam(getActivity(), "username", "");
        presenter = new FragmentInfoPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        headImg.setImageBitmap(readBitmap());
        userName.setText(userNameTv);
        return view;
    }

    @OnClick({R.id.findAllOrderLayout, R.id.headImgLayout, R.id.updateNameLayout, R.id.updatePasswordLayout, R.id.updateAddressLayout})
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), updateInfoActivity.class);
        switch (view.getId()) {
            case R.id.headImgLayout:
                showPopupWindow(view);
                break;
            case R.id.findAllOrderLayout:
                intent = new Intent(getActivity(), FindAllOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.updateNameLayout:
                intent.putExtra("info", "name");
                startActivityForResult(intent, 3);
                break;
            case R.id.updatePasswordLayout:
//                intent.putExtra("info", "password");
                Intent intent2 = new Intent(getActivity(), CommentActivity.class);
                startActivity(intent2);
                break;
            case R.id.updateAddressLayout:
                intent.putExtra("info", "managerAddress");
                startActivity(intent);
                break;
        }

    }

    private void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popup_window, null);
        btnPickPhoto = (Button) contentView.findViewById(R.id.btn_pick_photo);
        btnTakePhoto = (Button) contentView.findViewById(R.id.btn_take_photo);
        btnCancel = (Button) contentView.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
            }
        });
        btnPickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pickPhoto();
                openGallery();
                popupWindow.dismiss();
            }
        });
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                takePhoto();
                openCramera();
                popupWindow.dismiss();
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.showAtLocation(fragmentInfo.this.getActivity().findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        backgroundAlpha(0.5f);
        popupWindow.showAsDropDown(view);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    private void openCramera() {
        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                Glide.with(getActivity()).load(resultList.get(0).getPhotoPath()).into(headImg);
                image = createBitmapUtil.createImageThumbnail(resultList.get(0).getPhotoPath());
                saveBitmap(image);
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }

    private void openGallery() {
        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                Glide.with(getActivity()).load(resultList.get(0).getPhotoPath()).into(headImg);
                image = createBitmapUtil.createImageThumbnail(resultList.get(0).getPhotoPath());
                saveBitmap(image);
                presenter.uploadHeadIcon(1,resultList.get(0).getPhotoPath());
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    private void pickPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 2);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3 && resultCode == 3) {
            userName.setText(data.getStringExtra("username"));
            SharedPreferencesUtils.setParam(getActivity(), "username", data.getStringExtra("username"));
            Snackbar.make(getView(), "修改成功!", Snackbar.LENGTH_SHORT).show();
        }
//        else if (data != null) {
//            //取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
//            Uri mImageCaptureUri = data.getData();
//            //返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
//            if (requestCode == 2 && mImageCaptureUri != null) {
//                try {
//                    //这个方法是根据Uri获取Bitmap图片的静态方法
////                      image = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), mImageCaptureUri);
//                    image = createBitmapUtil.getThumbnail(getContext(), mImageCaptureUri, 500);
//                    if (image != null) {
////                        BitmapDrawable bd = new BitmapDrawable(image);
//                        headImg.setImageBitmap(image);
////                        Glide.with(getActivity()).load(bd).into(headImg);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            } else if (requestCode == 1 && data != null) {
//                Bundle extras = data.getExtras();
//                if (extras != null) {
//                    //这里是有些拍照后的图片是直接存放到Bundle中的所以我们可以从这里面获取Bitmap图片
//                    image = extras.getParcelable("data");
//                    if (image != null) {
////                        BitmapDrawable bd = new BitmapDrawable(image);
//                        headImg.setImageBitmap(image);
////                        Glide.with(getActivity()).load(bd).into(headImg);
//                    }
//                }
//            }
//            saveBitmap(image);
//        }

    }

    public void saveBitmap(Bitmap bm) {
        File file = new File(getContext().getExternalFilesDir(DIRECTORY_PICTURES), "head.jpg");
        if (file.exists()) {
            file.delete();
        }
        try {
//            Matrix matrix = new Matrix();
//            matrix.setScale(0.5f,0.5f);
//            bm = Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight(),matrix,true);
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(out);
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.recycle();
        image = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (image != null) {
            image.recycle();
            image = null;
        }
    }

    //192.168.191.4a
    public Bitmap readBitmap() {
        File file = new File(getContext().getExternalFilesDir(DIRECTORY_PICTURES), "head.jpg");
        Log.d(TAG, "readBitmap: " + file.getAbsolutePath());
        Bitmap bm = null;
        bm = createBitmapUtil.createImageThumbnail(file.getAbsolutePath());
        return bm;
    }

    @Override
    public void onSuccessUpdate() {

    }

    @Override
    public void onFailedUpdate() {

    }
}
