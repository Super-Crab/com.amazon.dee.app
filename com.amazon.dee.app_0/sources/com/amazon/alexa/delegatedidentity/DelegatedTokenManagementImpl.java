package com.amazon.alexa.delegatedidentity;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement;
import com.amazon.alexa.delegatedidentity.model.Token;
import com.amazon.alexa.delegatedidentity.storage.TokenStorage;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
/* loaded from: classes6.dex */
public class DelegatedTokenManagementImpl implements DelegatedTokenManagement {
    private static final int DELEGATION_TOKEN_EXPIRY = 1800000;
    private static final String TAG = "com.amazon.alexa.delegatedidentity.DelegatedTokenManagementImpl";
    private static final int TOKEN_BUFFER = 600000;
    private final TokenAccessor tokenAccessor;
    private final TokenStorage tokenStorage;

    public DelegatedTokenManagementImpl(TokenAccessor tokenAccessor, TokenStorage tokenStorage) {
        this.tokenAccessor = tokenAccessor;
        this.tokenStorage = tokenStorage;
    }

    private String refreshDelegationToken(String str, String str2, boolean z) {
        String retrieveDelegatedTokenFromPIMS = retrieveDelegatedTokenFromPIMS(str, str2, Boolean.valueOf(z));
        if (retrieveDelegatedTokenFromPIMS != null && !TextUtils.isEmpty(retrieveDelegatedTokenFromPIMS)) {
            updateDelegationToken(retrieveDelegatedTokenFromPIMS);
            updateActorToken(str2);
        }
        return retrieveDelegatedTokenFromPIMS;
    }

    private String retrieveDelegatedTokenFromPIMS(String str, String str2, Boolean bool) {
        return this.tokenAccessor.getDelegationTokenForPerson(str, str2, bool);
    }

    private void updateActorToken(String str) {
        this.tokenStorage.putActorToken(Token.builder().withValue(str).withType(Token.TokenType.ACTOR).withExpiry(null).build());
    }

    private void updateDelegationToken(String str) {
        this.tokenStorage.putDelegatedToken(Token.builder().withValue(str).withType(Token.TokenType.DELEGATION).withExpiry(Long.valueOf(System.currentTimeMillis() + 1800000)).build());
    }

    @Override // com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement
    public String getDelegatedToken(@NonNull String str, @NonNull String str2, boolean z) {
        TokenStorage tokenStorage = this.tokenStorage;
        if (tokenStorage == null) {
            return retrieveDelegatedTokenFromPIMS(str, str2, false);
        }
        try {
            Token actorToken = tokenStorage.getActorToken();
            Token delegatedToken = this.tokenStorage.getDelegatedToken();
            if (actorToken != null && delegatedToken != null && str2.equals(actorToken.getValue())) {
                if (delegatedToken.getExpiry().longValue() <= System.currentTimeMillis() + FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS) {
                    return refreshDelegationToken(str, str2, false);
                }
                return delegatedToken.getValue();
            }
            return refreshDelegationToken(str, str2, false);
        } catch (Exception e) {
            Log.e(TAG, "Exception while getting the delegation token.", e);
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement
    public void initiateDelegation(String str, String str2) {
        if (this.tokenStorage == null) {
            this.tokenAccessor.getDelegationTokenForPerson(str, str2, true);
            return;
        }
        try {
            String delegationTokenForPerson = this.tokenAccessor.getDelegationTokenForPerson(str, str2, true);
            if (delegationTokenForPerson != null && !TextUtils.isEmpty(delegationTokenForPerson)) {
                this.tokenStorage.cleanupTokens();
                updateDelegationToken(delegationTokenForPerson);
                updateActorToken(str2);
                return;
            }
            throw new RuntimeException("Could not retrieve delegation token.");
        } catch (Exception e) {
            Log.e(TAG, "Exception while initiating delegation.", e);
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.alexa.delegatedidentity.api.DelegatedTokenManagement
    public void terminateDelegation(String str) {
        try {
            if (this.tokenStorage != null) {
                this.tokenStorage.cleanupTokens();
            }
            this.tokenAccessor.terminateDelegation(str);
        } catch (Exception e) {
            Log.e(TAG, "Exception while terminating delegation.", e);
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public DelegatedTokenManagementImpl(TokenAccessor tokenAccessor) {
        this.tokenAccessor = tokenAccessor;
        this.tokenStorage = null;
    }
}
