package com.amazon.alexa.voiceui.cards;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.routing.Destination;
import com.amazon.alexa.voice.ui.routing.Navigator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class VoiceNavigator implements Navigator {
    private static final String ALEXA_APP_PACKAGE_NAME = "com.amazon.dee.app";
    private static final String DEEPLINK_URL_PREFIX = "https://alexa.amazon.com/?fragment=";
    @VisibleForTesting
    static final String ELEMENTS_LISTS = "v2/lists";
    private static final String PLAY_STORE_PACKAGE_NAME = "com.android.vending";
    @VisibleForTesting
    static final String PLAY_STORE_URL = "http://play.google.com/store/apps/details?id=com.amazon.dee.app";
    private static final Map<String, String> ROUTE_URL_MAP = Collections.unmodifiableMap(new HashMap<String, String>() { // from class: com.amazon.alexa.voiceui.cards.VoiceNavigator.1
        {
            put(Destination.LISTS, "v2/lists");
        }
    });
    private static final String SPECIAL_LIST_DEFAULT = "lists/namedLists";
    private static final Map<String, String> SPECIAL_LIST_URLS;
    private static final String TAG = "VoiceNavigator";
    private final Activity activity;
    private final PackageManager packageManager;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("shopping", "lists/shopping");
        hashMap.put("todo", "lists/todos");
        SPECIAL_LIST_URLS = Collections.unmodifiableMap(hashMap);
    }

    @Inject
    public VoiceNavigator(Activity activity, PackageManager packageManager) {
        this.activity = activity;
        this.packageManager = packageManager;
    }

    private boolean isAlexaAppInstalled() {
        try {
            this.packageManager.getPackageInfo("com.amazon.dee.app", 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(TAG, "Alexa app is not installed");
            return false;
        }
    }

    private void routeToUrl(@NonNull String str) {
        Intent intent;
        if (isAlexaAppInstalled()) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(GeneratedOutlineSupport1.outline72(DEEPLINK_URL_PREFIX, str)));
            intent.setPackage(this.activity.getPackageName());
            intent.addFlags(131072);
        } else {
            intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(PLAY_STORE_URL));
            intent.setPackage("com.android.vending");
            intent.addFlags(268435456);
        }
        try {
            this.activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            String str2 = TAG;
            Log.e(str2, "Unable to launch intent: " + intent);
        }
    }

    @Override // com.amazon.alexa.voice.ui.routing.Navigator
    public void navigate(String str) {
        String str2 = ROUTE_URL_MAP.get(str);
        if (str2 != null) {
            routeToUrl(str2);
            return;
        }
        throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline72("Unsupported navigation to ", str));
    }

    @Override // com.amazon.alexa.voice.ui.routing.Navigator
    public void navigateToSpecialList(@NonNull String str) {
        String str2 = SPECIAL_LIST_URLS.get(str);
        if (str2 == null) {
            str2 = SPECIAL_LIST_DEFAULT;
        }
        routeToUrl(str2);
    }
}
