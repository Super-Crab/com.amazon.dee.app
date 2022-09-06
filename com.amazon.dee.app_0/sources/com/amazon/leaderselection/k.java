package com.amazon.leaderselection;

import android.os.RemoteException;
import android.util.Log;
/* loaded from: classes12.dex */
class k {
    private static final String d = "k";
    private final String a;
    private final a b;
    private Leader c = Leader.UNKNOWN;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(String str, a aVar) {
        this.a = str;
        this.b = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Leader a() {
        try {
            try {
                l b = this.b.b();
                if (b != null) {
                    this.c = b.a(this.a);
                    return this.c;
                }
            } catch (RemoteException unused) {
                Log.e(d, "Failed to select leader");
            }
            return Leader.UNKNOWN;
        } finally {
            this.b.a();
        }
    }
}
