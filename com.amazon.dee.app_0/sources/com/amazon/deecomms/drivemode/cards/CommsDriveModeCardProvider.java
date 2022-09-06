package com.amazon.deecomms.drivemode.cards;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.drivemode.api.DefaultDriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeCardChangeListener;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.voice.IEventSender;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CapabilitiesManager;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class CommsDriveModeCardProvider extends DefaultDriveModeCardsProvider {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsDriveModeCardProvider.class);
    private AlexaServicesConnection mAlexaServicesConnection;
    private CapabilitiesManager mCapabilitiesManager;
    private Context mContext;
    private IEventSender mEventSender;

    public CommsDriveModeCardProvider(@NonNull Context context, @NonNull IEventSender iEventSender, @NonNull @Named("commsAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull CapabilitiesManager capabilitiesManager) {
        this.mContext = context;
        this.mEventSender = iEventSender;
        this.mAlexaServicesConnection = alexaServicesConnection;
        this.mCapabilitiesManager = capabilitiesManager;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
    public void provideCards() {
        LOG.i("[drive mode] provideCards");
        if (this.mCapabilitiesManager.isDriveModeCommsNativeEnabled()) {
            LOG.d("[drive mode] DriveMode Comms Native weblab enabled, do not send cards");
            return;
        }
        DriveModeCardChangeListener cardChangeListener = getCardChangeListener();
        if (cardChangeListener == null) {
            return;
        }
        LOG.i("[drive mode] adding comms cards");
        cardChangeListener.addCard(new CallingCard(this.mContext, this.mEventSender, this.mAlexaServicesConnection, this.mCapabilitiesManager));
    }
}
