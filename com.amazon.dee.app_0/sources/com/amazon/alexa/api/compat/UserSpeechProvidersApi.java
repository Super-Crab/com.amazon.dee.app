package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.device.messaging.ADMRegistrationConstants;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: UserSpeechProvidersApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH'J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\nH&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\nH&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH'J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/UserSpeechProvidersApi;", "", "deregister", "", "alexaUserSpeechProvider", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderv2;", ADMRegistrationConstants.METHOD_REGISTER, "alexaUserSpeechProviderMetadata", "Lcom/amazon/alexa/api/AlexaUserSpeechProviderMetadata;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderMetadata;", "requestDialog", "alexaDialogRequest", "Lcom/amazon/alexa/api/AlexaDialogRequest;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface UserSpeechProvidersApi {
    @Deprecated(message = "use AlexaUserSpeechProviderv2")
    void deregister(@NotNull AlexaUserSpeechProvider alexaUserSpeechProvider);

    void deregister(@NotNull AlexaUserSpeechProviderv2 alexaUserSpeechProviderv2);

    @Deprecated(message = "use AlexaUserSpeechProviderv2")
    void register(@NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull com.amazon.alexa.api.AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata);

    void register(@NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata);

    void register(@NotNull AlexaUserSpeechProviderv2 alexaUserSpeechProviderv2, @NotNull AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata);

    @Deprecated(message = "use AlexaUserSpeechProviderv2")
    void requestDialog(@NotNull AlexaUserSpeechProvider alexaUserSpeechProvider, @NotNull AlexaDialogRequest alexaDialogRequest);

    void requestDialog(@NotNull AlexaUserSpeechProviderv2 alexaUserSpeechProviderv2, @NotNull AlexaDialogRequest alexaDialogRequest);
}
