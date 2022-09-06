package com.amazon.matter.handler;

import chip.setuppayload.SetupPayload;
import chip.setuppayload.SetupPayloadParser;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.matter.data.MatterError;
import com.amazon.matter.data.MatterErrorType;
import com.amazon.matter.data.QrCodeParserRequest;
import com.amazon.matter.data.QrCodeParserResponse;
import com.amazon.matter.eventbus.EventBusHelper;
import com.amazon.matter.eventbus.MatterEventType;
import com.amazon.matter.metrics.MatterMetricsService;
import com.amazon.matter.setuppayloadparser.QRSetupPayloadParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
/* loaded from: classes9.dex */
public class QrCodeParserRequestHandler implements RequestHandler {
    private static final Gson GSON = new Gson();
    private final EventBusHelper eventBusHelper;
    private final MatterMetricsService metricsService;
    private final SetupPayloadParser setupPayloadParser;

    public QrCodeParserRequestHandler(EventBusHelper eventBusHelper, SetupPayloadParser setupPayloadParser, MatterMetricsService matterMetricsService) {
        this.eventBusHelper = eventBusHelper;
        this.setupPayloadParser = setupPayloadParser;
        this.metricsService = matterMetricsService;
    }

    @Override // com.amazon.matter.handler.RequestHandler
    public void processMessage(Message message) {
        this.metricsService.recordRequestMetric(MatterEventType.QR_CODE_PARSER_REQUEST);
        MobilyticsMetricsTimer createAndStartTimer = this.metricsService.createAndStartTimer(MatterEventType.QR_CODE_PARSER_REQUEST);
        try {
            try {
                SetupPayload parseQRCode = new QRSetupPayloadParser(this.setupPayloadParser).parseQRCode(((QrCodeParserRequest) GSON.fromJson(message.getPayloadAsString(), (Class<Object>) QrCodeParserRequest.class)).getQrCode());
                this.metricsService.recordSuccessMetric(MatterEventType.QR_CODE_PARSER_RESPONSE_SUCCESS);
                this.eventBusHelper.sendMessageToEventBus(MatterEventType.QR_CODE_PARSER_RESPONSE_SUCCESS, GSON.toJson(new QrCodeParserResponse(parseQRCode)));
            } catch (SetupPayloadParser.UnrecognizedQrCodeException unused) {
                this.metricsService.recordErrorMetric(MatterEventType.QR_CODE_PARSER_REQUEST, MatterErrorType.INVALID_QR_CODE);
                this.eventBusHelper.sendMessageToEventBus(MatterEventType.QR_CODE_PARSER_RESPONSE_ERROR, GSON.toJson(new MatterError(MatterErrorType.INVALID_QR_CODE, "")));
            } catch (JsonSyntaxException unused2) {
                this.metricsService.recordErrorMetric(MatterEventType.QR_CODE_PARSER_REQUEST, MatterErrorType.INVALID_REQUEST);
                this.eventBusHelper.sendMessageToEventBus(MatterEventType.QR_CODE_PARSER_RESPONSE_ERROR, GSON.toJson(new MatterError(MatterErrorType.INVALID_REQUEST, "")));
            } catch (Exception unused3) {
                this.metricsService.recordErrorMetric(MatterEventType.QR_CODE_PARSER_REQUEST, MatterErrorType.UNKNOWN_ERROR);
                this.eventBusHelper.sendMessageToEventBus(MatterEventType.QR_CODE_PARSER_RESPONSE_ERROR, GSON.toJson(new MatterError(MatterErrorType.UNKNOWN_ERROR, "")));
            }
        } finally {
            this.metricsService.recordEventTime(createAndStartTimer);
        }
    }
}
