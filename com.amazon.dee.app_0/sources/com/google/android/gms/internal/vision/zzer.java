package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzer extends zzeq<FieldDescriptorType, Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzer(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.vision.zzeq
    public final void zzao() {
        if (!isImmutable()) {
            for (int i = 0; i < zzdl(); i++) {
                Map.Entry<FieldDescriptorType, Object> zzan = zzan(i);
                if (((zzcl) zzan.getKey()).zzbq()) {
                    zzan.setValue(Collections.unmodifiableList((List) zzan.getValue()));
                }
            }
            Iterator it2 = zzdm().iterator();
            while (it2.hasNext()) {
                Map.Entry entry = (Map.Entry) it2.next();
                if (((zzcl) entry.getKey()).zzbq()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzao();
    }
}
