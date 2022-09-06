package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.Puy;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.gson.TypeAdapter;
import java.util.Objects;
/* compiled from: PlayToken.java */
/* loaded from: classes.dex */
public class Puy implements StronglyTypedString {
    public final String zZm;

    public Puy(String str) {
        this.zZm = str;
    }

    public static Puy zZm(String str) {
        return new Puy(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Objects.equals(this.zZm, ((Puy) obj).zZm);
        }
        return false;
    }

    @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
    public String getValue() {
        return this.zZm;
    }

    public int hashCode() {
        return Objects.hash(this.zZm);
    }

    public String toString() {
        return this.zZm;
    }

    public static TypeAdapter<? extends Puy> zZm() {
        return new StronglyTypedString.StronglyTypedStringAdapter<Puy>() { // from class: com.amazon.alexa.client.alexaservice.audio.PlayToken$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString.StronglyTypedStringAdapter
            /* renamed from: instantiate */
            public Puy mo1132instantiate(@NonNull String str) {
                return Puy.zZm(str);
            }
        };
    }
}
