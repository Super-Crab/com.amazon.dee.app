package com.amazon.dee.app.elements;

import android.content.Context;
import android.view.View;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.facebook.react.ReactRootView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class ReactRootViewCorrectMeasure extends ReactRootView {
    private static final int MAX_MEASUREMENT_SIZE = 3;
    private static final String TAG = Log.tag(ReactRootViewCorrectMeasure.class);
    private List<Integer> lastHeightMeasurements;
    private final TabLayoutStatusService tabLayoutStatusService;

    public ReactRootViewCorrectMeasure(Context context, TabLayoutStatusService tabLayoutStatusService) {
        super(context);
        this.lastHeightMeasurements = new ArrayList();
        this.tabLayoutStatusService = tabLayoutStatusService;
    }

    private int getCorrectedHeight(int i) {
        int size = this.lastHeightMeasurements.size();
        if (size < 3) {
            return i;
        }
        if (size > 3) {
            this.lastHeightMeasurements.remove(0);
        }
        int intValue = this.lastHeightMeasurements.get(2).intValue();
        if (this.lastHeightMeasurements.get(0).intValue() != intValue) {
            return i;
        }
        int intValue2 = this.lastHeightMeasurements.get(1).intValue();
        if (intValue2 >= intValue) {
            intValue2 = intValue;
            intValue = intValue2;
        }
        if (this.tabLayoutStatusService.getIsTabLayoutShown()) {
            intValue = intValue2;
        }
        return View.MeasureSpec.makeMeasureSpec(intValue, View.MeasureSpec.getMode(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactRootView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        this.lastHeightMeasurements.add(Integer.valueOf(View.MeasureSpec.getSize(i2)));
        super.onMeasure(i, getCorrectedHeight(i2));
    }
}
