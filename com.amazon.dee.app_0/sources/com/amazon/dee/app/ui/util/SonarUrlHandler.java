package com.amazon.dee.app.ui.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.services.logging.Log;
import dagger.Lazy;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class SonarUrlHandler {
    private static final int HTTP_OK = 200;
    private static final String SONAR_ENCODED_KEY = "U";
    private static final String SONAR_URL_SUFFIX = "/gp/r.html";
    private static final String TAG = Log.tag(SonarUrlHandler.class);
    private final Lazy<OkHttpClient> okHttpClient;

    public SonarUrlHandler(Lazy<OkHttpClient> lazy) {
        this.okHttpClient = lazy;
    }

    public void backgroundHttpRequest(Uri uri) {
        try {
            Response execute = this.okHttpClient.mo358get().newCall(new Request.Builder().url(uri.toString()).build()).execute();
            if (execute.code() != 200) {
                String str = "Background HTTP request to Sonar returned with a status: " + execute.code();
            }
            execute.close();
        } catch (IOException unused) {
        }
    }

    @VisibleForTesting(otherwise = 2)
    Intent createIntent(String str, Uri uri) {
        return new Intent(str, uri);
    }

    public Uri getDecodedUrl(Uri uri) {
        return Uri.parse(uri.getQueryParameter(SONAR_ENCODED_KEY));
    }

    public boolean handleUrl(Activity activity, Uri uri) {
        try {
            Intent createIntent = createIntent("android.intent.action.VIEW", uri);
            createIntent.setPackage("com.amazon.dee.app");
            activity.startActivity(createIntent);
            return true;
        } catch (ActivityNotFoundException unused) {
            Intent createIntent2 = createIntent("android.intent.action.VIEW", uri);
            createIntent2.addFlags(268435456);
            activity.startActivity(createIntent2);
            return false;
        }
    }

    public boolean isSonarUrl(Uri uri) {
        return uri.toString().contains(SONAR_URL_SUFFIX);
    }
}
