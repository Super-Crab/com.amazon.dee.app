package com.facebook.react.views.scroll;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import java.util.Map;
/* loaded from: classes2.dex */
public class ReactScrollViewCommandHelper {
    public static final int COMMAND_FLASH_SCROLL_INDICATORS = 3;
    public static final int COMMAND_SCROLL_TO = 1;
    public static final int COMMAND_SCROLL_TO_END = 2;

    /* loaded from: classes2.dex */
    public interface ScrollCommandHandler<T> {
        void flashScrollIndicators(T t);

        void scrollTo(T t, ScrollToCommandData scrollToCommandData);

        void scrollToEnd(T t, ScrollToEndCommandData scrollToEndCommandData);
    }

    /* loaded from: classes2.dex */
    public static class ScrollToCommandData {
        public final boolean mAnimated;
        public final int mDestX;
        public final int mDestY;

        ScrollToCommandData(int i, int i2, boolean z) {
            this.mDestX = i;
            this.mDestY = i2;
            this.mAnimated = z;
        }
    }

    /* loaded from: classes2.dex */
    public static class ScrollToEndCommandData {
        public final boolean mAnimated;

        ScrollToEndCommandData(boolean z) {
            this.mAnimated = z;
        }
    }

    public static Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("scrollTo", 1, "scrollToEnd", 2, "flashScrollIndicators", 3);
    }

    public static <T> void receiveCommand(ScrollCommandHandler<T> scrollCommandHandler, T t, int i, @Nullable ReadableArray readableArray) {
        Assertions.assertNotNull(scrollCommandHandler);
        Assertions.assertNotNull(t);
        Assertions.assertNotNull(readableArray);
        if (i == 1) {
            scrollTo(scrollCommandHandler, t, readableArray);
        } else if (i == 2) {
            scrollToEnd(scrollCommandHandler, t, readableArray);
        } else if (i == 3) {
            scrollCommandHandler.flashScrollIndicators(t);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", Integer.valueOf(i), scrollCommandHandler.getClass().getSimpleName()));
        }
    }

    private static <T> void scrollTo(ScrollCommandHandler<T> scrollCommandHandler, T t, @Nullable ReadableArray readableArray) {
        scrollCommandHandler.scrollTo(t, new ScrollToCommandData(Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(0))), Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(1))), readableArray.getBoolean(2)));
    }

    private static <T> void scrollToEnd(ScrollCommandHandler<T> scrollCommandHandler, T t, @Nullable ReadableArray readableArray) {
        scrollCommandHandler.scrollToEnd(t, new ScrollToEndCommandData(readableArray.getBoolean(0)));
    }

    public static <T> void receiveCommand(ScrollCommandHandler<T> scrollCommandHandler, T t, String str, @Nullable ReadableArray readableArray) {
        char c;
        Assertions.assertNotNull(scrollCommandHandler);
        Assertions.assertNotNull(t);
        Assertions.assertNotNull(readableArray);
        int hashCode = str.hashCode();
        if (hashCode == -402165208) {
            if (str.equals("scrollTo")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 28425985) {
            if (hashCode == 2055114131 && str.equals("scrollToEnd")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("flashScrollIndicators")) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            scrollTo(scrollCommandHandler, t, readableArray);
        } else if (c == 1) {
            scrollToEnd(scrollCommandHandler, t, readableArray);
        } else if (c == 2) {
            scrollCommandHandler.flashScrollIndicators(t);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %s received by %s.", str, scrollCommandHandler.getClass().getSimpleName()));
        }
    }
}
