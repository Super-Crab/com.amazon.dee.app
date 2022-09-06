package com.amazon.alexa.accessory.speechapi.csm.speech;

import com.amazon.alexa.devices.speechrecognizer.AudioEventListener;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;
/* compiled from: CsmUtteranceProvider.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final /* synthetic */ class CsmUtteranceProvider$requestDialog$1 extends MutablePropertyReference0 {
    CsmUtteranceProvider$requestDialog$1(CsmUtteranceProvider csmUtteranceProvider) {
        super(csmUtteranceProvider);
    }

    @Override // kotlin.reflect.KProperty0
    @Nullable
    public Object get() {
        return CsmUtteranceProvider.access$getAudioEventListener$p((CsmUtteranceProvider) this.receiver);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "audioEventListener";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(CsmUtteranceProvider.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getAudioEventListener()Lcom/amazon/alexa/devices/speechrecognizer/AudioEventListener;";
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(@Nullable Object obj) {
        ((CsmUtteranceProvider) this.receiver).audioEventListener = (AudioEventListener) obj;
    }
}
