package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.voice.handsfree.utils.CrashlyticsCrashReportRecorder;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public class CrashReporterModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public CrashReportRecorder provideCrashReportRecorder(Context context) {
        return new CrashlyticsCrashReportRecorder(context);
    }
}
