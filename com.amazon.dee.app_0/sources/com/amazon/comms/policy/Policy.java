package com.amazon.comms.policy;
/* loaded from: classes11.dex */
public interface Policy {

    /* loaded from: classes11.dex */
    public interface Component {
        String getName();

        /* renamed from: getState */
        ComponentState mo3243getState();
    }

    /* loaded from: classes11.dex */
    public interface ComponentState {
    }

    /* loaded from: classes11.dex */
    public interface InputComponent extends Component {
        void setState(ComponentState componentState);
    }

    /* loaded from: classes11.dex */
    public interface OutputComponent extends Component {
    }

    /* loaded from: classes11.dex */
    public interface OutputComponentsStateChangeListener {
        void onOutputComponentsUpdated();
    }

    void registerListener(OutputComponentsStateChangeListener outputComponentsStateChangeListener);

    void reset();

    void unregisterListener(OutputComponentsStateChangeListener outputComponentsStateChangeListener);
}
