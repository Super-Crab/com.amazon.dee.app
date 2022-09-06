package com.amazon.alexa;

import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.auth.AccountManager;
import dagger.MembersInjector;
/* compiled from: AlexaAudioProviderManagerService_MembersInjector.java */
/* loaded from: classes.dex */
public final class zZm implements MembersInjector<AlexaAudioProviderManagerService> {
    public static void zZm(AlexaAudioProviderManagerService alexaAudioProviderManagerService, AccountManager accountManager) {
        alexaAudioProviderManagerService.zyO = accountManager;
    }

    public static void zZm(AlexaAudioProviderManagerService alexaAudioProviderManagerService, peZ pez) {
        alexaAudioProviderManagerService.jiA = pez;
    }

    public static void zZm(AlexaAudioProviderManagerService alexaAudioProviderManagerService, MessageReceiversManager messageReceiversManager) {
        alexaAudioProviderManagerService.Qle = messageReceiversManager;
    }
}
