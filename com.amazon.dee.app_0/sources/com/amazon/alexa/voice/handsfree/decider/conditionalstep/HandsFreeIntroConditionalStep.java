package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.voice.handsfree.R;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.HandsFreeIntroStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
/* loaded from: classes11.dex */
public class HandsFreeIntroConditionalStep extends ConditionalStep {
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private final StepCommand mStepCommand;
    private final UVRModule mUVRModule;

    public HandsFreeIntroConditionalStep(@NonNull Context context) {
        this(context, ExecutionStateMediator.getInstance(), new HandsFreeIntroStepCommand(context), AMPDInformationProvider.getInstance(context), UVRModule.INSTANCE);
    }

    private boolean hasShownIntro() {
        Context context = this.mContext;
        return context.getSharedPreferences(context.getString(R.string.preference_file_name_intro), 0).getBoolean(this.mContext.getString(R.string.preference_key_intro_shown), false);
    }

    private boolean isUVREnrolledOrNotAvailable() {
        if (!this.mUVRModule.isUVRAvailable()) {
            return true;
        }
        return this.mUVRModule.getUVRContract().getVendorSettings().isUVREnrolled(UserInfo.DEFAULT_USER);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.HANDS_FREE_INTRO;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isLaunchedInFtue() {
        return true;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isRequiredForWakeWord() {
        return false;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean needsExecution() {
        return this.mAMPDInformationProvider.isTrueTurnKey() && !isUVREnrolledOrNotAvailable() && !hasShownIntro();
    }

    @VisibleForTesting
    HandsFreeIntroConditionalStep(@NonNull Context context, @NonNull ExecutionState executionState, @NonNull StepCommand stepCommand, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull UVRModule uVRModule) {
        super(executionState);
        this.mContext = context;
        this.mStepCommand = stepCommand;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mUVRModule = uVRModule;
    }
}
