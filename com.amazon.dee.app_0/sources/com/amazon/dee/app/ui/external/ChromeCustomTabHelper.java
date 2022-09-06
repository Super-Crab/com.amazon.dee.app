package com.amazon.dee.app.ui.external;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.util.MissingBrowserDialog;
import com.amazon.dee.app.util.Utils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class ChromeCustomTabHelper {
    private static final String TAG = Utils.safeFormat(ChromeCustomTabHelper.class.getSimpleName(), new Object[0]);
    private Activity activity;
    private EnvironmentService environmentService;
    private String[] hostWhitelist;
    private String url;
    @UrlType
    @VisibleForTesting
    int urlType;

    /* loaded from: classes12.dex */
    public @interface UrlType {
        public static final int ALLOWED_URL = 0;
        public static final int BLOCKED_URL = -1;
        public static final int MAIN_APP_URL = 1;
    }

    public ChromeCustomTabHelper(Activity activity, String str, ArrayList<String> arrayList, EnvironmentService environmentService) {
        if (arrayList == null) {
            this.hostWhitelist = new String[0];
        } else {
            this.hostWhitelist = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        this.activity = activity;
        this.url = str;
        this.environmentService = environmentService;
        this.urlType = validateUrl(str);
    }

    @UrlType
    private int validateUrl(String str) {
        if (this.environmentService.isWithinWebAppUrl(str)) {
            return 1;
        }
        return (this.environmentService.isWithinAlexaWebViewNonIndex(str) || this.environmentService.isWithinHostList(str, this.hostWhitelist)) ? 0 : -1;
    }

    public boolean isAllowedUrl() {
        return this.urlType == 0;
    }

    public boolean isCalendarLink(String str) {
        String queryParameter;
        try {
            Uri parse = Uri.parse(URLDecoder.decode(str, "UTF-8"));
            for (String str2 : parse.getQueryParameterNames()) {
                if (str2 != null && str2.equalsIgnoreCase(NotificationCompat.CATEGORY_SERVICE) && (queryParameter = parse.getQueryParameter(str2)) != null && queryParameter.equalsIgnoreCase("Eon")) {
                    return true;
                }
            }
        } catch (UnsupportedEncodingException unused) {
        }
        return false;
    }

    public void openCustomTab() {
        if (this.urlType == 0) {
            Resources resources = this.activity.getResources();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            if (resources != null) {
                builder.setToolbarColor(resources.getColor(R.color.toolbar_background_color));
            }
            builder.setShowTitle(true);
            builder.enableUrlBarHiding();
            builder.build().launchUrl(this.activity, Uri.parse(this.url));
        }
    }

    public void openCustomTabForResult(int i) {
        if (this.urlType == 0) {
            Resources resources = this.activity.getResources();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            if (resources != null) {
                builder.setToolbarColor(resources.getColor(R.color.toolbar_background_color));
            }
            builder.setShowTitle(true);
            builder.enableUrlBarHiding();
            CustomTabsIntent build = builder.build();
            build.intent.setData(Uri.parse(this.url));
            try {
                this.activity.startActivityForResult(build.intent, i);
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, e, "Caught ActivityNotFoundException while trying to open custom tab for result", new Object[0]);
                MissingBrowserDialog.show(this.activity);
            }
        }
    }
}
