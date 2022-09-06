package com.amazon.identity.auth.device.interactive;

import android.content.Context;
import android.net.Uri;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.identity.auth.device.interactive.InteractiveListener;
import com.amazon.identity.auth.device.workflow.WorkflowCancellation;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes12.dex */
public final class AggregateInteractiveListener<T extends InteractiveListener<S, U, V>, S, U, V> implements InteractiveListener<S, U, V> {
    private Set<T> listeners;
    private String requestType;

    public AggregateInteractiveListener(String str, Set<T> set) {
        this.requestType = str;
        this.listeners = set == null ? Collections.emptySet() : set;
        for (T t : this.listeners) {
            if (!str.equals(t.getRequestType())) {
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("AggregateInteractiveListener created for request type \"", str, "\" but received listener with request type \"");
                outline115.append(t.getRequestType());
                outline115.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                throw new IllegalStateException(outline115.toString());
            }
        }
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveAPI
    public String getRequestType() {
        return this.requestType;
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveListener, com.amazon.identity.auth.device.api.CancellableListener
    public void onCancel(U u) {
        for (T t : this.listeners) {
            t.onCancel(u);
        }
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveListener, com.amazon.identity.auth.device.api.CancellableListener, com.amazon.identity.auth.device.api.Listener
    public void onError(V v) {
        for (T t : this.listeners) {
            t.onError(v);
        }
    }

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public void onRequestCancel(Context context, InteractiveRequestRecord interactiveRequestRecord, WorkflowCancellation workflowCancellation) {
        for (T t : this.listeners) {
            t.onRequestCancel(context, interactiveRequestRecord, workflowCancellation);
        }
    }

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public void onRequestCompletion(Context context, InteractiveRequestRecord interactiveRequestRecord, Uri uri) {
        for (T t : this.listeners) {
            t.onRequestCompletion(context, interactiveRequestRecord, uri);
        }
    }

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public void onRequestError(Context context, InteractiveRequestRecord interactiveRequestRecord, Exception exc) {
        for (T t : this.listeners) {
            t.onRequestError(context, interactiveRequestRecord, exc);
        }
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveListener, com.amazon.identity.auth.device.api.CancellableListener, com.amazon.identity.auth.device.api.Listener
    public void onSuccess(S s) {
        for (T t : this.listeners) {
            t.onSuccess(s);
        }
    }
}
