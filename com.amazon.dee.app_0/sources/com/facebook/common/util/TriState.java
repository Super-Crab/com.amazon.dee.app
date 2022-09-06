package com.facebook.common.util;

import com.facebook.infer.annotation.Functional;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public enum TriState {
    YES,
    NO,
    UNSET;

    /* renamed from: com.facebook.common.util.TriState$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$util$TriState = new int[TriState.values().length];

        static {
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.YES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.NO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$common$util$TriState[TriState.UNSET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Functional
    public static TriState fromDbValue(int value) {
        if (value != 1) {
            if (value != 2) {
                return UNSET;
            }
            return NO;
        }
        return YES;
    }

    @Functional
    public boolean asBoolean() {
        int ordinal = ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return false;
            }
            if (ordinal != 2) {
                throw new IllegalStateException("Unrecognized TriState value: " + this);
            }
            throw new IllegalStateException("No boolean equivalent for UNSET");
        }
        return true;
    }

    @Nullable
    @Functional
    public Boolean asBooleanObject() {
        int ordinal = ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return Boolean.FALSE;
            }
            if (ordinal == 2) {
                return null;
            }
            throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
        return Boolean.TRUE;
    }

    @Functional
    public int getDbValue() {
        int ordinal = ordinal();
        if (ordinal != 0) {
            return ordinal != 1 ? 3 : 2;
        }
        return 1;
    }

    @Functional
    public boolean isSet() {
        return this != UNSET;
    }

    @Functional
    public static TriState valueOf(boolean bool) {
        return bool ? YES : NO;
    }

    @Functional
    public static TriState valueOf(@Nullable Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }

    @Functional
    public boolean asBoolean(boolean defaultValue) {
        int ordinal = ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return false;
            }
            if (ordinal == 2) {
                return defaultValue;
            }
            throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
        return true;
    }
}
