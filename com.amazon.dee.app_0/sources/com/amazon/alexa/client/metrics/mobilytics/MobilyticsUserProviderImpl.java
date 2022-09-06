package com.amazon.alexa.client.metrics.mobilytics;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.AccountChangedListener;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.amazon.alexa.mobilytics.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes6.dex */
public class MobilyticsUserProviderImpl implements MobilyticsUserProvider, AccountChangedListener, Handler.Callback {
    private static final String THREAD_NAME = "Alexaservice_MobilyticsUserProvider";
    private final AccountManager accountManager;
    private final Handler handler;
    private final HandlerThread handlerThread;
    private final List<MobilyticsUserProvider.Listener> listeners;
    private final MarketplaceAuthority marketplaceAuthority;
    private static final String TAG = Log.tag(MobilyticsUserProviderImpl.class);
    private static final long REQUEST_TIMEOUT_MILLISECONDS = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);

    /* loaded from: classes6.dex */
    private static class Action {
        public static final int USER_UPDATE = 1;

        private Action() {
        }
    }

    protected MobilyticsUserProviderImpl(@NonNull AccountManager accountManager, @NonNull MarketplaceAuthority marketplaceAuthority, @Nullable HandlerThread handlerThread) {
        this.listeners = new ArrayList();
        this.accountManager = accountManager;
        this.marketplaceAuthority = marketplaceAuthority;
        if (handlerThread == null) {
            this.handlerThread = new HandlerThread(THREAD_NAME);
        } else {
            this.handlerThread = handlerThread;
        }
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper(), this);
        this.accountManager.addAccountChangedListener(this);
        updateUser();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onUserChanged$0(MobilyticsUser mobilyticsUser, MobilyticsUserProvider.Listener listener) {
        try {
            listener.onUserChanged(mobilyticsUser);
        } catch (Exception e) {
            Log.e(TAG, e, "Error processing listener", new Object[0]);
        }
    }

    private void onUpdateUser() {
        onUserChanged(user());
    }

    private void updateUser() {
        this.handler.sendMessage(this.handler.obtainMessage(1));
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public void addListener(@NonNull MobilyticsUserProvider.Listener listener) {
        this.listeners.add(listener);
    }

    @VisibleForTesting
    ConditionVariable createConditionVariable() {
        return new ConditionVariable(false);
    }

    @VisibleForTesting
    protected String getDirectedId() {
        if (this.accountManager == null) {
            return "Unknown";
        }
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final ConditionVariable createConditionVariable = createConditionVariable();
        this.accountManager.getDirectedID(new AccountManager.ResultCallback<String>() { // from class: com.amazon.alexa.client.metrics.mobilytics.MobilyticsUserProviderImpl.1
            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onError(Exception exc) {
                atomicReference2.set(exc);
                createConditionVariable.open();
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onResult(String str) {
                atomicReference.set(str);
                createConditionVariable.open();
            }
        });
        if (createConditionVariable.block(REQUEST_TIMEOUT_MILLISECONDS)) {
            return atomicReference2.get() == null ? (String) atomicReference.get() : "Unknown";
        }
        Log.e(TAG, "getDirectedID: Request timed out. Returning null");
        return "Unknown";
    }

    @VisibleForTesting
    protected final HandlerThread getHandlerThread() {
        return this.handlerThread;
    }

    @VisibleForTesting
    protected String getMarketplaceId() {
        MarketplaceAuthority marketplaceAuthority = this.marketplaceAuthority;
        return marketplaceAuthority == null ? "Unknown" : marketplaceAuthority.getMarketplace().id;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        onUpdateUser();
        return false;
    }

    @Override // com.amazon.alexa.auth.AccountChangedListener
    public void onAccountAdded() {
        updateUser();
    }

    @Override // com.amazon.alexa.auth.AccountChangedListener
    public void onAccountRemoved() {
        updateUser();
    }

    @VisibleForTesting
    protected void onUserChanged(@Nullable final MobilyticsUser mobilyticsUser) {
        Observable.from(this.listeners).forEach(new Action1() { // from class: com.amazon.alexa.client.metrics.mobilytics.-$$Lambda$MobilyticsUserProviderImpl$CGymtghjySA72etpjx4C-E_NXb0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MobilyticsUserProviderImpl.lambda$onUserChanged$0(MobilyticsUser.this, (MobilyticsUserProvider.Listener) obj);
            }
        });
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public void removeListener(@NonNull MobilyticsUserProvider.Listener listener) {
        this.listeners.remove(listener);
    }

    public void shutdown() {
        this.handlerThread.quitSafely();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider
    public MobilyticsUser user() {
        return MobilyticsUserImpl.builder().withDirectedId(getDirectedId()).withMarketplaceId(getMarketplaceId()).build();
    }

    public MobilyticsUserProviderImpl(@NonNull AccountManager accountManager, @NonNull MarketplaceAuthority marketplaceAuthority) {
        this(accountManager, marketplaceAuthority, null);
    }
}
