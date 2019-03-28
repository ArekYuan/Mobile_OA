package com.sewz.mobile_oa.ui.photoSelect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ImageUtil {

    public static ArrayList<String> imgeFromUrl(String imgUrl) {
        ArrayList<String> imgs = new ArrayList<>();
        if (imgUrl != null) {
            if (TextUtils.isEmpty(imgUrl)) {
                imgs.add("");
            } else {
                String[] pics = imgUrl.split("\\|");
                for (int i = 0; i < pics.length; i++) {
                    imgs.add(pics[i]);
                }
            }
        }
        return imgs;
    }


    //保存文件到指定路径
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        // 保存图片目录 （根目录下XXX）
        File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/msk/photo");
        if (!appDir.exists()) {
            /*mkdirs();//多级目录*/
            Boolean isSuccess = appDir.mkdirs();
            Log.i("ImageUtil--->","创建" + isSuccess.toString());
        } else {
            Log.i("ImageUtil--->","已有文件夹");
        }
        // 文件名
        String fileName = "oa_" + System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);

        try {
            Log.i("ImageUtil--->","开始保存");
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String getImageStr(String path) {
        String base64code = null;
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        File file = new File(path);
        if (file.exists()) {
            try {
                fis = new FileInputStream(path);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            try {
                while ((count = fis.read(buffer)) >= 0) {
                    baos.write(buffer, 0, count);
                }
                base64code = new String(android.util.Base64.encode(
                        baos.toByteArray(), android.util.Base64.DEFAULT));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return base64code;
    }

}
