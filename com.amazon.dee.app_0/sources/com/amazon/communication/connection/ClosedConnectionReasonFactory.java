package com.amazon.communication.connection;

import com.amazon.communication.websocket.CloseStatusCodes;
import com.dp.utils.FailFast;
/* loaded from: classes12.dex */
public class ClosedConnectionReasonFactory {
    public static String getReasonForStatusCode(int i) {
        String str;
        switch (i) {
            case CloseStatusCodes.AUTHENTICATION_FAILED /* 4001 */:
                str = "Connection closed for authentication failure. Please make sure you have registered an valid accout.";
                break;
            case CloseStatusCodes.CLIENT_ERROR /* 4002 */:
                str = "Connection is closed on device side.";
                break;
            case CloseStatusCodes.EXTRA_DATA_RECEIVED /* 4003 */:
                str = "Extra data is read on the websocket connection.";
                break;
            case CloseStatusCodes.EOF_ERROR /* 4004 */:
            case CloseStatusCodes.IO_ERROR /* 4007 */:
                str = "IO Problem in the connection.";
                break;
            case CloseStatusCodes.INCORRECT_FRAME_HEADER /* 4005 */:
                str = "Websocket frame header incorrect.";
                break;
            case CloseStatusCodes.PINGPONG_FAILURE /* 4006 */:
                str = "Websocket PingPong handle failure";
                break;
            case CloseStatusCodes.SERVER_COMMUNICATION_ERROR /* 4008 */:
                str = "Server side closed this connection for communication error.";
                break;
            case CloseStatusCodes.SERVER_RECEIVES_NEW_CONNECTION /* 4009 */:
                str = "Server side closed this connection because it detects a new connection to this device.";
                break;
            case CloseStatusCodes.SERVER_INITIATED_CLOSE /* 4010 */:
                str = "Server side closed this connection. No error happened.";
                break;
            default:
                str = "Unkown reason.";
                break;
        }
        FailFast.expectTrue(true);
        return str;
    }
}
