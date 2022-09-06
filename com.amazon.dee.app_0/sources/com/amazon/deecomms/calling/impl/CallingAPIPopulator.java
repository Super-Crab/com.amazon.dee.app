package com.amazon.deecomms.calling.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.calling.service.Call;
import com.amazon.deecomms.calling.api.CallInfo;
import com.amazon.deecomms.calling.api.CallTarget;
import com.amazon.deecomms.calling.api.Caller;
import com.amazon.deecomms.calling.api.ContactCallTarget;
import com.amazon.deecomms.calling.api.GroupCallTarget;
import com.amazon.deecomms.calling.api.enums.CallDirection;
import com.amazon.deecomms.calling.api.enums.CallMediaStream;
import com.amazon.deecomms.calling.api.enums.CallState;
import com.amazon.deecomms.calling.api.enums.ContactIdentifierType;
import com.amazon.deecomms.calling.api.enums.GroupIdentifierType;
import com.amazon.deecomms.calling.model.CallContext;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CallingAPIPopulator {
    @NonNull
    private final CallContext callContext;

    /* renamed from: com.amazon.deecomms.calling.impl.CallingAPIPopulator$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$comms$calling$service$Call$State = new int[Call.State.values().length];

        static {
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$State[Call.State.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$State[Call.State.Created.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$State[Call.State.Ringing.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$State[Call.State.Complete.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$State[Call.State.OnLocalHold.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$comms$calling$service$Call$State[Call.State.OnRemoteHold.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Inject
    public CallingAPIPopulator(@NonNull CallContext callContext) {
        this.callContext = callContext;
    }

    @NonNull
    CallState calculateCallState(@NonNull Call call, @NonNull CallDirection callDirection) {
        CallState callState = CallState.INACTIVE;
        int ordinal = call.getState().ordinal();
        if (ordinal == 0) {
            return callDirection == CallDirection.Outgoing ? CallState.CALLING : CallState.INBOUND_RECEIVED;
        } else if (ordinal == 1) {
            return callDirection == CallDirection.Outgoing ? CallState.OUTBOUND_RINGING : CallState.INBOUND_RINGING;
        } else if (ordinal == 2) {
            return CallState.ACTIVE;
        } else {
            return (ordinal == 3 || ordinal == 4 || ordinal != 5) ? callState : CallState.INACTIVE;
        }
    }

    @NonNull
    CallTarget calculateCallTarget(@NonNull Call call, @NonNull CallDirection callDirection) {
        if (this.callContext.isGroupCall()) {
            return new GroupCallTarget(GroupIdentifierType.Groupid, call.getRemoteParticipant().getProviderSpecifiedId(), call.isDropInCall());
        }
        if (callDirection == CallDirection.Outgoing) {
            return new ContactCallTarget(ContactIdentifierType.Commsid, call.getRemoteParticipant().getProviderSpecifiedId(), call.isDropInCall());
        }
        return new ContactCallTarget(ContactIdentifierType.Commsid, call.getLocalParticipant().getProviderSpecifiedId(), call.isDropInCall());
    }

    @NonNull
    Caller calculateCaller(@NonNull Call call, @NonNull CallDirection callDirection) {
        if (callDirection == CallDirection.Outgoing) {
            return new Caller(ContactIdentifierType.Commsid, call.getLocalParticipant().getProviderSpecifiedId(), null);
        }
        return new Caller(ContactIdentifierType.Commsid, call.getRemoteParticipant().getProviderSpecifiedId(), null);
    }

    @NonNull
    CallMediaStream[] calculateSupportedMediaStreams(@NonNull Call call) {
        return call.getMediaStatus().isLocalVideoCapable() ? new CallMediaStream[]{CallMediaStream.Audio, CallMediaStream.Video} : new CallMediaStream[]{CallMediaStream.Audio};
    }

    @Nullable
    public CallInfo getCurrentCallInfo() {
        Call currentCall = this.callContext.getCurrentCall();
        if (currentCall == null) {
            return null;
        }
        CallDirection callDirection = currentCall.getOrigin() == Call.Side.Local ? CallDirection.Outgoing : CallDirection.Incoming;
        return new CallInfo(currentCall.getCallId(), calculateCaller(currentCall, callDirection), calculateCallTarget(currentCall, callDirection), calculateCallState(currentCall, callDirection), callDirection, calculateSupportedMediaStreams(currentCall));
    }
}
