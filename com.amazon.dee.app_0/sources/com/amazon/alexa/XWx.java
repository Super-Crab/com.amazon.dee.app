package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import java.util.UUID;
/* compiled from: DialogTurnIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class XWx implements StronglyTypedString {
    public static final XWx zZm = zZm("none", false);

    public static XWx BIo() {
        StringBuilder zZm2 = C0179Pya.zZm("Mobile_TTA_");
        zZm2.append(UUID.randomUUID().toString());
        return zZm(zZm2.toString(), true);
    }

    public static XWx zZm() {
        StringBuilder zZm2 = C0179Pya.zZm("dtid-");
        zZm2.append(UUID.randomUUID().toString());
        return zZm(zZm2.toString(), false);
    }

    public static XWx zZm(@Nullable String str, boolean z) {
        if (str == null) {
            return zZm;
        }
        return new Tqo(str, z);
    }
}
