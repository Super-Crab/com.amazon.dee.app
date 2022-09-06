package com.amazon.alexa.voice.routing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.data.RouteUrls;
import com.amazon.alexa.voice.ui.routing.Destination;
import com.amazon.alexa.voice.ui.routing.Navigator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class VoiceNavigator implements Navigator {
    private static final String DEEPLINK_URL_PREFIX = "https://alexa.amazon.com/?fragment=";
    private static final String SPECIAL_LIST_DEFAULT = "lists/namedLists";
    private final Activity activity;
    private static final Map<String, String> ROUTE_URL_MAP = ImmutableMap.of(Destination.HANDSFREE_SETTINGS, RouteUrls.HANDSFREE_SETTINGS, Destination.LISTS, "v2/lists");
    private static final Map<String, String> SPECIAL_LIST_URLS = ImmutableMap.of("shopping", "lists/shopping", "todo", "lists/todos");

    public VoiceNavigator(Activity activity) {
        this.activity = activity;
    }

    private void routeToUrl(@NonNull String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(GeneratedOutlineSupport1.outline72(DEEPLINK_URL_PREFIX, str)));
        intent.setPackage(this.activity.getPackageName());
        intent.addFlags(131072);
        this.activity.startActivity(intent);
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
