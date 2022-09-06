package com.amazon.identity.auth.device.api.workflow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.identity.auth.device.RequestManager;
import com.amazon.identity.auth.device.endpoint.ResponseUri;
import com.amazon.identity.auth.device.interactive.AggregateInteractiveListener;
import com.amazon.identity.auth.device.interactive.InteractiveAPI;
import com.amazon.identity.auth.device.interactive.InteractiveListener;
import com.amazon.identity.auth.device.interactive.InteractiveRequest;
import com.amazon.identity.auth.device.interactive.InteractiveRequestMap;
import com.amazon.identity.auth.device.interactive.InteractiveRequestRecord;
import com.amazon.identity.auth.device.interactive.InteractiveState;
import com.amazon.identity.auth.device.interactive.InternalInteractiveListener;
import com.amazon.identity.auth.device.interactive.RequestSource;
import com.amazon.identity.auth.device.interactive.RequestSourceActivityWrapper;
import com.amazon.identity.auth.device.interactive.RequestSourceFragmentActivityWrapper;
import com.amazon.identity.auth.device.interactive.RequestSourceFragmentWrapper;
import com.amazon.identity.auth.device.interactive.RequestSourceSupportFragmentWrapper;
import com.amazon.identity.auth.device.thread.ThreadUtils;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes12.dex */
public final class RequestContext {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.api.workflow.RequestContext";
    private final Map<String, Set<InteractiveListener<?, ?, ?>>> listeners;
    private final UUID requestContextId;
    private final RequestSource requestSource;

    RequestContext(RequestSource requestSource) {
        if (requestSource != null) {
            this.requestSource = requestSource;
            this.requestContextId = UUID.randomUUID();
            this.listeners = new HashMap();
            return;
        }
        throw new IllegalArgumentException("requestSource must be non-null");
    }

