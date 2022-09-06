package com.amazon.alexa;

import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.google.auto.value.AutoValue;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* compiled from: UserSpeechProviderIdentifier.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class piE implements StronglyTypedString {
    public static final piE BIo;
    public static final piE zZm = zZm("internal-speech-provider");

    static {
        zZm("system-wake-word");
        BIo = zZm("internal-text-provider");
    }

    public static piE zZm(String str) {
        return new uVX(str);
    }

    public static piE zZm(ExtendedClient extendedClient, AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        StringBuilder zZm2 = C0179Pya.zZm("external-speech-provider:");
        zZm2.append(extendedClient.getPackageName());
        zZm2.append(ProcessIdUtil.DEFAULT_PROCESSID);
        zZm2.append(alexaUserSpeechProviderMetadata.getProviderScope());
        return zZm(zZm2.toString());
    }
}
