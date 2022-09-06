package com.amazon.alexa;

import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.ExtendedClient;
/* compiled from: UserSpeechProvider.java */
/* loaded from: classes.dex */
public interface mqw {
    void BIo(XWx xWx);

    void BIo(qSf qsf);

    boolean BIo();

    ExtendedClient getClient();

    AlexaUserSpeechProviderMetadata getMetadata();

    void pauseWakeWordDetection(String str);

    void resumeWakeWordDetection(String str);

    void setWakeWordDetectionEnabled(boolean z);

    piE zZm();

    void zZm(Jpo jpo, AlexaDialogRequest alexaDialogRequest);

    void zZm(XWx xWx);

    void zZm(qSf qsf);

    void zZm(yWg ywg);
}
