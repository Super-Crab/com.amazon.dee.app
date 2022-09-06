package com.amazon.alexa.accessory.speechapi.csm;

import android.content.Context;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.VoiceServiceFactory;
import com.amazon.alexa.accessory.speechapi.csm.factories.AlexaFactoryProvider;
import com.amazon.alexa.accessory.speechapi.csm.factories.AlexaFactoryProviderInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmVoiceServiceFactory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/CsmVoiceServiceFactory;", "Lcom/amazon/alexa/accessory/speechapi/VoiceServiceFactory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "alexaConnection", "Lcom/amazon/alexa/accessory/speechapi/csm/CsmAlexaConnection;", "alexaFactoryProvider", "Lcom/amazon/alexa/accessory/speechapi/csm/factories/AlexaFactoryProviderInterface;", JoinPoint.SYNCHRONIZATION_LOCK, "", "provideAlexaConnection", "Lcom/amazon/alexa/accessory/speechapi/AlexaConnection;", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmVoiceServiceFactory implements VoiceServiceFactory {
    private CsmAlexaConnection alexaConnection;
    private final AlexaFactoryProviderInterface alexaFactoryProvider;
    private final Context context;
    private final Object lock;

    public CsmVoiceServiceFactory(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.alexaFactoryProvider = new AlexaFactoryProvider();
        this.lock = new Object();
    }

    @Override // com.amazon.alexa.accessory.speechapi.VoiceServiceFactory
    @NotNull
    public AlexaConnection provideAlexaConnection() {
        CsmAlexaConnection csmAlexaConnection;
        synchronized (this.lock) {
            CsmAlexaConnection csmAlexaConnection2 = this.alexaConnection;
            if (csmAlexaConnection2 == null) {
                csmAlexaConnection2 = new CsmAlexaConnection(this.context, this.alexaFactoryProvider, null, null, null, null, 60, null);
            }
            this.alexaConnection = csmAlexaConnection2;
            csmAlexaConnection = this.alexaConnection;
            if (csmAlexaConnection == null) {
                Intrinsics.throwNpe();
            }
        }
        return csmAlexaConnection;
    }
}
