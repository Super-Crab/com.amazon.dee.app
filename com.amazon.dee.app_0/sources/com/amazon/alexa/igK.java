package com.amazon.alexa;

import com.google.gson.stream.JsonToken;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CapabilityAdapter.java */
/* loaded from: classes.dex */
public /* synthetic */ class igK {
    public static final /* synthetic */ int[] zZm = new int[JsonToken.values().length];

    static {
        try {
            zZm[JsonToken.STRING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zZm[JsonToken.NUMBER.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[JsonToken.BOOLEAN.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zZm[JsonToken.BEGIN_ARRAY.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zZm[JsonToken.BEGIN_OBJECT.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zZm[JsonToken.NAME.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zZm[JsonToken.END_OBJECT.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zZm[JsonToken.END_DOCUMENT.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            zZm[JsonToken.END_ARRAY.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            zZm[JsonToken.NULL.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
