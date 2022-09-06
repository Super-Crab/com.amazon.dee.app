package com.swmansion.gesturehandler.react;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.views.view.ReactViewGroup;
import com.swmansion.gesturehandler.PointerEventsConfig;
import com.swmansion.gesturehandler.ViewConfigurationHelper;
/* loaded from: classes3.dex */
public class RNViewConfigurationHelper implements ViewConfigurationHelper {

    /* renamed from: com.swmansion.gesturehandler.react.RNViewConfigurationHelper$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$PointerEvents = new int[PointerEvents.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$uimanager$PointerEvents[PointerEvents.BOX_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$PointerEvents[PointerEvents.BOX_NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$PointerEvents[PointerEvents.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.swmansion.gesturehandler.ViewConfigurationHelper
    public View getChildInDrawingOrderAtIndex(ViewGroup viewGroup, int i) {
        if (viewGroup instanceof ReactViewGroup) {
            return viewGroup.getChildAt(((ReactViewGroup) viewGroup).getZIndexMappedChildIndex(i));
        }
        return viewGroup.getChildAt(i);
    }

    @Override // com.swmansion.gesturehandler.ViewConfigurationHelper
    public PointerEventsConfig getPointerEventsConfigForView(View view) {
        PointerEvents pointerEvents = view instanceof ReactPointerEventsView ? ((ReactPointerEventsView) view).getPointerEvents() : PointerEvents.AUTO;
        if (!view.isEnabled()) {
            if (pointerEvents == PointerEvents.AUTO) {
                return PointerEventsConfig.BOX_NONE;
            }
            if (pointerEvents == PointerEvents.BOX_ONLY) {
                return PointerEventsConfig.NONE;
            }
        }
        int ordinal = pointerEvents.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return PointerEventsConfig.BOX_NONE;
            }
            if (ordinal != 2) {
                return PointerEventsConfig.AUTO;
            }
            return PointerEventsConfig.BOX_ONLY;
        }
        return PointerEventsConfig.NONE;
    }

    @Override // com.swmansion.gesturehandler.ViewConfigurationHelper
    public boolean isViewClippingChildren(ViewGroup viewGroup) {
        int i = Build.VERSION.SDK_INT;
        if (!viewGroup.getClipChildren()) {
            if (!(viewGroup instanceof ReactViewGroup)) {
                return false;
            }
            return "hidden".equals(((ReactViewGroup) viewGroup).getOverflow());
        }
        return true;
    }
}
