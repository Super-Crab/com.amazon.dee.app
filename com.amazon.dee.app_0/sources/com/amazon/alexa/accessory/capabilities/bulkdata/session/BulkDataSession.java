package com.amazon.alexa.accessory.capabilities.bulkdata.session;

import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.BulkDataException;
import com.amazon.alexa.accessory.capabilities.bulkdata.identifiers.DataIdentifier;
import com.amazon.alexa.accessory.capabilities.bulkdata.identifiers.SessionIdentifier;
import com.amazon.alexa.accessory.io.Source;
/* loaded from: classes.dex */
public interface BulkDataSession {

    /* loaded from: classes.dex */
    public interface BlockReceivedCallback {
        void onBlockReceived(int i, int i2);

        void onBlockReceivedError(BulkDataException bulkDataException);
    }

    /* loaded from: classes.dex */
    public interface SessionAbortCallback {
        void onSessionAborted(BulkDataException bulkDataException);

        void onSessionAbortedError(BulkDataException bulkDataException);
    }

    /* loaded from: classes.dex */
    public interface SessionCompleteCallback {
        void onSessionComplete();

        void onSessionCompleteError(BulkDataException bulkDataException);
    }

    /* loaded from: classes.dex */
    public interface SessionStartCallback {
        void onSessionStart(BulkDataSession bulkDataSession, int i);

        void onSessionStartError(BulkDataException bulkDataException);
    }

    /* loaded from: classes.dex */
    public enum State {
        CREATED,
        STARTED,
        COMPLETED,
        ABORTED
    }

    void abort();

    void complete();

    DataIdentifier getDataIdentifier();

    SessionIdentifier getSessionIdentifier();

    Source getSource();

    State getState();

    boolean isAborted();

    boolean isComplete();

    void start(SessionStartCallback sessionStartCallback, int i);
}
