package com.sewz.mobile_oa.ui.photoSelect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChoosePicActivity extends TakePhotoActivity {
    private boolean isTakePhoto;
    private boolean isChoosePic;
    private boolean isCrop;

    private int ChoosePic;
    private static final int MIN_CHOOSE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isTakePhoto = getIntent().getBooleanExtra("isTakePhoto", false);
        isChoosePic = getIntent().getBooleanExtra("isChoosePic", false);
        isCrop = getIntent().getBooleanExtra("isCrop", false);
        ChoosePic = getIntent().getIntExtra("ChoosePic", MIN_CHOOSE);

        CompressConfig config = new CompressConfig.Builder()
                .setMaxSize(480 * 800)
                .setMaxPixel(800)
                .create();
        getTakePhoto().onEnableCompress(config, false);

        if (isTakePhoto) {
            takePhoto();
        } else {
            choosePic();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null && !isCrop) finish();

        isCrop = false;
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void takePhoto() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();

        if (isCrop) {
            CropOptions cropOptions = new CropOptions.Builder().setAspectX(2).setAspectY(2).setWithOwnCrop(true).create();
            getTakePhoto().onPickFromCaptureWithCrop(Uri.fromFile(file), cropOptions);
        } else {
            getTakePhoto().onPickFromCapture(Uri.fromFile(file));
        }
    }

    private void choosePic() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();

        if (isCrop) {
            CropOptions cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
            getTakePhoto().onPickFromGalleryWithCrop(Uri.fromFile(file), cropOptions);
        } else {
            getTakePhoto().onPickMultiple(ChoosePic);
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        List<String> picList = new ArrayList<>();

        if (ChoosePic == MIN_CHOOSE) {
            picList.add(result.getImage().getCompressPath());
        } else {
            for (TImage image : result.getImages()) {
                picList.add(image.getCompressPath());
            }
        }
        ChoosePicUtil.Builder.callResult(picList);
        finish();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Log.d("ChoosePicActivity", "--msg->" + result.getImage().toString());
        Toast.makeText(this, "拍照失败", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        finish();
    }

}
