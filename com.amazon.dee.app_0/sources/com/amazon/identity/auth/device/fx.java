package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fx implements ix<fx> {
    public final String directedId;
    public final String displayName;
    public final Map<String, gj<String>> nr;
    public final Map<String, gj<String>> tokens;

    public fx(String str, String str2) {
        this(str, str2, new HashMap());
    }

    @Override // com.amazon.identity.auth.device.ix
    /* renamed from: eO */
    public fx ek() {
        return new fx(this.directedId, this.displayName, Cif.i(this.nr), Cif.i(this.tokens));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && fx.class == obj.getClass()) {
            fx fxVar = (fx) obj;
            if (TextUtils.equals(this.directedId, fxVar.directedId) && TextUtils.equals(this.displayName, fxVar.displayName) && Cif.equals(this.nr, fxVar.nr) && Cif.equals(this.tokens, fxVar.tokens)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.directedId;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.displayName;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, gj<String>> map = this.tokens;
        int hashCode3 = (hashCode2 + (map == null ? 0 : map.hashCode())) * 31;
        Map<String, gj<String>> map2 = this.nr;
        if (map2 != null) {
            i = map2.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return String.format("DirectedId: %s, Display Name: %s, userdata: %s, tokens: %s", this.directedId, this.displayName, this.nr.toString(), this.tokens.toString());
    }

    public fx(String str, String str2, Map<String, gj<String>> map) {
        this(str, str2, map, new HashMap());
    }

    public fx(String str, String str2, Map<String, gj<String>> map, Map<String, gj<String>> map2) {
        this.directedId = str;
        this.displayName = str2;
        this.nr = map;
        this.tokens = map2;
    }
}
