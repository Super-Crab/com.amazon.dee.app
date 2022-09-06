package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.device.messaging.ADMRegistrationConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UserSpeechProvidersSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0017J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0017J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000eH\u0016J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0017J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/api/compat/UserSpeechProvidersSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/UserSpeechProvidersApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "deregister", "", "alexaUserSpeechProvider", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderv2;", ADMRegistrationConstants.METHOD_REGISTER, "alexaUserSpeechProviderMetadata", "Lcom/amazon/alexa/api/AlexaUserSpeechProviderMetadata;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderMetadata;", "requestDialog", "alexaDialogRequest", "Lcom/amazon/alexa/api/AlexaDialogRequest;", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class UserSpeechProvidersSubcomponent extends Subcomponent implements UserSpeechProvidersApi {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Map<AlexaUserSpeechProvider, AlexaUserSpeechProviderAdapter> compatListeners = new LinkedHashMap();
    @NotNull
    private static final Map<AlexaUserSpeechProviderv2, AlexaUserSpeechProviderAdapterv2> compatv2Listeners = new LinkedHashMap();

    /* compiled from: UserSpeechProvidersSubcomponent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/api/compat/UserSpeechProvidersSubcomponent$Companion;", "", "()V", "compatListeners", "", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderAdapter;", "getCompatListeners", "()Ljava/util/Map;", "compatv2Listeners", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderv2;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderAdapterv2;", "getCompatv2Listeners", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<AlexaUserSpeechProvider, AlexaUserSpeechProviderAdapter> getCompatListeners() {
            return UserSpeechProvidersSubcomponent.compatListeners;
        }

        @NotNull
        public final Map<AlexaUserSpeechProviderv2, AlexaUserSpeechProviderAdapterv2> getCompatv2Listeners() {
            return UserSpeechProvidersSubcomponent.compatv2Listeners;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSpeechProvidersSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.UserSpeechProvidersApi
    @Deprecated(message = "use AlexaUserSpeechProviderv2")
    public void deregister(@NotNull AlexaUserSpeechProvider alexaUserSpeechProvider) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
        AlexaUserSpeechProviderAdapter remove = compatListeners.remove(alexaUserSpeechProvider);
        if (remove != null) {
            getFrameworkApis().getUserSpeechProviders().deregister(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.UserSpeechProvidersApi
    @Deprecated(message = "use AlexaUserSpeechProviderv2")
    public void register(@NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull com.amazon.alexa.api.AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProviderMetadata, "alexaUserSpeechProviderMetadata");
        AlexaUserSpeechProviderAdapter alexaUserSpeechProviderAdapter = new AlexaUserSpeechProviderAdapter(alexaUserSpeechProvider);
        compatListeners.put(alexaUserSpeechProvider, alexaUserSpeechProviderAdapter);
        getFrameworkApis().getUserSpeechProviders().register(alexaUserSpeechProviderAdapter, alexaUserSpeechProviderMetadata);
    }

    @Override // com.amazon.alexa.api.compat.UserSpeechProvidersApi
    @Deprecated(message = "use AlexaUserSpeechProviderv2")
    public void requestDialog(@NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull AlexaDialogRequest alexaDialogRequest) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
        Intrinsics.checkParameterIsNotNull(alexaDialogRequest, "alexaDialogRequest");
        if (compatListeners.containsKey(alexaUserSpeechProvider)) {
            AlexaUserSpeechProviderAdapter alexaUserSpeechProviderAdapter = compatListeners.get(alexaUserSpeechProvider);
            if (alexaUserSpeechProviderAdapter == null) {
                return;
            }
            getFrameworkApis().getUserSpeechProviders().requestDialog(alexaUserSpeechProviderAdapter, alexaDialogRequest);
            return;
        }
        alexaUserSpeechProvider.onDialogRequestDenied();
    }

    @Override // com.amazon.alexa.api.compat.UserSpeechProvidersApi
    public void deregister(@NotNull AlexaUserSpeechProviderv2 alexaUserSpeechProvider) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
        AlexaUserSpeechProviderAdapterv2 remove = compatv2Listeners.remove(alexaUserSpeechProvider);
        if (remove != null) {
            getFrameworkApis().getUserSpeechProviders().deregister(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.UserSpeechProvidersApi
    public void register(@NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProviderMetadata, "alexaUserSpeechProviderMetadata");
        AlexaUserSpeechProviderAdapter alexaUserSpeechProviderAdapter = new AlexaUserSpeechProviderAdapter(alexaUserSpeechProvider);
        compatListeners.put(alexaUserSpeechProvider, alexaUserSpeechProviderAdapter);
        getFrameworkApis().getUserSpeechProviders().register(alexaUserSpeechProviderAdapter, AlexaUserSpeechProviderMetadata.Companion.convertFromCompat$AlexaMobileAndroidVoiceSDKApiCompat_release(alexaUserSpeechProviderMetadata));
    }

    @Override // com.amazon.alexa.api.compat.UserSpeechProvidersApi
    public void requestDialog(@NotNull AlexaUserSpeechProviderv2 alexaUserSpeechProvider, @NotNull AlexaDialogRequest alexaDialogRequest) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
        Intrinsics.checkParameterIsNotNull(alexaDialogRequest, "alexaDialogRequest");
        if (compatv2Listeners.containsKey(alexaUserSpeechProvider)) {
            AlexaUserSpeechProviderAdapterv2 alexaUserSpeechProviderAdapterv2 = compatv2Listeners.get(alexaUserSpeechProvider);
            if (alexaUserSpeechProviderAdapterv2 == null) {
                return;
            }
            getFrameworkApis().getUserSpeechProviders().requestDialog(alexaUserSpeechProviderAdapterv2, alexaDialogRequest);
            return;
        }
        alexaUserSpeechProvider.onDialogRequestDenied(null);
    }

    @Override // com.amazon.alexa.api.compat.UserSpeechProvidersApi
    public void register(@NotNull AlexaUserSpeechProviderv2 alexaUserSpeechProvider, @NotNull AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProviderMetadata, "alexaUserSpeechProviderMetadata");
        AlexaUserSpeechProviderAdapterv2 alexaUserSpeechProviderAdapterv2 = new AlexaUserSpeechProviderAdapterv2(alexaUserSpeechProvider);
        compatv2Listeners.put(alexaUserSpeechProvider, alexaUserSpeechProviderAdapterv2);
        getFrameworkApis().getUserSpeechProviders().register(alexaUserSpeechProviderAdapterv2, AlexaUserSpeechProviderMetadata.Companion.convertFromCompat$AlexaMobileAndroidVoiceSDKApiCompat_release(alexaUserSpeechProviderMetadata));
    }
}
