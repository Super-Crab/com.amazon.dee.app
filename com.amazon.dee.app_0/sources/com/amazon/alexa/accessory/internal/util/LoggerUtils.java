package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class LoggerUtils {
    private static final String NULL_IN_STRING = "null";

    /* renamed from: com.amazon.alexa.accessory.internal.util.LoggerUtils$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$StateOuterClass$State$ValueCase = new int[StateOuterClass.State.ValueCase.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$StateOuterClass$State$ValueCase[StateOuterClass.State.ValueCase.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$StateOuterClass$State$ValueCase[StateOuterClass.State.ValueCase.INTEGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$StateOuterClass$State$ValueCase[StateOuterClass.State.ValueCase.VALUE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private LoggerUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static void received(Accessories.Command command, Accessories.Response response) {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            Logger.d("Received response message: command=%s, response=%s", command, response);
        } else {
            Logger.e("Received error response message: command=%s, response=%s", command, response);
        }
    }

    public static void send(ControlMessage controlMessage) {
        Logger.d("Dispatching control message: " + controlMessage);
    }

    public static String stateToString(StateOuterClass.State state) {
        if (state == null) {
            return "null";
        }
        int feature = state.getFeature();
        int ordinal = state.getValueCase().ordinal();
        if (ordinal == 0) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("{feature:", feature, ",boolean:");
            outline109.append(state.getBoolean());
            outline109.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            return outline109.toString();
        } else if (ordinal != 1) {
            return GeneratedOutlineSupport1.outline52("{feature:", feature, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        } else {
            StringBuilder outline1092 = GeneratedOutlineSupport1.outline109("{feature:", feature, ",int:");
            outline1092.append(state.getInteger());
            outline1092.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            return outline1092.toString();
        }
    }

    public static void taskState(TaskManager.Task task, String str, String str2, int i) {
        Logger.d("'%s' (priority: %d) changed from state '%s' to state '%s'.", task.toString(), Integer.valueOf(i), str, str2);
    }

    public static String userToString(System.User user) {
        if (user == null) {
            return "null";
        }
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN, "id:");
        outline112.append(user.getId());
        outline112.append(",name:");
        outline112.append(user.getName());
        outline112.append(",address:");
        outline112.append(user.getAddress());
        outline112.append(",self:");
        outline112.append(user.getSelf());
        outline112.append(",connectedAudioTypes:[");
        int connectedAudioTypesCount = user.getConnectedAudioTypesCount();
        List<System.User.AudioConnectionType> connectedAudioTypesList = user.getConnectedAudioTypesList();
        if (connectedAudioTypesCount > 0) {
            outline112.append(connectedAudioTypesList.get(0).toString());
            if (connectedAudioTypesCount > 1) {
                for (int i = 1; i < connectedAudioTypesCount; i++) {
                    outline112.append(",");
                    outline112.append(connectedAudioTypesList.get(i).toString());
                }
            }
        }
        outline112.append("]");
        outline112.append(",primaryAudioConnectionType:");
        outline112.append(user.getPrimaryAudioConnectionType().toString());
        outline112.append(",connected:");
        outline112.append(user.getConnected());
        outline112.append(",focus:");
        outline112.append(user.getFocus().toString());
        outline112.append(",volume:");
        outline112.append(user.getVolume());
        outline112.append(",aapConnected:");
        outline112.append(user.getAapConnected());
        outline112.append(",lastKnownAapCapability:");
        outline112.append(user.getLastKnownAapCapability().toString());
        outline112.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline112.toString();
    }

    public static String usersToString(System.Users users) {
        if (users == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("[");
        Iterator<System.User> it2 = users.getUsersList().iterator();
        if (it2.hasNext()) {
            sb.append(userToString(it2.next()));
        }
        while (it2.hasNext()) {
            sb.append(",");
            sb.append(userToString(it2.next()));
        }
        sb.append("]");
        return sb.toString();
    }

    public static void received(ControlMessage controlMessage) {
        Logger.d("Received control message: " + controlMessage);
    }
}
