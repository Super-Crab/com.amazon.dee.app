package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.utils.security.SignatureVerifier;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/* compiled from: MessengerModule.java */
@Module
/* renamed from: com.amazon.alexa.PyL  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0178PyL {
    @Provides
    @Singleton
    public SignatureVerifier zZm(Context context) {
        return new SignatureVerifier(context);
    }

    @Provides
    public MessageReceiversManager zZm(SignatureVerifier signatureVerifier) {
        return new MessageReceiversManager(signatureVerifier);
    }
}
