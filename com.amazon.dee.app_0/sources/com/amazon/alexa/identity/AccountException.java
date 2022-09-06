package com.amazon.alexa.identity;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class AccountException extends Exception {
    public final int error;

    public AccountException(int i) {
        super(GeneratedOutlineSupport1.outline49("Account error ", i));
        this.error = i;
    }
}
