package com.google.android.gms.common.api.internal;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zaan extends zaau {
    final /* synthetic */ zaak zagj;
    private final Map<Api.Client, zaam> zagl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaan(zaak zaakVar, Map<Api.Client, zaam> map) {
        super(zaakVar, null);
        this.zagj = zaakVar;
        this.zagl = map;
    }

    @Override // com.google.android.gms.common.api.internal.zaau
    @GuardedBy("mLock")
    @WorkerThread
    public final void zaan() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        boolean z;
        Context context2;
        zabe zabeVar;
        com.google.android.gms.signin.zad zadVar;
        com.google.android.gms.signin.zad zadVar2;
        zabe zabeVar2;
        Context context3;
        boolean z2;
        googleApiAvailabilityLight = this.zagj.zaey;
        GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(googleApiAvailabilityLight);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.zagl.keySet()) {
            if (client.requiresGooglePlayServices()) {
                z2 = this.zagl.get(client).zaec;
                if (!z2) {
                    arrayList.add(client);
                }
            }
            arrayList2.add(client);
        }
        int i = -1;
        int i2 = 0;
        if (arrayList.isEmpty()) {
            int size = arrayList2.size();
            while (i2 < size) {
                Object obj = arrayList2.get(i2);
                i2++;
                context3 = this.zagj.mContext;
                i = googleApiAvailabilityCache.getClientAvailability(context3, (Api.Client) obj);
                if (i == 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList.size();
            while (i2 < size2) {
                Object obj2 = arrayList.get(i2);
                i2++;
                context = this.zagj.mContext;
                i = googleApiAvailabilityCache.getClientAvailability(context, (Api.Client) obj2);
                if (i != 0) {
                    break;
                }
            }
        }
        if (i == 0) {
            z = this.zagj.zagd;
            if (z) {
                zadVar = this.zagj.zagb;
                if (zadVar != null) {
                    zadVar2 = this.zagj.zagb;
                    zadVar2.connect();
                }
            }
            for (Api.Client client2 : this.zagl.keySet()) {
                zaam zaamVar = this.zagl.get(client2);
                if (client2.requiresGooglePlayServices()) {
                    context2 = this.zagj.mContext;
                    if (googleApiAvailabilityCache.getClientAvailability(context2, client2) != 0) {
                        zabeVar = this.zagj.zaft;
                        zabeVar.zaa(new zaap(this, this.zagj, zaamVar));
                    }
                }
                client2.connect(zaamVar);
            }
            return;
        }
        ConnectionResult connectionResult = new ConnectionResult(i, null);
        zabeVar2 = this.zagj.zaft;
        zabeVar2.zaa(new zaao(this, this.zagj, connectionResult));
    }
}
