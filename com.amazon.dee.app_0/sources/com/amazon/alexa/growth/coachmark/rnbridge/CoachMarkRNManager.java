package com.amazon.alexa.growth.coachmark.rnbridge;

import android.util.Log;
import com.amazon.alexa.accessorykit.finishsetup.bridge.FinishSetupModule;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class CoachMarkRNManager extends ViewGroupManager<CoachMarkRNView> {
    private static final int COMMAND_SHOW = 1;
    private static final String REACT_CLASS_NAME = "CoachMark";
    private static final String TAG = "CoachMarkRNManager";

    private boolean commandVerification(String str, ReadableArray readableArray, String str2, int i) {
        if (readableArray == null && i == 0) {
            return true;
        }
        if (readableArray == null) {
            Log.w(TAG, String.format("Arguments of %s (%s) were not sent", str, str2));
            return false;
        } else if (readableArray.size() == i) {
            return true;
        } else {
            Log.w(TAG, String.format("Expected %d arguments for command %s, receiving %d instead", Integer.valueOf(i), str, Integer.valueOf(readableArray.size())));
            return false;
        }
    }

    private void show(CoachMarkRNView coachMarkRNView, ReadableArray readableArray) {
        if (commandVerification("show", readableArray, "No argument", 0)) {
            Log.i(TAG, "Showing coach mark by calling command 1");
            coachMarkRNView.show();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("show", 1);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("onShow", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onShow"))).put(FinishSetupModule.EVENT_ON_DISMISS, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", FinishSetupModule.EVENT_ON_DISMISS))).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return REACT_CLASS_NAME;
    }

    @ReactProp(name = "id")
    public void setId(@Nonnull CoachMarkRNView coachMarkRNView, String str) {
        GeneratedOutlineSupport1.outline163("Setting id for coach mark as ", str, TAG);
        coachMarkRNView.coachMarkId = str;
    }

    @ReactProp(name = "showOnLoad")
    public void setShowOnLoad(@Nonnull CoachMarkRNView coachMarkRNView, Boolean bool) {
        String str = TAG;
        Log.i(str, "Setting showOnLoad for coach mark as " + bool);
        coachMarkRNView.showOnLoad = bool;
    }

    @ReactProp(name = "text")
    public void setText(@Nonnull CoachMarkRNView coachMarkRNView, String str) {
        GeneratedOutlineSupport1.outline163("Setting text for coach mark as ", str, TAG);
        coachMarkRNView.text = str;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public CoachMarkRNView mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new CoachMarkRNView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(@Nonnull CoachMarkRNView coachMarkRNView, int i, @Nullable ReadableArray readableArray) {
        try {
            if (i != 1) {
                String str = TAG;
                Log.w(str, "Receives unsupported command " + i);
            } else {
                show(coachMarkRNView, readableArray);
            }
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Error when calling command " + i + RealTimeTextConstants.COLON_SPACE + e);
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.TEST_ID)
    public void setTestId(@Nonnull CoachMarkRNView coachMarkRNView, String str) {
        GeneratedOutlineSupport1.outline163("Setting testID for coach mark as ", str, TAG);
        coachMarkRNView.testId = str;
    }
}
