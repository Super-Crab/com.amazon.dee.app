package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.ThirdPartyServiceHelper;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.AuthorizationToken;
import com.amazon.identity.auth.device.dataobject.RequestedScope;
import com.amazon.identity.auth.device.datastore.AuthorizationTokenDataSource;
import com.amazon.identity.auth.device.datastore.ProfileDataSource;
import com.amazon.identity.auth.device.datastore.RequestedScopeDataSource;
import com.amazon.identity.auth.device.token.AccessAtzToken;
import com.amazon.identity.auth.device.token.RefreshAtzToken;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class TokenVendor {
    private static final int DEFAULT_MINIMUM_TOKEN_LIFETIME = 300;
    private static final String LOG_TAG = "com.amazon.identity.auth.device.endpoint.TokenVendor";
    protected ServerCommunication mServerCommunication = new ServerCommunication();

    private static AccessAtzToken getCommonAccessToken(RequestedScope[] requestedScopeArr, Context context) {
        MAPLog.i(LOG_TAG, "Try finding a common access token for requested scopes");
        if (requestedScopeArr == null || requestedScopeArr.length == 0) {
            return null;
        }
        AuthorizationTokenDataSource authorizationTokenDataSource = AuthorizationTokenDataSource.getInstance(context);
        AccessAtzToken accessAtzToken = (AccessAtzToken) authorizationTokenDataSource.findById(requestedScopeArr[0].getAuthorizationAccessTokenId());
        if (accessAtzToken == null) {
            return null;
        }
        for (int i = 1; i < requestedScopeArr.length; i++) {
            AuthorizationToken findById = authorizationTokenDataSource.findById(requestedScopeArr[i].getAuthorizationAccessTokenId());
            if (findById == null || findById.getRowId() != accessAtzToken.getRowId()) {
                MAPLog.i(LOG_TAG, "Common access token not found!");
                return null;
            }
        }
        String str = LOG_TAG;
        MAPLog.pii(str, "Common access token found.", "accessAtzToken=" + accessAtzToken);
        return accessAtzToken;
    }

    private static RefreshAtzToken getCommonRefreshToken(RequestedScope[] requestedScopeArr, Context context) {
        MAPLog.i(LOG_TAG, "Try finding a common refresh token for requested scopes");
        if (requestedScopeArr == null || requestedScopeArr.length == 0) {
            return null;
        }
        AuthorizationTokenDataSource authorizationTokenDataSource = AuthorizationTokenDataSource.getInstance(context);
        RefreshAtzToken refreshAtzToken = (RefreshAtzToken) authorizationTokenDataSource.findById(requestedScopeArr[0].getAuthorizationRefreshTokenId());
        if (refreshAtzToken == null) {
            return null;
        }
        for (int i = 1; i < requestedScopeArr.length; i++) {
            AuthorizationToken findById = authorizationTokenDataSource.findById(requestedScopeArr[i].getAuthorizationRefreshTokenId());
            if (findById == null || findById.getRowId() != refreshAtzToken.getRowId()) {
                MAPLog.i(LOG_TAG, "Common refresh token not found!");
                return null;
            }
        }
        String str = LOG_TAG;
        MAPLog.pii(str, "Common refresh token found.", "refreshAtzToken=" + refreshAtzToken);
        return refreshAtzToken;
    }

    private boolean isTokenAcceptable(AccessAtzToken accessAtzToken, Bundle bundle) {
        int i = 300;
        if (bundle != null) {
            i = bundle.getInt(AuthzConstants.BUNDLE_KEY.MINIMUM_TOKEN_LIFETIME.val, 300);
        }
        return accessAtzToken != null && accessAtzToken.isRemainingLifeAcceptable(i);
    }

    private void updateExistingRefreshToken(AuthorizationToken authorizationToken, AuthorizationToken authorizationToken2, Context context) throws IOException {
        authorizationToken.setRowId(authorizationToken2.getRowId());
        if (authorizationToken.update(context)) {
            return;
        }
        throw new IOException("Updating token failed unexpectedly!");
    }

    private String updateExistingToken(RefreshAtzToken refreshAtzToken, String str, String[] strArr, AccessAtzToken accessAtzToken, Context context, AppInfo appInfo) throws IOException, AuthError {
        AuthorizationToken authorizationToken;
        RefreshAtzToken refreshAtzToken2 = refreshAtzToken;
        MAPLog.pii(LOG_TAG, "Updating existing token", "token=" + accessAtzToken);
        if (refreshAtzToken2 != null) {
            if (strArr != null) {
                try {
                    if (strArr.length != 0) {
                        AuthorizationToken[] authorizationTokens = this.mServerCommunication.getAuthorizationTokens(refreshAtzToken, str, strArr, context, null, appInfo);
                        boolean z = false;
                        authorizationToken = authorizationTokens[0];
                        if (authorizationTokens[1] != null) {
                            MAPLog.pii(LOG_TAG, "Refresh token", "token=" + refreshAtzToken2);
                            updateExistingRefreshToken(authorizationTokens[1], refreshAtzToken2, context);
                            refreshAtzToken2 = (RefreshAtzToken) authorizationTokens[1];
                        }
                        RefreshAtzToken refreshAtzToken3 = refreshAtzToken2;
                        if (authorizationToken != null) {
                            MAPLog.pii(LOG_TAG, "Refreshed token", "token=" + accessAtzToken);
                            if (accessAtzToken != null) {
                                authorizationToken.setRowId(accessAtzToken.getRowId());
                            } else {
                                z = true;
                            }
                            ProfileDataSource.getInstance(context).deleteAllRows();
                            if (authorizationToken.insertOrUpdate(context)) {
                                if (z) {
                                    updateRequestedScopes(appInfo.getAppFamilyId(), strArr, context, (AccessAtzToken) authorizationToken, refreshAtzToken3, str);
                                }
                                MAPLog.i(LOG_TAG, "Update success!");
                            } else {
                                throw new IOException("Updating token failed unexpectedly!");
                            }
                        }
                    }
                } finally {
                    ThirdPartyServiceHelper.unbind(context);
                }
            }
            return null;
        }
        authorizationToken = null;
        if (authorizationToken != null) {
            return authorizationToken.getTokenValue();
        }
        return null;
    }

    public void clearAuthStateServerSide(Context context, AppInfo appInfo, Bundle bundle) throws AuthError, IOException {
        AccessAtzToken commonAccessToken;
        List<RequestedScope> cachedScopes = getCachedScopes(context);
        if (!cachedScopes.isEmpty() && (commonAccessToken = getCommonAccessToken((RequestedScope[]) cachedScopes.toArray(new RequestedScope[cachedScopes.size()]), context)) != null) {
            ((LogoutResponse) this.mServerCommunication.executeRequest(new LogoutRequest(context, appInfo, commonAccessToken.getTokenValue()), context)).getLogoutResponse();
        }
    }

    public List<RequestedScope> getCachedScopes(Context context) {
        return RequestedScopeDataSource.getInstance(context).findAllRows();
    }

    public RequestedScope[] getRequestedScopes(String str, String str2, String[] strArr, Context context) {
        RequestedScope[] requestedScopeArr = new RequestedScope[strArr.length];
        for (int i = 0; i < requestedScopeArr.length; i++) {
            RequestedScope findByPrimaryKey = RequestedScopeDataSource.getInstance(context).findByPrimaryKey(strArr[i], str2, str);
            if (findByPrimaryKey != null) {
                requestedScopeArr[i] = findByPrimaryKey;
            } else {
                String str3 = LOG_TAG;
                MAPLog.w(str3, "RequestedScope shouldn't be null!!!! - " + findByPrimaryKey + ", but continuing anyway...");
                requestedScopeArr[i] = new RequestedScope(strArr[i], str2, str);
            }
        }
        return requestedScopeArr;
    }

    protected void insertToken(Context context, AuthorizationToken authorizationToken) throws AuthError {
        if (authorizationToken.insert(context) != -1) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to insert ");
        outline107.append(authorizationToken.getType());
        outline107.append(" token into db");
        throw new AuthError(outline107.toString(), AuthError.ERROR_TYPE.ERROR_DATA_STORAGE);
    }

    public void insertTokens(Context context, AccessAtzToken accessAtzToken, RefreshAtzToken refreshAtzToken, String str, String str2, String[] strArr) throws AuthError {
        long insert = accessAtzToken.insert(context);
        if (insert != -1) {
            accessAtzToken.setId(insert);
            long insert2 = refreshAtzToken.insert(context);
            if (insert2 != -1) {
                refreshAtzToken.setId(insert2);
                updateRequestedScopes(str2, strArr, context, accessAtzToken, refreshAtzToken, str);
                return;
            }
            throw new AuthError("Unable to insert refresh token into db", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE);
        }
        throw new AuthError("Unable to insert access atz token into db", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE);
    }

    public void setServerCommunication(ServerCommunication serverCommunication) {
        this.mServerCommunication = serverCommunication;
    }

    protected void updateRequestedScopes(String str, String[] strArr, Context context, AccessAtzToken accessAtzToken, RefreshAtzToken refreshAtzToken, String str2) {
        RequestedScope[] requestedScopes = getRequestedScopes(str2, str, strArr, context);
        for (RequestedScope requestedScope : requestedScopes) {
            if (requestedScope.getRowId() == -1) {
                requestedScope.setAuthorizationAccessTokenId(accessAtzToken.getRowId());
                requestedScope.setAuthorizationRefreshTokenId(refreshAtzToken.getRowId());
                MAPLog.i(LOG_TAG, "Inserting " + requestedScope + " : rowid=" + requestedScope.insert(context));
            } else {
                AuthorizationToken findByRowId = accessAtzToken.mo4057getDataSource(context).findByRowId(requestedScope.getAuthorizationAccessTokenId());
                if (findByRowId != null) {
                    MAPLog.pii(LOG_TAG, "Deleting old access token.", "accessAtzToken=" + findByRowId + " : " + findByRowId.delete(context));
                }
                requestedScope.setAuthorizationAccessTokenId(accessAtzToken.getRowId());
                AuthorizationToken findByRowId2 = refreshAtzToken.mo4057getDataSource(context).findByRowId(requestedScope.getAuthorizationRefreshTokenId());
                if (findByRowId2 != null) {
                    MAPLog.pii(LOG_TAG, "Deleting old refresh token ", "refreshAtzToken=" + findByRowId2 + " : " + findByRowId2.delete(context));
                }
                requestedScope.setAuthorizationRefreshTokenId(refreshAtzToken.getRowId());
                MAPLog.i(LOG_TAG, "Updating " + requestedScope + " : " + requestedScope.update(context));
            }
        }
    }

    public Bundle vendNewTokensFromCode(String str, String str2, String[] strArr, String str3, AppInfo appInfo, Context context) throws IOException, AuthError {
        return vendNewTokensFromCode(str, null, str2, strArr, str3, context, appInfo);
    }

    public String vendToken(String str, String[] strArr, Context context, Bundle bundle, AppInfo appInfo) throws IOException, AuthError {
        String str2 = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Vending out token: appId=");
        outline107.append(appInfo.getAppFamilyId());
        outline107.append(", scopes=");
        outline107.append(Arrays.toString(strArr));
        String sb = outline107.toString();
        MAPLog.pii(str2, sb, "directedId=" + str);
        if (strArr == null || strArr.length == 0) {
            MAPLog.i(LOG_TAG, "Vend token - No scopes passed in");
        }
        RequestedScope[] requestedScopes = getRequestedScopes(str, appInfo.getAppFamilyId(), strArr, context);
        AccessAtzToken commonAccessToken = getCommonAccessToken(requestedScopes, context);
        RefreshAtzToken commonRefreshToken = getCommonRefreshToken(requestedScopes, context);
        if (isTokenAcceptable(commonAccessToken, bundle)) {
            MAPLog.i(LOG_TAG, "Common token still has acceptable life, returning it back to caller");
            return commonAccessToken.getTokenValue();
        }
        return updateExistingToken(commonRefreshToken, str, strArr, commonAccessToken, context, appInfo);
    }

    public Bundle vendNewTokensFromCode(String str, String str2, String str3, String[] strArr, String str4, Context context, AppInfo appInfo) throws IOException, AuthError {
        return vendNewTokensFromCode(str, str2, str3, strArr, str4, context, appInfo, Bundle.EMPTY);
    }

    public Bundle vendNewTokensFromCode(String str, String str2, String str3, String[] strArr, String str4, Context context, AppInfo appInfo, Bundle bundle) throws IOException, AuthError {
        if (strArr != null && strArr.length != 0) {
            MAPLog.i(LOG_TAG, "Vending new tokens from Code");
            AuthorizationToken[] tokensFromCode = this.mServerCommunication.getTokensFromCode(str, str2, str3, strArr, str4, context, appInfo);
            if (tokensFromCode != null) {
                AccessAtzToken accessAtzToken = (AccessAtzToken) tokensFromCode[0];
                if (accessAtzToken != null) {
                    insertToken(context, accessAtzToken);
                    RefreshAtzToken refreshAtzToken = (RefreshAtzToken) tokensFromCode[1];
                    if (refreshAtzToken != null) {
                        insertToken(context, refreshAtzToken);
                        updateRequestedScopes(appInfo.getAppFamilyId(), strArr, context, accessAtzToken, refreshAtzToken, str4);
                        Bundle bundle2 = new Bundle();
                        bundle2.putString(AuthzConstants.BUNDLE_KEY.AUTHORIZE.val, "authorized");
                        if (bundle != null && bundle.getBoolean(LWAConstants.AUTHORIZE_BUNDLE_KEY.RETURN_ACCESS_TOKEN.val)) {
                            bundle2.putString(AuthzConstants.BUNDLE_KEY.TOKEN.val, accessAtzToken.getTokenValue());
                        }
                        return bundle2;
                    }
                    throw new AuthError("Refresh Atz token was null from server communication", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
                }
                throw new AuthError("Access Atz token was null from server communication", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
            }
            throw new AuthError("No tokens returned", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
        throw new AuthError("No scopes provided in parameters", AuthError.ERROR_TYPE.ERROR_BAD_API_PARAM);
    }
}
