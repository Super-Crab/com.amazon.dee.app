package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MAPError;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.BadPaddingException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class he {
    public static final String TAG = "com.amazon.identity.auth.device.he";
    private static ExecutorService pR = Executors.newSingleThreadExecutor(ji.dG("MAP-TokenCacheThread"));
    private final String bm;
    private final Account ci;
    private ed o;
    private hq pS;
    private cv pT;
    private final ConcurrentHashMap<String, b> pU;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        void a(MAPError mAPError, Bundle bundle);

        void c(MAPError mAPError, String str, int i, String str2);

        void w();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public class b {
        private final String qb;
        private final String qc;

        public b(he heVar, String str) {
            this(str, heVar.bY(str));
        }

        public String gb() {
            return this.qb;
        }

        public String gc() {
            return this.qc;
        }

        public b(String str, String str2) {
            this.qb = str;
            this.qc = str2;
        }
    }

    public he(Context context, Account account) {
        if (context != null && account != null) {
            this.o = ed.M(context);
            this.bm = this.o.getPackageName();
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append(this.bm);
            sb.append(" created a new Token Cache");
            io.dm(str);
            this.pS = (hq) this.o.getSystemService("dcp_account_manager");
            this.ci = account;
            this.pT = new cv(this.o, account);
            this.pU = new ConcurrentHashMap<>();
            return;
        }
        throw new IllegalArgumentException("One or more arguments are null");
    }

    private b ak(String str, String str2) {
        if (str2 == null) {
            this.pU.remove(str);
            return null;
        } else if (!this.pU.containsKey(str)) {
            return am(str, str2);
        } else {
            b bVar = this.pU.get(str);
            return !str2.equals(bVar.gb()) ? am(str, str2) : bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b am(String str, String str2) {
        b bVar = new b(this, str2);
        this.pU.put(str, bVar);
        return bVar;
    }

    private String cQ(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("com.amazon.dcp.sso.property.account.extratokens")) {
            return a(this.ci, str);
        }
        return peekAuthToken(this.ci, str);
    }

    public void al(String str, String str2) {
        String str3 = TAG;
        io.i(str3, this.bm + ": setAuthToken: " + str);
        String bX = bX(str2);
        this.pU.put(str, new b(bX, str2));
        a(this.ci, str, bX);
    }

    protected String bX(String str) {
        return this.pT.bX(str);
    }

    protected String bY(String str) {
        try {
            return this.pT.bY(str);
        } catch (BadPaddingException unused) {
            io.e(TAG, "The decrypt throw BadPaddingException. This should not happen in AccountTokenEncryptor!");
            return null;
        }
    }

    public String cO(String str) throws OperationCanceledException, AuthenticatorException, IOException {
        b ak;
        String str2 = TAG;
        io.i(str2, this.bm + ": blockingFetchToken: " + str);
        Bundle result = a(str, (AccountManagerCallback<Bundle>) null).getResult();
        if (result == null || (ak = ak(str, result.getString("authtoken"))) == null) {
            return null;
        }
        return ak.gc();
    }

    public void cP(String str) {
        String str2 = TAG;
        io.i(str2, this.bm + ": invalidateAuthTokenByType: " + str);
        b(this.ci, cQ(str));
    }

    public String ca(String str) {
        b ak = ak(str, cQ(str));
        if (ak != null) {
            return ak.gc();
        }
        return null;
    }

    public void invalidateAuthToken(String str) {
        String str2 = TAG;
        io.i(str2, this.bm + ": invalidateAuthToken");
        b(this.ci, bX(str));
    }

    protected String peekAuthToken(Account account, String str) {
        return this.pS.peekAuthToken(account, str);
    }

    protected void b(Account account, String str) {
        this.pS.invalidateAuthToken(account.type, str);
    }

    public void a(String[] strArr, final a aVar) {
        String str = TAG;
        io.i(str, this.bm + ": fetchTokens: " + TextUtils.join(",", strArr));
        final Stack stack = new Stack();
        stack.addAll(Arrays.asList(strArr));
        a((String) stack.peek(), new AccountManagerCallback<Bundle>() { // from class: com.amazon.identity.auth.device.he.1
            @Override // android.accounts.AccountManagerCallback
            public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                String str2 = (String) stack.peek();
                try {
                    Bundle result = accountManagerFuture.getResult();
                    if (result.containsKey("authtoken")) {
                        he.this.am(str2, result.getString("authtoken"));
                        he heVar = he.this;
                        Account unused = he.this.ci;
                        if (he.a(heVar, stack, this)) {
                            return;
                        }
                        aVar.w();
                        return;
                    }
                    aVar.a(MAPError.TokenError.FETCH_TOKEN_FAILED, result);
                } catch (AuthenticatorException e) {
                    a aVar2 = aVar;
                    MAPError.CommonError commonError = MAPError.CommonError.INVALID_RESPONSE;
                    aVar2.c(commonError, "Authentication Exception occurred with message: " + e.getMessage(), 5, e.getMessage());
                } catch (OperationCanceledException e2) {
                    a aVar3 = aVar;
                    MAPError.CommonError commonError2 = MAPError.CommonError.OPERATION_CANCELLED;
                    aVar3.c(commonError2, "Operation was cancelled with message: " + e2.getMessage(), 4, e2.getMessage());
                } catch (IOException e3) {
                    String message = e3.getMessage();
                    il.u(he.this.o, message);
                    mq.incrementCounterAndRecord("NetworkError7:TokenCache", new String[0]);
                    aVar.c(MAPError.CommonError.NETWORK_ERROR, GeneratedOutlineSupport1.outline37(e3, new StringBuilder("Network Error occurred with message: ")), 3, message);
                } catch (IllegalArgumentException e4) {
                    aVar.c(MAPError.CommonError.BAD_REQUEST, GeneratedOutlineSupport1.outline43(e4, new StringBuilder("IllegalArgumentException occurred with message: ")), 7, e4.getMessage());
                } catch (RuntimeException e5) {
                    io.e(he.TAG, "Generic error while fetching Tokens", e5);
                    a aVar4 = aVar;
                    MAPError.CommonError commonError3 = MAPError.CommonError.INTERNAL_ERROR;
                    aVar4.c(commonError3, "An internal error occurred while fetching token: " + e5.getMessage(), 1, e5.getMessage());
                }
            }
        });
    }

    protected String a(Account account, String str) {
        return this.pS.getUserData(account, str);
    }

    protected void a(Account account, String str, String str2) {
        this.pS.setAuthToken(account, str, str2);
    }

    protected AccountManagerFuture<Bundle> a(String str, final AccountManagerCallback<Bundle> accountManagerCallback) {
        return this.pS.a(this.ci, str, accountManagerCallback != null ? new AccountManagerCallback<Bundle>() { // from class: com.amazon.identity.auth.device.he.2
            @Override // android.accounts.AccountManagerCallback
            public void run(final AccountManagerFuture<Bundle> accountManagerFuture) {
                he.pR.execute(new Runnable() { // from class: com.amazon.identity.auth.device.he.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        accountManagerCallback.run(accountManagerFuture);
                    }
                });
            }
        } : null);
    }

    static /* synthetic */ boolean a(he heVar, Stack stack, AccountManagerCallback accountManagerCallback) {
        stack.pop();
        if (stack.size() == 0) {
            return false;
        }
        heVar.a((String) stack.peek(), accountManagerCallback);
        return true;
    }
}
