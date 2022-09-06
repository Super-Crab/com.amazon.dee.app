package com.amazon.comms.policy;

import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.policy.Policy;
import com.amazon.comms.util.ListenerSet;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public abstract class CallingServiceRuntimePolicy implements Policy {
    private static final CommsLogger log = CommsLogger.getLogger(CallingServiceRuntimePolicy.class);
    private CSOutputComponent deviceCallingAndroidServiceComponent = new CSOutputComponent("DeviceCallingAndroidServiceComponent");
    private CSInputComponent callComponent = new CSInputComponent("CallComponent");
    private CSInputComponent showModeComponent = new CSInputComponent("ShowModeComponent");
    private final ListenerSet<Policy.OutputComponentsStateChangeListener> compStateChangeListeners = new ListenerSet<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public abstract class BaseComponent implements Policy.Component {
        private String name;
        protected RuntimeState state;

        public BaseComponent(String str) {
            this.name = str;
        }

        @Override // com.amazon.comms.policy.Policy.Component
        public String getName() {
            return this.name;
        }

        @Override // com.amazon.comms.policy.Policy.Component
        /* renamed from: getState */
        public RuntimeState mo3243getState() {
            return this.state;
        }
    }

    /* loaded from: classes11.dex */
    public class CSInputComponent extends BaseComponent implements Policy.InputComponent {
        CSInputComponent(String str) {
            super(str);
        }

        @Override // com.amazon.comms.policy.CallingServiceRuntimePolicy.BaseComponent, com.amazon.comms.policy.Policy.Component
        public /* bridge */ /* synthetic */ String getName() {
            return super.getName();
        }

        @Override // com.amazon.comms.policy.CallingServiceRuntimePolicy.BaseComponent, com.amazon.comms.policy.Policy.Component
        /* renamed from: getState */
        public /* bridge */ /* synthetic */ RuntimeState mo3243getState() {
            return super.mo3243getState();
        }

        @Override // com.amazon.comms.policy.Policy.InputComponent
        public void setState(Policy.ComponentState componentState) {
            if (!(componentState instanceof RuntimeState)) {
                CallingServiceRuntimePolicy.log.e("Not a RuntimeState object");
                return;
            }
            RuntimeState runtimeState = this.state;
            setStateInternal((RuntimeState) componentState);
            CallingServiceRuntimePolicy.this.inputComponentUpdated(this, runtimeState);
        }

        void setStateInternal(RuntimeState runtimeState) {
            this.state = runtimeState;
        }
    }

    /* loaded from: classes11.dex */
    public class CSOutputComponent extends BaseComponent implements Policy.OutputComponent {
        CSOutputComponent(String str) {
            super(str);
        }

        @Override // com.amazon.comms.policy.CallingServiceRuntimePolicy.BaseComponent, com.amazon.comms.policy.Policy.Component
        public /* bridge */ /* synthetic */ String getName() {
            return super.getName();
        }

        @Override // com.amazon.comms.policy.CallingServiceRuntimePolicy.BaseComponent, com.amazon.comms.policy.Policy.Component
        /* renamed from: getState */
        public /* bridge */ /* synthetic */ RuntimeState mo3243getState() {
            return super.mo3243getState();
        }

        public void setState(RuntimeState runtimeState) {
            CommsLogger commsLogger = CallingServiceRuntimePolicy.log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Component:");
            outline107.append(getName());
            outline107.append(" set to:");
            outline107.append(runtimeState);
            commsLogger.i(outline107.toString());
            this.state = runtimeState;
        }
    }

    /* loaded from: classes11.dex */
    public enum RuntimeState implements Policy.ComponentState {
        Disable,
        Standby,
        Enable
    }

    protected CallingServiceRuntimePolicy() {
    }

    public CSInputComponent getCallComponent() {
        return this.callComponent;
    }

    public CSOutputComponent getDeviceCallingAndroidServiceComponent() {
        return this.deviceCallingAndroidServiceComponent;
    }

    public CSInputComponent getShowModeComponent() {
        return this.showModeComponent;
    }

    public abstract void inputComponentUpdated(CSInputComponent cSInputComponent, RuntimeState runtimeState);

    protected void notifyListeners() {
        this.compStateChangeListeners.notify(new ListenerSet.Notifier<Policy.OutputComponentsStateChangeListener>() { // from class: com.amazon.comms.policy.CallingServiceRuntimePolicy.1
            @Override // com.amazon.comms.util.ListenerSet.Notifier
            public void notify(Policy.OutputComponentsStateChangeListener outputComponentsStateChangeListener) {
                outputComponentsStateChangeListener.onOutputComponentsUpdated();
            }
        });
    }

    @Override // com.amazon.comms.policy.Policy
    public void registerListener(Policy.OutputComponentsStateChangeListener outputComponentsStateChangeListener) {
        this.compStateChangeListeners.add(outputComponentsStateChangeListener);
    }

    @Override // com.amazon.comms.policy.Policy
    public void reset() {
        this.compStateChangeListeners.clear();
        setAllOutputComponents(RuntimeState.Disable);
        setAllInputComponents(RuntimeState.Disable);
    }

    protected void setAllInputComponents(RuntimeState runtimeState) {
        this.callComponent.setStateInternal(runtimeState);
        this.showModeComponent.setStateInternal(runtimeState);
    }

    protected void setAllOutputComponents(RuntimeState runtimeState) {
        this.deviceCallingAndroidServiceComponent.setState(runtimeState);
    }

    @Override // com.amazon.comms.policy.Policy
    public void unregisterListener(Policy.OutputComponentsStateChangeListener outputComponentsStateChangeListener) {
        this.compStateChangeListeners.remove(outputComponentsStateChangeListener);
    }
}
