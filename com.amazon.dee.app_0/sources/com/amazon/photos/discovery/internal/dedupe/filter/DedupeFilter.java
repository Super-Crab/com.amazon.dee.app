package com.amazon.photos.discovery.internal.dedupe.filter;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.photos.discovery.model.UnifiedItem;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DedupeFilter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\b"}, d2 = {"Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "", "Lcom/amazon/photos/discovery/model/UnifiedItem;", "metricsTag", "", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface DedupeFilter {
    @NotNull
    Collection<UnifiedItem> filter(@NotNull String str, @NotNull Collection<UnifiedItem> collection);
}
