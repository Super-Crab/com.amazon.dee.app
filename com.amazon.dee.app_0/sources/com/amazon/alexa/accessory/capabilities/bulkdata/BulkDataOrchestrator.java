package com.amazon.alexa.accessory.capabilities.bulkdata;

import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.BulkDataException;
import com.amazon.alexa.accessory.capabilities.bulkdata.identifiers.DataIdentifier;
import com.amazon.alexa.accessory.capabilities.bulkdata.manifest.CategorySpecificData;
import com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession;
import com.amazon.alexa.accessory.protocol.Common;
/* loaded from: classes.dex */
public interface BulkDataOrchestrator {

    /* loaded from: classes.dex */
    public interface BulkDataCapabilityApi {
        void getIncomingManifest(int i, BulkDataManifestCallback bulkDataManifestCallback);

        String identifyAccessory();

        void notifyOutgoingDataAvailable(int i, NotifyDataAvailableCallback notifyDataAvailableCallback);

        void sendOutgoingManifest(int i, Iterable<CategorySpecificData> iterable, SendManifestCallback sendManifestCallback);

        void startIncomingBulkDataSession(DataIdentifier dataIdentifier, int i, int i2, BulkDataSession.BlockReceivedCallback blockReceivedCallback, BulkDataSession.SessionStartCallback sessionStartCallback, BulkDataSession.SessionCompleteCallback sessionCompleteCallback, BulkDataSession.SessionAbortCallback sessionAbortCallback);

        void startIncomingBulkDataSession(DataIdentifier dataIdentifier, int i, BulkDataSession.BlockReceivedCallback blockReceivedCallback, BulkDataSession.SessionStartCallback sessionStartCallback, BulkDataSession.SessionCompleteCallback sessionCompleteCallback, BulkDataSession.SessionAbortCallback sessionAbortCallback);
    }

    /* loaded from: classes.dex */
    public interface BulkDataManifestCallback {
        void onComplete();

        void onError(BulkDataException bulkDataException);

        void onManifestEntryReceived(DataIdentifier dataIdentifier, int i, int i2, int i3, CategorySpecificData categorySpecificData);
    }

    /* loaded from: classes.dex */
    public interface NotifyDataAvailableCallback {
        void onError(BulkDataException bulkDataException);

        void onNotifySuccess();
    }

    /* loaded from: classes.dex */
    public interface OnRequestManifestCallback {
        void sendErrorResponse(Common.ErrorCode errorCode);

        void sendSuccessResponse();
    }

    /* loaded from: classes.dex */
    public interface SendManifestCallback {
        void onComplete();

        void onError(BulkDataException bulkDataException);
    }

    void deregisterApi(BulkDataCapabilityApi bulkDataCapabilityApi);

    void onNotifyIncomingDataAvailable(BulkDataCapabilityApi bulkDataCapabilityApi, int i);

    void onReceiveOutgoingManifestRequest(int i, BulkDataCapabilityApi bulkDataCapabilityApi, OnRequestManifestCallback onRequestManifestCallback);

    void registerApi(BulkDataCapabilityApi bulkDataCapabilityApi);
}
