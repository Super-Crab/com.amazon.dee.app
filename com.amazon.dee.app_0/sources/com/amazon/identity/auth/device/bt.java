package com.amazon.identity.auth.device;

import android.text.TextUtils;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bt extends br {
    private final a hE;

    public bt(String str, String str2) {
        this.hE = new a(str, str2);
    }

    @Override // com.amazon.identity.auth.device.kq
    public a y() {
        return this.hE;
    }

    @Override // com.amazon.identity.auth.device.br
    public boolean z() {
        return TextUtils.isEmpty(this.hE.getToken()) || TextUtils.isEmpty(this.hE.a());
    }
}
