package com.amazon.deecomms.calling.contracts.incoming;

import com.amazon.deecomms.calling.contracts.BasePresenterContract;
/* loaded from: classes12.dex */
public interface IncomingCallPresenterContract extends BasePresenterContract {
    void checkForPermissionsAndAcceptCall();

    void rejectCall();
}
