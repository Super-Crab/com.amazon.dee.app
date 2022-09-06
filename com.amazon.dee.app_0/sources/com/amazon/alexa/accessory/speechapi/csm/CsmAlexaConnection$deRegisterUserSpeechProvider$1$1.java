package com.amazon.alexa.accessory.speechapi.csm;

import com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;
/* compiled from: CsmAlexaConnection.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final /* synthetic */ class CsmAlexaConnection$deRegisterUserSpeechProvider$1$1 extends MutablePropertyReference0 {
    CsmAlexaConnection$deRegisterUserSpeechProvider$1$1(CsmAlexaConnection csmAlexaConnection) {
        super(csmAlexaConnection);
    }

    @Override // kotlin.reflect.KProperty0
    @Nullable
    public Object get() {
        return CsmAlexaConnection.access$getSpeechRecognizerComponent$p((CsmAlexaConnection) this.receiver);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "speechRecognizerComponent";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(CsmAlexaConnection.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getSpeechRecognizerComponent()Lcom/amazon/alexa/devices/speechrecognizer/SpeechRecognizerComponent;";
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(@Nullable Object obj) {
        ((CsmAlexaConnection) this.receiver).speechRecognizerComponent = (SpeechRecognizerComponent) obj;
    }
}
