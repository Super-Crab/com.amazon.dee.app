package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.util.EnumSet;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class an implements as {
    private as di = null;
    private as dj = null;
    private final ed o;

    public an(ed edVar) {
        this.o = edVar;
    }

    private as ag() {
        if (this.di == null) {
            this.di = new av(this.o);
        }
        return this.di;
    }

    private as ah() {
        if (this.dj == null) {
            this.dj = new ao(this.o, (byte) 0);
        }
        return this.dj;
    }

    @Override // com.amazon.identity.auth.device.as
    public MAPFuture<Bundle> a(String str, String str2, Callback callback, Bundle bundle, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet, ej ejVar) {
        if (au.a(im.dl(str2))) {
            return ah().a(str, im.dl(str2).getKey(), callback, bundle, enumSet, ejVar);
        }
        return ag().a(str, str2, callback, bundle, enumSet, ejVar);
    }

    @Override // com.amazon.identity.auth.device.as
    public Bundle peekAttribute(String str, String str2) {
        if (au.a(im.dl(str2))) {
            return ah().peekAttribute(str, im.dl(str2).getKey());
        }
        return ag().peekAttribute(str, str2);
    }

    @Override // com.amazon.identity.auth.device.as
    public MAPFuture<Bundle> setAttribute(String str, String str2, String str3, Callback callback) {
        if (au.a(im.dl(str2))) {
            return ah().setAttribute(str, im.dl(str2).getKey(), str3, callback);
        }
        return ag().setAttribute(str, str2, str3, callback);
    }
}