    public static RequestContext create(Activity activity) {
        return create(new RequestSourceActivityWrapper(activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> Set<T> getListenersInternal(String str, Class<T> cls) throws ListenerNotFoundException {
        Set<InteractiveListener<?, ?, ?>> set;
        if (str != null) {
            synchronized (this.listeners) {
                set = this.listeners.get(str);
            }
            if (set == null || set.isEmpty()) {
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("No listeners were registered with type \"", str, "\" for RequestContext ");
                outline115.append(this.requestContextId);
                outline115.append(". Listener types present: ");
                outline115.append(this.listeners.keySet());
                throw new ListenerNotFoundException(outline115.toString());
            } else if (cls == null) {
                return null;
            } else {
                HashSet hashSet = new HashSet(set.size());
                for (InteractiveListener<?, ?, ?> interactiveListener : set) {
                    try {
                        hashSet.add(cls.cast(interactiveListener));
                    } catch (ClassCastException e) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to retrieve listener of class type \"");
                        outline107.append(cls.toString());
                        outline107.append("\" for request type \"");
                        outline107.append(str);
                        outline107.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                        throw new ListenerNotFoundException(outline107.toString(), e);
                    }
                }
                return hashSet;
            }
        }
        throw new IllegalArgumentException("requestType must be non-null");
    }

    public void assertListenerPresent(InteractiveAPI interactiveAPI) throws ListenerNotFoundException {
        if (interactiveAPI != null) {
            getListenersInternal(interactiveAPI.getRequestType(), null);
            return;
        }
        throw new IllegalArgumentException("api must be non-null");
    }

    public <T extends InteractiveListener<S, U, V>, S, U, V> InteractiveListener<S, U, V> getAggregateListener(InteractiveRequest<T, S, U, V> interactiveRequest) throws ListenerNotFoundException {
        return new AggregateInteractiveListener(interactiveRequest.getRequestType(), getListeners(interactiveRequest, interactiveRequest.getListenerClass()));
    }

    public Context getContext() {
        return this.requestSource.getContext();
    }

    String getId() {
        return this.requestContextId.toString();
    }

    public <T> Set<T> getListeners(InteractiveAPI interactiveAPI, Class<T> cls) {
        if (interactiveAPI != null) {
            if (cls != null) {
                return getListenersInternal(interactiveAPI.getRequestType(), cls);
            }
            throw new IllegalArgumentException("listenerClass must be non-null");
        }
        throw new IllegalArgumentException("api must be non-null");
    }

    public void onResume() {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RequestContext ");
        outline107.append(this.requestContextId);
        outline107.append(": onResume");
        MAPLog.d(str, outline107.toString());
        InteractiveState interactiveState = this.requestSource.getInteractiveState();
        if (interactiveState != null) {
            interactiveState.processPendingResponses(this);
            return;
        }
        String str2 = LOG_TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("RequestContext ");
        outline1072.append(this.requestContextId);
        outline1072.append(": could not retrieve interactive state to process pending responses");
        MAPLog.e(str2, outline1072.toString());
    }

    public void onStartRequest(InteractiveRequestRecord interactiveRequestRecord) {
        if (interactiveRequestRecord != null) {
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RequestContext ");
            outline107.append(this.requestContextId);
            outline107.append(": onStartRequest for request ID ");
            outline107.append(interactiveRequestRecord.getRequestId());
            MAPLog.d(str, outline107.toString());
            this.requestSource.onStartRequest(interactiveRequestRecord);
            return;
        }
        throw new IllegalArgumentException("request must be non-null");
    }

    public void processResponse(final InteractiveRequestRecord interactiveRequestRecord, final Uri uri) {
        if (interactiveRequestRecord != null) {
            if (uri != null) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RequestContext ");
                outline107.append(this.requestContextId);
                outline107.append(": processing response");
                String sb = outline107.toString();
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("uri=");
                outline1072.append(uri.toString());
                MAPLog.pii(str, sb, outline1072.toString());
                final Context context = this.requestSource.getContext();
                ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.api.workflow.RequestContext.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (RequestManager.getInstance().handleResponse(uri, context, RequestContext.this)) {
                                return;
                            }
                            for (InternalInteractiveListener internalInteractiveListener : RequestContext.this.getListenersInternal(new ResponseUri(uri).getStateValues().get(LWAConstants.INTERACTIVE_REQUEST_TYPE_KEY), InternalInteractiveListener.class)) {
                                internalInteractiveListener.onRequestCompletion(context, interactiveRequestRecord, uri);
                            }
                        } catch (Exception e) {
                            String str2 = RequestContext.LOG_TAG;
                            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("RequestContext ");
                            outline1073.append(RequestContext.this.requestContextId);
                            outline1073.append(": Unable to handle activity result");
                            MAPLog.e(str2, outline1073.toString(), e);
                        }
                    }
                });
                return;
            }
            throw new IllegalArgumentException("uri must be non-null");
        }
        throw new IllegalArgumentException("request must be non-null");
    }

    public void registerListener(InteractiveListener<?, ?, ?> interactiveListener) {
        if (interactiveListener != null) {
            String requestType = interactiveListener.getRequestType();
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RequestContext ");
            outline107.append(this.requestContextId);
            outline107.append(": registerListener for of request type ");
            outline107.append(requestType);
            String sb = outline107.toString();
            MAPLog.pii(str, sb, "listener=" + interactiveListener);
            synchronized (this.listeners) {
                Set<InteractiveListener<?, ?, ?>> set = this.listeners.get(requestType);
                if (set == null) {
                    set = new HashSet<>();
                    this.listeners.put(requestType, set);
                }
                set.add(interactiveListener);
            }
            return;
        }
        throw new IllegalArgumentException("listener must be non-null");
    }

    public boolean unregisterListener(InteractiveListener<?, ?, ?> interactiveListener) {
        if (interactiveListener != null) {
            String requestType = interactiveListener.getRequestType();
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RequestContext ");
            outline107.append(this.requestContextId);
            outline107.append(": unregisterListener for listener of request type ");
            outline107.append(requestType);
            String sb = outline107.toString();
            MAPLog.pii(str, sb, "listener=" + interactiveListener);
            synchronized (this.listeners) {
                Set<InteractiveListener<?, ?, ?>> set = this.listeners.get(requestType);
                if (set == null) {
                    return false;
                }
                return set.remove(interactiveListener);
            }
        }
        throw new IllegalArgumentException("listener must be non-null");
    }

    @SuppressLint({"NewApi"})
    public static RequestContext create(Fragment fragment) {
        return create(new RequestSourceFragmentWrapper(fragment));
    }

    public static RequestContext create(FragmentActivity fragmentActivity) {
        return create(new RequestSourceFragmentActivityWrapper(fragmentActivity));
    }

    public static RequestContext create(androidx.fragment.app.Fragment fragment) {
        return create(new RequestSourceSupportFragmentWrapper(fragment));
    }

    private static RequestContext create(RequestSource requestSource) {
        Object backingObject = requestSource.getBackingObject();
        RequestContext requestContextForSource = InteractiveRequestMap.getInstance().getRequestContextForSource(backingObject);
        if (requestContextForSource == null) {
            RequestContext requestContext = new RequestContext(requestSource);
            InteractiveRequestMap.getInstance().putRequestContextForSource(backingObject, requestContext);
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Created RequestContext ");
            outline107.append(requestContext.requestContextId);
            String sb = outline107.toString();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("requestSource=");
            outline1072.append(requestSource.getBackingObject());
            MAPLog.pii(str, sb, outline1072.toString());
            return requestContext;
        }
        String str2 = LOG_TAG;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Reusing RequestContext ");
        outline1073.append(requestContextForSource.requestContextId);
        String sb2 = outline1073.toString();
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("requestSource=");
        outline1074.append(requestSource.getBackingObject());
        MAPLog.pii(str2, sb2, outline1074.toString());
        return requestContextForSource;
    }
}
