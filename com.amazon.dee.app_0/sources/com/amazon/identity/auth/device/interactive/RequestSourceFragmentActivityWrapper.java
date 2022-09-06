package com.amazon.identity.auth.device.interactive;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
/* loaded from: classes12.dex */
public final class RequestSourceFragmentActivityWrapper implements RequestSource {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.interactive.RequestSourceFragmentActivityWrapper";
    private final WeakReference<FragmentActivity> activityReference;

    public RequestSourceFragmentActivityWrapper(FragmentActivity fragmentActivity) {
        if (fragmentActivity != null) {
            this.activityReference = new WeakReference<>(fragmentActivity);
            return;
        }
        throw new IllegalArgumentException("activity must be non-null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RequestSourceFragmentActivityWrapper.class != obj.getClass()) {
            return false;
        }
        RequestSourceFragmentActivityWrapper requestSourceFragmentActivityWrapper = (RequestSourceFragmentActivityWrapper) obj;
        WeakReference<FragmentActivity> weakReference = this.activityReference;
        if (weakReference == null) {
            if (requestSourceFragmentActivityWrapper.activityReference != null) {
                return false;
            }
        } else if (requestSourceFragmentActivityWrapper.activityReference == null) {
            return false;
        } else {
            if (weakReference.get() == null) {
                if (requestSourceFragmentActivityWrapper.activityReference.get() != null) {
                    return false;
                }
            } else if (!this.activityReference.get().equals(requestSourceFragmentActivityWrapper.activityReference.get())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Object getBackingObject() {
        return this.activityReference.get();
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Context getContext() {
        return this.activityReference.get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.amazon.identity.auth.device.interactive.InteractiveStateFragment] */
    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public InteractiveState getInteractiveState() {
        FragmentActivity fragmentActivity = this.activityReference.get();
        if (fragmentActivity != null) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            try {
                InteractiveStateFragment interactiveStateFragment = (InteractiveStateFragment) supportFragmentManager.findFragmentByTag(InteractiveStateFragment.TAG_ID);
                WorkflowSupportFragment workflowSupportFragment = interactiveStateFragment;
                if (interactiveStateFragment == null) {
                    WorkflowSupportFragment workflowSupportFragment2 = new WorkflowSupportFragment();
                    supportFragmentManager.beginTransaction().add(workflowSupportFragment2, InteractiveStateFragment.TAG_ID).commit();
                    workflowSupportFragment = workflowSupportFragment2;
                }
                return workflowSupportFragment.getState();
            } catch (ClassCastException e) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Found an invalid fragment looking for fragment with tag ");
                outline107.append(InteractiveStateFragment.TAG_ID);
                outline107.append(". Please use a different fragment tag.");
                MAPLog.e(str, outline107.toString(), e);
                return null;
            }
        }
        MAPLog.e(LOG_TAG, "Failed to get InteractiveState on a garbage-collected FragmentActivity");
        return null;
    }

    public int hashCode() {
        WeakReference<FragmentActivity> weakReference = this.activityReference;
        return 31 + ((weakReference == null || weakReference.get() == null) ? 0 : this.activityReference.get().hashCode());
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public void onStartRequest(InteractiveRequestRecord interactiveRequestRecord) {
        InteractiveState interactiveState = getInteractiveState();
        if (interactiveState != null) {
            interactiveState.onRequestStart(interactiveRequestRecord);
        }
    }
}
