package com.amazon.alexa.accessory.speechapi.csm.reportstate;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender;
import com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmReportAccessoryStateDependencyHolder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\nJ\u0006\u0010\u000f\u001a\u00020\bJ\u0006\u0010\u0010\u001a\u00020\fJ\u0006\u0010\u0011\u001a\u00020\fJ\u0006\u0010\u0012\u001a\u00020\fJ\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\bJ\u0006\u0010\u0018\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/reportstate/CsmReportAccessoryStateDependencyHolder;", "", "()V", "TAG", "", "accessoryEventSender", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryEventSender;", "accessoryFeatureChecker", "Lcom/amazon/alexa/accessory/utils/feature/FeatureChecker;", "accessoryStateReportGenerator", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator;", "initialized", "", "getAccessoryEventSender", "getAccessoryStateReportGenerator", "getFeatureChecker", "hasAccessoryEventSender", "hasAccessoryStateReportGenerator", "hasFeatureChecker", "init", "", "reportGenerator", "eventSender", "featureChecker", "reset", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmReportAccessoryStateDependencyHolder {
    public static final CsmReportAccessoryStateDependencyHolder INSTANCE = new CsmReportAccessoryStateDependencyHolder();
    private static final String TAG = "CsmReportAccessoryStateDependencyHolder:";
    private static AccessoryEventSender accessoryEventSender;
    private static FeatureChecker accessoryFeatureChecker;
    private static AccessoryStateReportGenerator accessoryStateReportGenerator;
    private static boolean initialized;

    private CsmReportAccessoryStateDependencyHolder() {
    }

    @NotNull
    public final AccessoryEventSender getAccessoryEventSender() {
        Logger.d("CsmReportAccessoryStateDependencyHolder: getAccessoryEventSender");
        AccessoryEventSender accessoryEventSender2 = accessoryEventSender;
        if (accessoryEventSender2 != null) {
            if (accessoryEventSender2 == null) {
                Intrinsics.throwNpe();
            }
            return accessoryEventSender2;
        }
        throw new IllegalStateException("AccessoryEventSender instance is unavailable!".toString());
    }

    @NotNull
    public final AccessoryStateReportGenerator getAccessoryStateReportGenerator() {
        Logger.d("CsmReportAccessoryStateDependencyHolder: getAccessoryStateReportGenerator");
        AccessoryStateReportGenerator accessoryStateReportGenerator2 = accessoryStateReportGenerator;
        if (accessoryStateReportGenerator2 != null) {
            if (accessoryStateReportGenerator2 == null) {
                Intrinsics.throwNpe();
            }
            return accessoryStateReportGenerator2;
        }
        throw new IllegalStateException("AccessoryStateReportGenerator instance is unavailable!".toString());
    }

    @NotNull
    public final FeatureChecker getFeatureChecker() {
        Logger.d("CsmReportAccessoryStateDependencyHolder: getFeatureChecker");
        FeatureChecker featureChecker = accessoryFeatureChecker;
        if (featureChecker != null) {
            if (featureChecker == null) {
                Intrinsics.throwNpe();
            }
            return featureChecker;
        }
        throw new IllegalStateException("FeatureChecker instance is unavailable!".toString());
    }

    public final boolean hasAccessoryEventSender() {
        Logger.d("CsmReportAccessoryStateDependencyHolder: hasAccessoryEventSender");
        return accessoryEventSender != null;
    }

    public final boolean hasAccessoryStateReportGenerator() {
        Logger.d("CsmReportAccessoryStateDependencyHolder: hasAccessoryStateReportGenerator");
        return accessoryStateReportGenerator != null;
    }

    public final boolean hasFeatureChecker() {
        Logger.d("CsmReportAccessoryStateDependencyHolder: hasFeatureChecker");
        return accessoryFeatureChecker != null;
    }

    public final void init(@NotNull AccessoryStateReportGenerator reportGenerator, @NotNull AccessoryEventSender eventSender, @NotNull FeatureChecker featureChecker) {
        Intrinsics.checkParameterIsNotNull(reportGenerator, "reportGenerator");
        Intrinsics.checkParameterIsNotNull(eventSender, "eventSender");
        Intrinsics.checkParameterIsNotNull(featureChecker, "featureChecker");
        Preconditions.notNull(reportGenerator, "reportGenerator");
        Preconditions.notNull(eventSender, "eventSender");
        Preconditions.notNull(featureChecker, "featureChecker");
        if (!initialized) {
            Logger.d("CsmReportAccessoryStateDependencyHolder: init");
            accessoryStateReportGenerator = reportGenerator;
            accessoryEventSender = eventSender;
            accessoryFeatureChecker = featureChecker;
            initialized = true;
            return;
        }
        throw new IllegalStateException("CsmReportAccessoryStateDependencyHolder: is already initialized!".toString());
    }

    public final void reset() {
        accessoryStateReportGenerator = null;
        accessoryEventSender = null;
        accessoryFeatureChecker = null;
        initialized = false;
    }
}
