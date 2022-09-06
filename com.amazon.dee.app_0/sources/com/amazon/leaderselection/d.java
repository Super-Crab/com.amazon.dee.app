package com.amazon.leaderselection;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class d {
    private static final String d = "d";
    private final String a;
    private final PackageManager b;
    private final f c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(String str, PackageManager packageManager, f fVar) {
        this.a = str;
        this.b = packageManager;
        this.c = fVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<c> a() {
        String str;
        StringBuilder sb;
        String str2;
        List<ResolveInfo> queryIntentServices = this.b.queryIntentServices(LeaderSelectionService.e, 128);
        HashSet hashSet = new HashSet();
        if (queryIntentServices == null) {
            return hashSet;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            String str3 = resolveInfo.serviceInfo.packageName;
            if (!this.a.equals(str3) && resolveInfo.serviceInfo.exported) {
                ComponentName componentName = new ComponentName(str3, resolveInfo.serviceInfo.name);
                String str4 = "Found candidate: " + componentName;
                int i = resolveInfo.serviceInfo.applicationInfo.metaData.getInt(CandidateRegistration.METADATA_REGISTRATION_KEY, 0);
                if (i == 0) {
                    str = d;
                    sb = new StringBuilder();
                    str2 = "No registration resource available for: ";
                } else {
                    CandidateRegistration a = this.c.a(i, resolveInfo.serviceInfo);
                    if (CandidateRegistration.UNKNOWN.equals(a)) {
                        str = d;
                        sb = new StringBuilder();
                        str2 = "Failed to register: ";
                    } else {
                        hashSet.add(new c(componentName, a));
                    }
                }
                sb.append(str2);
                sb.append(componentName);
                Log.e(str, sb.toString());
            }
        }
        return hashSet;
    }
}
