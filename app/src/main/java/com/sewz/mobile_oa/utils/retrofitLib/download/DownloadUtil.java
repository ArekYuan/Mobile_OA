package com.sewz.mobile_oa.utils.retrofitLib.download;


import com.sewz.mobile_oa.utils.YLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DownloadUtil {

    /**
     * 默认连接超时时间
     */
    public static final int CONNECTION_TIME_OUT_DEFAULT = 60;

    /**
     * 默认读取超时时间
     */
    public static final int READ_TIME_OUT_DEFAULT = 60;

    /**
     * 默认写超时时间
     */
    public static final int WRITE_TIME_OUT_DEFAULT = 60;

    /**
     * 访问网络单子例对象
     */
    private static OkHttpClient client = null;

    private DownloadUtil() {
    }

    /**
     * <返回访问网络单子例对象> <功能详细描述>
     *
     * @return 返 回 类 型：ConnectSersvice
     */
    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (DownloadUtil.class) {
                if (client == null) {
                    client = new OkHttpClient().newBuilder()
                            .connectTimeout(CONNECTION_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIME_OUT_DEFAULT, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
//                            .cache(cache)
                            .build();
                }
            }
        }
        return client;
    }

    /**
     * 下载文件 用户更新 文件时需要
     *
     * @param url
     * @param fileDir
     * @param fileName
     */
    public static void downLoadFile(String url, final String fileDir, final String fileName, final FileProgressCallBack callBack) {
//        String url = SeverConstants.SERVER_URL + method;
        doDownloadFile(url, fileDir, fileName, new DownLoadFileCallBack() {
            @Override
            public void onSuccess(long total, long current) {
                callBack.fileProgressChange(total, current);
            }

            @Override
            public void onDownLoadFinish(File file) {
                callBack.fileFinishDownLoad(file);
            }

            @Override
            public void onFail(String errorMessage) {
                callBack.fileProgressFailed(errorMessage);
            }
        });

    }

    /**
     * 下载 文件
     *
     * @param url
     * @param fileDir
     * @param fileName
     * @param callBack
     */
    private static void doDownloadFile(final String url, String fileDir, String fileName, final DownLoadFileCallBack callBack) {
        final File file = new File(fileDir, fileName);
        if (file.exists()) {
            file.deleteOnExit();
        }
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFail(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    YLog.d(url + "<--total-->" + total);
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        YLog.d(url + "<--current-->" + current);
                        callBack.onSuccess(total, current);
                        if (total == current) {
                            callBack.onDownLoadFinish(file);
                        }
                    }
                    fos.flush();
                } catch (IOException e) {
                    YLog.d(e);
                    callBack.onFail("下载失败");
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        YLog.d(e);
                    }
                }
            }
        });
    }
}
