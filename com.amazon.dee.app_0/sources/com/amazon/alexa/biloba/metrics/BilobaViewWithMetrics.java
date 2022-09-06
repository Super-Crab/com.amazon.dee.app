package com.amazon.alexa.biloba.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.generated.models.Card;
import com.amazon.alexa.biloba.view.cards.NotificationCard;
import com.amazon.alexa.biloba.view.cards.TipsCard;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public abstract class BilobaViewWithMetrics {
    private static final String TAG = "BilobaViewWithMetrics";
    @Inject
    protected BilobaMetricsService bilobaMetricsService;

    private synchronized void inject() {
        if (this.bilobaMetricsService == null) {
            BilobaDependencies.inject(this);
        }
    }

    public void recordCardsClickMetric(@NonNull Card card, @NonNull String str) {
        inject();
        if (this.bilobaMetricsService != null) {
            if (Card.CategoryEnum.FEATURE_DISCOVERY_TIP.equals(card.getCategory())) {
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("TipsCard.", str, ".");
                outline115.append(card.getType());
                recordClickMetric(outline115.toString(), "");
            } else if (!Card.CategoryEnum.NOTIFICATION_ALERT.equals(card.getCategory())) {
            } else {
                StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("AlertCard.", str, ".");
                outline1152.append(card.getType());
                recordClickMetric(outline1152.toString(), "");
            }
        }
    }

    public void recordCardsViewMetric(@NonNull List<BaseRecyclerItem> list, @NonNull String str) {
        inject();
        if (this.bilobaMetricsService != null) {
            for (BaseRecyclerItem baseRecyclerItem : list) {
                if (baseRecyclerItem instanceof TipsCard) {
                    Card card = ((TipsCard) baseRecyclerItem).getCard();
                    StringBuilder outline115 = GeneratedOutlineSupport1.outline115("TipsCard.", str, ".");
                    outline115.append(card.getType());
                    recordViewMetric(outline115.toString(), "");
                }
                if (baseRecyclerItem instanceof NotificationCard) {
                    Card card2 = ((NotificationCard) baseRecyclerItem).getCard();
                    StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("AlertCard.", str, ".");
                    outline1152.append(card2.getType());
                    recordViewMetric(outline1152.toString(), "");
                }
            }
        }
    }

    public void recordClickMetric(@NonNull String str, @NonNull String str2) {
        inject();
        BilobaMetricsService bilobaMetricsService = this.bilobaMetricsService;
        if (bilobaMetricsService != null) {
            bilobaMetricsService.recordUserClick(str, str2);
        }
    }

    public void recordCommsCall(@NonNull String str, @NonNull String str2) {
        inject();
        BilobaMetricsService bilobaMetricsService = this.bilobaMetricsService;
        if (bilobaMetricsService != null) {
            bilobaMetricsService.recordUserCommsCall(str, str2);
        }
    }

    public void recordCommsMessage(@NonNull String str, @NonNull String str2) {
        inject();
        BilobaMetricsService bilobaMetricsService = this.bilobaMetricsService;
        if (bilobaMetricsService != null) {
            bilobaMetricsService.recordUserCommsMessage(str, str2);
        }
    }

    public void recordOccurrence(@NonNull String str, @NonNull boolean z) {
        inject();
        BilobaMetricsService bilobaMetricsService = this.bilobaMetricsService;
        if (bilobaMetricsService != null) {
            bilobaMetricsService.recordOccurrence(str, z);
        }
    }

    public void recordViewMetric(@NonNull String str, @NonNull String str2) {
        inject();
        BilobaMetricsService bilobaMetricsService = this.bilobaMetricsService;
        if (bilobaMetricsService != null) {
            bilobaMetricsService.recordUserView(str, str2);
        }
    }
}
