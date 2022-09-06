package com.amazon.tarazed.core.sessionclient.sessioncache;

import com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionCredentialsCipher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionCredentialsCipher;", "", "decryptCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "cachedCredentials", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/CachedSessionCredentials;", "deleteKeyIfExists", "", "encryptCredentials", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/EncryptedCredentialsData;", "credentials", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface SessionCredentialsCipher {
    @NotNull
    CreateCredentialsResponse decryptCredentials(@NotNull CachedSessionCredentials cachedSessionCredentials);

    void deleteKeyIfExists();

    @NotNull
    EncryptedCredentialsData encryptCredentials(@NotNull CreateCredentialsResponse createCredentialsResponse);
}
