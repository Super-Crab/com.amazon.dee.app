package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ar {
    private static ar dB;
    private final aq dC;
    private final ed o;
    private final gg w;
    public static final long dz = jj.b(1, TimeUnit.MILLISECONDS);
    private static final long dA = jj.c(1, TimeUnit.MILLISECONDS);
    private static final String TAG = ar.class.getName();
    private final Map<String, a> dD = new HashMap();
    private final eh F = new eh();
    private final Random dE = new Random();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.ar$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] dG = new int[CORPFMResponse.ComputationConfidenceValue.values().length];

        static {
            try {
                dG[CORPFMResponse.ComputationConfidenceValue.CUSTOMER_PROVIDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                dG[CORPFMResponse.ComputationConfidenceValue.CUSTOMER_BASED_GUESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                dG[CORPFMResponse.ComputationConfidenceValue.DEVICE_BASED_GUESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        final CountDownLatch dH = new CountDownLatch(1);
        CORPFMResponse dI;

        a() {
        }
    }

    ar(Context context) {
        this.o = ed.M(context);
        this.w = this.o.dV();
        this.dC = new aq(this.o);
    }

    private String ao(String str) {
        try {
            return Settings.Secure.getString(this.o.getContentResolver(), str);
        } catch (Exception e) {
            io.w(TAG, "Error calling Secure Settings for resource ".concat(String.valueOf(str)), e);
            return null;
        }
    }

    private CORPFMResponse ap(String str) {
        String b = this.w.b(str, AccountConstants.KEY_COR);
        String b2 = this.w.b(str, AccountConstants.KEY_PFM);
        io.i(TAG, String.format("COR is empty: %b, PFM is empty: %b", Boolean.valueOf(TextUtils.isEmpty(b)), Boolean.valueOf(TextUtils.isEmpty(b2))));
        return new CORPFMResponse(b, b2, CORPFMResponse.ComputationConfidenceValue.CUSTOMER_PROVIDED, je.dB(this.w.b(str, "last_updated_cor_pfm")));
    }

    public static boolean b(String str, String str2, String str3) {
        return (str == null || TextUtils.isEmpty(str2) || str3 == null) ? false : true;
    }

    public static void generateNewInstance(Context context) {
        dB = new ar(context.getApplicationContext());
    }

    public static synchronized ar h(Context context) {
        ar arVar;
        synchronized (ar.class) {
            if (dB == null || jk.gR()) {
                generateNewInstance(context);
            }
            arVar = dB;
        }
        return arVar;
    }

    boolean a(CORPFMResponse cORPFMResponse, Long l) {
        long longValue = l.longValue();
        long j = dA;
        if (longValue >= j) {
            j = l.longValue();
        }
        Long am = cORPFMResponse.am();
        if (am == null) {
            return true;
        }
        if (am.longValue() == -1) {
            return false;
        }
        long currentTimeMillis = this.F.currentTimeMillis();
        double nextDouble = this.dE.nextDouble() * (this.dE.nextBoolean() ? 1 : -1) * 0.5d * j;
        io.i(TAG, String.format(Locale.ENGLISH, "Current time: %d %n cor/pfm last updated time: %d %n TTL: %d %n Jitter: %d", Long.valueOf(currentTimeMillis), am, Long.valueOf(j), Long.valueOf((long) nextDouble)));
        if (currentTimeMillis < am.longValue() + j + nextDouble) {
            io.i(TAG, "COR or PFM isn't expired.");
            return false;
        }
        io.i(TAG, "COR or PFM is expired.");
        return true;
    }

    public CORPFMResponse b(String str, ej ejVar) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        a aVar;
        boolean z;
        Throwable th;
        CORPFMResponse cORPFMResponse;
        synchronized (this.dD) {
            aVar = this.dD.get(str);
            if (aVar == null) {
                aVar = new a();
                this.dD.put(str, aVar);
                z = true;
            } else {
                z = false;
            }
        }
        CORPFMResponse cORPFMResponse2 = null;
        if (z) {
            io.i(TAG, "No outstanding request to fetch cor/pfm. Calling DCAS.");
            try {
                cORPFMResponse = new ap(this.o, str, ejVar).aq();
                if (cORPFMResponse != null) {
                    try {
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        boolean a2 = a(str, cORPFMResponse, linkedHashMap);
                        if (str != null) {
                            this.w.a(new fz(str, linkedHashMap, null));
                        }
                        if (a2) {
                            io.i(TAG, "COR/PFM value has changed. Sending notifications.");
                            a(str, aa.f(this.o));
                        } else {
                            io.i(TAG, "COR/PFM values are not different. Not firing the changed broadcast");
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        aVar.dI = cORPFMResponse;
                        aVar.dH.countDown();
                        synchronized (this.dD) {
                            this.dD.remove(str);
                        }
                        throw th;
                    }
                }
                aVar.dI = cORPFMResponse;
                aVar.dH.countDown();
                synchronized (this.dD) {
                    this.dD.remove(str);
                }
                return cORPFMResponse;
            } catch (Throwable th3) {
                th = th3;
                cORPFMResponse = null;
            }
        } else {
            io.dm(TAG);
            try {
                if (aVar.dH.await(5L, TimeUnit.SECONDS)) {
                    cORPFMResponse2 = aVar.dI;
                } else {
                    io.e(TAG, "Timed out waiting for cor/pfm response");
                }
                return cORPFMResponse2;
            } catch (InterruptedException e) {
                io.e(TAG, "Interrupted waiting for cor/pfm response", e);
                return cORPFMResponse2;
            }
        }
    }

    public void a(CORPFMResponse cORPFMResponse, Map<String, String> map) {
        if (cORPFMResponse == null) {
            io.w(TAG, "Cor/PFM response given to set is null. Not setting.");
        } else {
            a(null, cORPFMResponse, map);
        }
    }

    private boolean a(String str, CORPFMResponse cORPFMResponse, Map<String, String> map) {
        boolean b;
        String str2 = TAG;
        Object[] objArr = new Object[3];
        objArr[0] = cORPFMResponse.ai();
        objArr[1] = cORPFMResponse.ak();
        objArr[2] = cORPFMResponse.an() != null ? cORPFMResponse.an().toString() : null;
        io.i(str2, String.format("Trying to save COR/PFM response : %nCoR: %s %nPFM:%s %n Computation Confidence Value: %s", objArr));
        int i = AnonymousClass2.dG[cORPFMResponse.an().ordinal()];
        if (i == 1) {
            b = b(str, cORPFMResponse, map);
        } else if (i == 2) {
            b = false;
        } else if (i == 3) {
            b = b(cORPFMResponse);
        } else {
            throw new IllegalStateException(String.format("Cor Pfm value type %s is not supported", cORPFMResponse.an().name()));
        }
        Long am = cORPFMResponse.am();
        if (am != null) {
            map.put("last_updated_cor_pfm", String.valueOf(am));
        }
        map.put("is_cor_pfm_set", "true");
        if (TextUtils.isEmpty(cORPFMResponse.ai())) {
            io.i(TAG, "COR is empty.");
            mq.incrementCounterAndRecord("CorIsEmpty", new String[0]);
        } else {
            io.i(TAG, "COR is not empty.");
        }
        if (TextUtils.isEmpty(cORPFMResponse.ak())) {
            io.i(TAG, "PFM is empty.");
            mq.incrementCounterAndRecord("PfmIsEmpty", new String[0]);
        } else {
            io.i(TAG, "PFM is not empty.");
        }
        return b;
    }

    private boolean b(CORPFMResponse cORPFMResponse) {
        io.i(TAG, "Saving device defaults COR/PFM");
        CORPFMResponse av = this.dC.av();
        this.dC.a(cORPFMResponse);
        if (cORPFMResponse.equals(av)) {
            io.i(TAG, "Default COR/PFM has not changed.");
            return false;
        }
        return true;
    }

    public static void a(String str, z zVar) {
        io.dm(TAG);
        Intent dy = iz.dy(CustomerAttributeStore.COR_PFM_CHANGED_INTENT_ACTION);
        dy.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, str);
        zVar.a(str, dy, AccountConstants.PERMISSION_MANAGE_COR_PFM);
    }

    private boolean b(String str, CORPFMResponse cORPFMResponse, Map<String, String> map) {
        io.i(TAG, "Saving user backed COR/PFM");
        if (str != null) {
            if (!this.w.D(str)) {
                io.w(TAG, "Could not save COR/PFM values because the given account does not exist");
                return false;
            } else if (cORPFMResponse.equals(ap(str))) {
                io.i(TAG, "User COR PFM has not changed.");
                return false;
            }
        }
        map.put(AccountConstants.KEY_COR, cORPFMResponse.ai());
        map.put(AccountConstants.KEY_PFM, cORPFMResponse.ak());
        return true;
    }

    public CORPFMResponse a(final String str, final ej ejVar) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        CORPFMResponse cORPFMResponse;
        CORPFMResponse cORPFMResponse2 = null;
        boolean z = false;
        if (str != null && this.w.D(str)) {
            if (this.w.b(str, "is_cor_pfm_set") != null) {
                z = true;
            }
            if (!z) {
                io.i(TAG, "MAP has not set PRM/COR yet.");
            } else {
                cORPFMResponse2 = ap(str);
            }
        } else {
            io.i(TAG, "Account is empty or not registered.");
            io.a("Account %s not registered. Getting default COR/PFM from secure settings or device default", str);
            io.i(TAG, "getting Cor/Pfm from Secure Settings");
            String ao = ao("DEFAULT_COR");
            String ao2 = ao("DEFAULT_PFM");
            if (TextUtils.isEmpty(ao) || TextUtils.isEmpty(ao2)) {
                cORPFMResponse = null;
            } else {
                io.i(TAG, String.format("Returning Cor/Pfm from Secure Settings. Cor: %s, Pfm: %s", ao, ao2));
                cORPFMResponse = new CORPFMResponse(ao, ao2, CORPFMResponse.ComputationConfidenceValue.CUSTOMER_PROVIDED, (Long) (-1L));
            }
            if (cORPFMResponse != null) {
                cORPFMResponse2 = cORPFMResponse;
            } else if (this.dC.as()) {
                cORPFMResponse2 = this.dC.av();
            }
        }
        if (cORPFMResponse2 != null) {
            if (a(cORPFMResponse2, Long.valueOf(dz))) {
                io.i(TAG, "COR/PFM expires, refreshing it.");
                ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.ar.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            ar.this.b(str, ejVar);
                        } catch (Exception e) {
                            io.e(ar.TAG, "Exception on refreshing COR/PFM from server side.", e);
                        }
                    }
                });
            }
            io.i(TAG, "COR PFM has already been fetched. Returning current COR/PFM");
            return cORPFMResponse2;
        }
        io.i(TAG, "CoR/PFM is not currently set. Waiting to fetch CoR/PFM");
        return b(str, ejVar);
    }
}
