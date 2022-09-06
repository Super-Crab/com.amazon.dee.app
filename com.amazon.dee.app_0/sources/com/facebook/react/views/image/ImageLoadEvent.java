package com.facebook.react.views.image;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
/* loaded from: classes2.dex */
public class ImageLoadEvent extends Event<ImageLoadEvent> {
    public static final int ON_ERROR = 1;
    public static final int ON_LOAD = 2;
    public static final int ON_LOAD_END = 3;
    public static final int ON_LOAD_START = 4;
    public static final int ON_PROGRESS = 5;
    @Nullable
    private final String mErrorMessage;
    private final int mEventType;
    private final int mHeight;
    private final int mLoaded;
    @Nullable
    private final String mSourceUri;
    private final int mTotal;
    private final int mWidth;

    private ImageLoadEvent(int i, int i2) {
        this(i, i2, null, null, 0, 0, 0, 0);
    }

    public static final ImageLoadEvent createErrorEvent(int i, Throwable th) {
        return new ImageLoadEvent(i, 1, th.getMessage(), null, 0, 0, 0, 0);
    }

    private WritableMap createEventDataSource() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("uri", this.mSourceUri);
        createMap.putDouble("width", this.mWidth);
        createMap.putDouble("height", this.mHeight);
        return createMap;
    }

    public static final ImageLoadEvent createLoadEndEvent(int i) {
        return new ImageLoadEvent(i, 3);
    }

    public static final ImageLoadEvent createLoadEvent(int i, @Nullable String str, int i2, int i3) {
        return new ImageLoadEvent(i, 2, null, str, i2, i3, 0, 0);
    }

    public static final ImageLoadEvent createLoadStartEvent(int i) {
        return new ImageLoadEvent(i, 4);
    }

    public static final ImageLoadEvent createProgressEvent(int i, @Nullable String str, int i2, int i3) {
        return new ImageLoadEvent(i, 5, null, str, 0, 0, i2, i3);
    }

    public static String eventNameForType(int i) {
        if (i != 1) {
            if (i == 2) {
                return "topLoad";
            }
            if (i == 3) {
                return "topLoadEnd";
            }
            if (i == 4) {
                return "topLoadStart";
            }
            if (i == 5) {
                return "topProgress";
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid image event: ");
            outline107.append(Integer.toString(i));
            throw new IllegalStateException(outline107.toString());
        }
        return "topError";
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap createMap;
        int i = this.mEventType;
        if (i == 1) {
            createMap = Arguments.createMap();
            createMap.putString("error", this.mErrorMessage);
        } else if (i == 2) {
            createMap = Arguments.createMap();
            createMap.putMap("source", createEventDataSource());
        } else if (i != 5) {
            createMap = null;
        } else {
            createMap = Arguments.createMap();
            createMap.putInt("loaded", this.mLoaded);
            createMap.putInt("total", this.mTotal);
        }
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), createMap);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) this.mEventType;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return eventNameForType(this.mEventType);
    }

    private ImageLoadEvent(int i, int i2, @Nullable String str, @Nullable String str2, int i3, int i4, int i5, int i6) {
        super(i);
        this.mEventType = i2;
        this.mErrorMessage = str;
        this.mSourceUri = str2;
        this.mWidth = i3;
        this.mHeight = i4;
        this.mLoaded = i5;
        this.mTotal = i6;
    }
}
