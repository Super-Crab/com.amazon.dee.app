package amazon.speech.util;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class ResourceStringReader {
    private static final String TAG = "ResourceStringReader";

    public String readFileDataFromBundle(PackageManager packageManager, String str, Bundle bundle, String str2) {
        int i;
        if (packageManager == null || TextUtils.isEmpty(str) || bundle == null || TextUtils.isEmpty(str2) || (i = bundle.getInt(str2, 0)) <= 0) {
            return null;
        }
        try {
            try {
                return InputStreamUtil.readStream(readRawResource(packageManager, str, i));
            } catch (IOException e) {
                Log.w(TAG, String.format("Failed to deserialize file data from package (%s); skipping", str), e);
                return null;
            }
        } catch (Resources.NotFoundException e2) {
            Log.w(TAG, String.format("Failed to read resource from package (%s); skipping", str), e2);
            return null;
        }
    }

    public InputStream readRawResource(PackageManager packageManager, String str, int i) {
        if (packageManager != null && !TextUtils.isEmpty(str) && i != 0) {
            try {
                Resources resourcesForApplication = packageManager.getResourcesForApplication(str);
                if (resourcesForApplication == null) {
                    String str2 = TAG;
                    Log.i(str2, "no resource found at " + str);
                    return null;
                }
                return resourcesForApplication.openRawResource(i);
            } catch (PackageManager.NameNotFoundException e) {
                String str3 = TAG;
                Log.i(str3, "not able to find the resource for " + str, e);
            }
        }
        return null;
    }
}
