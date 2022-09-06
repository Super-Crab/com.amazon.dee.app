package com.amazon.deecomms.common.util;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public final class WebIntentUtils {
    private static final String PARAM_POPUP = "pop-up";
    private static final String PARAM_POPUP_VALUE = "1";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, WebIntentUtils.class);
    private static final Set<Uri> POPUP_LINKS = Collections.unmodifiableSet(new HashSet(Arrays.asList(Uri.parse("https://www.amazon.com/gp/help/customer/display.html"))));

    private WebIntentUtils() {
    }

    public static boolean containsPopUpUri(@NonNull Intent intent) {
        return isWebIntent(intent) && isPopupUri(intent.getData());
    }

    public static Intent ensurePopupIfApplicable(@NonNull Intent intent) {
        if (containsPopUpUri(intent)) {
            intent.setData(ensurePopupParam(intent.getData()));
        } else if (isWebIntent(intent)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Uri doesn't need to add pop-up param: ");
            outline1.append(LOG.sensitive(intent.getData().toString()));
            commsLogger.v(outline1.toString());
        }
        return intent;
    }

    public static Uri ensurePopupParam(@NonNull Uri uri) {
        if (TextUtils.equals("1", uri.getQueryParameter(PARAM_POPUP))) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Uri already has popup param: ");
            outline1.append(LOG.sensitive(uri.toString()));
            commsLogger.d(outline1.toString());
            return uri;
        }
        Uri.Builder clearQuery = uri.buildUpon().clearQuery();
        for (String str : uri.getQueryParameterNames()) {
            if (!str.equals(PARAM_POPUP)) {
                clearQuery.appendQueryParameter(str, uri.getQueryParameter(str));
            }
        }
        Uri build = clearQuery.appendQueryParameter(PARAM_POPUP, "1").build();
        CommsLogger commsLogger2 = LOG;
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("Added popup param to Uri: ");
        outline12.append(LOG.sensitive(build.toString()));
        commsLogger2.d(outline12.toString());
        return build;
    }

    public static boolean isPopupUri(@NonNull Uri uri) {
        return POPUP_LINKS.contains(uri.buildUpon().clearQuery().build());
    }

    public static boolean isWebIntent(@NonNull Intent intent) {
        return TextUtils.equals(intent.getAction(), "android.intent.action.VIEW") && intent.getData() != null;
    }
}
