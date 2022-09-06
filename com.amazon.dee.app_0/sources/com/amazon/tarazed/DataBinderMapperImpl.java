package com.amazon.tarazed;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.tarazed.databinding.ActivityTarazedPrimerBindingImpl;
import com.amazon.tarazed.databinding.ControlBarBindingImpl;
import com.amazon.tarazed.databinding.DynamicSessionBorderBindingImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes13.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);
    private static final int LAYOUT_ACTIVITYTARAZEDPRIMER = 1;
    private static final int LAYOUT_CONTROLBAR = 2;
    private static final int LAYOUT_DYNAMICSESSIONBORDER = 3;

    /* loaded from: classes13.dex */
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

    /* loaded from: classes13.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(3);

        static {
            sKeys.put("layout/activity_tarazed_primer_0", Integer.valueOf(R.layout.activity_tarazed_primer));
            sKeys.put("layout/control_bar_0", Integer.valueOf(R.layout.control_bar));
            sKeys.put("layout/dynamic_session_border_0", Integer.valueOf(R.layout.dynamic_session_border));
        }

        private InnerLayoutIdLookup() {
        }
    }

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_tarazed_primer, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.control_bar, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dynamic_session_border, 3);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.amazon.tarazed.core.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            }
            if (i2 == 1) {
                if ("layout/activity_tarazed_primer_0".equals(tag)) {
                    return new ActivityTarazedPrimerBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for activity_tarazed_primer is invalid. Received: ", tag));
            } else if (i2 == 2) {
                if ("layout/control_bar_0".equals(tag)) {
                    return new ControlBarBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for control_bar is invalid. Received: ", tag));
            } else if (i2 != 3) {
                return null;
            } else {
                if ("layout/dynamic_session_border_0".equals(tag)) {
                    return new DynamicSessionBorderBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for dynamic_session_border is invalid. Received: ", tag));
            }
        }
        return null;
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
