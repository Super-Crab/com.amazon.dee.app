package com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public abstract class EnrollmentTypeResolver {
    public static final String IS_1PSV_DECOUPLING_ENABLED = "is1PSVDecouplingEnabled";
    public static final String IS_SPEAKER_ID_ALREADY_CREATED = "isSpeakerIDCreated";
    public static final String PREFERENCE_FILE_NAME = "com.amazon.alexa.handsfree.uservoicerecognition.SpeakerID";
    private static final String TAG = "EnrollmentTypeResolver";
    private final Context mContext;
    private final EnrollmentStatusManager mEnrollmentStatusManager;

    /* renamed from: com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$protocols$uservoicerecognition$enrollmenttype$EnrollmentStatus = new int[EnrollmentStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$protocols$uservoicerecognition$enrollmenttype$EnrollmentStatus[EnrollmentStatus.SETUP_IN_1PSV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$protocols$uservoicerecognition$enrollmenttype$EnrollmentStatus[EnrollmentStatus.SETUP_IN_3PSV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EnrollmentTypeResolver(@NonNull Context context, @NonNull EnrollmentStatusManager enrollmentStatusManager) {
        this.mContext = context;
        this.mEnrollmentStatusManager = enrollmentStatusManager;
    }

    public abstract void check1PSVDecoupledState();

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    public EnrollmentType getEnrollmentType() {
        Log.i(TAG, "getEnrollmentType");
        if (getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV) {
            if (this.mEnrollmentStatusManager.getEnrollmentStatus() == EnrollmentStatus.SETUP_NOT_SET) {
                check1PSVDecoupledState();
            }
            boolean z = getContext().getSharedPreferences(PREFERENCE_FILE_NAME, 0).getBoolean(IS_1PSV_DECOUPLING_ENABLED, false);
            boolean z2 = getContext().getSharedPreferences(PREFERENCE_FILE_NAME, 0).getBoolean(IS_SPEAKER_ID_ALREADY_CREATED, false);
            if (z) {
                Log.i(TAG, "getEnrollmentType _1PSV_DECOUPLED");
                return EnrollmentType._1PSV_DECOUPLED;
            } else if (!z2) {
                Log.i(TAG, "getEnrollmentType _1PSV_DUAL");
                return EnrollmentType._1PSV_DUAL;
            } else {
                Log.i(TAG, "getEnrollmentType _1PSV_ONLY");
                return EnrollmentType._1PSV_ONLY;
            }
        }
        Log.i(TAG, "getEnrollmentType _3PSV");
        return EnrollmentType._3PSV;
    }

    protected abstract EnrollmentType getNotSetupEnrollmentType();

    public EnrollmentType getSpeakerVerificationEnrollmentType() {
        int ordinal = this.mEnrollmentStatusManager.getEnrollmentStatus().ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                return getNotSetupEnrollmentType();
            }
            return EnrollmentType._1PSV;
        }
        return EnrollmentType._3PSV;
    }

    protected abstract boolean isComponentEnabled(@NonNull HandsFreeComponent handsFreeComponent);
}
