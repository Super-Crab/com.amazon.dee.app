package com.amazon.alexa.accessory.speechapi.voicesdk;

import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.VoiceServiceFactory;
import com.amazon.alexa.api.AlexaServicesConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxVoiceServiceFactory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/VoxVoiceServiceFactory;", "Lcom/amazon/alexa/accessory/speechapi/VoiceServiceFactory;", "alexaServicesConnection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "(Lcom/amazon/alexa/api/AlexaServicesConnection;)V", "voxAlexaConnection", "Lcom/amazon/alexa/accessory/speechapi/AlexaConnection;", "provideAlexaConnection", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxVoiceServiceFactory implements VoiceServiceFactory {
    private final AlexaServicesConnection alexaServicesConnection;
    private final AlexaConnection voxAlexaConnection;

    public VoxVoiceServiceFactory(@NotNull AlexaServicesConnection alexaServicesConnection) {
        Intrinsics.checkParameterIsNotNull(alexaServicesConnection, "alexaServicesConnection");
        this.alexaServicesConnection = alexaServicesConnection;
        this.voxAlexaConnection = new VoxAlexaConnection(this.alexaServicesConnection);
    }

    @Override // com.amazon.alexa.accessory.speechapi.VoiceServiceFactory
    @NotNull
    public AlexaConnection provideAlexaConnection() {
        return this.voxAlexaConnection;
    }
}
