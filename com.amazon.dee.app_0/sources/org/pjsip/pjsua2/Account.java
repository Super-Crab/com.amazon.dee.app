package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class Account {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected Account(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(Account account) {
        if (account == null) {
            return 0L;
        }
        return account.swigCPtr;
    }

    public static Account lookup(int i) {
        long Account_lookup = pjsua2JNI.Account_lookup(i);
        if (Account_lookup == 0) {
            return null;
        }
        return new Account(Account_lookup, false);
    }

    public void create(AccountConfig accountConfig, boolean z) throws Exception {
        pjsua2JNI.Account_create__SWIG_0(this.swigCPtr, this, AccountConfig.getCPtr(accountConfig), accountConfig, z);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Account(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    public BuddyVector enumBuddies() throws Exception {
        return new BuddyVector(pjsua2JNI.Account_enumBuddies(this.swigCPtr, this), false);
    }

    protected void finalize() {
        delete();
    }

    public Buddy findBuddy(String str, FindBuddyMatch findBuddyMatch) throws Exception {
        long Account_findBuddy__SWIG_0 = pjsua2JNI.Account_findBuddy__SWIG_0(this.swigCPtr, this, str, FindBuddyMatch.getCPtr(findBuddyMatch), findBuddyMatch);
        if (Account_findBuddy__SWIG_0 == 0) {
            return null;
        }
        return new Buddy(Account_findBuddy__SWIG_0, false);
    }

    public int getId() {
        return pjsua2JNI.Account_getId(this.swigCPtr, this);
    }

    public AccountInfo getInfo() throws Exception {
        return new AccountInfo(pjsua2JNI.Account_getInfo(this.swigCPtr, this), true);
    }

    public boolean isDefault() {
        return pjsua2JNI.Account_isDefault(this.swigCPtr, this);
    }

    public boolean isValid() {
        return pjsua2JNI.Account_isValid(this.swigCPtr, this);
    }

    public void modify(AccountConfig accountConfig) throws Exception {
        pjsua2JNI.Account_modify(this.swigCPtr, this, AccountConfig.getCPtr(accountConfig), accountConfig);
    }

    public void onIncomingCall(OnIncomingCallParam onIncomingCallParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onIncomingCall(this.swigCPtr, this, OnIncomingCallParam.getCPtr(onIncomingCallParam), onIncomingCallParam);
        } else {
            pjsua2JNI.Account_onIncomingCallSwigExplicitAccount(this.swigCPtr, this, OnIncomingCallParam.getCPtr(onIncomingCallParam), onIncomingCallParam);
        }
    }

    public void onIncomingSubscribe(OnIncomingSubscribeParam onIncomingSubscribeParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onIncomingSubscribe(this.swigCPtr, this, OnIncomingSubscribeParam.getCPtr(onIncomingSubscribeParam), onIncomingSubscribeParam);
        } else {
            pjsua2JNI.Account_onIncomingSubscribeSwigExplicitAccount(this.swigCPtr, this, OnIncomingSubscribeParam.getCPtr(onIncomingSubscribeParam), onIncomingSubscribeParam);
        }
    }

    public void onInstantMessage(OnInstantMessageParam onInstantMessageParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onInstantMessage(this.swigCPtr, this, OnInstantMessageParam.getCPtr(onInstantMessageParam), onInstantMessageParam);
        } else {
            pjsua2JNI.Account_onInstantMessageSwigExplicitAccount(this.swigCPtr, this, OnInstantMessageParam.getCPtr(onInstantMessageParam), onInstantMessageParam);
        }
    }

    public void onInstantMessageStatus(OnInstantMessageStatusParam onInstantMessageStatusParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onInstantMessageStatus(this.swigCPtr, this, OnInstantMessageStatusParam.getCPtr(onInstantMessageStatusParam), onInstantMessageStatusParam);
        } else {
            pjsua2JNI.Account_onInstantMessageStatusSwigExplicitAccount(this.swigCPtr, this, OnInstantMessageStatusParam.getCPtr(onInstantMessageStatusParam), onInstantMessageStatusParam);
        }
    }

    public void onMwiInfo(OnMwiInfoParam onMwiInfoParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onMwiInfo(this.swigCPtr, this, OnMwiInfoParam.getCPtr(onMwiInfoParam), onMwiInfoParam);
        } else {
            pjsua2JNI.Account_onMwiInfoSwigExplicitAccount(this.swigCPtr, this, OnMwiInfoParam.getCPtr(onMwiInfoParam), onMwiInfoParam);
        }
    }

    public void onPublishErrorNotify(OnPublishErrorNotifyParam onPublishErrorNotifyParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onPublishErrorNotify(this.swigCPtr, this, OnPublishErrorNotifyParam.getCPtr(onPublishErrorNotifyParam), onPublishErrorNotifyParam);
        } else {
            pjsua2JNI.Account_onPublishErrorNotifySwigExplicitAccount(this.swigCPtr, this, OnPublishErrorNotifyParam.getCPtr(onPublishErrorNotifyParam), onPublishErrorNotifyParam);
        }
    }

    public void onRegStarted(OnRegStartedParam onRegStartedParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onRegStarted(this.swigCPtr, this, OnRegStartedParam.getCPtr(onRegStartedParam), onRegStartedParam);
        } else {
            pjsua2JNI.Account_onRegStartedSwigExplicitAccount(this.swigCPtr, this, OnRegStartedParam.getCPtr(onRegStartedParam), onRegStartedParam);
        }
    }

    public void onRegState(OnRegStateParam onRegStateParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onRegState(this.swigCPtr, this, OnRegStateParam.getCPtr(onRegStateParam), onRegStateParam);
        } else {
            pjsua2JNI.Account_onRegStateSwigExplicitAccount(this.swigCPtr, this, OnRegStateParam.getCPtr(onRegStateParam), onRegStateParam);
        }
    }

    public void onTypingIndication(OnTypingIndicationParam onTypingIndicationParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onTypingIndication(this.swigCPtr, this, OnTypingIndicationParam.getCPtr(onTypingIndicationParam), onTypingIndicationParam);
        } else {
            pjsua2JNI.Account_onTypingIndicationSwigExplicitAccount(this.swigCPtr, this, OnTypingIndicationParam.getCPtr(onTypingIndicationParam), onTypingIndicationParam);
        }
    }

    public void presNotify(PresNotifyParam presNotifyParam) throws Exception {
        pjsua2JNI.Account_presNotify(this.swigCPtr, this, PresNotifyParam.getCPtr(presNotifyParam), presNotifyParam);
    }

    public void setDefault() throws Exception {
        pjsua2JNI.Account_setDefault(this.swigCPtr, this);
    }

    public void setOnlineStatus(PresenceStatus presenceStatus) throws Exception {
        pjsua2JNI.Account_setOnlineStatus(this.swigCPtr, this, PresenceStatus.getCPtr(presenceStatus), presenceStatus);
    }

    public void setRegistration(boolean z) throws Exception {
        pjsua2JNI.Account_setRegistration(this.swigCPtr, this, z);
    }

    public void setTransport(int i) throws Exception {
        pjsua2JNI.Account_setTransport(this.swigCPtr, this, i);
    }

    public void shutdown() {
        pjsua2JNI.Account_shutdown(this.swigCPtr, this);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.Account_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.Account_change_ownership(this, this.swigCPtr, true);
    }

    public void updateRegHeaders(SipHeaderVector sipHeaderVector) throws Exception {
        pjsua2JNI.Account_updateRegHeaders(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public void create(AccountConfig accountConfig) throws Exception {
        pjsua2JNI.Account_create__SWIG_1(this.swigCPtr, this, AccountConfig.getCPtr(accountConfig), accountConfig);
    }

    public Buddy findBuddy(String str) throws Exception {
        long Account_findBuddy__SWIG_1 = pjsua2JNI.Account_findBuddy__SWIG_1(this.swigCPtr, this, str);
        if (Account_findBuddy__SWIG_1 == 0) {
            return null;
        }
        return new Buddy(Account_findBuddy__SWIG_1, false);
    }

    public Account() {
        this(pjsua2JNI.new_Account(), true);
        pjsua2JNI.Account_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
