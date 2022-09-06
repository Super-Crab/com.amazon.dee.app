package com.amazon.identity.auth.device.interactive;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
@SuppressLint({"NewApi"})
/* loaded from: classes12.dex */
public final class RequestSourceFragmentWrapper implements RequestSource {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.interactive.RequestSourceFragmentWrapper";
    private final WeakReference<Fragment> fragmentReference;

    public RequestSourceFragmentWrapper(Fragment fragment) {
        if (fragment != null) {
            this.fragmentReference = new WeakReference<>(fragment);
            return;
        }
        throw new IllegalArgumentException("fragment must be non-null");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.amazon.identity.auth.device.interactive.InteractiveStateFragment] */
    private InteractiveState getInteractiveStateAddingRequest(InteractiveRequestRecord interactiveRequestRecord) {
        Fragment fragment = this.fragmentReference.get();
        if (fragment != null) {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            try {
                InteractiveStateFragment interactiveStateFragment = (InteractiveStateFragment) fragmentManager.findFragmentByTag(InteractiveStateFragment.TAG_ID);
                WorkflowFragment workflowFragment = interactiveStateFragment;
                if (interactiveStateFragment == null) {
                    WorkflowFragment workflowFragment2 = new WorkflowFragment();
                    fragmentManager.beginTransaction().add(workflowFragment2, InteractiveStateFragment.TAG_ID).commit();
                    workflowFragment = workflowFragment2;
                }
                if (interactiveRequestRecord != null) {
                    Bundle bundle = new Bundle();
                    fragmentManager.putFragment(bundle, InteractiveState.FRAGMENT_WRAPPER_KEY, fragment);
                    interactiveRequestRecord.setFragmentWrapper(bundle);
                    workflowFragment.getState().onRequestStart(interactiveRequestRecord);
                }
                return workflowFragment.getState();
            } catch (ClassCastException e) {
                String str = LOG_TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Found an invalid fragment looking for fragment with tag ");
                outline107.append(InteractiveStateFragment.TAG_ID);
                outline107.append(". Please use a different fragment tag.");
                MAPLog.e(str, outline107.toString(), e);
                return null;
            }
        }
        MAPLog.e(LOG_TAG, "Failed to get InteractiveState for a garbage-collected Fragment");
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RequestSourceFragmentWrapper.class != obj.getClass()) {
            return false;
        }
        RequestSourceFragmentWrapper requestSourceFragmentWrapper = (RequestSourceFragmentWrapper) obj;
        WeakReference<Fragment> weakReference = this.fragmentReference;
        if (weakReference == null) {
            if (requestSourceFragmentWrapper.fragmentReference != null) {
                return false;
            }
        } else if (requestSourceFragmentWrapper.fragmentReference == null) {
            return false;
        } else {
            if (weakReference.get() == null) {
                if (requestSourceFragmentWrapper.fragmentReference.get() != null) {
                    return false;
                }
            } else if (!this.fragmentReference.get().equals(requestSourceFragmentWrapper.fragmentReference.get())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Object getBackingObject() {
        return this.fragmentReference.get();
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public Context getContext() {
        return this.fragmentReference.get().getActivity();
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public InteractiveState getInteractiveState() {
        return getInteractiveStateAddingRequest(null);
    }

    public int hashCode() {
        WeakReference<Fragment> weakReference = this.fragmentReference;
        return 31 + ((weakReference == null || weakReference.get() == null) ? 0 : this.fragmentReference.get().hashCode());
    }

    @Override // com.amazon.identity.auth.device.interactive.RequestSource
    public void onStartRequest(InteractiveRequestRecord interactiveRequestRecord) {
        getInteractiveStateAddingRequest(interactiveRequestRecord);
    }
}
