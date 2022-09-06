package com.amazon.deecomms.calling.commsbridgehandlers;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.ObjectMapper;
/* loaded from: classes12.dex */
public final class CallingBridgeHandlerRegistry {
    public static final String CALL_ANSWER = "calling::call::answer";
    public static final String CALL_END = "calling::call::terminate";
    public static final String CALL_INITIATE = "calling::call::initiate";
    public static final String CALL_REJECT = "calling::call::reject";
    public static final String GET_CALLHISTORY = "calling::history::get";
    public static final String GET_CALLINFORMATION = "calling::call::getInfo";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallingBridgeHandlerRegistry.class);

    private CallingBridgeHandlerRegistry() {
    }

    public static void register() {
        CommsBridgeService commsBridgeService = (CommsBridgeService) GeneratedOutlineSupport1.outline20(CommsBridgeService.class);
        ICallingAPI iCallingAPI = (ICallingAPI) GeneratedOutlineSupport1.outline20(ICallingAPI.class);
        registerIfNeeded(commsBridgeService, CALL_INITIATE, new InitiateCallHandler(iCallingAPI, new ObjectMapper()));
        registerIfNeeded(commsBridgeService, CALL_ANSWER, new AnswerCallHandler(iCallingAPI));
        registerIfNeeded(commsBridgeService, CALL_REJECT, new RejectCallHandler(iCallingAPI));
        registerIfNeeded(commsBridgeService, CALL_END, new EndCallHandler(iCallingAPI));
        registerIfNeeded(commsBridgeService, GET_CALLINFORMATION, new GetCallInformationHandler(iCallingAPI));
        registerIfNeeded(commsBridgeService, GET_CALLHISTORY, new GetCallHistoryHandler(iCallingAPI));
    }

    private static void registerIfNeeded(@NonNull CommsBridgeService commsBridgeService, @NonNull String str, @NonNull RequestHandler<String> requestHandler) {
        if (!commsBridgeService.isRequestHandlerRegistered(str)) {
            commsBridgeService.registerRequestHandler(str, requestHandler);
            return;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.i("Handler for event " + str + " is already registered.");
    }
}
