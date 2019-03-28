package com.sewz.mobile_oa.utils.retrofitLib.download;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.utils.FileUtil;
import com.sewz.mobile_oa.utils.YLog;

import java.io.File;

public class DownLoadActivity extends Activity {

    ProgressBar downLoadPBar;
    TextView downLoadSizeTxt;
    TextView fileSizeTxt;
    String downloadUrl = "";
    String fileName = "wsjlb.apk";
    File apkFile = null;
    String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        downloadUrl = getIntent().getStringExtra("downloadUrl");
        downLoadPBar = (ProgressBar) findViewById(R.id.downLoadPBar);
        downLoadSizeTxt = (TextView) findViewById(R.id.downLoadSizeTxt);
        fileSizeTxt = (TextView) findViewById(R.id.fileSizeTxt);
        if (!downloadUrl.equals("")) {
            downLoadApp(downloadUrl);
        }
    }

    private void initData(final String downloadUrl) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                downLoadApp(downloadUrl);
            }
        });
    }

    private void downLoadApp(String downloadUrl) {

        DownloadUtil.downLoadFile(downloadUrl, filePath, fileName, new FileProgressCallBack() {
            @Override
            public void fileProgressChange(long fileSize, long downloadedSize) {
                YLog.d("文件大小---->" + fileSize + "<--下载进度-->" + downloadedSize);
                downLoad(fileSize, downloadedSize);
            }

            @Override
            public void fileFinishDownLoad(File file) {
                apkFile = file;
                YLog.d("文件---<->-->" + file.getAbsolutePath());
            }

            @Override
            public void fileProgressFailed(String msg) {
                YLog.d("文件---->" + msg);
            }
        });
    }

    private void downLoad(final long fileSize, final long downloadedSize) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!isFirst) {
                    fileSizeTxt.setText(FileUtil.FormetFileSize(fileSize));
                    isFirst = true;
                }
                String downSizeStr = FileUtil.FormetFileSize(downloadedSize);
                downLoadSizeTxt.setText(downSizeStr);
//                int progress = (int) (downloadedSize * 100 / fileSize);
                double progress = ((double) downloadedSize / (double) fileSize) * 100;
                if (progress == 100) {
//                    isOk = true;
                    if (apkFile != null) {
                        inStallApk(DownLoadActivity.this, apkFile);
                        finish();
                    }
                }
                downLoadPBar.setProgress((int) progress);
            }
        });
    }

    private void inStallApk(Context mContext, File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, "com.yunxin.loveskill.provider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile),
                    "application/vnd.android.package-archive");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
