package com.amazon.alexa.voice.handsfree.decider.conditionalstep;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.PartnerIntentResolver;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.DoubleMicrophonePermissionsStepCommand;
import com.amazon.alexa.voice.handsfree.decider.stepcommand.StepCommand;
/* loaded from: classes11.dex */
public class DoubleMicrophonePermissionsConditionalStep extends ConditionalStep {
    static final String[] ALEXA_PERMISSIONS = {"android.permission.RECORD_AUDIO"};
    private static final String TAG = "HFPermsConditionalStep";
    private final Context mContext;
    private final PartnerIntentResolver mPartnerIntentResolver;
    private final StepCommand mStepCommand;

    public DoubleMicrophonePermissionsConditionalStep(@NonNull Context context) {
        this(context, ExecutionStateMediator.getInstance(), new DoubleMicrophonePermissionsStepCommand(context), new PartnerIntentResolver(context));
    }

    private boolean isAlexaPermissionsGranted() {
        for (String str : ALEXA_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this.mContext, str) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isHandsFreePermissionGranted() {
        String str;
        Intent partnerPermissionsIntent = this.mPartnerIntentResolver.getPartnerPermissionsIntent();
        if (partnerPermissionsIntent == null) {
            Log.e(TAG, "Reached setup flow but no partner activity was found");
            return true;
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        ComponentName component = partnerPermissionsIntent.getComponent();
        if (component != null) {
            str = component.getPackageName();
        } else if (partnerPermissionsIntent.getPackage() != null) {
            str = partnerPermissionsIntent.getPackage();
        } else {
            Log.e(TAG, "isHandsFreePermissionGranted: Unable to find package name.");
            return true;
        }
        return packageManager.checkPermission("android.permission.RECORD_AUDIO", str) == 0;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepCommand getStepCommand() {
        return this.mStepCommand;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    @NonNull
    public StepType getStepType() {
        return StepType.HANDS_FREE_PERMISSIONS;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isLaunchedInFtue() {
        return true;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean isRequiredForWakeWord() {
        return true;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.conditionalstep.ConditionalStep
    public boolean needsExecution() {
        return !isAlexaPermissionsGranted() || !isHandsFreePermissionGranted();
    }

    @VisibleForTesting
    DoubleMicrophonePermissionsConditionalStep(@NonNull Context context, @NonNull ExecutionState executionState, @NonNull StepCommand stepCommand, @NonNull PartnerIntentResolver partnerIntentResolver) {
        super(executionState);
        this.mContext = context;
        this.mStepCommand = stepCommand;
        this.mPartnerIntentResolver = partnerIntentResolver;
    }
}
