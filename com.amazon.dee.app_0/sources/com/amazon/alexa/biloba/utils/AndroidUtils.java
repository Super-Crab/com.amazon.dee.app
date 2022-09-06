package com.amazon.alexa.biloba.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public final class AndroidUtils {
    private static final String TAG = "AndroidUtils";

    private AndroidUtils() {
    }

    public static ClickableSpan createRouteClickableSpan(@NonNull final RoutingService routingService, @NonNull final String str, @NonNull final Context context, final int i, final boolean z) {
        return new ClickableSpan() { // from class: com.amazon.alexa.biloba.utils.AndroidUtils.2
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                GeneratedOutlineSupport1.outline145(RoutingService.this, str);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                AndroidUtils.updateDrawState(textPaint, context, i, z);
            }
        };
    }

    public static ClickableSpan createShareLinkClickableSpan(@NonNull final Context context, @NonNull final String str, final int i, final int i2, final boolean z) {
        return new ClickableSpan() { // from class: com.amazon.alexa.biloba.utils.AndroidUtils.3
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                Context context2 = context;
                AndroidUtils.shareLink(context2, context2.getString(i), str);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                AndroidUtils.updateDrawState(textPaint, context, i2, z);
            }
        };
    }

    public static ClickableSpan createWebviewClickableSpan(@NonNull final Context context, @NonNull final String str, final int i, final boolean z) {
        return new ClickableSpan() { // from class: com.amazon.alexa.biloba.utils.AndroidUtils.1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                AndroidUtils.startWebview(str, context);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
                AndroidUtils.updateDrawState(textPaint, context, i, z);
            }
        };
    }

    public static String getFormattedTimeString(String str, String str2, String str3) {
        return String.format("%s %s %s %s", str, "·", str2, str3);
    }

    public static String getString(Context context, int i, Object... objArr) {
        return context == null ? "" : context.getString(i, objArr);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static void navigateToGlobalNotificationSettings(Context context) {
        Intent intent = new Intent();
        int i = Build.VERSION.SDK_INT;
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        context.startActivity(intent);
    }

    public static void popRouteBackstackForBiloba(RoutingService routingService) {
        LogUtils.d(TAG, "Clearing the routes within Care Hub.");
        RouteContext[] backstack = routingService.getBackstack();
        int length = backstack.length;
        int i = 0;
        for (int i2 = 0; i2 < length && !backstack[i2].getRoute().getName().startsWith("biloba"); i2++) {
            i++;
        }
        for (int length2 = backstack.length - 1; length2 >= i; length2--) {
            routingService.removeTopFromBackStack();
        }
    }

    public static void shareLink(Context context, String str, String str2) {
        String outline75 = GeneratedOutlineSupport1.outline75(str, " ", str2);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TITLE", str);
        intent.putExtra("android.intent.extra.TEXT", outline75);
        intent.setType(Constants.TEXT_PLAIN_MEDIA_TYPE);
        ((Activity) context).startActivity(Intent.createChooser(intent, str));
    }

    public static void startWebview(@NonNull String str, Context context) {
        String str2 = TAG;
        LogUtils.d(str2, "Opening webview for URL: " + str);
        Intent intent = new Intent(WebConstants.WebviewConstants.EXTERNAL_WEBVIEW_INTENT);
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra("IS_DOM_STORAGE_ENABLED", true);
        intent.putExtra("CONTINUE_ON_NON_FATAL_ERRORS", true);
        intent.putExtra("URL_REGEX_KEY", WebConstants.WebviewConstants.EXTERNAL_WEBVIEW_EXCLUDE_APP_LINK_REGEX_VALUE);
        intent.putExtra("BRIDGE_ACTION_KEY", "launchExternal");
        intent.putExtra("WEBVIEW_TITLE_ID_KEY", R.string.main_title);
        ((Activity) context).startActivity(intent);
    }

    public static void updateDrawState(@NonNull TextPaint textPaint, @NonNull Context context, int i, boolean z) {
        textPaint.setColor(ThemeUtil.getColorFromAttribute(context, i));
        textPaint.setUnderlineText(z);
    }

    public static String getString(Context context, int i) {
        return context == null ? "" : context.getString(i);
    }
}
