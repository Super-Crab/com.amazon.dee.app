package com.amazon.alexa.redesign.view.homeFeed;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
/* loaded from: classes10.dex */
public class RVAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
    public RVAccessibilityDelegate(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        AccessibilityNodeInfoCompat.CollectionInfoCompat collectionInfo = accessibilityNodeInfoCompat.getCollectionInfo();
        int i = 0;
        boolean z = collectionInfo != null && collectionInfo.isHierarchical();
        if (collectionInfo != null) {
            i = collectionInfo.getSelectionMode();
        }
        accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(-1, -1, z, i));
    }
}
