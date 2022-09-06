package com.amazon.alexa.voice.handsfree.decider;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.contract.SetupFlow;
import com.amazon.alexa.handsfree.vendor.bridge.VoiceAppPackageInfo;
import com.amazon.alexa.handsfree.vendor.bridge.VoiceAppPackageInfoProvider;
import com.amazon.alexa.voice.handsfree.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes11.dex */
public class StepsProvider {
    private static final String TAG = "StepsProvider";
    private static StepsProvider sInstance;
    private SetupFlow mSetupFlow;
    private List<String> mStepStrings = null;

    /* renamed from: com.amazon.alexa.voice.handsfree.decider.StepsProvider$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$settings$contract$SetupFlow = new int[SetupFlow.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$settings$contract$SetupFlow[SetupFlow.DOUBLE_MICROPHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$settings$contract$SetupFlow[SetupFlow.DOUBLE_MICROPHONE_1PSV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$settings$contract$SetupFlow[SetupFlow.DOUBLE_MICROPHONE_AIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @NonNull
    private List<String> getAISProfileStepStrings(@NonNull Context context) {
        SetupFlow setupFlow;
        if (this.mStepStrings == null || ((setupFlow = this.mSetupFlow) != SetupFlow.DOUBLE_MICROPHONE_AIS && setupFlow != SetupFlow.SINGLE_MICROPHONE_AIS)) {
            VoiceAppPackageInfo voiceAppPackageInfo = getVoiceAppPackageInfo(context);
            if (voiceAppPackageInfo != null && voiceAppPackageInfo.hasRecordAudioPermissions(context)) {
                this.mStepStrings = Arrays.asList(context.getResources().getStringArray(R.array.ais_single_microphone_decider_steps));
                this.mSetupFlow = SetupFlow.SINGLE_MICROPHONE_AIS;
            } else {
                this.mStepStrings = Arrays.asList(context.getResources().getStringArray(R.array.ais_double_microphone_decider_steps));
                this.mSetupFlow = SetupFlow.DOUBLE_MICROPHONE_AIS;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getAISProfileStepStrings: ");
            outline107.append(this.mSetupFlow);
            Log.d(TAG, outline107.toString());
        }
        return this.mStepStrings;
    }

    @NonNull
    public static synchronized StepsProvider getInstance() {
        StepsProvider stepsProvider;
        synchronized (StepsProvider.class) {
            if (sInstance == null) {
                sInstance = new StepsProvider();
            }
            stepsProvider = sInstance;
        }
        return stepsProvider;
    }

    @NonNull
    private List<String> getStepStrings(@NonNull Context context) {
        SetupFlow setupFlow;
        if (this.mStepStrings == null || (setupFlow = this.mSetupFlow) == SetupFlow.DOUBLE_MICROPHONE_AIS || setupFlow == SetupFlow.SINGLE_MICROPHONE_AIS || setupFlow == SetupFlow.DEFAULT) {
            VoiceAppPackageInfo voiceAppPackageInfo = getVoiceAppPackageInfo(context);
            EnrollmentTypeResolver enrollmentTypeResolver = getEnrollmentTypeResolver(context);
            if (enrollmentTypeResolver != null && enrollmentTypeResolver.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV) {
                if (voiceAppPackageInfo != null && voiceAppPackageInfo.hasRecordAudioPermissions(context)) {
                    this.mStepStrings = Arrays.asList(context.getResources().getStringArray(R.array.edgesv_single_microphone_decider_steps));
                    this.mSetupFlow = SetupFlow.SINGLE_MICROPHONE_1PSV;
                } else {
                    this.mStepStrings = Arrays.asList(context.getResources().getStringArray(R.array.edgesv_double_microphone_decider_steps));
                    this.mSetupFlow = SetupFlow.DOUBLE_MICROPHONE_1PSV;
                }
            } else if (voiceAppPackageInfo != null && voiceAppPackageInfo.hasRecordAudioPermissions(context)) {
                this.mStepStrings = Arrays.asList(context.getResources().getStringArray(R.array.single_microphone_decider_steps));
                this.mSetupFlow = SetupFlow.SINGLE_MICROPHONE;
            } else {
                this.mStepStrings = Arrays.asList(context.getResources().getStringArray(R.array.double_microphone_decider_steps));
                this.mSetupFlow = SetupFlow.DOUBLE_MICROPHONE;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getStepStrings: ");
            outline107.append(this.mSetupFlow);
            Log.d(TAG, outline107.toString());
        }
        return this.mStepStrings;
    }

    @NonNull
    private List<StepType> getStepsFromStepStrings(@NonNull List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            arrayList.add(StepType.valueOf(str));
        }
        return arrayList;
    }

    @NonNull
    public void decacheStepStrings() {
        this.mStepStrings = null;
        this.mSetupFlow = null;
    }

    @NonNull
    public List<StepType> getAISProfileSteps(@NonNull Context context) {
        return getStepsFromStepStrings(getAISProfileStepStrings(context));
    }

    @NonNull
    @VisibleForTesting
    EnrollmentTypeResolver getEnrollmentTypeResolver(@NonNull Context context) {
        return ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy().mo358get();
    }

    public SetupFlow getSetupFlow() {
        return this.mSetupFlow;
    }

    @NonNull
    public List<StepType> getSteps(@NonNull Context context) {
        return getStepsFromStepStrings(getStepStrings(context));
    }

    @VisibleForTesting
    VoiceAppPackageInfo getVoiceAppPackageInfo(@NonNull Context context) {
        return new VoiceAppPackageInfoProvider(context).getSupportedVoiceApp();
    }

    public boolean isDoubleMicrophoneFlow() {
        int ordinal = this.mSetupFlow.ordinal();
        return ordinal == 0 || ordinal == 2 || ordinal == 4;
    }

    @NonNull
    public void updateSetupFlow(@NonNull SetupFlow setupFlow) {
        this.mSetupFlow = setupFlow;
    }
}
