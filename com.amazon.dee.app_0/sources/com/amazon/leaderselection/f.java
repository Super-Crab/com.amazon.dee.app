package com.amazon.leaderselection;

import android.content.pm.PackageManager;
/* loaded from: classes12.dex */
public class f {
    private final PackageManager a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(PackageManager packageManager) {
        this.a = packageManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0053, code lost:
        if (r4 == 1) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
        r10.skipValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0059, code lost:
        r2 = com.amazon.leaderselection.LeaderSelectionServiceVersion.create(r10.nextString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.leaderselection.CandidateRegistration a(int r9, android.content.pm.ServiceInfo r10) {
        /*
            r8 = this;
            java.lang.String r0 = "ContentValues"
            android.content.pm.PackageManager r1 = r8.a     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ld6
            android.content.pm.ApplicationInfo r10 = r10.applicationInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ld6
            java.lang.String r10 = r10.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ld6
            android.content.res.Resources r10 = r1.getResourcesForApplication(r10)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ld6
            java.io.InputStream r9 = r10.openRawResource(r9)     // Catch: java.io.IOException -> Lc9 java.io.UnsupportedEncodingException -> Lcd
            android.util.JsonReader r10 = new android.util.JsonReader     // Catch: java.lang.Throwable -> Lbb
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> Lbb
            r1.<init>(r9)     // Catch: java.lang.Throwable -> Lbb
            r10.<init>(r1)     // Catch: java.lang.Throwable -> Lbb
            com.amazon.leaderselection.CandidateRole r1 = com.amazon.leaderselection.CandidateRole.NONE     // Catch: java.lang.Throwable -> Laf
            com.amazon.leaderselection.LeaderSelectionServiceVersion r2 = com.amazon.leaderselection.LeaderSelectionServiceVersion.UNKNOWN     // Catch: java.lang.Throwable -> Laf
            r10.beginObject()     // Catch: java.lang.Throwable -> Laf
        L21:
            boolean r3 = r10.hasNext()     // Catch: java.lang.Throwable -> Laf
            if (r3 == 0) goto L6b
            java.lang.String r3 = r10.nextName()     // Catch: java.lang.Throwable -> Laf
            r4 = -1
            int r5 = r3.hashCode()     // Catch: java.lang.Throwable -> Laf
            r6 = 3506294(0x358076, float:4.913364E-39)
            r7 = 1
            if (r5 == r6) goto L47
            r6 = 351608024(0x14f51cd8, float:2.4750055E-26)
            if (r5 == r6) goto L3c
            goto L51
        L3c:
            java.lang.String r5 = "version"
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Throwable -> Laf
            if (r3 == 0) goto L51
            r4 = r7
            goto L51
        L47:
            java.lang.String r5 = "role"
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Throwable -> Laf
            if (r3 == 0) goto L51
            r4 = 0
        L51:
            if (r4 == 0) goto L62
            if (r4 == r7) goto L59
            r10.skipValue()     // Catch: java.lang.Throwable -> Laf
            goto L21
        L59:
            java.lang.String r2 = r10.nextString()     // Catch: java.lang.Throwable -> Laf
            com.amazon.leaderselection.LeaderSelectionServiceVersion r2 = com.amazon.leaderselection.LeaderSelectionServiceVersion.create(r2)     // Catch: java.lang.Throwable -> Laf
            goto L21
        L62:
            java.lang.String r1 = r10.nextString()     // Catch: java.lang.Throwable -> Laf
            com.amazon.leaderselection.CandidateRole r1 = com.amazon.leaderselection.CandidateRole.create(r1)     // Catch: java.lang.Throwable -> Laf
            goto L21
        L6b:
            r10.endObject()     // Catch: java.lang.Throwable -> Laf
            com.amazon.leaderselection.CandidateRole r3 = com.amazon.leaderselection.CandidateRole.NONE     // Catch: java.lang.Throwable -> Laf
            if (r3 == r1) goto L88
            com.amazon.leaderselection.LeaderSelectionServiceVersion r3 = com.amazon.leaderselection.LeaderSelectionServiceVersion.UNKNOWN     // Catch: java.lang.Throwable -> Laf
            boolean r3 = r3.equals(r2)     // Catch: java.lang.Throwable -> Laf
            if (r3 == 0) goto L7b
            goto L88
        L7b:
            com.amazon.leaderselection.CandidateRegistration r1 = com.amazon.leaderselection.CandidateRegistration.create(r1, r2)     // Catch: java.lang.Throwable -> Laf
            r10.close()     // Catch: java.lang.Throwable -> Lbb
            if (r9 == 0) goto L87
            r9.close()     // Catch: java.io.IOException -> Lc9 java.io.UnsupportedEncodingException -> Lcd
        L87:
            return r1
        L88:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Laf
            r3.<init>()     // Catch: java.lang.Throwable -> Laf
            java.lang.String r4 = "Candidate registration was invalid: "
            r3.append(r4)     // Catch: java.lang.Throwable -> Laf
            r3.append(r1)     // Catch: java.lang.Throwable -> Laf
            java.lang.String r1 = " "
            r3.append(r1)     // Catch: java.lang.Throwable -> Laf
            r3.append(r2)     // Catch: java.lang.Throwable -> Laf
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> Laf
            android.util.Log.e(r0, r1)     // Catch: java.lang.Throwable -> Laf
            com.amazon.leaderselection.CandidateRegistration r1 = com.amazon.leaderselection.CandidateRegistration.UNKNOWN     // Catch: java.lang.Throwable -> Laf
            r10.close()     // Catch: java.lang.Throwable -> Lbb
            if (r9 == 0) goto Lae
            r9.close()     // Catch: java.io.IOException -> Lc9 java.io.UnsupportedEncodingException -> Lcd
        Lae:
            return r1
        Laf:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> Lb1
        Lb1:
            r2 = move-exception
            r10.close()     // Catch: java.lang.Throwable -> Lb6
            goto Lba
        Lb6:
            r10 = move-exception
            r1.addSuppressed(r10)     // Catch: java.lang.Throwable -> Lbb
        Lba:
            throw r2     // Catch: java.lang.Throwable -> Lbb
        Lbb:
            r10 = move-exception
            throw r10     // Catch: java.lang.Throwable -> Lbd
        Lbd:
            r1 = move-exception
            if (r9 == 0) goto Lc8
            r9.close()     // Catch: java.lang.Throwable -> Lc4
            goto Lc8
        Lc4:
            r9 = move-exception
            r10.addSuppressed(r9)     // Catch: java.io.IOException -> Lc9 java.io.UnsupportedEncodingException -> Lcd
        Lc8:
            throw r1     // Catch: java.io.IOException -> Lc9 java.io.UnsupportedEncodingException -> Lcd
        Lc9:
            r9 = move-exception
            java.lang.String r10 = "Failed to parse registration"
            goto Ld0
        Lcd:
            r9 = move-exception
            java.lang.String r10 = "Cannot convert data in format"
        Ld0:
            android.util.Log.e(r0, r10, r9)
            com.amazon.leaderselection.CandidateRegistration r9 = com.amazon.leaderselection.CandidateRegistration.UNKNOWN
            return r9
        Ld6:
            java.lang.String r9 = "Name of the package was not found"
            android.util.Log.w(r0, r9)
            com.amazon.leaderselection.CandidateRegistration r9 = com.amazon.leaderselection.CandidateRegistration.UNKNOWN
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.leaderselection.f.a(int, android.content.pm.ServiceInfo):com.amazon.leaderselection.CandidateRegistration");
    }
}
