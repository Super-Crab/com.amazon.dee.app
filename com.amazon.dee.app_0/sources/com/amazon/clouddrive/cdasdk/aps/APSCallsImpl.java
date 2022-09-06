package com.amazon.clouddrive.cdasdk.aps;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.aps.account.APSAccountCalls;
import com.amazon.clouddrive.cdasdk.aps.account.APSAccountCallsImpl;
import com.amazon.clouddrive.cdasdk.aps.message.APSMessageCalls;
import com.amazon.clouddrive.cdasdk.aps.message.APSMessageCallsImpl;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class APSCallsImpl implements APSCalls {
    @NonNull
    private final APSAccountCalls accountCalls;
    @NonNull
    private final APSMessageCalls messageCalls;

    public APSCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        APSCallUtil aPSCallUtil = new APSCallUtil(clientConfig);
        this.accountCalls = new APSAccountCallsImpl(aPSCallUtil, retrofit);
        this.messageCalls = new APSMessageCallsImpl(aPSCallUtil, retrofit);
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.APSCalls
    @NonNull
    public APSAccountCalls getAccountCalls() {
        return this.accountCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.APSCalls
    @NonNull
    public APSMessageCalls getMessageCalls() {
        return this.messageCalls;
    }
}
