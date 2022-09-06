package com.amazon.alexa.voice.tta.statemachine.scxml;

import android.app.Activity;
import com.amazon.alexa.voice.tta.R;
import com.amazon.alexa.voice.tta.statemachine.scxml.logging.AndroidLogger;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.scxml.NativeFunctions;
import com.amazon.scxml.SCXMLStateMachine;
import com.amazon.scxml.SCXMLStateMachineBuilder;
import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.InputStream;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InAppSearchStateMachineBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineBuilder;", "Lcom/amazon/scxml/logging/Loggable;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "nativeInvocations", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/NativeInvocations;", "scxmlBuilder", "Lcom/amazon/scxml/SCXMLStateMachineBuilder;", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchContext;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachine;", JsonPOJOBuilder.DEFAULT_WITH_PREFIX, "delegate", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineActions;", "nativeEvaluators", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/NativeEvaluators;", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class InAppSearchStateMachineBuilder extends Loggable {
    public static final Companion Companion = new Companion(null);
    private final NativeInvocations nativeInvocations;
    private final SCXMLStateMachineBuilder<InAppSearchContext> scxmlBuilder;

    /* compiled from: InAppSearchStateMachineBuilder.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineBuilder$Companion;", "", "()V", "createBuilder", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineBuilder;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "defaultLogger", "Lcom/amazon/scxml/logging/Logger;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ InAppSearchStateMachineBuilder createBuilder$default(Companion companion, Activity activity, Logger logger, int i, Object obj) {
            if ((i & 2) != 0) {
                logger = new AndroidLogger();
            }
            return companion.createBuilder(activity, logger);
        }

        @NotNull
        public final InAppSearchStateMachineBuilder createBuilder(@NotNull Activity activity, @Nullable Logger logger) {
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            if (logger != null) {
                Loggable.Companion.setLog(logger);
            }
            return new InAppSearchStateMachineBuilder(activity, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private InAppSearchStateMachineBuilder(Activity activity) {
        this.scxmlBuilder = new SCXMLStateMachineBuilder<>();
        this.nativeInvocations = new NativeInvocations();
        InputStream openRawResource = activity.getResources().openRawResource(R.raw.scxml_in_app_search);
        Intrinsics.checkExpressionValueIsNotNull(openRawResource, "activity.resources.openR….raw.scxml_in_app_search)");
        this.scxmlBuilder.withXmlStream(openRawResource);
        with(this.nativeInvocations);
        with(new NativeEvaluators());
        this.scxmlBuilder.withContext(new InAppSearchContext());
    }

    private final InAppSearchStateMachineBuilder with(NativeEvaluators nativeEvaluators) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isMultiturnFlagSet", new InAppSearchStateMachineBuilder$with$1(nativeEvaluators));
        linkedHashMap.put("simbaCallContextsMatch", new InAppSearchStateMachineBuilder$with$2(nativeEvaluators));
        this.scxmlBuilder.withNativeEvaluators(new NativeFunctions<>(linkedHashMap));
        return this;
    }

    @Nullable
    public final InAppSearchStateMachine build() {
        if (this.nativeInvocations.getActions() == null) {
            Loggable.Companion.getLog().e(getTAG(), "nativeInvocations.delegate == null, stateMachine will be null");
            return null;
        }
        SCXMLStateMachine build = this.scxmlBuilder.build();
        if (build == null) {
            Loggable.Companion.getLog().e(getTAG(), "scxml instance built by builder was null");
            return null;
        }
        Loggable.Companion.getLog().i(getTAG(), "scxml instance started and returned");
        return new ConcreteInAppSearchStateMachine(build);
    }

    private final InAppSearchStateMachineBuilder with(NativeInvocations nativeInvocations) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("callSIMBA", new InAppSearchStateMachineBuilder$with$3(nativeInvocations));
        linkedHashMap.put("cancelExistingSIMBACall", new InAppSearchStateMachineBuilder$with$4(nativeInvocations));
        linkedHashMap.put("clearIsMultiturnFlag", new InAppSearchStateMachineBuilder$with$5(nativeInvocations));
        linkedHashMap.put("tryRenderCardDirective", new InAppSearchStateMachineBuilder$with$6(nativeInvocations));
        linkedHashMap.put("notifyNoSimbaResults", new InAppSearchStateMachineBuilder$with$7(nativeInvocations));
        linkedHashMap.put("removeExistingAvsResponse", new InAppSearchStateMachineBuilder$with$8(nativeInvocations));
        linkedHashMap.put("sendTextToAVS", new InAppSearchStateMachineBuilder$with$9(nativeInvocations));
        linkedHashMap.put("setIsMultiturnFlag", new InAppSearchStateMachineBuilder$with$10(nativeInvocations));
        linkedHashMap.put("showExistingAvsResponse", new InAppSearchStateMachineBuilder$with$11(nativeInvocations));
        linkedHashMap.put("showSIMBAResults", new InAppSearchStateMachineBuilder$with$12(nativeInvocations));
        linkedHashMap.put("storeNewRenderCardDir", new InAppSearchStateMachineBuilder$with$13(nativeInvocations));
        linkedHashMap.put("storeNewAvsTextResponse", new InAppSearchStateMachineBuilder$with$14(nativeInvocations));
        linkedHashMap.put("storeUserUtterance", new InAppSearchStateMachineBuilder$with$15(nativeInvocations));
        this.scxmlBuilder.withNativeInvocations(new NativeFunctions<>(linkedHashMap));
        return this;
    }

    public /* synthetic */ InAppSearchStateMachineBuilder(Activity activity, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity);
    }

    @NotNull
    public final InAppSearchStateMachineBuilder with(@NotNull InAppSearchStateMachineActions delegate) {
        Intrinsics.checkParameterIsNotNull(delegate, "delegate");
        this.nativeInvocations.setActions(delegate);
        return this;
    }
}
