package com.amazon.alexa.client.core.marketplace;

import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class MarketplaceAuthority {
    @VisibleForTesting
    public static final Marketplace DEFAULT_MARKETPLACE = Marketplace.US;
    @VisibleForTesting
    public static final String MARKETPLACE_KEY = "marketplace";
    private static final long REQUEST_TIMEOUT_MILLISECONDS = 10000;
    private static final String TAG = "MarketplaceAuthority";
    private final AccountManager accountManager;
    private final Lazy<PersistentStorage> persistentStorage;

    @Inject
    public MarketplaceAuthority(AccountManager accountManager, Lazy<PersistentStorage> lazy) {
        this.persistentStorage = lazy;
        this.accountManager = accountManager;
    }

    private Marketplace requestMarketPlace() {
        final AtomicReference atomicReference = new AtomicReference();
        final ConditionVariable conditionVariable = new ConditionVariable(false);
        this.accountManager.getMarketplace(new AccountManager.ResultCallback<String>() { // from class: com.amazon.alexa.client.core.marketplace.MarketplaceAuthority.1
            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onError(Exception exc) {
                Log.e(MarketplaceAuthority.TAG, "Caught error in getToken callback: ", exc);
                conditionVariable.open();
            }

            @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
            public void onResult(String str) {
                String unused = MarketplaceAuthority.TAG;
                GeneratedOutlineSupport1.outline158("Got marketplace result: ", str);
                atomicReference.set(str);
                conditionVariable.open();
            }
        });
        conditionVariable.block(10000L);
        if (atomicReference.get() == null) {
            return DEFAULT_MARKETPLACE;
        }
        return Marketplace.findMarketplaceById((String) atomicReference.get(), DEFAULT_MARKETPLACE);
    }

    public Marketplace getMarketplace() {
        if (this.persistentStorage.mo358get().contains("marketplace")) {
            return Marketplace.valueOf(this.persistentStorage.mo358get().getString("marketplace", DEFAULT_MARKETPLACE.name()));
        }
        Marketplace requestMarketPlace = requestMarketPlace();
        this.persistentStorage.mo358get().edit().set("marketplace", requestMarketPlace.name()).commitAsynchronously();
        return requestMarketPlace;
    }
}
