package com.amazon.alexa.accessory.speechapi.csm.context;

import amazon.speech.simclient.context.ContextClient;
import amazon.speech.simclient.context.DeviceContextAddCallback;
import amazon.speech.simclient.context.DeviceContextAddResult;
import amazon.speech.simclient.context.DeviceContextRemoveCallback;
import amazon.speech.simclient.context.DeviceContextRemoveResult;
import android.content.Context;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.context.MessageContext;
import com.amazon.alexa.accessory.speechapi.context.MessageContextProvider;
import com.amazon.alexa.accessory.speechapi.csm.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmContextProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\rJ\u0006\u0010\u0018\u001a\u00020\u0012J\u000e\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR2\u0010\u000b\u001a&\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u00060\u000eR\u00020\u00000\fj\u0012\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u00060\u000eR\u00020\u0000`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider;", "", "context", "Landroid/content/Context;", "contextClient", "Lamazon/speech/simclient/context/ContextClient;", "(Landroid/content/Context;Lamazon/speech/simclient/context/ContextClient;)V", "getContext", "()Landroid/content/Context;", "getContextClient", "()Lamazon/speech/simclient/context/ContextClient;", "contextProviderMap", "Ljava/util/HashMap;", "Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;", "Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider$AddRemoveDeviceContext;", "Lkotlin/collections/HashMap;", JoinPoint.SYNCHRONIZATION_LOCK, "clearContext", "", "recordCounterMetric", "metricName", "", "registerContextProvider", "messageContextProvider", "setContext", "unregisterContextProvider", "AddRemoveDeviceContext", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmContextProvider {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CsmContextProvider:";
    @NotNull
    private final Context context;
    @NotNull
    private final ContextClient contextClient;
    private final HashMap<MessageContextProvider, AddRemoveDeviceContext> contextProviderMap;
    private final Object lock;

    /* compiled from: CsmContextProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmOverloads
    public CsmContextProvider(@NotNull Context context) {
        this(context, null, 2, null);
    }

    @JvmOverloads
    public CsmContextProvider(@NotNull Context context, @NotNull ContextClient contextClient) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(contextClient, "contextClient");
        this.context = context;
        this.contextClient = contextClient;
        this.lock = new Object();
        this.contextProviderMap = new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordCounterMetric(String str) {
        GeneratedOutlineSupport1.outline171(str, "alexa_accessories", true, null);
    }

    public final void clearContext() {
        Logger.d(TAG, "CsmContextProvider: clearContext");
        synchronized (this.lock) {
            for (AddRemoveDeviceContext addRemoveDeviceContext : this.contextProviderMap.values()) {
                addRemoveDeviceContext.removeDeviceContext();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final ContextClient getContextClient() {
        return this.contextClient;
    }

    public final void registerContextProvider(@NotNull MessageContextProvider messageContextProvider) {
        Intrinsics.checkParameterIsNotNull(messageContextProvider, "messageContextProvider");
        Logger.d("CsmContextProvider: register ContextProvider: " + messageContextProvider);
        synchronized (this.lock) {
            if (this.contextProviderMap.containsKey(messageContextProvider)) {
                Logger.d("CsmContextProvider: ContextProvider already registered: " + messageContextProvider);
                return;
            }
            AddRemoveDeviceContext addRemoveDeviceContext = new AddRemoveDeviceContext(this, messageContextProvider);
            addRemoveDeviceContext.addDeviceContext();
            this.contextProviderMap.put(messageContextProvider, addRemoveDeviceContext);
        }
    }

    public final void setContext() {
        Logger.d("CsmContextProvider: setContext");
        synchronized (this.lock) {
            for (AddRemoveDeviceContext addRemoveDeviceContext : this.contextProviderMap.values()) {
                addRemoveDeviceContext.addDeviceContext();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void unregisterContextProvider(@NotNull MessageContextProvider messageContextProvider) {
        Intrinsics.checkParameterIsNotNull(messageContextProvider, "messageContextProvider");
        Logger.d("CsmContextProvider: unregister ContextProvider: " + messageContextProvider);
        synchronized (this.lock) {
            AddRemoveDeviceContext remove = this.contextProviderMap.remove(messageContextProvider);
            if (remove != null) {
                remove.removeDeviceContext();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* compiled from: CsmContextProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider$AddRemoveDeviceContext;", "Lamazon/speech/simclient/context/DeviceContextAddCallback;", "Lamazon/speech/simclient/context/DeviceContextRemoveCallback;", "messageContextProvider", "Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;", "(Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider;Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;)V", "messageContext", "Lcom/amazon/alexa/accessory/speechapi/context/MessageContext;", "getMessageContextProvider", "()Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;", "addDeviceContext", "", "onFinished", "deviceContextAddResult", "Lamazon/speech/simclient/context/DeviceContextAddResult;", "deviceContextRemoveResult", "Lamazon/speech/simclient/context/DeviceContextRemoveResult;", "removeDeviceContext", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private final class AddRemoveDeviceContext implements DeviceContextAddCallback, DeviceContextRemoveCallback {
        private MessageContext messageContext;
        @NotNull
        private final MessageContextProvider messageContextProvider;
        final /* synthetic */ CsmContextProvider this$0;

        public AddRemoveDeviceContext(@NotNull CsmContextProvider csmContextProvider, MessageContextProvider messageContextProvider) {
            Intrinsics.checkParameterIsNotNull(messageContextProvider, "messageContextProvider");
            this.this$0 = csmContextProvider;
            this.messageContextProvider = messageContextProvider;
        }

        public final void addDeviceContext() {
            this.messageContext = this.messageContextProvider.getMessageContext();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmContextProvider: inside addDeviceContext. getMessageContext: ");
            MessageContext messageContext = this.messageContext;
            if (messageContext == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            outline107.append(messageContext.toString());
            Logger.d(outline107.toString());
            ContextClient contextClient = this.this$0.getContextClient();
            MessageContext messageContext2 = this.messageContext;
            if (messageContext2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            String namespace = messageContext2.getMessageHeader().getNamespace();
            MessageContext messageContext3 = this.messageContext;
            if (messageContext3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            String name = messageContext3.getMessageHeader().getName();
            MessageContext messageContext4 = this.messageContext;
            if (messageContext4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            contextClient.addDeviceContext(namespace, name, messageContext4.getMessagePayload().getPayload(), true, this);
            this.this$0.recordCounterMetric(MetricsConstants.CsmContext.ADD_DEVICE_CONTEXT_COUNT);
        }

        @NotNull
        public final MessageContextProvider getMessageContextProvider() {
            return this.messageContextProvider;
        }

        @Override // amazon.speech.simclient.context.DeviceContextAddCallback
        public void onFinished(@NotNull DeviceContextAddResult deviceContextAddResult) {
            Intrinsics.checkParameterIsNotNull(deviceContextAddResult, "deviceContextAddResult");
            StringBuilder sb = new StringBuilder();
            sb.append("CsmContextProvider: adding deviceContext: ");
            MessageContext messageContext = this.messageContext;
            if (messageContext == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            sb.append(messageContext);
            sb.append(" with CSM returned: ");
            sb.append(deviceContextAddResult);
            sb.append(".name");
            Logger.d(sb.toString());
            if (deviceContextAddResult != DeviceContextAddResult.SUCCESS) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmAddDeviceContextFailed:");
                outline107.append(deviceContextAddResult.name());
                this.this$0.recordCounterMetric(outline107.toString());
            }
        }

        public final void removeDeviceContext() {
            this.messageContext = this.messageContextProvider.getMessageContext();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmContextProvider: inside removeDeviceContext. getMessageContext: ");
            MessageContext messageContext = this.messageContext;
            if (messageContext == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            outline107.append(messageContext.toString());
            Logger.d(outline107.toString());
            ContextClient contextClient = this.this$0.getContextClient();
            MessageContext messageContext2 = this.messageContext;
            if (messageContext2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            String namespace = messageContext2.getMessageHeader().getNamespace();
            MessageContext messageContext3 = this.messageContext;
            if (messageContext3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            contextClient.removeDeviceContext(namespace, messageContext3.getMessageHeader().getName(), this);
            this.this$0.recordCounterMetric(MetricsConstants.CsmContext.REMOVE_DEVICE_CONTEXT_COUNT);
        }

        @Override // amazon.speech.simclient.context.DeviceContextRemoveCallback
        public void onFinished(@NotNull DeviceContextRemoveResult deviceContextRemoveResult) {
            Intrinsics.checkParameterIsNotNull(deviceContextRemoveResult, "deviceContextRemoveResult");
            StringBuilder sb = new StringBuilder();
            sb.append("CsmContextProvider: removing deviceContext: ");
            MessageContext messageContext = this.messageContext;
            if (messageContext == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageContext");
            }
            sb.append(messageContext);
            sb.append(" with CSM returned: ");
            sb.append(deviceContextRemoveResult.name());
            Logger.d(sb.toString());
            if (deviceContextRemoveResult != DeviceContextAddResult.SUCCESS) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmRemoveDeviceContextFailed:");
                outline107.append(deviceContextRemoveResult.name());
                this.this$0.recordCounterMetric(outline107.toString());
            }
        }
    }

    public /* synthetic */ CsmContextProvider(Context context, ContextClient contextClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new ContextClient(context) : contextClient);
    }
}
