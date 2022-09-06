package com.amazon.alexa.redesign.view.carousel;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.redesign.entity.viewtypes.GridIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselAdapter extends RecyclerView.Adapter<CarouselViewHolder> {
    Context context;
    HomeMetricsRecorder metricsRecorder;
    Map<String, Object> topLevelMetricsObject;
    List<ViewTypeModel> viewTypeModels;

    /* renamed from: com.amazon.alexa.redesign.view.carousel.CarouselAdapter$1  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$redesign$view$carousel$CarouselItemType = new int[CarouselItemType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$carousel$CarouselItemType[CarouselItemType.ChipIconTitle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$carousel$CarouselItemType[CarouselItemType.ChipTitle.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$redesign$view$carousel$CarouselItemType[CarouselItemType.GridIconTitle.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public CarouselAdapter(List<ViewTypeModel> list, Context context, HomeMetricsRecorder homeMetricsRecorder, Map<String, Object> map) {
        this.viewTypeModels = list;
        this.context = context;
        this.metricsRecorder = homeMetricsRecorder;
        this.topLevelMetricsObject = map;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.viewTypeModels.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i < 0 || i >= this.viewTypeModels.size()) {
            return -1;
        }
        ViewTypeModel viewTypeModel = this.viewTypeModels.get(i);
        if (viewTypeModel instanceof ChipIconTitleModel) {
            return CarouselItemType.ChipIconTitle.getCarouselTypeIntValue();
        }
        if (!(viewTypeModel instanceof GridIconTitleModel)) {
            return -1;
        }
        return CarouselItemType.GridIconTitle.getCarouselTypeIntValue();
    }

    public ViewTypeModel getViewTypeModel(int i) {
        if (i < 0 || i >= this.viewTypeModels.size()) {
            return null;
        }
        return this.viewTypeModels.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull CarouselViewHolder carouselViewHolder, int i) {
        carouselViewHolder.bind(this.viewTypeModels.get(i), this.topLevelMetricsObject, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public CarouselViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int ordinal = CarouselItemType.from(i).ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return null;
            }
            if (ordinal != 2) {
                return new ChipIconTitleViewHolder(viewGroup, this.metricsRecorder);
            }
            return new GridIconTitleViewHolder(viewGroup, this.metricsRecorder);
        }
        return new ChipIconTitleViewHolder(viewGroup, this.metricsRecorder);
    }
}
