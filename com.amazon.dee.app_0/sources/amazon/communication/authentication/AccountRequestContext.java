package amazon.communication.authentication;

import android.accounts.Account;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public class AccountRequestContext implements RequestContext {
    @FireOsSdk
    public static final AccountRequestContext EMPTY_ACCOUNT = new AccountRequestContext((String) null);
    private final String mDirectedId;

    @FireOsSdk
    public AccountRequestContext() {
        throw new UnsupportedOperationException("Do no use empty constructor, use directedId instead");
    }

    @FireOsSdk
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && AccountRequestContext.class == obj.getClass() && this.mDirectedId == ((AccountRequestContext) obj).mDirectedId;
    }

    @FireOsSdk
    public String getDirectedId() {
        return this.mDirectedId;
    }

    @FireOsSdk
    public AccountRequestContext(Account account) {
        throw new UnsupportedOperationException("Do no use Account constructor, use directedId instead");
    }

    @FireOsSdk
    public AccountRequestContext(String str) {
        this.mDirectedId = str;
    }
}
