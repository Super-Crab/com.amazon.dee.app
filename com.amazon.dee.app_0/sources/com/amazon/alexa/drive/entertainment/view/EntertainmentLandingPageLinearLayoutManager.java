package com.amazon.alexa.drive.entertainment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes7.dex */
public class EntertainmentLandingPageLinearLayoutManager extends LinearLayoutManager {
    private static final String TAG = "EntertainmentLandingPageLinearLayoutManager";

    public EntertainmentLandingPageLinearLayoutManager(Context context) {
        super(context);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            String str = TAG;
            Log.e(str, "IndexOutOfBoundsException" + e);
        }
    }

    public EntertainmentLandingPageLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public EntertainmentLandingPageLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
