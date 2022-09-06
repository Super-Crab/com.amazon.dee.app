package com.amazon.alexa.voice.handsfree.initialization;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.vendor.bridge.VoiceAppPackageInfo;
import com.amazon.alexa.handsfree.vendor.bridge.VoiceAppPackageInfoProvider;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceSubscriber;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class TestModeInitializer {
    private static final String TAG = "TestModeInitializer";
    private final IdentityServiceSubscriber mIdentityServiceSubscriber;
    private final VoiceAppPackageInfo mVoiceAppPackageInfo;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public TestModeInitializer(VoiceAppPackageInfoProvider voiceAppPackageInfoProvider, IdentityServiceSubscriber identityServiceSubscriber) {
        this.mVoiceAppPackageInfo = voiceAppPackageInfoProvider.getSupportedVoiceApp();
        this.mIdentityServiceSubscriber = identityServiceSubscriber;
    }

    public synchronized void initialize(@NonNull Context context) {
        Log.d(TAG, "TestMode initialize(..) Test Mode if supported");
        if (this.mVoiceAppPackageInfo != null) {
            String packageName = this.mVoiceAppPackageInfo.getPackageName();
            String str = TAG;
            Log.d(str, "TestMode initialize(...) success, voicePackageName = " + packageName);
            this.mIdentityServiceSubscriber.subscribeForTestMode(context, packageName);
        }
    }
}
