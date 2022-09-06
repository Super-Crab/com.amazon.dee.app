package com.amazon.alexa.accessory.speechapi.csm.factories;

import android.content.Context;
import com.amazon.alexa.devices.Alexa;
import com.amazon.alexa.devices.android.AlexaFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaFactoryProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/factories/AlexaFactoryProvider;", "Lcom/amazon/alexa/accessory/speechapi/csm/factories/AlexaFactoryProviderInterface;", "()V", "getAlexa", "Lcom/amazon/alexa/devices/Alexa;", "context", "Landroid/content/Context;", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaFactoryProvider implements AlexaFactoryProviderInterface {
    @Override // com.amazon.alexa.accessory.speechapi.csm.factories.AlexaFactoryProviderInterface
    @NotNull
    public Alexa getAlexa(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Alexa alexaFactory = AlexaFactory.getInstance(context);
        Intrinsics.checkExpressionValueIsNotNull(alexaFactory, "AlexaFactory.getInstance(context)");
        return alexaFactory;
    }
}
