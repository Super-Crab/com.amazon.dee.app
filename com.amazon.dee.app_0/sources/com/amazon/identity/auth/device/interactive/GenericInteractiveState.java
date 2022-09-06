package com.amazon.identity.auth.device.interactive;

import android.os.Bundle;
import com.amazon.identity.auth.device.ResponseManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.api.workflow.RequestContext;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes12.dex */
final class GenericInteractiveState implements InteractiveState {
    static final String INTERACTIVE_STATE_ID_KEY = "interactiveStateId";
    private static final String LOG_TAG = "com.amazon.identity.auth.device.interactive.GenericInteractiveState";
    static final String REQUEST_LIST_KEY = "requestRecords";
    static final String SAVED_INSTANCE_STATE_KEY = GeneratedOutlineSupport1.outline40(InteractiveState.class, new StringBuilder(), ".instanceState");
    private WeakReference<InteractiveStateFragment> backingFragment;
    private final InteractiveRequestMap interactiveRequestMap;
    private final Set<InteractiveRequestRecord> requests;
    private final ResponseManager responseManager;
    private UUID stateId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericInteractiveState(InteractiveStateFragment interactiveStateFragment) {
        this(interactiveStateFragment, ResponseManager.getInstance(), InteractiveRequestMap.getInstance());
    }

    void doProcessPendingResponses(RequestContext requestContext) {
        RequestContext requestContextForRequest;
        LinkedList linkedList = new LinkedList();
        for (InteractiveRequestRecord interactiveRequestRecord : this.requests) {
            String requestId = interactiveRequestRecord.getRequestId();
            if (this.responseManager.hasPendingResponseForRequest(requestId) && (requestContextForRequest = getRequestContextForRequest(interactiveRequestRecord)) == requestContext) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InteractiveState ");
                outline107.append(this.stateId);
                outline107.append(": Processing request ");
                outline107.append(requestId);
                MAPLog.d(str, outline107.toString());
                requestContextForRequest.processResponse(interactiveRequestRecord, this.responseManager.removePendingResponse(requestId));
                linkedList.add(interactiveRequestRecord);
            }
        }
        this.requests.removeAll(linkedList);
    }

    String getId() {
        return this.stateId.toString();
    }

    RequestContext getRequestContextForRequest(InteractiveRequestRecord interactiveRequestRecord) {
        return this.interactiveRequestMap.getRequestContextForSource(getRequestSourceForRequest(interactiveRequestRecord));
    }

    Object getRequestSourceForRequest(InteractiveRequestRecord interactiveRequestRecord) {
        Bundle fragmentWrapper = interactiveRequestRecord.getFragmentWrapper();
        Object fragment = fragmentWrapper != null ? this.backingFragment.get().getFragment(fragmentWrapper) : null;
        return fragment == null ? this.backingFragment.get().getParentActivity() : fragment;
    }

    Set<InteractiveRequestRecord> getRequests() {
        return this.requests;
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveState
    public synchronized void onRequestStart(InteractiveRequestRecord interactiveRequestRecord) {
        String str = interactiveRequestRecord.getFragmentWrapper() == null ? MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME : "fragment";
        String str2 = LOG_TAG;
        MAPLog.d(str2, "InteractiveState " + this.stateId + ": Recording " + str + " request " + interactiveRequestRecord.getRequestId());
        this.requests.add(interactiveRequestRecord);
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveState
    public synchronized void processPendingResponses(RequestContext requestContext) {
        if (shouldAttemptProcessingResponses()) {
            doProcessPendingResponses(requestContext);
        } else {
            String str = LOG_TAG;
            MAPLog.d(str, "InteractiveState " + this.stateId + ": No responses to process");
        }
    }

    public void readFromBundle(Bundle bundle) {
        Bundle bundle2;
        if (bundle == null || (bundle2 = bundle.getBundle(SAVED_INSTANCE_STATE_KEY)) == null) {
            return;
        }
        MAPLog.d(LOG_TAG, "Restoring interactive state from saved instance state");
        String string = bundle2.getString(INTERACTIVE_STATE_ID_KEY);
        if (string == null) {
            MAPLog.w(LOG_TAG, "Restoring interactive state from instance state but no state ID found");
        } else {
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Reassigning interactive state ");
            outline107.append(this.stateId);
            outline107.append(" to ");
            outline107.append(string);
            MAPLog.d(str, outline107.toString());
            this.stateId = UUID.fromString(string);
        }
        ArrayList parcelableArrayList = bundle2.getParcelableArrayList(REQUEST_LIST_KEY);
        if (parcelableArrayList == null) {
            return;
        }
        this.requests.addAll(parcelableArrayList);
    }

    public boolean shouldAttemptProcessingResponses() {
        return (this.requests.size() > 0) && (this.responseManager.size() > 0);
    }

    public void writeToBundle(Bundle bundle) {
        if (this.requests.size() > 0) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(INTERACTIVE_STATE_ID_KEY, this.stateId.toString());
            bundle2.putParcelableArrayList(REQUEST_LIST_KEY, new ArrayList<>(this.requests));
            bundle.putBundle(SAVED_INSTANCE_STATE_KEY, bundle2);
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InteractiveState ");
            outline107.append(this.stateId);
            outline107.append(": writing to save instance state");
            MAPLog.d(str, outline107.toString());
        }
    }

    GenericInteractiveState(InteractiveStateFragment interactiveStateFragment, ResponseManager responseManager, InteractiveRequestMap interactiveRequestMap) {
        this.backingFragment = new WeakReference<>(interactiveStateFragment);
        this.responseManager = responseManager;
        this.interactiveRequestMap = interactiveRequestMap;
        this.requests = new HashSet();
        this.stateId = UUID.randomUUID();
    }
}
