package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.Accumulator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public abstract class EventAccumulator<T extends Event> implements Accumulator<T, WJWorkflowStateStore> {
    private static final String TAG = "EventAccumulator";

    /* renamed from: com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State = new int[Event.State.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.UPDATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.SUCCESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$deviceprovisioningservice$workflow$state$Event$State[Event.State.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class UnhandledEventState extends RuntimeException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public UnhandledEventState(com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event.State r2) {
            /*
                r1 = this;
                java.lang.String r0 = "Unhandled State: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
                java.lang.String r2 = r2.name()
                r0.append(r2)
                java.lang.String r2 = r0.toString()
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.deviceevent.EventAccumulator.UnhandledEventState.<init>(com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.Event$State):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isWorkflowActive(WJWorkflowStateStore wJWorkflowStateStore) {
        WJResult lastWJResult = wJWorkflowStateStore.getLastWJResult();
        boolean z = lastWJResult == null || !lastWJResult.isA(WJResult.WorkflowIdle.class);
        if (!z) {
            WJLog.d(TAG, "Workflow is not active");
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.Accumulator
    /* renamed from: accumulate  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ WJWorkflowStateStore mo5716accumulate(Event event, WJWorkflowStateStore wJWorkflowStateStore) {
        return mo5716accumulate((EventAccumulator<T>) event, wJWorkflowStateStore);
    }

    protected WJWorkflowStateStore error(T t, WJWorkflowStateStore wJWorkflowStateStore) {
        throw new UnhandledEventState(Event.State.ERROR);
    }

    protected WJWorkflowStateStore idle(T t, WJWorkflowStateStore wJWorkflowStateStore) {
        throw new UnhandledEventState(Event.State.IDLE);
    }

    protected WJWorkflowStateStore inProgress(T t, WJWorkflowStateStore wJWorkflowStateStore) {
        throw new UnhandledEventState(Event.State.IN_PROGRESS);
    }

    public boolean sessionExists(WJProvisionee wJProvisionee, WJWorkflowStateStore wJWorkflowStateStore) {
        boolean z = wJWorkflowStateStore.getSession(wJProvisionee) != null;
        if (!z) {
            WJLog.d(TAG, "Session Doesn't Exist");
        }
        return z;
    }

    protected WJWorkflowStateStore success(T t, WJWorkflowStateStore wJWorkflowStateStore) {
        throw new UnhandledEventState(Event.State.SUCCESS);
    }

    protected WJWorkflowStateStore update(T t, WJWorkflowStateStore wJWorkflowStateStore) {
        throw new UnhandledEventState(Event.State.UPDATE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.accumulator.Accumulator
    /* renamed from: accumulate */
    public WJWorkflowStateStore mo5716accumulate(T t, WJWorkflowStateStore wJWorkflowStateStore) {
        int ordinal = t.state.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return inProgress(t, wJWorkflowStateStore);
            }
            if (ordinal == 2) {
                return update(t, wJWorkflowStateStore);
            }
            if (ordinal == 3) {
                return success(t, wJWorkflowStateStore);
            }
            if (ordinal == 4) {
                return error(t, wJWorkflowStateStore);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unrecognized State: ");
            outline107.append(t.state);
            throw new IllegalStateException(outline107.toString());
        }
        return idle(t, wJWorkflowStateStore);
    }
}
