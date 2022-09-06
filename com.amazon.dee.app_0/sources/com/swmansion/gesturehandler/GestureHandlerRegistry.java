package com.swmansion.gesturehandler;

import android.view.View;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public interface GestureHandlerRegistry {
    ArrayList<GestureHandler> getHandlersForView(View view);
}
