package com.amazon.alexa.handsfree.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.handsfree.ui.utils.ResultReceiverWrapper;
/* loaded from: classes8.dex */
public abstract class ManagedActivity extends AppCompatActivity {
    public static final String EXTRA_FINISH_ACTIVITY_RESULT_RECEIVER = "receiveResultOnFinishingTheActivity";
    public static final String EXTRA_ONSTEPFINISH_RESULT_RECEIVER = "onStepFinishedResultReceiver";
    public static final String EXTRA_STEP_RESULT = "stepResult";
    private Intent mCallerIntent;
    private ResultReceiver mOnStepFinishResultReceiver;
    private StepResult mStepResult;

    /* loaded from: classes8.dex */
    public enum StepResult {
        CONTINUE,
        EXIT
    }

    public void finishStep(@NonNull StepResult stepResult) {
        this.mStepResult = stepResult;
        if (this.mOnStepFinishResultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putAll(this.mCallerIntent.getExtras());
            bundle.putSerializable(EXTRA_STEP_RESULT, this.mStepResult);
            bundle.putParcelable(EXTRA_FINISH_ACTIVITY_RESULT_RECEIVER, getOnStartNextActivityResultReceiver(this));
            this.mOnStepFinishResultReceiver.send(0, bundle);
        }
    }

    @VisibleForTesting
    Handler getMainHandler(@NonNull Context context) {
        return new Handler(context.getMainLooper());
    }

    @VisibleForTesting
    ResultReceiver getOnStartNextActivityResultReceiver(@NonNull Context context) {
        return ResultReceiverWrapper.getReceiverOf(new ResultReceiver(getMainHandler(context)) { // from class: com.amazon.alexa.handsfree.ui.ManagedActivity.1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, @NonNull Bundle bundle) {
                ManagedActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setup(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @VisibleForTesting
    void setup(@Nullable Intent intent) {
        this.mCallerIntent = intent;
        this.mStepResult = StepResult.EXIT;
        if (intent == null || !intent.hasExtra(EXTRA_ONSTEPFINISH_RESULT_RECEIVER)) {
            return;
        }
        this.mOnStepFinishResultReceiver = (ResultReceiver) intent.getParcelableExtra(EXTRA_ONSTEPFINISH_RESULT_RECEIVER);
    }
}
