package com.amazon.rtcsc.service.appclient;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.rtcsc.interfaces.RtcscAppDisconnectCode;
import com.amazon.rtcsc.service.PayloadObject;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes13.dex */
public class RtcscAppClientNotifier {
    private static final String SESSION_DOMAIN_KEY = "sessionDomain";
    private static final String mAction = "com.amazon.rtcsessioncontroller.ACTION_START_APPCLIENT";
    private RtcscAppClientJNIWrapper mAppClientWrapper;
    private Context mContext;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscAppClientNotifier.class);

    public RtcscAppClientNotifier(Context context, RtcscAppClientJNIWrapper rtcscAppClientJNIWrapper) {
        this.mContext = context;
        this.mAppClientWrapper = rtcscAppClientJNIWrapper;
    }

    private Intent createQueryIntent() {
        return new Intent(mAction);
    }

    private ResolveInfo findIntentHandlerComponent(Intent intent, PackageManager packageManager, String str) {
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
            for (int size = queryIntentServices.size() - 1; size >= 0; size--) {
                if (!hasValidSessionDomain(queryIntentServices.get(size).serviceInfo.metaData, str)) {
                    queryIntentServices.remove(size);
                }
            }
            int size2 = queryIntentServices.size();
            if (size2 > 1) {
                this.mLog.w(String.format("Multiple AppClient services claim that they handle sessionDomain : %s. Filtering out those that aren't on the system image.", str));
                for (int i = size2 - 1; i >= 0; i--) {
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(queryIntentServices.get(i).serviceInfo.packageName, 0);
                        if ((applicationInfo.flags & 1) == 0 && (applicationInfo.flags & 128) == 0) {
                            queryIntentServices.remove(i);
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        queryIntentServices.remove(i);
                    }
                }
            }
            if (queryIntentServices.isEmpty()) {
                return null;
            }
            if (queryIntentServices.size() > 1) {
                this.mLog.w(String.format("More than one system service can handle sessionDomain : %s. Aborting this session.", str));
                return null;
            }
            return queryIntentServices.get(0);
        }
        this.mLog.e("No registered services found that handle the pre-defined action.");
        return null;
    }

    private boolean hasValidSessionDomain(Bundle bundle, String str) {
        if (bundle == null) {
            this.mLog.d("Could not find metadata");
            return false;
        }
        String string = bundle.getString(SESSION_DOMAIN_KEY);
        if (!TextUtils.isEmpty(string) && string.equals(str)) {
            return true;
        }
        RtcscLogger rtcscLogger = this.mLog;
        rtcscLogger.d("Metadata " + string + " doesn't match with the sessionDomain received in the payload.");
        return false;
    }

    @TargetApi(26)
    private ComponentName startForegroundService(Intent intent) {
        this.mLog.d("Target API 26+. Starting AppClient as a foreground service..");
        return this.mContext.startForegroundService(intent);
    }

    public void notifyAppClient(String str, PayloadObject payloadObject) {
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("notifyAppClient called for directive: ", str, ", sessionDomain: ");
        outline115.append(payloadObject.getSessionDomain());
        rtcscLogger.i(outline115.toString());
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent createQueryIntent = createQueryIntent();
        ResolveInfo findIntentHandlerComponent = findIntentHandlerComponent(createQueryIntent, packageManager, payloadObject.getSessionDomain());
        if (findIntentHandlerComponent == null) {
            this.mLog.i("Appropriate AppClient service could not be found. Calling disconnectSession()...");
            this.mAppClientWrapper.disconnectSession(payloadObject.getSessionId(), RtcscAppDisconnectCode.APPCLIENT_NOT_FOUND);
            return;
        }
        createQueryIntent.setComponent(new ComponentName(findIntentHandlerComponent.serviceInfo.packageName, findIntentHandlerComponent.serviceInfo.name));
        int i = Build.VERSION.SDK_INT;
        if (startForegroundService(createQueryIntent) == null) {
            this.mLog.i("AppClient could not be started. Calling disconnectSession()...");
            this.mAppClientWrapper.disconnectSession(payloadObject.getSessionId(), RtcscAppDisconnectCode.APPCLIENT_START_FAILED);
            return;
        }
        this.mLog.i("Successfully notified the AppClient of the incoming session.");
    }
}
