package com.sewz.mobile_oa.ui.photoSelect;

import android.content.Context;
import android.content.Intent;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author 沈小建 on 2016/9/29 0029.
 */

public class ChoosePicUtil {


    private static final int REQUEST_NUMBER = 1;

    private static final String CAMERA = "拍照";
    private static final String GALLERY = "从相册选择";

    private ChoosePicUtil() {
    }

    public static class Builder {

        private WeakReference<Context> mContext;

        private String title;
        private String takePhotoName;
        private String choosePicName;

        private boolean isMultiple;
        private boolean isCrop;
        private boolean isCompress = true;
        private int chooseNumber;


        private static ResultBack resultBack;

        public Builder(Context mContext) {
            this.mContext = new WeakReference<Context>(mContext);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTakePhotoName(String takePhotoName) {
            this.takePhotoName = takePhotoName;
            return this;
        }

        public Builder setChoosePicName(String choosePicName) {
            this.choosePicName = choosePicName;
            return this;
        }

        public Builder setCrop(boolean crop) {
            isCrop = crop;
            return this;
        }

        public Builder setCompress(boolean compress) {
            isCompress = compress;
            return this;
        }

        public Builder setMultiple(boolean multiple) {
            isMultiple = multiple;
            return this;
        }

        public Builder setChooseNumber(int chooseNumber) {
            this.chooseNumber = chooseNumber;
            return this;
        }

        public Builder setResultCallback(ResultBack resultBack) {
            this.resultBack = resultBack;
            return this;
        }

        private void apply(ActionSheetDialog sheetDialog) {
            sheetDialog.addSheetItem(takePhotoName == null ? CAMERA :
                            takePhotoName, ActionSheetDialog.SheetItemColor.BLACK,
                    which -> chooseFromCamera())
                    .addSheetItem(choosePicName == null ? GALLERY : choosePicName,
                            ActionSheetDialog.SheetItemColor.BLACK,
                            which -> chooseFromGallery());
        }

        private void chooseFromCamera() {
            Intent intent = new Intent(mContext.get(), ChoosePicActivity.class);
            intent.putExtra("isTakePhoto", true);
            intent.putExtra("isCrop", isCrop);
            mContext.get().startActivity(intent);
        }

        private void chooseFromGallery() {
            Intent intent = new Intent(mContext.get(), ChoosePicActivity.class);
            intent.putExtra("isChoosePic", true);
            intent.putExtra("isCrop", isCrop);
            if (isMultiple) {
                intent.putExtra("ChoosePic", chooseNumber);
            }
            mContext.get().startActivity(intent);
        }

        public ActionSheetDialog create() {
            ActionSheetDialog dialog = new ActionSheetDialog(mContext.get());
            apply(dialog.builder());
            return dialog;
        }

        public static void callResult(List<String> pathList) {
            resultBack.chooseSuccess(pathList);
        }

        public interface ResultBack {
            public void chooseSuccess(List<String> picPathList);
        }


    }
}

