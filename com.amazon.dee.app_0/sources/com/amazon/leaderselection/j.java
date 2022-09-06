package com.amazon.leaderselection;

import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.amazon.leaderselection.l;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
class j extends l.a {
    private static final String d = j.class.getSimpleName();
    private final x a;
    private final SignatureVerifier b;
    private final m c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[SignatureVerifier.VerifyResult.values().length];

        static {
            try {
                a[SignatureVerifier.VerifyResult.VERIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[SignatureVerifier.VerifyResult.UNKNOWN_PACKAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[SignatureVerifier.VerifyResult.PACKAGENAME_NOT_FOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[SignatureVerifier.VerifyResult.INTERNAL_ERROR_VERIFYING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[SignatureVerifier.VerifyResult.SIGNATURE_COUNT_EXCEEDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[SignatureVerifier.VerifyResult.NOT_VERIFIED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(PackageManager packageManager, SignatureVerifier signatureVerifier, m mVar) {
        this(signatureVerifier, new x(packageManager), mVar);
    }

    j(SignatureVerifier signatureVerifier, x xVar, m mVar) {
        this.b = signatureVerifier;
        this.a = xVar;
        this.c = mVar;
    }

    private Leader a(o oVar, String str) {
        Candidate a2 = oVar.a();
        if (!"unknown".equals(a2.getPackageName()) && this.a.a(a2, str)) {
            return Leader.create(a2.getPackageName(), str);
        }
        return Leader.UNKNOWN;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0043, code lost:
        return com.amazon.leaderselection.Leader.UNKNOWN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.leaderselection.Leader a(com.amazon.leaderselection.EnumC0247r r3, com.amazon.leaderselection.o r4, java.lang.String r5) {
        /*
            r2 = this;
            r0 = 0
            r1 = r4
            r4 = r3
        L3:
            r3 = r0
        L4:
            if (r3 != 0) goto L44
            com.amazon.leaderselection.m r3 = r2.c
            android.os.Message r3 = r3.a(r4, r1)
            boolean r4 = com.amazon.leaderselection.p.a(r3)
            if (r4 == 0) goto L2b
            int r4 = r3.what
            com.amazon.leaderselection.r r4 = com.amazon.leaderselection.EnumC0247r.b(r4)
            com.amazon.leaderselection.o r1 = new com.amazon.leaderselection.o
            android.os.Bundle r3 = r3.getData()
            r1.<init>(r3)
            com.amazon.leaderselection.r r3 = com.amazon.leaderselection.EnumC0247r.FINISHED
            if (r4 == r3) goto L29
            com.amazon.leaderselection.r r3 = com.amazon.leaderselection.EnumC0247r.ERROR
            if (r4 != r3) goto L3
        L29:
            r3 = 1
            goto L4
        L2b:
            java.lang.String r4 = com.amazon.leaderselection.j.d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Message was invalid: "
            r5.append(r0)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            android.util.Log.e(r4, r3)
        L41:
            com.amazon.leaderselection.Leader r3 = com.amazon.leaderselection.Leader.UNKNOWN
            return r3
        L44:
            com.amazon.leaderselection.r r3 = com.amazon.leaderselection.EnumC0247r.FINISHED
            if (r4 != r3) goto L4d
            com.amazon.leaderselection.Leader r3 = r2.a(r1, r5)
            return r3
        L4d:
            java.lang.String r3 = com.amazon.leaderselection.j.d
            java.lang.String r4 = "ERROR while selecting leader"
            android.util.Log.e(r3, r4)
            goto L41
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.leaderselection.j.a(com.amazon.leaderselection.r, com.amazon.leaderselection.o, java.lang.String):com.amazon.leaderselection.Leader");
    }

    private void b() {
        String str;
        int i = a.a[this.b.verifyUid(Binder.getCallingUid()).ordinal()];
        if (i != 1) {
            if (i == 2) {
                throw new MissingPackageSecurityException("Uid did not resolve to a package name");
            }
            if (i == 3) {
                throw new MissingPackageSecurityException("Package name not found");
            }
            if (i == 4) {
                str = "Internal error verifying signature";
            } else if (i != 5) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Call from unacceptable uid: ");
                outline107.append(Binder.getCallingUid());
                str = outline107.toString();
            } else {
                str = "App signed by too many signatures";
            }
            throw new VerificationSecurityException(str);
        }
    }

    @Override // com.amazon.leaderselection.l
    public synchronized Message a(Message message) {
        b();
        if (p.a(message)) {
            return this.c.a(EnumC0247r.b(message.what), new o(message.getData()));
        }
        String str = d;
        Log.e(str, "Message was invalid: " + message);
        return p.d;
    }

    @Override // com.amazon.leaderselection.l
    public synchronized Leader a(String str) {
        b();
        if (TextUtils.isEmpty(str)) {
            Log.e(d, "Started leader selection with a null or empty serviceName: " + str);
            return Leader.UNKNOWN;
        }
        String str2 = "Selecting Leader for: " + str;
        Message d2 = this.c.d();
        if (p.a(d2)) {
            EnumC0247r b = EnumC0247r.b(d2.what);
            o oVar = new o(d2.getData());
            if (b == EnumC0247r.FINISHED) {
                return a(oVar, str);
            }
            return a(b, oVar, str);
        }
        Log.e(d, "Message was invalid: " + d2);
        return Leader.UNKNOWN;
    }
}
