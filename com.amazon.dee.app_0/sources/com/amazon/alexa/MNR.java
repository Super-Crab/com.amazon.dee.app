package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import java.util.UUID;
/* compiled from: ApiCallIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class MNR {
    public static final MNR zZm = zZm("");

    public static MNR zZm(@Nullable String str) {
        if (str == null) {
            StringBuilder zZm2 = C0179Pya.zZm("api-");
            zZm2.append(UUID.randomUUID().toString());
            str = zZm2.toString();
        }
        return new C0185YfC(str);
    }
}
