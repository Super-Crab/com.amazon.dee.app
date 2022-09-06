package com.amazon.identity.auth.attributes;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class CORPFMResponse {
    private final String dk;
    private final String dl;
    private final Long dm;
    private final ComputationConfidenceValue dn;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum ComputationConfidenceValue {
        CUSTOMER_PROVIDED("CUSTOMER_PROVIDED", false),
        CUSTOMER_BASED_GUESS("CUSTOMER_BASED_GUESS", true),
        DEVICE_BASED_GUESS("DEVICE_BASED_GUESS", true);
        
        private static final Map<String, ComputationConfidenceValue> LOOKUP_BY_VALUE = new HashMap();
        private final boolean mIsGuess;
        private final String mValue;

        static {
            ComputationConfidenceValue[] values;
            for (ComputationConfidenceValue computationConfidenceValue : values()) {
                LOOKUP_BY_VALUE.put(computationConfidenceValue.getValue(), computationConfidenceValue);
            }
        }

        ComputationConfidenceValue(String str, boolean z) {
            this.mValue = str;
            this.mIsGuess = z;
        }

        public static ComputationConfidenceValue parseFromValue(String str, ComputationConfidenceValue computationConfidenceValue) {
            ComputationConfidenceValue computationConfidenceValue2 = LOOKUP_BY_VALUE.get(str);
            return computationConfidenceValue2 != null ? computationConfidenceValue2 : computationConfidenceValue;
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean isGuess() {
            return this.mIsGuess;
        }
    }

    public CORPFMResponse(String str, String str2, ComputationConfidenceValue computationConfidenceValue, Long l) {
        this.dk = str;
        this.dl = str2;
        this.dn = computationConfidenceValue;
        this.dm = l;
    }

    private boolean ao() {
        return an() == ComputationConfidenceValue.CUSTOMER_PROVIDED;
    }

    public String ai() {
        return this.dk;
    }

    public String aj() {
        if (!ao()) {
            return null;
        }
        return ai();
    }

    public String ak() {
        return this.dl;
    }

    public String al() {
        if (!ao()) {
            return null;
        }
        return ak();
    }

    public Long am() {
        return this.dm;
    }

    public ComputationConfidenceValue an() {
        return this.dn;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && CORPFMResponse.class == obj.getClass()) {
            CORPFMResponse cORPFMResponse = (CORPFMResponse) obj;
            if (TextUtils.equals(ai(), cORPFMResponse.ai()) && TextUtils.equals(ak(), cORPFMResponse.ak()) && this.dn == cORPFMResponse.an()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.dk;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.dl;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        ComputationConfidenceValue computationConfidenceValue = this.dn;
        if (computationConfidenceValue != null) {
            i = computationConfidenceValue.hashCode();
        }
        return hashCode2 + i;
    }

    public CORPFMResponse(String str, String str2, String str3, Long l) {
        this.dk = str;
        this.dl = str2;
        this.dn = ComputationConfidenceValue.parseFromValue(str3, ComputationConfidenceValue.CUSTOMER_PROVIDED);
        this.dm = l;
    }
}
