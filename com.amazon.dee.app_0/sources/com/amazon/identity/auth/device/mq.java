package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.mv;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class mq {
    private static final String TAG = "mq";
    private static Context mO;
    private static mu vs;
    private static volatile Boolean vt;
    private static volatile Boolean vu;
    private static volatile Boolean vv;
    private static volatile Boolean vw;
    private static final boolean vx = "yes".equals(new eg().get("com.amazon.map.verbose.metrics"));

    private mq() {
    }

    public static void P(Context context) {
        mO = context.getApplicationContext();
    }

    public static Callback a(final mv mvVar, final Callback callback) {
        return new Callback() { // from class: com.amazon.identity.auth.device.mq.1
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                mv.this.stop();
                Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.onError(bundle);
                }
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                mv.this.stop();
                Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.onSuccess(bundle);
                }
            }
        };
    }

    public static mv aD(String str, String str2) {
        mv bVar;
        String outline75 = GeneratedOutlineSupport1.outline75(str, "_", str2);
        mu aO = aO(mO);
        if (aO != null) {
            bVar = aO.eO(outline75);
        } else {
            bVar = new mv.b();
        }
        bVar.start();
        return bVar;
    }

    public static mv aE(String str, String str2) {
        if (vx) {
            return aD(str, str2);
        }
        return new mv.b();
    }

    public static void aF(String str, String str2) {
        aO(mO).a("MAP_3P", str, str2);
    }

    public static void aG(String str, String str2) {
        aO(mO).a("MAP_FireOS", str, str2);
    }

    public static synchronized mu aO(Context context) {
        synchronized (mq.class) {
            if (context == null) {
                io.e(TAG, "MetricsHelper has not been init yet. MAP will not be able to emit metrics");
                return new mt(null);
            }
            return s(context, context.getPackageName(), "MAPClientLib");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean aP(Context context) {
        Boolean bool = vv;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        if (aR(context)) {
            try {
                Class<?> cls = Class.forName("com.amazon.client.metrics.thirdparty.PeriodicMetricReporter");
                Class.forName("com.amazon.client.metrics.thirdparty.PeriodicMetricReporterImpl");
                cls.getMethod("startRecordingPeriodically", Long.TYPE, TimeUnit.class);
                cls.getMethod("getMetricEvent", new Class[0]);
                bool2 = Boolean.TRUE;
                io.i(TAG, "ThirdParty Periodic metrics is supported");
            } catch (ClassNotFoundException unused) {
                io.w(TAG, "ThirdParty Periodic metrics not supported");
            } catch (NoSuchMethodException unused2) {
                io.w(TAG, "ThirdParty Periodic metrics library is too old");
            }
        }
        vv = bool2;
        return bool2.booleanValue();
    }

    public static boolean aQ(Context context) {
        return aR(context) || iO();
    }

    public static boolean aR(Context context) {
        Boolean bool = vt;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        try {
            Class.forName("com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl");
            Class.forName("com.amazon.client.metrics.thirdparty.MetricsFactory").getMethod("createConcurrentMetricEvent", String.class, String.class);
            if (mz.iQ()) {
                bool2 = Boolean.FALSE;
                io.gE();
            } else {
                bool2 = Boolean.TRUE;
                io.i(TAG, "ThirdParty DCP metrics is supported");
            }
        } catch (ClassNotFoundException unused) {
            io.w(TAG, "ThirdParty DCP metrics not supported");
        } catch (NoSuchMethodException unused2) {
            io.w(TAG, "ThirdParty DCP metrics is too old");
        }
        vt = bool2;
        if (!bool2.booleanValue() && !jk.gR() && !mz.aY(context)) {
            io.gE();
            io.w(TAG, "ThirdParty Metrics component is not integrated. Please integrate with latest metrics component.");
        }
        return bool2.booleanValue();
    }

    @Deprecated
    public static void b(String str, String... strArr) {
        aO(mO).b(str, strArr);
    }

    public static synchronized mu eT(String str) {
        synchronized (mq.class) {
            if (mO == null) {
                io.e(TAG, "MetricsHelper has not been init yet. MAP will not be able to emit metrics");
                return new mt(null);
            }
            return t(mO, mO.getPackageName(), str);
        }
    }

    public static boolean iN() {
        Boolean bool = vw;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        if (iO()) {
            try {
                Class<?> cls = Class.forName("com.amazon.client.metrics.PeriodicMetricReporter");
                Class.forName("com.amazon.client.metrics.PeriodicMetricReporterImpl");
                cls.getMethod("startRecordingPeriodically", Long.TYPE, TimeUnit.class);
                cls.getMethod("getMetricEvent", new Class[0]);
                Boolean bool3 = Boolean.TRUE;
                io.i(TAG, "FireOS Periodic metrics is supported");
                bool2 = bool3;
            } catch (ClassNotFoundException unused) {
                io.w(TAG, "FireOS Periodic metrics not supported");
                return false;
            } catch (NoSuchMethodException unused2) {
                io.w(TAG, "FireOS Periodic metrics library is too old");
                return false;
            }
        }
        vw = bool2;
        return bool2.booleanValue();
    }

    public static boolean iO() {
        Boolean bool = vu;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        try {
            Class.forName("com.amazon.client.metrics.AndroidMetricsFactoryImpl");
            Class.forName("com.amazon.client.metrics.MetricsFactory").getMethod("createConcurrentMetricEvent", String.class, String.class);
            bool2 = Boolean.TRUE;
            io.i(TAG, "FireOS DCP metrics is supported");
        } catch (ClassNotFoundException unused) {
            io.w(TAG, "FireOS DCP metrics not supported");
        } catch (NoSuchMethodException unused2) {
            io.w(TAG, "FireOS DCP metrics is too old");
        }
        vu = bool2;
        if (!bool2.booleanValue() && !jk.gR() && mz.aY(mO)) {
            io.gE();
            io.w(TAG, "FireOS Metrics component is not integrated. Please integrate with latest metrics component.");
        }
        return bool2.booleanValue();
    }

    public static void incrementCounterAndRecord(String str, String... strArr) {
        mu aO = aO(mO);
        aO.b(str, strArr);
        aO.iJ();
    }

    private static synchronized mu s(Context context, String str, String str2) {
        mu t;
        synchronized (mq.class) {
            mu muVar = vs;
            if (!(muVar instanceof mx) && !(muVar instanceof mn)) {
                if (jk.gR()) {
                    io.i(TAG, "Running in unit test, creating logging metrics collector");
                    t = new mt("UnitTest");
                } else {
                    t = t(context, str, str2);
                }
                vs = t;
                return t;
            }
            return muVar;
        }
    }

    private static synchronized mu t(Context context, String str, String str2) {
        synchronized (mq.class) {
            if (context != null) {
                if (aR(context)) {
                    io.i(TAG, "Using the ThirdPartyPlatformDCPMetricsCollector");
                    return new mx(context, str, str2);
                } else if (iO()) {
                    io.i(TAG, "Using the FireOSPlatformDCPMetricsCollector");
                    return new mn(context, str, str2);
                }
            }
            io.i(TAG, "Using the PlatformLoggingMetricsCollector");
            return new mt(null);
        }
    }

    public static Callback a(ej ejVar, Callback callback) {
        return a(ejVar, (mv) null, callback);
    }

    public static Callback a(ej ejVar, Callback callback, gy gyVar) {
        return a(ejVar, (mv) null, callback, gyVar);
    }

    public static String b(RegistrationType registrationType) {
        return registrationType == null ? "NullRegType" : registrationType.name();
    }

    public static Callback a(ej ejVar, mv mvVar, Callback callback) {
        return a(ejVar, mvVar, callback, null, false);
    }

    public static Callback a(ej ejVar, mv mvVar, Callback callback, gy gyVar) {
        return a(ejVar, mvVar, callback, gyVar, null, false);
    }

    public static Callback a(ej ejVar, mv mvVar, Callback callback, ed edVar) {
        return a(ejVar, mvVar, callback, edVar, false);
    }

    public static Callback a(ej ejVar, mv mvVar, Callback callback, ed edVar, boolean z) {
        return a(ejVar, mvVar, callback, null, edVar, z);
    }

    private static Callback a(ej ejVar, mv mvVar, Callback callback, gy gyVar, ed edVar, boolean z) {
        return a(ejVar, mvVar, callback, gyVar, edVar, z, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED);
    }

    public static Callback a(Callback callback, ej ejVar, String str, MAPAccountManager.RegistrationError registrationError) {
        return a(ejVar, null, callback, null, null, true, str, registrationError);
    }

    private static Callback a(final ej ejVar, final mv mvVar, final Callback callback, final gy gyVar, final ed edVar, final boolean z, final String str, final MAPAccountManager.RegistrationError registrationError) {
        return new Callback() { // from class: com.amazon.identity.auth.device.mq.2
            private String e(ed edVar2) {
                if (edVar2 == null) {
                    return null;
                }
                return ((ea) edVar2.getSystemService("dcp_device_info")).getDeviceType();
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                if (gyVar != null) {
                    io.dm(mq.TAG);
                    gyVar.onFinish(new Bundle());
                }
                mv mvVar2 = mv.this;
                if (mvVar2 != null) {
                    mvVar2.stop();
                }
                if (ejVar != null) {
                    String string = bundle.getString(MAPError.KEY_ERROR_TYPE);
                    if (string != null) {
                        ejVar.b("MAPError:".concat(string), e(edVar));
                    }
                    ejVar.b("Error:".concat(String.valueOf(MAPAccountManager.RegistrationError.fromValue(bundle.getInt(str, registrationError.value()), registrationError).getName())), e(edVar));
                    ejVar.eb();
                }
                Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.onError(bundle);
                }
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                mv mvVar2 = mv.this;
                if (mvVar2 != null) {
                    mvVar2.stop();
                }
                ej ejVar2 = ejVar;
                if (ejVar2 != null) {
                    if (z) {
                        ejVar2.bA("Success");
                    }
                    ejVar.eb();
                }
                Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.onSuccess(bundle);
                }
            }
        };
    }
}
