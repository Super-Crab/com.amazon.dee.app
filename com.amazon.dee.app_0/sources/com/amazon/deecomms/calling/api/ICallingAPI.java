package com.amazon.deecomms.calling.api;

import android.os.Bundle;
import com.amazon.deecomms.calling.api.exceptions.CallException;
import java.util.List;
/* loaded from: classes12.dex */
public interface ICallingAPI {
    void answerCall(String str);

    void endCall(String str);

    void getCallHistory(int i, ResponseCallback<List<HistoricalCall>> responseCallback);

    CallInfo[] getCallInformation();

    void handleDirective(String str, String str2, String str3);

    void handleNotification(Bundle bundle);

    void initiateCall(CallRequest callRequest, CallStateListener callStateListener) throws CallException;

    void rejectCall(String str);
}
