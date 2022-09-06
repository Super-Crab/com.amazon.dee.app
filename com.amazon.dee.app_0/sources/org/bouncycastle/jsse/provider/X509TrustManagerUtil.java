package org.bouncycastle.jsse.provider;

import java.lang.reflect.Constructor;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class X509TrustManagerUtil {
    private static final Constructor<? extends X509TrustManager> exportX509TrustManagerConstructor;
    private static final Constructor<? extends BCX509ExtendedTrustManager> importX509TrustManagerConstructor;
    private static final Class<?> x509ExtendedTrustManagerClass;

    /* JADX WARN: Removed duplicated region for block: B:23:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            java.lang.String r0 = "javax.net.ssl.X509ExtendedTrustManager"
            r1 = 0
            java.lang.Class r2 = org.bouncycastle.jsse.provider.ReflectionUtil.getClass(r0)     // Catch: java.lang.Exception -> L8
            goto L9
        L8:
            r2 = r1
        L9:
            org.bouncycastle.jsse.provider.X509TrustManagerUtil.x509ExtendedTrustManagerClass = r2
            r2 = 0
            r3 = 1
            java.lang.reflect.Method[] r0 = org.bouncycastle.jsse.provider.ReflectionUtil.getMethods(r0)     // Catch: java.lang.Exception -> L20
            if (r0 == 0) goto L20
            java.lang.String r0 = "org.bouncycastle.jsse.provider.ExportX509TrustManager_7"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L20
            java.lang.Class<org.bouncycastle.jsse.BCX509ExtendedTrustManager> r5 = org.bouncycastle.jsse.BCX509ExtendedTrustManager.class
            r4[r2] = r5     // Catch: java.lang.Exception -> L20
            java.lang.reflect.Constructor r0 = org.bouncycastle.jsse.provider.ReflectionUtil.getDeclaredConstructor(r0, r4)     // Catch: java.lang.Exception -> L20
            goto L21
        L20:
            r0 = r1
        L21:
            org.bouncycastle.jsse.provider.X509TrustManagerUtil.exportX509TrustManagerConstructor = r0
            java.lang.Class<?> r0 = org.bouncycastle.jsse.provider.X509TrustManagerUtil.x509ExtendedTrustManagerClass
            if (r0 == 0) goto L31
            java.lang.String r4 = "org.bouncycastle.jsse.provider.ImportX509TrustManager_7"
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L31
            r3[r2] = r0     // Catch: java.lang.Exception -> L31
            java.lang.reflect.Constructor r1 = org.bouncycastle.jsse.provider.ReflectionUtil.getDeclaredConstructor(r4, r3)     // Catch: java.lang.Exception -> L31
        L31:
            org.bouncycastle.jsse.provider.X509TrustManagerUtil.importX509TrustManagerConstructor = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.X509TrustManagerUtil.<clinit>():void");
    }

    X509TrustManagerUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509TrustManager exportX509TrustManager(BCX509ExtendedTrustManager bCX509ExtendedTrustManager) {
        if (bCX509ExtendedTrustManager instanceof ImportX509TrustManager) {
            return ((ImportX509TrustManager) bCX509ExtendedTrustManager).unwrap();
        }
        Constructor<? extends X509TrustManager> constructor = exportX509TrustManagerConstructor;
        if (constructor != null) {
            try {
                return constructor.newInstance(bCX509ExtendedTrustManager);
            } catch (Exception unused) {
            }
        }
        return new ExportX509TrustManager_5(bCX509ExtendedTrustManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCX509ExtendedTrustManager importX509TrustManager(JcaJceHelper jcaJceHelper, X509TrustManager x509TrustManager) {
        if (x509TrustManager instanceof BCX509ExtendedTrustManager) {
            return (BCX509ExtendedTrustManager) x509TrustManager;
        }
        if (x509TrustManager instanceof ExportX509TrustManager) {
            return ((ExportX509TrustManager) x509TrustManager).unwrap();
        }
        if (importX509TrustManagerConstructor != null && x509ExtendedTrustManagerClass.isInstance(x509TrustManager)) {
            try {
                return importX509TrustManagerConstructor.newInstance(x509TrustManager);
            } catch (Exception unused) {
            }
        }
        return new ImportX509TrustManager_5(jcaJceHelper, x509TrustManager);
    }
}
