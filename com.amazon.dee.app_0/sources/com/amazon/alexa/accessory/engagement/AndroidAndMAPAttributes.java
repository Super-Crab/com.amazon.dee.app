package com.amazon.alexa.accessory.engagement;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import java.util.concurrent.ExecutionException;
/* loaded from: classes.dex */
final class AndroidAndMAPAttributes implements EnvironmentAttributes {
    private static final String TAG = "AndroidAndMAPAttributes:";
    private final MAPAccountManager accountManager;
    private final Context applicationContext;
    private final CustomerAttributeStore customerAttributeStore;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndroidAndMAPAttributes(@NonNull Context context) {
        this(CustomerAttributeStore.getInstance(context.getApplicationContext()), new MAPAccountManager(context.getApplicationContext()), context.getApplicationContext());
    }

    @Nullable
    private String getCustomerAttribute(String str, String str2) {
        try {
            return CustomerAttributeStore.getValueOrAttributeDefault(this.customerAttributeStore.getAttribute(str2, str, null).get());
        } catch (MAPCallbackErrorException | InterruptedException | ExecutionException e) {
            Logger.e("%s Failed to get attribute %s from MAP.", e, TAG, str);
            return null;
        }
    }

    @Override // com.amazon.alexa.accessory.engagement.EnvironmentAttributes
    @Nullable
    public String getApplicationDeviceSerialNumber() {
        return getCustomerAttribute("com.amazon.dcp.sso.token.device.deviceserialname", getDirectedCustomerId());
    }

    @Override // com.amazon.alexa.accessory.engagement.EnvironmentAttributes
    @Nullable
    public String getApplicationDeviceType() {
        return getCustomerAttribute("com.amazon.dcp.sso.token.devicedevicetype", getDirectedCustomerId());
    }

    @Override // com.amazon.alexa.accessory.engagement.EnvironmentAttributes
    @Nullable
    public String getApplicationVersionCode() {
        try {
            return Long.toString(this.applicationContext.getPackageManager().getPackageInfo(this.applicationContext.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e("%s Failed to get package name for device engagement metrics.", e, TAG);
            return null;
        }
    }

    @Override // com.amazon.alexa.accessory.engagement.EnvironmentAttributes
    @Nullable
    public String getCountryOfResidence() {
        return getCustomerAttribute(CustomerAttributeKeys.KEY_COR, getDirectedCustomerId());
    }

    @Override // com.amazon.alexa.accessory.engagement.EnvironmentAttributes
    @NonNull
    public String getDirectedCustomerId() {
        Preconditions.notNull(this.accountManager.getAccount(), "accountManager.getAccount()");
        return this.accountManager.getAccount();
    }

    @Override // com.amazon.alexa.accessory.engagement.EnvironmentAttributes
    @Nullable
    public String getPreferredMarketplaceId() {
        return getCustomerAttribute(CustomerAttributeKeys.KEY_PFM, getDirectedCustomerId());
    }

    @Override // com.amazon.alexa.accessory.engagement.EnvironmentAttributes
    public boolean isApplicationOnFireOsDevice() {
        try {
            Class.forName("amazon.os.Build$VERSION");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // com.amazon.alexa.accessory.engagement.EnvironmentAttributes
    public boolean isUserSignedIn() {
        String account = this.accountManager.getAccount();
        if (account == null) {
            return false;
        }
        return this.accountManager.isAccountRegistered(account);
    }

    @VisibleForTesting
    AndroidAndMAPAttributes(@NonNull CustomerAttributeStore customerAttributeStore, @NonNull MAPAccountManager mAPAccountManager, @NonNull Context context) {
        Preconditions.notNull(customerAttributeStore, "customerAttributeStore");
        Preconditions.notNull(mAPAccountManager, "accountManager");
        Preconditions.notNull(context, "applicationContext");
        this.customerAttributeStore = customerAttributeStore;
        this.accountManager = mAPAccountManager;
        this.applicationContext = context;
    }
}
