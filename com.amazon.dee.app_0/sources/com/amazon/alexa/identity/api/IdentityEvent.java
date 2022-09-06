package com.amazon.alexa.identity.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface IdentityEvent {
    public static final String IDENTITY_CHANGED = "identity:changed";
    public static final String IDENTITY_COOKIES_UPDATED = "identity:cookies:updated";
    public static final String IDENTITY_COR_PFM_UPDATED = "identity:tou:updated";
    public static final String IDENTITY_LINK_CODE_AUTHORIZED = "identity:linkCode:authorized";
    public static final String IDENTITY_LINK_CODE_NOT_AUTHORIZED = "identity:linkCode:notAuthorized";
    public static final String IDENTITY_LINK_CODE_REQUEST = "identity:linkCode:request";
    public static final String IDENTITY_OOBE_PROFILE_SELECTED = "oobe:profile:selected";
    public static final String IDENTITY_PFM_CHANGED = "identity:pfm:changed";
    public static final String IDENTITY_PROFILE_CHANGED = "identity:profile:changed";
    public static final String IDENTITY_PROFILE_DELEGATION_CHANGED = "identity:delegation:changed";
    public static final String IDENTITY_PROFILE_OOBE_COMPLETED = "identity:profileOOBE:completed";
    public static final String IDENTITY_PROFILE_SELECTION_ATTEMPT = "identity:profile:select:attempt";
    public static final String IDENTITY_SIGN_IN_FAILURE = "identity:signIn:failure";
    public static final String IDENTITY_SIGN_IN_SUCCESS = "identity:signIn:success";
    public static final String IDENTITY_SIGN_OUT_FAILURE = "identity:signOut:failure";
    public static final String IDENTITY_SIGN_OUT_SUCCESS = "identity:signOut:success";
    public static final String IDENTITY_TOU_ACCEPTED = "identity:tou:accepted";
}
