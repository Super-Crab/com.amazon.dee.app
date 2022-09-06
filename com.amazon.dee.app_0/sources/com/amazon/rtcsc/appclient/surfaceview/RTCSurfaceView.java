package com.amazon.rtcsc.appclient.surfaceview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.rtcsc.appclient.RtcscAppClient;
import com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceViewInterface;
import com.amazon.rtcsc.common.RtcscClientHandler;
import com.amazon.rtcsc.interfaces.RtcscScalingType;
import com.amazon.rtcsc.interfaces.RtcscViewDirection;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class RTCSurfaceView extends SurfaceView implements RTCSurfaceViewInterface, SurfaceHolder.Callback {
    private Map<CachedOperationType, CachedOperation> mCachedOperationMap;
    private RTCSurfaceViewInterface.ViewDirection mDirection;
    private String mLabel;
    private RtcscLogger mLog;
    private String mResourceName;
    private RtcscClientHandler mRtcscClientHandler;
    private String mSessionId;

    /* renamed from: com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceView$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$rtcsc$appclient$surfaceview$RTCSurfaceView$CachedOperationType = new int[CachedOperationType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$rtcsc$appclient$surfaceview$RTCSurfaceView$CachedOperationType[CachedOperationType.SET_SCALING_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$appclient$surfaceview$RTCSurfaceView$CachedOperationType[CachedOperationType.ENABLE_HW_SCALER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$appclient$surfaceview$RTCSurfaceView$CachedOperationType[CachedOperationType.SET_MIRROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$appclient$surfaceview$RTCSurfaceView$CachedOperationType[CachedOperationType.SURFACE_CREATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$appclient$surfaceview$RTCSurfaceView$CachedOperationType[CachedOperationType.SURFACE_CHANGED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$appclient$surfaceview$RTCSurfaceView$CachedOperationType[CachedOperationType.ON_MEASURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$rtcsc$appclient$surfaceview$RTCSurfaceView$CachedOperationType[CachedOperationType.ON_LAYOUT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class CachedBooleanOperation extends CachedOperation {
        public final boolean enabled;

        CachedBooleanOperation(CachedOperationType cachedOperationType, boolean z) {
            super(cachedOperationType);
            this.enabled = z;
        }
    }

    /* loaded from: classes13.dex */
    public static class CachedOnLayoutOperation extends CachedOperation {
        public final int bottom;
        public final boolean changed;
        public final int left;
        public final int right;
        public final int top;

        CachedOnLayoutOperation(CachedOperationType cachedOperationType, boolean z, int i, int i2, int i3, int i4) {
            super(cachedOperationType);
            this.changed = z;
            this.left = i;
            this.top = i2;
            this.right = i3;
            this.bottom = i4;
        }
    }

    /* loaded from: classes13.dex */
    public static class CachedOnMeasureOperation extends CachedOperation {
        public final int heightSpec;
        public final int widthSpec;

        CachedOnMeasureOperation(CachedOperationType cachedOperationType, int i, int i2) {
            super(cachedOperationType);
            this.widthSpec = i;
            this.heightSpec = i2;
        }
    }

    /* loaded from: classes13.dex */
    public static class CachedOperation {
        public final CachedOperationType type;

        public CachedOperation(CachedOperationType cachedOperationType) {
            this.type = cachedOperationType;
        }
    }

    /* loaded from: classes13.dex */
    public enum CachedOperationType {
        SET_SCALING_TYPE,
        ENABLE_HW_SCALER,
        SET_MIRROR,
        SURFACE_CREATED,
        SURFACE_CHANGED,
        ON_MEASURE,
        ON_LAYOUT
    }

    /* loaded from: classes13.dex */
    public static class CachedSetScalingOperation extends CachedOperation {
        public final RTCSurfaceViewInterface.ScalingType scalingType;

        CachedSetScalingOperation(CachedOperationType cachedOperationType, RTCSurfaceViewInterface.ScalingType scalingType) {
            super(cachedOperationType);
            this.scalingType = scalingType;
        }
    }

    /* loaded from: classes13.dex */
    public static class CachedSurfaceChangedOperation extends CachedOperation {
        public final int format;
        public final int height;
        public final SurfaceHolder holder;
        public final int width;

        CachedSurfaceChangedOperation(CachedOperationType cachedOperationType, SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            super(cachedOperationType);
            this.holder = surfaceHolder;
            this.format = i;
            this.width = i2;
            this.height = i3;
        }
    }

    /* loaded from: classes13.dex */
    public static class CachedSurfaceCreatedOperation extends CachedOperation {
        public final SurfaceHolder holder;

        CachedSurfaceCreatedOperation(CachedOperationType cachedOperationType, SurfaceHolder surfaceHolder) {
            super(cachedOperationType);
            this.holder = surfaceHolder;
        }
    }

    public RTCSurfaceView(Context context) {
        super(context);
        this.mLog = RtcscLogger.getLogger(RTCSurfaceView.class);
        this.mResourceName = "";
        this.mSessionId = "";
        this.mLabel = "";
        this.mDirection = RTCSurfaceViewInterface.ViewDirection.REMOTE_VIEW;
        this.mRtcscClientHandler = null;
        this.mCachedOperationMap = new HashMap();
        this.mResourceName = getResourceName();
        getHolder().addCallback(this);
        this.mLog.i("RTCSurfaceView(Context context)");
    }

    public static RTCSurfaceView createLocalView(Context context, String str, int i) {
        return new RTCSurfaceView(context, str, RTCSurfaceViewInterface.ViewDirection.LOCAL_VIEW, i);
    }

    public static RTCSurfaceView createRemoteView(Context context, String str, int i) {
        return new RTCSurfaceView(context, str, RTCSurfaceViewInterface.ViewDirection.REMOTE_VIEW, i);
    }

    private String getResourceName() {
        try {
            return getResources().getResourceEntryName(getId()) + RealTimeTextConstants.COLON_SPACE;
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    private void processCachedOperation(CachedOperation cachedOperation) {
        if (cachedOperation == null) {
            this.mLog.e("null CachedOperation");
            return;
        }
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CachedOperation type = ");
        outline107.append(cachedOperation.type);
        rtcscLogger.i(outline107.toString());
        switch (cachedOperation.type.ordinal()) {
            case 0:
                this.mRtcscClientHandler.setScalingType(this.mSessionId, this.mLabel, RtcscScalingType.valueOf(((CachedSetScalingOperation) cachedOperation).scalingType.toString()));
                return;
            case 1:
                this.mRtcscClientHandler.setEnableHardwareScaler(this.mSessionId, this.mLabel, ((CachedBooleanOperation) cachedOperation).enabled);
                return;
            case 2:
                this.mRtcscClientHandler.setMirror(this.mSessionId, this.mLabel, ((CachedBooleanOperation) cachedOperation).enabled);
                return;
            case 3:
                this.mRtcscClientHandler.surfaceCreated(this.mSessionId, this.mLabel, ((CachedSurfaceCreatedOperation) cachedOperation).holder.getSurface());
                return;
            case 4:
                CachedSurfaceChangedOperation cachedSurfaceChangedOperation = (CachedSurfaceChangedOperation) cachedOperation;
                this.mRtcscClientHandler.surfaceChanged(this.mSessionId, this.mLabel, cachedSurfaceChangedOperation.holder.getSurface(), cachedSurfaceChangedOperation.format, cachedSurfaceChangedOperation.width, cachedSurfaceChangedOperation.height);
                return;
            case 5:
                CachedOnMeasureOperation cachedOnMeasureOperation = (CachedOnMeasureOperation) cachedOperation;
                this.mRtcscClientHandler.onRendererMeasure(this.mSessionId, this.mLabel, cachedOnMeasureOperation.widthSpec, cachedOnMeasureOperation.heightSpec);
                return;
            case 6:
                CachedOnLayoutOperation cachedOnLayoutOperation = (CachedOnLayoutOperation) cachedOperation;
                this.mRtcscClientHandler.onRendererLayout(this.mSessionId, this.mLabel, cachedOnLayoutOperation.changed, cachedOnLayoutOperation.left, cachedOnLayoutOperation.top, cachedOnLayoutOperation.right, cachedOnLayoutOperation.bottom);
                return;
            default:
                this.mLog.e("invalid operation!");
                return;
        }
    }

    @Override // com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceViewInterface
    public void clearImage() {
        this.mLog.i("clearImage");
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.clearImage(this.mSessionId, this.mLabel);
        }
    }

    public void forwardMeasuredDimension(int i, int i2) {
        this.mLog.i("forwardMeasuredDimension");
        setMeasuredDimension(i, i2);
    }

    @Override // com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceViewInterface
    public void init(String str, RTCSurfaceViewInterface.ViewDirection viewDirection) {
        this.mLog.i(String.format("init, sessionId = %s, resourceName = %s, videoDirection = %s", str, this.mResourceName, viewDirection));
        this.mSessionId = str;
        this.mDirection = viewDirection;
        this.mLabel = viewDirection.toString();
        this.mRtcscClientHandler = RtcscAppClient.getClientHandler();
        this.mRtcscClientHandler.initRenderer(this.mSessionId, this.mLabel, new RtcscViewRendererListener(this), RtcscViewDirection.valueOf(this.mDirection.toString()), this.mResourceName);
        for (CachedOperation cachedOperation : this.mCachedOperationMap.values()) {
            processCachedOperation(cachedOperation);
        }
        this.mCachedOperationMap.clear();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mLog.i(ViewProps.ON_LAYOUT);
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.onRendererLayout(this.mSessionId, this.mLabel, z, i, i2, i3, i4);
            return;
        }
        this.mLog.i("Caching onLayout");
        Map<CachedOperationType, CachedOperation> map = this.mCachedOperationMap;
        CachedOperationType cachedOperationType = CachedOperationType.ON_LAYOUT;
        map.put(cachedOperationType, new CachedOnLayoutOperation(cachedOperationType, z, i, i2, i3, i4));
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        this.mLog.i("onMeasure");
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.onRendererMeasure(this.mSessionId, this.mLabel, i, i2);
            return;
        }
        forwardMeasuredDimension(i, i2);
        this.mLog.i("Caching onMeasure");
        Map<CachedOperationType, CachedOperation> map = this.mCachedOperationMap;
        CachedOperationType cachedOperationType = CachedOperationType.ON_MEASURE;
        map.put(cachedOperationType, new CachedOnMeasureOperation(cachedOperationType, i, i2));
    }

    @Override // com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceViewInterface
    public void release() {
        this.mLog.i(String.format("release, sessionId = %s, resourceName = %s, videoDirection = %s", this.mSessionId, this.mResourceName, this.mDirection));
        this.mRtcscClientHandler.releaseRenderer(this.mSessionId, this.mLabel);
        this.mRtcscClientHandler = null;
        this.mCachedOperationMap.clear();
    }

    @Override // com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceViewInterface
    public void setEnableHardwareScaler(boolean z) {
        this.mLog.i("setEnableHardwareScaler");
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.setEnableHardwareScaler(this.mSessionId, this.mLabel, z);
            return;
        }
        this.mLog.i("Caching setEnableHardwareScaler");
        Map<CachedOperationType, CachedOperation> map = this.mCachedOperationMap;
        CachedOperationType cachedOperationType = CachedOperationType.ENABLE_HW_SCALER;
        map.put(cachedOperationType, new CachedBooleanOperation(cachedOperationType, z));
    }

    @Override // android.view.View
    public void setId(int i) {
        super.setId(i);
        this.mResourceName = getResourceName();
    }

    @Override // com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceViewInterface
    public void setMirror(boolean z) {
        this.mLog.i("setMirror");
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.setMirror(this.mSessionId, this.mLabel, z);
            return;
        }
        this.mLog.i("Caching setMirror");
        Map<CachedOperationType, CachedOperation> map = this.mCachedOperationMap;
        CachedOperationType cachedOperationType = CachedOperationType.SET_MIRROR;
        map.put(cachedOperationType, new CachedBooleanOperation(cachedOperationType, z));
    }

    @Override // com.amazon.rtcsc.appclient.surfaceview.RTCSurfaceViewInterface
    public void setScalingType(RTCSurfaceViewInterface.ScalingType scalingType) {
        this.mLog.i("setScalingType");
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.setScalingType(this.mSessionId, this.mLabel, RtcscScalingType.valueOf(scalingType.toString()));
            return;
        }
        this.mLog.i("Caching setScalingType");
        Map<CachedOperationType, CachedOperation> map = this.mCachedOperationMap;
        CachedOperationType cachedOperationType = CachedOperationType.SET_SCALING_TYPE;
        map.put(cachedOperationType, new CachedSetScalingOperation(cachedOperationType, scalingType));
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mLog.i("surfaceChanged");
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.surfaceChanged(this.mSessionId, this.mLabel, surfaceHolder.getSurface(), i, i2, i3);
            return;
        }
        Map<CachedOperationType, CachedOperation> map = this.mCachedOperationMap;
        CachedOperationType cachedOperationType = CachedOperationType.SURFACE_CHANGED;
        map.put(cachedOperationType, new CachedSurfaceChangedOperation(cachedOperationType, surfaceHolder, i, i2, i3));
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mLog.i(String.format("surfaceCreated, sessionId = %s, videoDirection = %s, surfaceHolder = %s", this.mSessionId, this.mDirection, surfaceHolder));
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.surfaceCreated(this.mSessionId, this.mLabel, surfaceHolder.getSurface());
            return;
        }
        this.mLog.i("Caching surfaceCreated");
        Map<CachedOperationType, CachedOperation> map = this.mCachedOperationMap;
        CachedOperationType cachedOperationType = CachedOperationType.SURFACE_CREATED;
        map.put(cachedOperationType, new CachedSurfaceCreatedOperation(cachedOperationType, surfaceHolder));
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mLog.i(String.format("surfaceDestroyed, sessionId = %s, videoDirection = %s, surfaceHolder = %s", this.mSessionId, this.mDirection, surfaceHolder));
        RtcscClientHandler rtcscClientHandler = this.mRtcscClientHandler;
        if (rtcscClientHandler != null) {
            rtcscClientHandler.surfaceDestroyed(this.mSessionId, this.mLabel, surfaceHolder.getSurface());
        }
    }

    public RTCSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLog = RtcscLogger.getLogger(RTCSurfaceView.class);
        this.mResourceName = "";
        this.mSessionId = "";
        this.mLabel = "";
        this.mDirection = RTCSurfaceViewInterface.ViewDirection.REMOTE_VIEW;
        this.mRtcscClientHandler = null;
        this.mCachedOperationMap = new HashMap();
        this.mResourceName = getResourceName();
        getHolder().addCallback(this);
        this.mLog.i("RTCSurfaceView(Context context, AttributeSet attrs)");
    }

    public RTCSurfaceView(Context context, String str, RTCSurfaceViewInterface.ViewDirection viewDirection, int i) {
        super(context);
        this.mLog = RtcscLogger.getLogger(RTCSurfaceView.class);
        this.mResourceName = "";
        this.mSessionId = "";
        this.mLabel = "";
        this.mDirection = RTCSurfaceViewInterface.ViewDirection.REMOTE_VIEW;
        this.mRtcscClientHandler = null;
        this.mCachedOperationMap = new HashMap();
        setId(i);
        this.mResourceName = getResourceName();
        this.mSessionId = str;
        this.mDirection = viewDirection;
        getHolder().addCallback(this);
    }

    public RTCSurfaceView(Context context, AttributeSet attributeSet, String str, RTCSurfaceViewInterface.ViewDirection viewDirection) {
        super(context, attributeSet);
        this.mLog = RtcscLogger.getLogger(RTCSurfaceView.class);
        this.mResourceName = "";
        this.mSessionId = "";
        this.mLabel = "";
        this.mDirection = RTCSurfaceViewInterface.ViewDirection.REMOTE_VIEW;
        this.mRtcscClientHandler = null;
        this.mCachedOperationMap = new HashMap();
        this.mResourceName = getResourceName();
        this.mSessionId = str;
        this.mDirection = viewDirection;
        getHolder().addCallback(this);
    }
}
