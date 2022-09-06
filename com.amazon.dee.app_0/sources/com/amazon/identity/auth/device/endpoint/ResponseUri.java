package com.amazon.identity.auth.device.endpoint;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.identity.auth.device.AuthError;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class ResponseUri {
    public static final String STATE_PARAMETER_NAME = "state";
    private final Uri uri;

    public ResponseUri(Uri uri) {
        this.uri = uri;
    }

    public Map<String, String> getStateValues() throws AuthError {
        String queryParameter = this.uri.getQueryParameter("state");
        if (queryParameter != null) {
            HashMap hashMap = new HashMap();
            for (String str : TextUtils.split(queryParameter, WebConstants.UriConstants.AMPERSAND_KEY)) {
                String[] split = TextUtils.split(str, Config.Compare.EQUAL_TO);
                if (split != null && split.length == 2) {
                    hashMap.put(split[0], split[1]);
                }
            }
            return hashMap;
        }
        throw new AuthError(String.format("Response does not have a state parameter: %s", this.uri.toString()), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
    }
}
