package com.yao.breakskyyo.tools;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by yoyo on 2015/5/19.
 */
public class AppInfoUtil {
    public static final String BaiduDiskPackageName = "com.baidu.netdisk";

    public static String getVersionName(Context mContext) {
        PackageManager manager;

        PackageInfo info = null;

        manager = mContext.getPackageManager();

        try {

            info = manager.getPackageInfo(mContext.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {

// TODO Auto-generated catch block

            e.printStackTrace();
            return null;

        }
        return info.versionName;
       /* info.versionCode;

        info.versionName;

        info.packageName;

        info.signatures;*/
    }

    public static Object getVersionCode(Context mContext) {
        PackageManager manager;

        PackageInfo info = null;

        manager = mContext.getPackageManager();

        try {

            info = manager.getPackageInfo(mContext.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;

        }
        return info.versionCode;
       /* info.versionCode;

        info.versionName;

        info.packageName;

        info.signatures;*/
    }

    public static boolean isRunningBaiduDisk(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(BaiduDiskPackageName)) {
                return true;
               // return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            }
        }
        return false;

    }
}
