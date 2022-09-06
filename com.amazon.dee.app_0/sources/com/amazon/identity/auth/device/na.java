package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Build;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.platform.weblab.MAPWeblabClient;
import com.amazon.identity.platform.weblab.Weblab;
import com.amazon.weblab.mobile.IMobileWeblabClient;
import com.amazon.weblab.mobile.MobileWeblabClientFactory;
import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import com.amazon.weblab.mobile.settings.MobileWeblabClientAttributes;
import com.amazon.weblab.mobile.settings.MobileWeblabOS;
import com.amazon.weblab.mobile.settings.MobileWeblabRuntimeConfiguration;
import com.amazon.weblab.mobile.settings.MobileWeblabServiceEndpoint;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class na implements MAPWeblabClient {
    private static final String TAG = "na";
    private static na vX;
    private static volatile Boolean vY;
    private ea at;
    private volatile IMobileWeblabClient mClient;
    private EnvironmentUtils vZ = EnvironmentUtils.cd();

    private na(Context context) {
        this.at = (ea) ed.M(context).getSystemService("dcp_device_info");
        this.mClient = bq(context);
    }

    public static synchronized na bp(Context context) {
        na naVar;
        synchronized (na.class) {
            if (vX == null && bt(context) && context != null) {
                vX = new na(context);
            }
            naVar = vX;
        }
        return naVar;
    }

    public static synchronized boolean bt(Context context) {
        Boolean bool;
        synchronized (na.class) {
            if (vY != null) {
                return vY.booleanValue();
            }
            Boolean bool2 = Boolean.FALSE;
            try {
                try {
                    Class<?> cls = Class.forName("com.amazon.weblab.mobile.settings.MobileWeblabClientAttributes");
                    cls.getConstructor(String.class, String.class, String.class, MobileWeblabOS.class, String.class);
                    cls.getMethod("addWeblab", String.class, String.class);
                    Class<?> cls2 = Class.forName("com.amazon.weblab.mobile.settings.MobileWeblabRuntimeConfiguration");
                    cls2.getConstructor(String.class);
                    cls2.getMethod("setEndpoint", MobileWeblabServiceEndpoint.class);
                    cls2.getMethod("setUpdateAtInitEnabled", Boolean.TYPE);
                    Class.forName("com.amazon.weblab.mobile.MobileWeblabClientFactory").getMethod("createMobileWeblabClient", MobileWeblabClientAttributes.class, MobileWeblabRuntimeConfiguration.class, String.class, String.class, String.class, Context.class);
                    Class.forName("com.amazon.weblab.mobile.IMobileWeblabClient").getMethod("getWeblab", String.class);
                    Class.forName("com.amazon.weblab.mobile.IMobileWeblab").getMethod("getTreatmentAndRecordTrigger", new Class[0]);
                    Class.forName("com.amazon.weblab.mobile.IMobileWeblabTreatmentAndTriggerResult").getMethod("getTreatment", new Class[0]);
                    io.dm(TAG);
                    bool = Boolean.TRUE;
                } catch (Exception e) {
                    String str = TAG;
                    io.w(str, "Other exception occurs: " + e.getMessage(), e);
                }
            } catch (ClassNotFoundException e2) {
                String str2 = TAG;
                io.w(str2, "MobileWeblabAndroidClient is not supported: " + e2.getMessage());
            } catch (NoSuchMethodException e3) {
                String str3 = TAG;
                io.w(str3, "MobileWeblabAndroidClient API changed " + e3.getMessage(), e3);
            }
            vY = Boolean.valueOf(bool.booleanValue() && mq.aQ(context));
            io.i(TAG, String.format(Locale.getDefault(), "Current App support weblab? %b", vY));
            return vY.booleanValue();
        }
    }

    private Weblab eX(String str) {
        try {
            return Weblab.eY(str);
        } catch (IllegalArgumentException e) {
            io.w(TAG, "Error while converting weblab ".concat(String.valueOf(str)), e);
            return null;
        }
    }

    IMobileWeblabClient bq(Context context) {
        IMobileWeblabClient createMobileWeblabClient = MobileWeblabClientFactory.createMobileWeblabClient(bs(context), br(context), iU(), this.vZ.co(), (String) null, context.getApplicationContext());
        String str = TAG;
        String.format("Weblab instance: %s, weblab market: %s", createMobileWeblabClient.getIMobileWeblabClientAttributes().getIdentifier(), createMobileWeblabClient.getMarketplace());
        io.dm(str);
        return createMobileWeblabClient;
    }

    MobileWeblabRuntimeConfiguration br(Context context) {
        File dir = context.getApplicationContext().getDir("mapweblab", 0);
        if (!dir.exists() && !dir.mkdirs()) {
            io.e(TAG, "can not create weblab folder");
        }
        MobileWeblabRuntimeConfiguration mobileWeblabRuntimeConfiguration = new MobileWeblabRuntimeConfiguration(dir.getAbsolutePath());
        mobileWeblabRuntimeConfiguration.setEndpoint(MobileWeblabServiceEndpoint.PROD);
        mobileWeblabRuntimeConfiguration.setUpdateAtInitEnabled(true);
        return mobileWeblabRuntimeConfiguration;
    }

    MobileWeblabClientAttributes bs(Context context) {
        MobileWeblabOS mobileWeblabOS;
        Weblab[] values;
        if (mz.aZ(context)) {
            io.dm(TAG);
            mobileWeblabOS = MobileWeblabOS.FIRE_OS;
        } else {
            io.dm(TAG);
            mobileWeblabOS = MobileWeblabOS.ANDROID;
        }
        MobileWeblabClientAttributes mobileWeblabClientAttributes = new MobileWeblabClientAttributes("MAP_ANDROID_ID", getAppName(context), hv.gt(), mobileWeblabOS, Integer.toString(Build.VERSION.SDK_INT));
        for (Weblab weblab : Weblab.values()) {
            mobileWeblabClientAttributes.addWeblab(weblab.getName(), weblab.iW().name());
        }
        return mobileWeblabClientAttributes;
    }

    String eW(String str) {
        return str.length() > 10 ? str.substring(0, 10) : str;
    }

    String getAppName(Context context) {
        String au;
        if (mz.bb(context)) {
            au = context.getApplicationContext().getPackageName();
        } else {
            au = ie.au(context);
        }
        GeneratedOutlineSupport1.outline161(au, "Weblab APP name is: ", TAG);
        return au;
    }

    @Override // com.amazon.identity.platform.weblab.MAPWeblabClient
    public String getTreatmentAndRecordTrigger(String str) {
        Weblab eX = eX(str);
        if (eX == null) {
            io.w(TAG, "Cannot converting weblab, return C");
            return Weblab.Treatment.C.name();
        }
        try {
            Future futureMobileWeblabTrigger = this.mClient.getWeblab(eX.getName()).getTreatmentAndRecordTrigger().getFutureMobileWeblabTrigger();
            if (futureMobileWeblabTrigger != null) {
                long currentTimeMillis = System.currentTimeMillis();
                MobileWeblabTriggerResult mobileWeblabTriggerResult = (MobileWeblabTriggerResult) futureMobileWeblabTrigger.get(1000L, TimeUnit.MILLISECONDS);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                String str2 = TAG;
                new StringBuilder("MAP weblab trigger result: ").append(mobileWeblabTriggerResult.name());
                io.dm(str2);
                String str3 = TAG;
                "MAP weblab updating time cost in milli sec: ".concat(String.valueOf(currentTimeMillis2));
                io.dm(str3);
                mq.b("MAP_WeblabTriggerResult_".concat(String.valueOf(mobileWeblabTriggerResult)), new String[0]);
                mq.b("MAP_WeblabTimeCost_".concat(String.valueOf(currentTimeMillis2)), new String[0]);
            }
            String treatment = this.mClient.getWeblab(eX.getName()).getTreatmentAndRecordTrigger().getTreatment();
            String str4 = TAG;
            String.format("MAP weblab value for %s: %s", str, treatment);
            io.dm(str4);
            mq.b("MAP_WeblabValue_".concat(String.valueOf(treatment)), new String[0]);
            return treatment;
        } catch (TimeoutException unused) {
            String name = eX.iW().name();
            io.a(TAG, null, "Timeout in weblab service call, returning default value: ".concat(String.valueOf(name)), "MAPWeblabTimeOut");
            return name;
        } catch (Exception e) {
            String name2 = eX.iW().name();
            io.e(TAG, "Exception when trying to get the weblab, fall back to default: ".concat(String.valueOf(name2)), e);
            return name2;
        }
    }

    String iU() {
        String dS = this.at.dS();
        GeneratedOutlineSupport1.outline161(dS, "The dsn for session id: ", TAG);
        String eW = eW(dS);
        GeneratedOutlineSupport1.outline161(eW, "Session ID: ", TAG);
        return eW;
    }
}
