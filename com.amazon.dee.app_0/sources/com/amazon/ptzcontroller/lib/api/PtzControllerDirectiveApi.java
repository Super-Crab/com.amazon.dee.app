package com.amazon.ptzcontroller.lib.api;

import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.alexa.directive.Directive;
import com.amazon.alexa.header.Header;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.api.directive.AdjustRangeValuePayload;
import com.amazon.ptzcontroller.lib.api.interfaces.PanHandler;
import com.amazon.ptzcontroller.lib.api.interfaces.TiltHandler;
import com.amazon.ptzcontroller.lib.api.interfaces.ZoomHandler;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import com.amazon.ptzcontroller.lib.model.serialization.PtzSerializer;
import com.amazon.ptzcontroller.lib.model.serialization.type.PtzAdjustRangeValueDirective;
import com.amazon.ptzcontroller.lib.model.serialization.type.PtzDirective;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes13.dex */
public class PtzControllerDirectiveApi {
    private PanHandler panHandler;
    private TiltHandler tiltHandler;
    private ZoomHandler zoomHandler;

    /* renamed from: com.amazon.ptzcontroller.lib.api.PtzControllerDirectiveApi$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ptzcontroller$lib$model$api$ptz$directive$CameraPtzInstance = new int[CameraPtzInstance.values().length];

        static {
            try {
                $SwitchMap$com$amazon$ptzcontroller$lib$model$api$ptz$directive$CameraPtzInstance[CameraPtzInstance.CAMERA_PAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ptzcontroller$lib$model$api$ptz$directive$CameraPtzInstance[CameraPtzInstance.CAMERA_TILT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ptzcontroller$lib$model$api$ptz$directive$CameraPtzInstance[CameraPtzInstance.CAMERA_ZOOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes13.dex */
    public static class PtzControllerDirectiveApiBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private boolean panHandler$set;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private PanHandler panHandler$value;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private boolean tiltHandler$set;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private TiltHandler tiltHandler$value;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private boolean zoomHandler$set;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private ZoomHandler zoomHandler$value;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        PtzControllerDirectiveApiBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PtzControllerDirectiveApi build() {
            PanHandler panHandler = this.panHandler$value;
            if (!this.panHandler$set) {
                panHandler = PtzControllerDirectiveApi.$default$panHandler();
            }
            TiltHandler tiltHandler = this.tiltHandler$value;
            if (!this.tiltHandler$set) {
                tiltHandler = PtzControllerDirectiveApi.$default$tiltHandler();
            }
            ZoomHandler zoomHandler = this.zoomHandler$value;
            if (!this.zoomHandler$set) {
                zoomHandler = PtzControllerDirectiveApi.$default$zoomHandler();
            }
            return new PtzControllerDirectiveApi(panHandler, tiltHandler, zoomHandler);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PtzControllerDirectiveApiBuilder panHandler(PanHandler panHandler) {
            this.panHandler$value = panHandler;
            this.panHandler$set = true;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PtzControllerDirectiveApiBuilder tiltHandler(TiltHandler tiltHandler) {
            this.tiltHandler$value = tiltHandler;
            this.tiltHandler$set = true;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PtzControllerDirectiveApi.PtzControllerDirectiveApiBuilder(panHandler$value=");
            outline107.append(this.panHandler$value);
            outline107.append(", tiltHandler$value=");
            outline107.append(this.tiltHandler$value);
            outline107.append(", zoomHandler$value=");
            outline107.append(this.zoomHandler$value);
            outline107.append(")");
            return outline107.toString();
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public PtzControllerDirectiveApiBuilder zoomHandler(ZoomHandler zoomHandler) {
            this.zoomHandler$value = zoomHandler;
            this.zoomHandler$set = true;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static PanHandler $default$panHandler() {
        return $$Lambda$PtzControllerDirectiveApi$brB97Rf2Wi0T3HiaupQEow5QNsE.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static TiltHandler $default$tiltHandler() {
        return $$Lambda$PtzControllerDirectiveApi$n4uZ2toxzkKPBZL_iDEYlIFZ2o.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static ZoomHandler $default$zoomHandler() {
        return $$Lambda$PtzControllerDirectiveApi$KdoHjaWAw16oWfsCONkTt27zjJw.INSTANCE;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    PtzControllerDirectiveApi(PanHandler panHandler, TiltHandler tiltHandler, ZoomHandler zoomHandler) {
        this.panHandler = panHandler;
        this.tiltHandler = tiltHandler;
        this.zoomHandler = zoomHandler;
    }

    private void adjustRangeValue(@NonNull CameraPtzInstance cameraPtzInstance, @NonNull String str, double d) {
        int ordinal = cameraPtzInstance.ordinal();
        if (ordinal == 0) {
            this.panHandler.pan(str, ControlType.ADJUST, d);
        } else if (ordinal == 1) {
            this.tiltHandler.tilt(str, ControlType.ADJUST, d);
        } else if (ordinal == 2) {
            this.zoomHandler.zoom(str, ControlType.ADJUST, d);
        } else {
            throw new IllegalArgumentException(String.format("Instance %s not supported", cameraPtzInstance.toString()));
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static PtzControllerDirectiveApiBuilder builder() {
        return new PtzControllerDirectiveApiBuilder();
    }

    @NonNull
    private HeaderName getDirectiveName(@NonNull Header<NamespaceName, HeaderName, CameraPtzInstance> header) {
        if (header.getName() != null) {
            return header.getName();
        }
        throw new IllegalArgumentException("DirectiveName is null");
    }

    private void handleAdjustRangeValueDirective(@NonNull PtzAdjustRangeValueDirective ptzAdjustRangeValueDirective) {
        Directive<NamespaceName, HeaderName, CameraPtzInstance, AdjustRangeValuePayload> directive = ptzAdjustRangeValueDirective.getDirective();
        if (directive != null) {
            Header<NamespaceName, HeaderName, CameraPtzInstance> header = getHeader(ptzAdjustRangeValueDirective);
            CameraPtzInstance header2 = header.getInstance();
            if (header2 != null) {
                String correlationToken = header.getCorrelationToken();
                if (correlationToken != null) {
                    AdjustRangeValuePayload payload = directive.getPayload();
                    if (payload != null) {
                        Double rangeValueDelta = payload.getRangeValueDelta();
                        if (rangeValueDelta != null) {
                            adjustRangeValue(header2, correlationToken, rangeValueDelta.doubleValue());
                            return;
                        }
                        throw new IllegalArgumentException("RangeValueDelta is null");
                    }
                    throw new IllegalArgumentException("Payload is null");
                }
                throw new IllegalArgumentException("Correlation token is null");
            }
            throw new IllegalArgumentException("Instance is null");
        }
        throw new IllegalArgumentException("Directive is null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$$default$panHandler$0(String str, ControlType controlType, double d) {
        throw new UnsupportedOperationException("PanHandler not specified");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$$default$tiltHandler$1(String str, ControlType controlType, double d) {
        throw new UnsupportedOperationException("TiltHandler not specified");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$$default$zoomHandler$2(String str, ControlType controlType, double d) {
        throw new UnsupportedOperationException("ZoomHandler not specified");
    }

    @NonNull
    public Header<NamespaceName, HeaderName, CameraPtzInstance> getHeader(@NonNull String str) {
        PtzDirective ptzDirective = (PtzDirective) PtzSerializer.INSTANCE.fromJson(str, PtzDirective.class);
        if (ptzDirective != null) {
            return getHeader(ptzDirective);
        }
        throw new IllegalArgumentException("Cannot handle null ptz directive");
    }

    public void handle(@NonNull String str) {
        if (getDirectiveName(getHeader(str)) == HeaderName.ADJUST_RANGE_VALUE) {
            PtzAdjustRangeValueDirective ptzAdjustRangeValueDirective = (PtzAdjustRangeValueDirective) PtzSerializer.INSTANCE.fromJson(str, PtzAdjustRangeValueDirective.class);
            if (ptzAdjustRangeValueDirective != null) {
                handleAdjustRangeValueDirective(ptzAdjustRangeValueDirective);
                return;
            }
            throw new IllegalArgumentException("PtzAdjustRangeValueDirective couldn't be deserialized");
        }
    }

    @NonNull
    private Header<NamespaceName, HeaderName, CameraPtzInstance> getHeader(@NonNull AlexaDirective<NamespaceName, HeaderName, CameraPtzInstance, ?> alexaDirective) {
        Directive<NamespaceName, HeaderName, CameraPtzInstance, ?> directive = alexaDirective.getDirective();
        if (directive != null) {
            Header<NamespaceName, HeaderName, CameraPtzInstance> header = directive.getHeader();
            if (header == null) {
                throw new IllegalArgumentException("Header is null");
            }
            return header;
        }
        throw new IllegalArgumentException("Directive is null");
    }
}
