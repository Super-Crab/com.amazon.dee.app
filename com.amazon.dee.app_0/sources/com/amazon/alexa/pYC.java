package com.amazon.alexa;

import com.amazon.alexa.api.LeaderSelectionAuthority;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExternalMediaPlayerBroadcastReceiver.java */
/* loaded from: classes.dex */
public /* synthetic */ class pYC {
    public static final /* synthetic */ int[] zZm = new int[LeaderSelectionAuthority.LeaderSelectionFailureReason.values().length];

    static {
        try {
            zZm[LeaderSelectionAuthority.LeaderSelectionFailureReason.NOT_VERIFIED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zZm[LeaderSelectionAuthority.LeaderSelectionFailureReason.MISSING_PACKAGE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[LeaderSelectionAuthority.LeaderSelectionFailureReason.TIMEOUT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zZm[LeaderSelectionAuthority.LeaderSelectionFailureReason.DISABLED.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zZm[LeaderSelectionAuthority.LeaderSelectionFailureReason.UNKNOWN_LEADER.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zZm[LeaderSelectionAuthority.LeaderSelectionFailureReason.UNKNOWN_REASON.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
