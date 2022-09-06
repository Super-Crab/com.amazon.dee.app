package com.amazon.deecomms.calling.enums;

import com.amazon.deecomms.R;
/* loaded from: classes12.dex */
public enum CallInitiationErrorState {
    NONE,
    ALEXA_CALL_ALREADY_IN_PROGRESS,
    PSTN_CALL_IN_PROGRESS,
    OFFLINE;

    /* renamed from: com.amazon.deecomms.calling.enums.CallInitiationErrorState$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$enums$CallInitiationErrorState = new int[CallInitiationErrorState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$enums$CallInitiationErrorState[CallInitiationErrorState.ALEXA_CALL_ALREADY_IN_PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$enums$CallInitiationErrorState[CallInitiationErrorState.PSTN_CALL_IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$enums$CallInitiationErrorState[CallInitiationErrorState.OFFLINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public int computeErrorMessage() {
        int ordinal = ordinal();
        if (ordinal != 1) {
            if (ordinal == 2) {
                return R.string.call_in_progress_dialog_message;
            }
            if (ordinal == 3) {
                return R.string.offline_message;
            }
            return 0;
        }
        return R.string.call_in_progress_dialog_message;
    }

    public int computeErrorTitle() {
        int ordinal = ordinal();
        if (ordinal != 1) {
            if (ordinal == 2) {
                return R.string.call_in_progress_dialog_title;
            }
            if (ordinal == 3) {
                return R.string.offline_title;
            }
            return 0;
        }
        return R.string.call_in_progress_dialog_title;
    }
}
