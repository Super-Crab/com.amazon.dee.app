package com.amazon.clouddrive.cdasdk.aps;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.aps.account.APSAccountCalls;
import com.amazon.clouddrive.cdasdk.aps.message.APSMessageCalls;
/* loaded from: classes11.dex */
public interface APSCalls {
    @NonNull
    APSAccountCalls getAccountCalls();

    @NonNull
    APSMessageCalls getMessageCalls();
}
