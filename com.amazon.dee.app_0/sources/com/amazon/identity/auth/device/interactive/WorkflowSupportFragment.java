package com.amazon.identity.auth.device.interactive;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
/* loaded from: classes12.dex */
public final class WorkflowSupportFragment extends Fragment implements InteractiveStateFragment {
    private GenericInteractiveState state = new GenericInteractiveState(this);

    @Override // com.amazon.identity.auth.device.interactive.InteractiveStateFragment
    public Object getFragment(Bundle bundle) {
        return getFragmentManager().getFragment(bundle, InteractiveState.FRAGMENT_WRAPPER_KEY);
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveStateFragment
    public Object getParentActivity() {
        return getActivity();
    }

    @Override // com.amazon.identity.auth.device.interactive.InteractiveStateFragment
    public InteractiveState getState() {
        return this.state;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.state.readFromBundle(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        this.state.writeToBundle(bundle);
        super.onSaveInstanceState(bundle);
    }

    void setState(GenericInteractiveState genericInteractiveState) {
        this.state = genericInteractiveState;
    }
}
