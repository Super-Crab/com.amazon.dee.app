package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.InvalidGrantAuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.AuthorizationToken;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.token.RefreshAtzToken;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Arrays;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class ServerCommunication {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.ServerCommunication";

    private static void checkNetwork(Context context) throws IOException {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) {
            throw new IOException("Network is unavailable");
        }
    }

    public <T extends PandaResponse> T executeRequest(AbstractPandaRequest<T> abstractPandaRequest, Context context) throws IOException, AuthError {
        checkNetwork(context);
        T submit = abstractPandaRequest.submit();
        submit.parse();
        return submit;
    }

    public AuthorizationToken[] getAuthorizationTokens(RefreshAtzToken refreshAtzToken, String str, String[] strArr, Context context, Bundle bundle, AppInfo appInfo) throws IOException, AuthError {
        String str2 = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getAuthorizationTokens : appId=");
        outline107.append(appInfo.getAppFamilyId());
        outline107.append(", scopes=");
        outline107.append(Arrays.toString(strArr));
        MAPLog.i(str2, outline107.toString());
        checkNetwork(context);
        try {
            OauthTokenResponse oauthTokenResponse = (OauthTokenResponse) new OauthTokenRequest(context, refreshAtzToken, appInfo).submit();
            oauthTokenResponse.parse();
            return oauthTokenResponse.getAtzTokens();
        } catch (InvalidGrantAuthError e) {
            MAPLog.e(LOG_TAG, "Invalid grant request given to the server. Cleaning up local state");
            DatabaseHelper.clearAuthorizationState(context);
            throw e;
        }
    }

    public JSONObject getProfile(Context context, String str, Bundle bundle, AppInfo appInfo) throws AuthError, IOException {
        checkNetwork(context);
        ProfileResponse profileResponse = (ProfileResponse) new ProfileRequest(bundle, str, context, appInfo).submit();
        profileResponse.parse();
        return profileResponse.getProfileResponse();
    }

    public AuthorizationToken[] getTokensFromCode(String str, String str2, String[] strArr, String str3, AppInfo appInfo, Context context) throws IOException, AuthError {
        return getTokensFromCode(str, null, str2, strArr, str3, context, appInfo);
    }

    public AuthorizationToken[] getTokensFromCode(String str, String str2, String str3, String[] strArr, String str4, Context context, AppInfo appInfo) throws IOException, AuthError {
        String str5 = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getTokensFromCode : appId=");
        outline107.append(appInfo.getAppFamilyId());
        outline107.append(", scopes=");
        outline107.append(Arrays.toString(strArr));
        MAPLog.i(str5, outline107.toString());
        checkNetwork(context);
        OauthCodeForTokenResponse oauthCodeForTokenResponse = (OauthCodeForTokenResponse) new OauthCodeForTokenRequest(str, str2, str3, str4, appInfo, context).submit();
        oauthCodeForTokenResponse.parse();
        return oauthCodeForTokenResponse.getAtzTokens();
    }
}
