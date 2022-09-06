package com.amazon.alexa.api;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.LegacyUserSpeechProvider;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class LegacyUserSpeechProviders {
    private static final String TAG = "LegacyUserSpeechProviders";
    private final Map<LegacyUserSpeechProvider.a, LegacyUserSpeechProvider> legacyUserSpeechProviders = new HashMap();

    private void continueDialog(LegacyUserSpeechProvider.a aVar, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        LegacyUserSpeechProvider legacyUserSpeechProvider = getLegacyUserSpeechProvider(aVar);
        String str = TAG;
        Log.i(str, "got USP for " + aVar + " : " + legacyUserSpeechProvider);
        if (legacyUserSpeechProvider != null) {
            legacyUserSpeechProvider.continueDialog(new AlexaAudioSink(parcelFileDescriptor, null));
            return;
        }
        Log.e(TAG, "failed to find user speech provider to continue dialog");
        dropDialog(aVar);
    }

    private LegacyUserSpeechProvider createLegacyUserSpeechProvider(LegacyUserSpeechProvider.a aVar, Set<AlexaUserPerceivedLatencyListener> set, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable ParcelFileDescriptor parcelFileDescriptor2, AlexaDialogExtras alexaDialogExtras) {
        Preconditions.notNull(set, "upl listeners is null");
        Preconditions.notNull(alexaAudioMetadata, "audioMetadata is null");
        Preconditions.notNull(parcelFileDescriptor, "audioFileDescriptor is null");
        Preconditions.notNull(alexaDialogExtras, "alexaDialogExtras is null");
        LegacyUserSpeechProvider legacyUserSpeechProvider = new LegacyUserSpeechProvider(aVar, set, alexaAudioMetadata, new AlexaAudioSink(parcelFileDescriptor, null), parcelFileDescriptor2 != null ? new AlexaDataSink(parcelFileDescriptor2, null) : null, alexaDialogExtras);
        this.legacyUserSpeechProviders.put(aVar, legacyUserSpeechProvider);
        String str = TAG;
        Log.i(str, "saved to map USP for " + aVar + " : " + legacyUserSpeechProvider);
        return legacyUserSpeechProvider;
    }

    private void dropDialog(LegacyUserSpeechProvider.a aVar) throws RemoteException {
        String str = TAG;
        Log.i(str, "dropping dialog " + aVar);
        aVar.a();
        aVar.d();
    }

    private void stopDialog(LegacyUserSpeechProvider.a aVar) throws RemoteException {
        LegacyUserSpeechProvider removeLegacyUserSpeechProvider = removeLegacyUserSpeechProvider(aVar);
        String str = TAG;
        Log.i(str, "removed USP for " + aVar + " : " + removeLegacyUserSpeechProvider);
        if (removeLegacyUserSpeechProvider != null) {
            removeLegacyUserSpeechProvider.stopDialog();
            return;
        }
        Log.e(TAG, "failed to find user speech provider to stop dialog");
        dropDialog(aVar);
    }

    private void stopDialogTurn(LegacyUserSpeechProvider.a aVar) throws RemoteException {
        aVar.a();
    }

    public void continueDialog(AlexaDialogControllerProxy alexaDialogControllerProxy, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        Preconditions.notNull(alexaDialogControllerProxy, "dialogControllerProxy is null");
        Preconditions.notNull(parcelFileDescriptor, "audioFileDescriptor is null");
        continueDialog(wrap(alexaDialogControllerProxy), parcelFileDescriptor);
    }

    public void continueDialog(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        Preconditions.notNull(alexaDialogControllerProxyV2, "dialogControllerProxy is null");
        Preconditions.notNull(parcelFileDescriptor, "audioFileDescriptor is null");
        continueDialog(wrap(alexaDialogControllerProxyV2), parcelFileDescriptor);
    }

    public LegacyUserSpeechProvider createLegacyUserSpeechProvider(AlexaDialogControllerProxy alexaDialogControllerProxy, Set<AlexaUserPerceivedLatencyListener> set, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable ParcelFileDescriptor parcelFileDescriptor2, AlexaDialogExtras alexaDialogExtras) {
        Preconditions.notNull(alexaDialogControllerProxy, "dialogControllerProxy is null");
        return createLegacyUserSpeechProvider(wrap(alexaDialogControllerProxy), set, alexaAudioMetadata, parcelFileDescriptor, parcelFileDescriptor2, alexaDialogExtras);
    }

    public LegacyUserSpeechProvider createLegacyUserSpeechProvider(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, Set<AlexaUserPerceivedLatencyListener> set, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable ParcelFileDescriptor parcelFileDescriptor2, AlexaDialogExtras alexaDialogExtras) {
        Preconditions.notNull(alexaDialogControllerProxyV2, "dialogControllerProxy is null");
        return createLegacyUserSpeechProvider(wrap(alexaDialogControllerProxyV2), set, alexaAudioMetadata, parcelFileDescriptor, parcelFileDescriptor2, alexaDialogExtras);
    }

    @Nullable
    LegacyUserSpeechProvider getLegacyUserSpeechProvider(LegacyUserSpeechProvider.a aVar) {
        return this.legacyUserSpeechProviders.get(aVar);
    }

    @Nullable
    LegacyUserSpeechProvider removeLegacyUserSpeechProvider(LegacyUserSpeechProvider.a aVar) {
        return this.legacyUserSpeechProviders.remove(aVar);
    }

    public void stopDialog(AlexaDialogControllerProxy alexaDialogControllerProxy) throws RemoteException {
        Preconditions.notNull(alexaDialogControllerProxy, "dialogControllerProxy is null");
        stopDialog(wrap(alexaDialogControllerProxy));
    }

    public void stopDialog(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) throws RemoteException {
        Preconditions.notNull(alexaDialogControllerProxyV2, "dialogControllerProxy is null");
        stopDialog(wrap(alexaDialogControllerProxyV2));
    }

    public void stopDialogTurn(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) throws RemoteException {
        Preconditions.notNull(alexaDialogControllerProxyV2, "dialogControllerProxy is null");
        stopDialogTurn(wrap(alexaDialogControllerProxyV2));
    }

    LegacyUserSpeechProvider.a wrap(AlexaDialogControllerProxy alexaDialogControllerProxy) {
        return new LegacyUserSpeechProvider.a(alexaDialogControllerProxy);
    }

    LegacyUserSpeechProvider.a wrap(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
        return new LegacyUserSpeechProvider.a(alexaDialogControllerProxyV2);
    }
}
