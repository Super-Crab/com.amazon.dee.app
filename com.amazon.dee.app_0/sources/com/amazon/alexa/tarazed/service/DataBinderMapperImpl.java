package com.amazon.alexa.tarazed.service;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes10.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(0);

    /* loaded from: classes10.dex */
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(12);

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "controlButtonState");
            sKeys.put(2, "messageText");
            sKeys.put(3, "borderVisible");
            sKeys.put(4, "barButtonHandlers");
            sKeys.put(5, "pauseResumeClickable");
            sKeys.put(6, "barBorderVisible");
            sKeys.put(7, HttpClientModule.WritableMapEncodingDataServiceObserver.ElementsResponseKey.STATUS_TEXT);
            sKeys.put(8, "overflowMenuVisible");
            sKeys.put(9, "buttonHandlers");
            sKeys.put(10, "notificationVisible");
        }

        private InnerBrLookup() {
        }
    }

    /* loaded from: classes10.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(0);

        private InnerLayoutIdLookup() {
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.amazon.tarazed.DataBinderMapperImpl());
        arrayList.add(new com.amazon.tarazed.core.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        if (INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || view.getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
