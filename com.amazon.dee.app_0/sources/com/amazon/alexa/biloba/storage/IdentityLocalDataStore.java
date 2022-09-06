package com.amazon.alexa.biloba.storage;

import com.amazon.alexa.biloba.storage.IdentityLocalDataStore;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import rx.Single;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
/* loaded from: classes6.dex */
public class IdentityLocalDataStore {
    public static final String CARE_HUB_STORAGE_SCOPE = "care_hub";
    public static final String LAST_KNOWN_SESSION_ID = "last_session_id";
    public static final String PASSCODE_AUTHORIZED = "passcode_authorized";
    private static final String TAG = "IdentityLocalDataStore";
    private IdentityService identityService;
    private PersistentStorage storageService;

    /* loaded from: classes6.dex */
    public interface SessionIdHandler {
        void handleSessionId(String str);
    }

    public IdentityLocalDataStore(PersistentStorage.Factory factory, IdentityService identityService) {
        this.storageService = factory.create(CARE_HUB_STORAGE_SCOPE);
        this.identityService = identityService;
    }

    public void clearAll() {
        LogUtils.d(TAG, "removing all persisted values for Care Hub");
        PersistentStorage.Transaction edit = this.storageService.edit();
        edit.remove(LAST_KNOWN_SESSION_ID);
        edit.remove(PASSCODE_AUTHORIZED);
        edit.commit();
    }

    public void getCurrentSessionId(final SessionIdHandler sessionIdHandler) {
        Single<String> subscribeOn = this.identityService.getSessionId(TAG).subscribeOn(Schedulers.io());
        sessionIdHandler.getClass();
        subscribeOn.subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$T3FzJKoW0itQdmQGhAjayQT-4MQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                IdentityLocalDataStore.SessionIdHandler.this.handleSessionId((String) obj);
            }
        });
    }

    public String getPersistedSessionId() {
        if (this.storageService.contains(LAST_KNOWN_SESSION_ID)) {
            return this.storageService.getString(LAST_KNOWN_SESSION_ID);
        }
        return null;
    }

    public boolean isPasscodeAuthorized() {
        if (this.storageService.contains(PASSCODE_AUTHORIZED)) {
            return this.storageService.getBoolean(PASSCODE_AUTHORIZED);
        }
        return false;
    }

    public boolean persistAuthorizationStatus(boolean z) {
        PersistentStorage persistentStorage = this.storageService;
        if (persistentStorage == null) {
            String str = TAG;
            LogUtils.e(str, "Cannot store auth status because the storage service is unavailable.\n The status was: " + z);
            return false;
        }
        PersistentStorage.Transaction edit = persistentStorage.edit();
        edit.set(PASSCODE_AUTHORIZED, z);
        edit.commit();
        return true;
    }

    public boolean persistSessionId(String str) {
        if (this.storageService == null) {
            String str2 = TAG;
            LogUtils.e(str2, "Cannot store session ID because the storage service is unavailable. The ID was: " + str);
            return false;
        }
        String str3 = TAG;
        LogUtils.d(str3, "storing the session ID value as " + str);
        PersistentStorage.Transaction edit = this.storageService.edit();
        edit.set(LAST_KNOWN_SESSION_ID, str);
        edit.commit();
        return true;
    }
}
