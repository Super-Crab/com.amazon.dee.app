package com.amazon.identity.auth.device.interactive;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
@SuppressLint({"NewApi"})
/* loaded from: classes12.dex */
public final class WorkflowFragment extends Fragment implements InteractiveStateFragment {
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

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.state.readFromBundle(bundle);
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        this.state.writeToBundle(bundle);
        super.onSaveInstanceState(bundle);
    }

    void setState(GenericInteractiveState genericInteractiveState) {
        this.state = genericInteractiveState;
    }
}
