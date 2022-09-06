package com.amazon.clouddrive.extended.auth;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.utils.AssertUtils;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.android.tools.r8.GeneratedOutlineSupport1;
import okhttp3.Request;
/* loaded from: classes11.dex */
public class MAPCookieAuthenticator implements RequestAuthenticator {
    private static final String COOKIE_HEADER = "cookie";
    private static final String SESSION_COOKIE_START = "session-id=";
    private static final String SESSION_HEADER = "x-amzn-sessionid";
    private static final String TAG = "MAPCookieAuthenticator";
    private final Context mContext;
    private final String mDomain;
    private final MAPAccountManager mMapAccountManager;

    public MAPCookieAuthenticator(Context context, MAPAccountManager mAPAccountManager, String str) {
        AssertUtils.assertNotNull(context, "Context was null.");
        AssertUtils.assertNotNull(mAPAccountManager, "MAPAccountManager was null.");
        AssertUtils.assertNotNull(str, "Domain was null.");
        this.mContext = context;
        this.mMapAccountManager = mAPAccountManager;
        this.mDomain = str;
    }

    private Request buildAuthenticatedRequest(Request.Builder builder, String[] strArr) {
        boolean z = false;
        for (String str : strArr) {
            if (str.startsWith(SESSION_COOKIE_START)) {
                String[] split = str.split(Config.Compare.EQUAL_TO);
                if (split.length == 2) {
                    builder.header(SESSION_HEADER, split[1]);
                    z = true;
                }
            }
            builder.addHeader(COOKIE_HEADER, str);
        }
        if (!z) {
            logError("MAP did not return a session id.");
        }
        return builder.build();
    }

    private String[] getCookieAuthenticationBlocking(boolean z) {
        Bundle bundleNoRetry = getBundleNoRetry(z);
        if (bundleNoRetry == null) {
            bundleNoRetry = getBundleNoRetry(z);
        }
        if (bundleNoRetry == null) {
            return null;
        }
        return bundleNoRetry.getStringArray(CookieKeys.KEY_COOKIES);
    }

    @Override // com.amazon.clouddrive.auth.RequestAuthenticator
    public Request authenticateRequest(Request request, boolean z) {
        String[] cookieAuthenticationBlocking = getCookieAuthenticationBlocking(z);
        if (cookieAuthenticationBlocking != null && cookieAuthenticationBlocking.length >= 1) {
            return buildAuthenticatedRequest(request.newBuilder(), cookieAuthenticationBlocking);
        }
        logError("MAP did not return any cookies.");
        return null;
    }

    protected Bundle getBundleNoRetry(boolean z) {
        String account = this.mMapAccountManager.getAccount();
        if (account == null) {
            return null;
        }
        Bundle outline13 = GeneratedOutlineSupport1.outline13(CookieKeys.Options.KEY_FORCE_REFRESH, z);
        BlockingMAPListener blockingMAPListener = new BlockingMAPListener();
        new TokenManagement(this.mContext).getCookies(account, this.mDomain, outline13, blockingMAPListener);
        return blockingMAPListener.getResults();
    }

    protected void logError(String str) {
        Log.e(TAG, str);
    }
}
