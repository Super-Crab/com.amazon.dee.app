package com.amazon.alexa.tarazed.account;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceId;
import com.amazon.alexa.tarazed.dagger.scope.TarazedIntegrationScope;
import com.amazon.tarazed.core.registry.component.AccountMetadataProvider;
import com.amazon.tarazed.core.type.Account;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccountMetadataProviderAlexaMobile.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/tarazed/account/AccountMetadataProviderAlexaMobile;", "Lcom/amazon/tarazed/core/registry/component/AccountMetadataProvider;", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "alexaMobileDeviceInformation", "Lcom/amazon/alexa/device/api/DeviceInformation;", "(Lcom/amazon/alexa/identity/api/IdentityService;Lcom/amazon/alexa/device/api/DeviceInformation;)V", "accountMetadata", "Lcom/amazon/tarazed/core/type/Account;", "getAccountMetadata", "()Lcom/amazon/tarazed/core/type/Account;", "Companion", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
@TarazedIntegrationScope
/* loaded from: classes10.dex */
public final class AccountMetadataProviderAlexaMobile implements AccountMetadataProvider {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AccountMetadataTarazed";
    private final DeviceInformation alexaMobileDeviceInformation;
    private final IdentityService identityService;

    /* compiled from: AccountMetadataProviderAlexaMobile.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/tarazed/account/AccountMetadataProviderAlexaMobile$Companion;", "", "()V", "TAG", "", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public AccountMetadataProviderAlexaMobile(@NotNull IdentityService identityService, @NotNull DeviceInformation alexaMobileDeviceInformation) {
        Intrinsics.checkParameterIsNotNull(identityService, "identityService");
        Intrinsics.checkParameterIsNotNull(alexaMobileDeviceInformation, "alexaMobileDeviceInformation");
        this.identityService = identityService;
        this.alexaMobileDeviceInformation = alexaMobileDeviceInformation;
    }

    @Override // com.amazon.tarazed.core.registry.component.AccountMetadataProvider
    @NotNull
    public Account getAccountMetadata() {
        String str;
        Marketplace marketplace;
        String str2 = "Unknown";
        UserIdentity user = this.identityService.getUser(TAG);
        MarketplaceId marketplaceId = null;
        String directedId = user != null ? user.getDirectedId() : null;
        try {
            str = this.alexaMobileDeviceInformation.getDeviceType();
        } catch (DeviceInformationException unused) {
            str = str2;
        }
        UserIdentity user2 = this.identityService.getUser(TAG);
        if (user2 != null && (marketplace = user2.getMarketplace()) != null) {
            marketplaceId = marketplace.getObfuscatedId();
        }
        String valueOf = String.valueOf(marketplaceId);
        try {
            str2 = this.alexaMobileDeviceInformation.getDeviceSerialNumber();
        } catch (DeviceInformationException unused2) {
        }
        return new Account(directedId, valueOf, str, str2);
    }
}
