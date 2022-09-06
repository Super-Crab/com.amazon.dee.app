package com.amazon.alexa.tarazed.service;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.tarazed.account.AccountMetadataProviderAlexaMobile;
import com.amazon.alexa.tarazed.account.AztecTokenProviderAlexaMobile;
import com.amazon.alexa.tarazed.account.IdentityEventListener;
import com.amazon.alexa.tarazed.api.AlexaTarazedService;
import com.amazon.alexa.tarazed.dagger.injectors.TarazedIntegrationInjector;
import com.amazon.alexa.tarazed.dmps.DMPSHandler;
import com.amazon.alexa.tarazed.utils.FeatureChecker;
import com.amazon.tarazed.core.TarazedIntents;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DefaultAlexaTarazedService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 f2\u00020\u0001:\u0001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010W\u001a\u00020H2\u0006\u0010X\u001a\u00020YH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010ZJ\b\u0010[\u001a\u00020HH\u0016J\u0011\u0010\\\u001a\u00020HH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010]J\b\u0010^\u001a\u00020HH\u0016J\b\u0010_\u001a\u00020`H\u0016J\b\u0010a\u001a\u00020HH\u0016J\b\u0010b\u001a\u00020HH\u0016J\u0010\u0010c\u001a\u00020H2\u0006\u0010d\u001a\u00020YH\u0002J\b\u0010e\u001a\u00020HH\u0016R$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\r8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00068\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\t\"\u0004\b\u0015\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u001d8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020#8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010(\u001a\u00020)8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001e\u0010.\u001a\u00020/8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001e\u00104\u001a\u0002058\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001e\u0010:\u001a\u00020;8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020A8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR=\u0010D\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020F\u0012\n\u0012\b\u0012\u0004\u0012\u00020H0G\u0012\u0006\u0012\u0004\u0018\u00010I0E8\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u0010\n\u0002\u0010N\u0012\u0004\bJ\u0010K\u001a\u0004\bL\u0010MR\u001e\u0010O\u001a\u00020P8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0014\u0010U\u001a\u00020A8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bV\u0010C\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006g"}, d2 = {"Lcom/amazon/alexa/tarazed/service/DefaultAlexaTarazedService;", "Lcom/amazon/alexa/tarazed/api/AlexaTarazedService;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "accountMetadataProvider", "Ljavax/inject/Provider;", "Lcom/amazon/alexa/tarazed/account/AccountMetadataProviderAlexaMobile;", "getAccountMetadataProvider$AlexaMobileAndroidTarazedIntegration_release", "()Ljavax/inject/Provider;", "setAccountMetadataProvider$AlexaMobileAndroidTarazedIntegration_release", "(Ljavax/inject/Provider;)V", "alexaMobileDeviceInformation", "Lcom/amazon/alexa/device/api/DeviceInformation;", "getAlexaMobileDeviceInformation$AlexaMobileAndroidTarazedIntegration_release", "()Lcom/amazon/alexa/device/api/DeviceInformation;", "setAlexaMobileDeviceInformation$AlexaMobileAndroidTarazedIntegration_release", "(Lcom/amazon/alexa/device/api/DeviceInformation;)V", "aztecTokenProvider", "Lcom/amazon/alexa/tarazed/account/AztecTokenProviderAlexaMobile;", "getAztecTokenProvider$AlexaMobileAndroidTarazedIntegration_release", "setAztecTokenProvider$AlexaMobileAndroidTarazedIntegration_release", "dmpsHandler", "Lcom/amazon/alexa/tarazed/dmps/DMPSHandler;", "getDmpsHandler$AlexaMobileAndroidTarazedIntegration_release", "()Lcom/amazon/alexa/tarazed/dmps/DMPSHandler;", "setDmpsHandler$AlexaMobileAndroidTarazedIntegration_release", "(Lcom/amazon/alexa/tarazed/dmps/DMPSHandler;)V", "eventBus", "Lcom/amazon/alexa/eventbus/api/EventBus;", "getEventBus$AlexaMobileAndroidTarazedIntegration_release", "()Lcom/amazon/alexa/eventbus/api/EventBus;", "setEventBus$AlexaMobileAndroidTarazedIntegration_release", "(Lcom/amazon/alexa/eventbus/api/EventBus;)V", "featureChecker", "Lcom/amazon/alexa/tarazed/utils/FeatureChecker;", "getFeatureChecker$AlexaMobileAndroidTarazedIntegration_release", "()Lcom/amazon/alexa/tarazed/utils/FeatureChecker;", "setFeatureChecker$AlexaMobileAndroidTarazedIntegration_release", "(Lcom/amazon/alexa/tarazed/utils/FeatureChecker;)V", "identityEventListener", "Lcom/amazon/alexa/tarazed/account/IdentityEventListener;", "getIdentityEventListener$AlexaMobileAndroidTarazedIntegration_release", "()Lcom/amazon/alexa/tarazed/account/IdentityEventListener;", "setIdentityEventListener$AlexaMobileAndroidTarazedIntegration_release", "(Lcom/amazon/alexa/tarazed/account/IdentityEventListener;)V", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "getIdentityService$AlexaMobileAndroidTarazedIntegration_release", "()Lcom/amazon/alexa/identity/api/IdentityService;", "setIdentityService$AlexaMobileAndroidTarazedIntegration_release", "(Lcom/amazon/alexa/identity/api/IdentityService;)V", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "getLogger$AlexaMobileAndroidTarazedIntegration_release", "()Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "setLogger$AlexaMobileAndroidTarazedIntegration_release", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "mainScope", "Lkotlinx/coroutines/CoroutineScope;", "getMainScope$AlexaMobileAndroidTarazedIntegration_release", "()Lkotlinx/coroutines/CoroutineScope;", "setMainScope$AlexaMobileAndroidTarazedIntegration_release", "(Lkotlinx/coroutines/CoroutineScope;)V", "sessionEndEvent", "Lcom/amazon/alexa/eventbus/api/Message;", "getSessionEndEvent", "()Lcom/amazon/alexa/eventbus/api/Message;", "sessionNotificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "", "sessionNotificationHandler$annotations", "()V", "getSessionNotificationHandler$AlexaMobileAndroidTarazedIntegration_release", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "getSessionNotifier$AlexaMobileAndroidTarazedIntegration_release", "()Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "setSessionNotifier$AlexaMobileAndroidTarazedIntegration_release", "(Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;)V", "sessionStartEvent", "getSessionStartEvent", "enableTarazedPermissionIfFeatureEnabled", "event", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endSession", "initTarazed", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialize", "isSessionActive", "", "pauseSession", "resumeSession", "sendRequest", "action", "suspendSession", "Companion", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public final class DefaultAlexaTarazedService implements AlexaTarazedService {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EVENT_BUS_ENDPOINTS_CHANGED_EVENT_TYPE = "ENDPOINTS_CHANGED";
    @NotNull
    public static final String EVENT_BUS_TARAZED_SESSION_END_EVENT_TYPE = "tarazed::session::end";
    @NotNull
    public static final String EVENT_BUS_TARAZED_SESSION_START_EVENT_TYPE = "tarazed::session::start";
    private static final String TAG = "AlexaTarazedService";
    @Inject
    @NotNull
    public Provider<AccountMetadataProviderAlexaMobile> accountMetadataProvider;
    @Inject
    @NotNull
    public DeviceInformation alexaMobileDeviceInformation;
    @Inject
    @NotNull
    public Provider<AztecTokenProviderAlexaMobile> aztecTokenProvider;
    private final Context context;
    @Inject
    @NotNull
    public DMPSHandler dmpsHandler;
    @Inject
    @NotNull
    public EventBus eventBus;
    @Inject
    @NotNull
    public FeatureChecker featureChecker;
    @Inject
    @NotNull
    public IdentityEventListener identityEventListener;
    @Inject
    @NotNull
    public IdentityService identityService;
    @Inject
    @NotNull
    public TarazedSessionLogger logger;
    @Inject
    @NotNull
    public CoroutineScope mainScope;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionNotificationHandler;
    @Inject
    @NotNull
    public TarazedSessionNotifier sessionNotifier;

    /* compiled from: DefaultAlexaTarazedService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/tarazed/service/DefaultAlexaTarazedService$Companion;", "", "()V", "EVENT_BUS_ENDPOINTS_CHANGED_EVENT_TYPE", "", "EVENT_BUS_ENDPOINTS_CHANGED_EVENT_TYPE$annotations", "EVENT_BUS_TARAZED_SESSION_END_EVENT_TYPE", "EVENT_BUS_TARAZED_SESSION_END_EVENT_TYPE$annotations", "EVENT_BUS_TARAZED_SESSION_START_EVENT_TYPE", "EVENT_BUS_TARAZED_SESSION_START_EVENT_TYPE$annotations", "TAG", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void EVENT_BUS_ENDPOINTS_CHANGED_EVENT_TYPE$annotations() {
        }

        @VisibleForTesting
        public static /* synthetic */ void EVENT_BUS_TARAZED_SESSION_END_EVENT_TYPE$annotations() {
        }

        @VisibleForTesting
        public static /* synthetic */ void EVENT_BUS_TARAZED_SESSION_START_EVENT_TYPE$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedNotificationEvent.values().length];

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_END.ordinal()] = 2;
        }
    }

    public DefaultAlexaTarazedService(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.sessionNotificationHandler = new DefaultAlexaTarazedService$sessionNotificationHandler$1(this, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Message getSessionEndEvent() {
        Message build = new Message.Builder().setEventType(EVENT_BUS_TARAZED_SESSION_END_EVENT_TYPE).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Message.Builder()\n      …\n                .build()");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Message getSessionStartEvent() {
        Message build = new Message.Builder().setEventType(EVENT_BUS_TARAZED_SESSION_START_EVENT_TYPE).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Message.Builder()\n      …\n                .build()");
        return build;
    }

    private final void sendRequest(String str) {
        Intent intent = new Intent(this.context, TarazedSessionAndroidService.class);
        intent.setAction(str);
        this.context.startService(intent);
    }

    @VisibleForTesting
    public static /* synthetic */ void sessionNotificationHandler$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object enableTarazedPermissionIfFeatureEnabled(@org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$enableTarazedPermissionIfFeatureEnabled$1
            if (r0 == 0) goto L13
            r0 = r9
            com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$enableTarazedPermissionIfFeatureEnabled$1 r0 = (com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$enableTarazedPermissionIfFeatureEnabled$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$enableTarazedPermissionIfFeatureEnabled$1 r0 = new com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$enableTarazedPermissionIfFeatureEnabled$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "logger"
            java.lang.String r4 = "AlexaTarazedService"
            r5 = 1
            if (r2 == 0) goto L3d
            if (r2 != r5) goto L35
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r0.L$0
            com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService r8 = (com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L77
        L35:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3d:
            kotlin.ResultKt.throwOnFailure(r9)
            com.amazon.tarazed.core.logging.TarazedSessionLogger r9 = r7.logger
            if (r9 != 0) goto L47
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L47:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = "Received "
            r2.append(r6)
            r2.append(r8)
            java.lang.String r6 = " from event bus, will enable MSS if necessary"
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r9.i(r4, r2)
            com.amazon.alexa.tarazed.utils.FeatureChecker r9 = r7.featureChecker
            if (r9 != 0) goto L69
            java.lang.String r2 = "featureChecker"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L69:
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r5
            java.lang.Object r9 = r9.isTarazedEnabled(r0)
            if (r9 != r1) goto L76
            return r1
        L76:
            r8 = r7
        L77:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L97
            com.amazon.tarazed.core.logging.TarazedSessionLogger r9 = r8.logger
            if (r9 != 0) goto L86
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L86:
            java.lang.String r0 = "MSS 3P feature is enabled for the account, proceeding to enable..."
            r9.i(r4, r0)
            com.amazon.alexa.tarazed.dmps.DMPSHandler r8 = r8.dmpsHandler
            if (r8 != 0) goto L94
            java.lang.String r9 = "dmpsHandler"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
        L94:
            r8.enableTarazedPermission()
        L97:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService.enableTarazedPermissionIfFeatureEnabled(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.amazon.alexa.tarazed.api.AlexaTarazedService
    public void endSession() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        tarazedSessionLogger.i(TAG, "Ending session requested");
        sendRequest(TarazedIntents.SessionAndroidService.END_SESSION_ON_3P_APP_EVENT);
    }

    @NotNull
    public final Provider<AccountMetadataProviderAlexaMobile> getAccountMetadataProvider$AlexaMobileAndroidTarazedIntegration_release() {
        Provider<AccountMetadataProviderAlexaMobile> provider = this.accountMetadataProvider;
        if (provider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("accountMetadataProvider");
        }
        return provider;
    }

    @NotNull
    public final DeviceInformation getAlexaMobileDeviceInformation$AlexaMobileAndroidTarazedIntegration_release() {
        DeviceInformation deviceInformation = this.alexaMobileDeviceInformation;
        if (deviceInformation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alexaMobileDeviceInformation");
        }
        return deviceInformation;
    }

    @NotNull
    public final Provider<AztecTokenProviderAlexaMobile> getAztecTokenProvider$AlexaMobileAndroidTarazedIntegration_release() {
        Provider<AztecTokenProviderAlexaMobile> provider = this.aztecTokenProvider;
        if (provider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aztecTokenProvider");
        }
        return provider;
    }

    @NotNull
    public final DMPSHandler getDmpsHandler$AlexaMobileAndroidTarazedIntegration_release() {
        DMPSHandler dMPSHandler = this.dmpsHandler;
        if (dMPSHandler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dmpsHandler");
        }
        return dMPSHandler;
    }

    @NotNull
    public final EventBus getEventBus$AlexaMobileAndroidTarazedIntegration_release() {
        EventBus eventBus = this.eventBus;
        if (eventBus == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventBus");
        }
        return eventBus;
    }

    @NotNull
    public final FeatureChecker getFeatureChecker$AlexaMobileAndroidTarazedIntegration_release() {
        FeatureChecker featureChecker = this.featureChecker;
        if (featureChecker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("featureChecker");
        }
        return featureChecker;
    }

    @NotNull
    public final IdentityEventListener getIdentityEventListener$AlexaMobileAndroidTarazedIntegration_release() {
        IdentityEventListener identityEventListener = this.identityEventListener;
        if (identityEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("identityEventListener");
        }
        return identityEventListener;
    }

    @NotNull
    public final IdentityService getIdentityService$AlexaMobileAndroidTarazedIntegration_release() {
        IdentityService identityService = this.identityService;
        if (identityService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("identityService");
        }
        return identityService;
    }

    @NotNull
    public final TarazedSessionLogger getLogger$AlexaMobileAndroidTarazedIntegration_release() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return tarazedSessionLogger;
    }

    @NotNull
    public final CoroutineScope getMainScope$AlexaMobileAndroidTarazedIntegration_release() {
        CoroutineScope coroutineScope = this.mainScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainScope");
        }
        return coroutineScope;
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getSessionNotificationHandler$AlexaMobileAndroidTarazedIntegration_release() {
        return this.sessionNotificationHandler;
    }

    @NotNull
    public final TarazedSessionNotifier getSessionNotifier$AlexaMobileAndroidTarazedIntegration_release() {
        TarazedSessionNotifier tarazedSessionNotifier = this.sessionNotifier;
        if (tarazedSessionNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionNotifier");
        }
        return tarazedSessionNotifier;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b9  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object initTarazed(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$1 r0 = (com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$1 r0 = new com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService r0 = (com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4d
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.alexa.tarazed.utils.FeatureChecker r6 = r5.featureChecker
            if (r6 != 0) goto L41
            java.lang.String r2 = "featureChecker"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L41:
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r6.isTarazedEnabled(r0)
            if (r6 != r1) goto L4c
            return r1
        L4c:
            r0 = r5
        L4d:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            java.lang.String r1 = "identityEventListener"
            java.lang.String r2 = "logger"
            java.lang.String r3 = "AlexaTarazedService"
            java.lang.String r4 = "dmpsHandler"
            if (r6 == 0) goto Lb9
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = r0.logger
            if (r6 != 0) goto L64
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L64:
            java.lang.String r2 = "MSS 3P feature is enabled for the customer"
            r6.i(r3, r2)
            com.amazon.alexa.tarazed.dmps.DMPSHandler r6 = r0.dmpsHandler
            if (r6 != 0) goto L70
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L70:
            r6.enableTarazedPermission()
            com.amazon.alexa.tarazed.dmps.DMPSHandler r6 = r0.dmpsHandler
            if (r6 != 0) goto L7a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L7a:
            r6.subscribe()
            com.amazon.alexa.tarazed.account.IdentityEventListener r6 = r0.identityEventListener
            if (r6 != 0) goto L84
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L84:
            r6.startListeningForEvents()
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier r6 = r0.sessionNotifier
            if (r6 != 0) goto L90
            java.lang.String r1 = "sessionNotifier"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L90:
            kotlin.jvm.functions.Function2<com.amazon.tarazed.core.notifier.TarazedNotificationEvent, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r0.sessionNotificationHandler
            com.amazon.tarazed.core.notifier.ListenerPriority r2 = com.amazon.tarazed.core.notifier.ListenerPriority.LOW
            r3 = 0
            r6.subscribe(r1, r2, r3)
            com.amazon.tarazed.core.registry.TarazedComponentRegistry r6 = com.amazon.tarazed.core.registry.TarazedComponentRegistry.INSTANCE
            java.lang.Class<com.amazon.tarazed.core.registry.component.AztecTokenProvider> r1 = com.amazon.tarazed.core.registry.component.AztecTokenProvider.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$2 r2 = new com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$2
            r2.<init>(r0)
            r6.registerComponent(r1, r2)
            com.amazon.tarazed.core.registry.TarazedComponentRegistry r6 = com.amazon.tarazed.core.registry.TarazedComponentRegistry.INSTANCE
            java.lang.Class<com.amazon.tarazed.core.registry.component.AccountMetadataProvider> r1 = com.amazon.tarazed.core.registry.component.AccountMetadataProvider.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$3 r2 = new com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initTarazed$3
            r2.<init>(r0)
            r6.registerComponent(r1, r2)
            goto Ld9
        Lb9:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r6 = r0.logger
            if (r6 != 0) goto Lc0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        Lc0:
            java.lang.String r2 = "MSS 3P feature is not enabled for the customer yet"
            r6.i(r3, r2)
            com.amazon.alexa.tarazed.dmps.DMPSHandler r6 = r0.dmpsHandler
            if (r6 != 0) goto Lcc
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        Lcc:
            r6.disableTarazedPermission()
            com.amazon.alexa.tarazed.account.IdentityEventListener r6 = r0.identityEventListener
            if (r6 != 0) goto Ld6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        Ld6:
            r6.stopListeningForEvents()
        Ld9:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService.initTarazed(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.amazon.alexa.tarazed.api.AlexaTarazedService
    public void initialize() {
        TarazedIntegrationInjector.INSTANCE.getComponent(this.context).inject(this);
        DeviceInformation deviceInformation = this.alexaMobileDeviceInformation;
        if (deviceInformation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alexaMobileDeviceInformation");
        }
        if (deviceInformation.isFireOS()) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            if (tarazedSessionLogger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger.i(TAG, "Alexa Mobile is running on FireOS, not registering Tarazed permission");
            return;
        }
        IdentityService identityService = this.identityService;
        if (identityService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("identityService");
        }
        if (identityService.isAuthenticated(TAG)) {
            TarazedSessionLogger tarazedSessionLogger2 = this.logger;
            if (tarazedSessionLogger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger2.i(TAG, "authenticated user, continue to initialize Tarazed");
            CoroutineScope coroutineScope = this.mainScope;
            if (coroutineScope == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mainScope");
            }
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DefaultAlexaTarazedService$initialize$1(this, null), 3, null);
        } else {
            TarazedSessionLogger tarazedSessionLogger3 = this.logger;
            if (tarazedSessionLogger3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger3.i(TAG, "User is not authenticated yet, waiting on OOBE event");
            EventBus eventBus = this.eventBus;
            if (eventBus == null) {
                Intrinsics.throwUninitializedPropertyAccessException("eventBus");
            }
            final MultiFilterSubscriber newSubscriber = eventBus.getNewSubscriber();
            newSubscriber.subscribeFilter(DefaultAlexaTarazedService$initialize$2.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initialize$3

                /* compiled from: DefaultAlexaTarazedService.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
                @DebugMetadata(c = "com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initialize$3$1", f = "DefaultAlexaTarazedService.kt", i = {0}, l = {141}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
                /* renamed from: com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initialize$3$1  reason: invalid class name */
                /* loaded from: classes10.dex */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Message $it;
                    Object L$0;
                    int label;
                    private CoroutineScope p$;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Message message, Continuation continuation) {
                        super(2, continuation);
                        this.$it = message;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$it, completion);
                        anonymousClass1.p$ = (CoroutineScope) obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* renamed from: invoke */
                    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended;
                        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.p$;
                            TarazedSessionLogger logger$AlexaMobileAndroidTarazedIntegration_release = DefaultAlexaTarazedService.this.getLogger$AlexaMobileAndroidTarazedIntegration_release();
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received ");
                            Message it2 = this.$it;
                            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                            outline107.append(it2.getEventType());
                            outline107.append(", unsubscribing from OOBE ");
                            outline107.append("events and initializing MSS");
                            logger$AlexaMobileAndroidTarazedIntegration_release.i("AlexaTarazedService", outline107.toString());
                            DefaultAlexaTarazedService.this.getEventBus$AlexaMobileAndroidTarazedIntegration_release().unsubscribe(newSubscriber);
                            DefaultAlexaTarazedService defaultAlexaTarazedService = DefaultAlexaTarazedService.this;
                            this.L$0 = coroutineScope;
                            this.label = 1;
                            if (defaultAlexaTarazedService.initTarazed(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        } else {
                            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(@NotNull Message it2) {
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    BuildersKt__Builders_commonKt.launch$default(DefaultAlexaTarazedService.this.getMainScope$AlexaMobileAndroidTarazedIntegration_release(), null, null, new AnonymousClass1(it2, null), 3, null);
                }
            });
        }
        TarazedSessionLogger tarazedSessionLogger4 = this.logger;
        if (tarazedSessionLogger4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        tarazedSessionLogger4.i(TAG, "Always listening to IDENTITY_CHANGED and ENDPOINTS_CHANGED events");
        EventBus eventBus2 = this.eventBus;
        if (eventBus2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventBus");
        }
        eventBus2.getNewSubscriber().subscribeFilter(DefaultAlexaTarazedService$initialize$4.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initialize$5

            /* compiled from: DefaultAlexaTarazedService.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
            @DebugMetadata(c = "com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initialize$5$1", f = "DefaultAlexaTarazedService.kt", i = {0}, l = {153}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
            /* renamed from: com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService$initialize$5$1  reason: invalid class name */
            /* loaded from: classes10.dex */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Message $it;
                Object L$0;
                int label;
                private CoroutineScope p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Message message, Continuation continuation) {
                    super(2, continuation);
                    this.$it = message;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$it, completion);
                    anonymousClass1.p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                /* renamed from: invoke */
                public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.p$;
                        DefaultAlexaTarazedService defaultAlexaTarazedService = DefaultAlexaTarazedService.this;
                        Message it2 = this.$it;
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        String eventType = it2.getEventType();
                        Intrinsics.checkExpressionValueIsNotNull(eventType, "it.eventType");
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (defaultAlexaTarazedService.enableTarazedPermissionIfFeatureEnabled(eventType, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(@NotNull Message it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                BuildersKt__Builders_commonKt.launch$default(DefaultAlexaTarazedService.this.getMainScope$AlexaMobileAndroidTarazedIntegration_release(), null, null, new AnonymousClass1(it2, null), 3, null);
            }
        });
    }

    @Override // com.amazon.alexa.tarazed.api.AlexaTarazedService
    public boolean isSessionActive() {
        return TarazedSessionAndroidService.Companion.isSessionActive();
    }

    @Override // com.amazon.alexa.tarazed.api.AlexaTarazedService
    public void pauseSession() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        tarazedSessionLogger.i(TAG, "Pausing session requested");
        sendRequest(TarazedIntents.SessionAndroidService.PAUSE_SESSION);
    }

    @Override // com.amazon.alexa.tarazed.api.AlexaTarazedService
    public void resumeSession() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        tarazedSessionLogger.i(TAG, "Resuming session requested");
        sendRequest(TarazedIntents.SessionAndroidService.RESUME_SUSPENDED_SESSION);
    }

    public final void setAccountMetadataProvider$AlexaMobileAndroidTarazedIntegration_release(@NotNull Provider<AccountMetadataProviderAlexaMobile> provider) {
        Intrinsics.checkParameterIsNotNull(provider, "<set-?>");
        this.accountMetadataProvider = provider;
    }

    public final void setAlexaMobileDeviceInformation$AlexaMobileAndroidTarazedIntegration_release(@NotNull DeviceInformation deviceInformation) {
        Intrinsics.checkParameterIsNotNull(deviceInformation, "<set-?>");
        this.alexaMobileDeviceInformation = deviceInformation;
    }

    public final void setAztecTokenProvider$AlexaMobileAndroidTarazedIntegration_release(@NotNull Provider<AztecTokenProviderAlexaMobile> provider) {
        Intrinsics.checkParameterIsNotNull(provider, "<set-?>");
        this.aztecTokenProvider = provider;
    }

    public final void setDmpsHandler$AlexaMobileAndroidTarazedIntegration_release(@NotNull DMPSHandler dMPSHandler) {
        Intrinsics.checkParameterIsNotNull(dMPSHandler, "<set-?>");
        this.dmpsHandler = dMPSHandler;
    }

    public final void setEventBus$AlexaMobileAndroidTarazedIntegration_release(@NotNull EventBus eventBus) {
        Intrinsics.checkParameterIsNotNull(eventBus, "<set-?>");
        this.eventBus = eventBus;
    }

    public final void setFeatureChecker$AlexaMobileAndroidTarazedIntegration_release(@NotNull FeatureChecker featureChecker) {
        Intrinsics.checkParameterIsNotNull(featureChecker, "<set-?>");
        this.featureChecker = featureChecker;
    }

    public final void setIdentityEventListener$AlexaMobileAndroidTarazedIntegration_release(@NotNull IdentityEventListener identityEventListener) {
        Intrinsics.checkParameterIsNotNull(identityEventListener, "<set-?>");
        this.identityEventListener = identityEventListener;
    }

    public final void setIdentityService$AlexaMobileAndroidTarazedIntegration_release(@NotNull IdentityService identityService) {
        Intrinsics.checkParameterIsNotNull(identityService, "<set-?>");
        this.identityService = identityService;
    }

    public final void setLogger$AlexaMobileAndroidTarazedIntegration_release(@NotNull TarazedSessionLogger tarazedSessionLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedSessionLogger, "<set-?>");
        this.logger = tarazedSessionLogger;
    }

    public final void setMainScope$AlexaMobileAndroidTarazedIntegration_release(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "<set-?>");
        this.mainScope = coroutineScope;
    }

    public final void setSessionNotifier$AlexaMobileAndroidTarazedIntegration_release(@NotNull TarazedSessionNotifier tarazedSessionNotifier) {
        Intrinsics.checkParameterIsNotNull(tarazedSessionNotifier, "<set-?>");
        this.sessionNotifier = tarazedSessionNotifier;
    }

    @Override // com.amazon.alexa.tarazed.api.AlexaTarazedService
    public void suspendSession() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        tarazedSessionLogger.i(TAG, "Suspending session requested");
        sendRequest(TarazedIntents.SessionAndroidService.SUSPEND_SESSION);
    }
}
