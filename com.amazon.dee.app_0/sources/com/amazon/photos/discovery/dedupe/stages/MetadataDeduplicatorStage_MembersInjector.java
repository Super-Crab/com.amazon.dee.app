package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEvents;
import com.amazon.photos.discovery.internal.dedupe.metadata.DateMatcher;
import com.amazon.photos.discovery.internal.dedupe.metadata.ItemMappingUtils;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class MetadataDeduplicatorStage_MembersInjector implements MembersInjector<MetadataDeduplicatorStage> {
    private final Provider<DateMatcher> dateMatcherProvider;
    private final Provider<DedupeFilter> dedupeFilterProvider;
    private final Provider<FilterEvents> filterEventsProvider;
    private final Provider<ItemMappingUtils> itemMappingUtilsProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;
    private final Provider<NodeUtils> nodeUtilsProvider;

    public MetadataDeduplicatorStage_MembersInjector(Provider<Logger> provider, Provider<Metrics> provider2, Provider<ItemMappingUtils> provider3, Provider<DateMatcher> provider4, Provider<FilterEvents> provider5, Provider<DedupeFilter> provider6, Provider<NodeUtils> provider7) {
        this.loggerProvider = provider;
        this.metricsProvider = provider2;
        this.itemMappingUtilsProvider = provider3;
        this.dateMatcherProvider = provider4;
        this.filterEventsProvider = provider5;
        this.dedupeFilterProvider = provider6;
        this.nodeUtilsProvider = provider7;
    }

    public static MembersInjector<MetadataDeduplicatorStage> create(Provider<Logger> provider, Provider<Metrics> provider2, Provider<ItemMappingUtils> provider3, Provider<DateMatcher> provider4, Provider<FilterEvents> provider5, Provider<DedupeFilter> provider6, Provider<NodeUtils> provider7) {
        return new MetadataDeduplicatorStage_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectDateMatcher(MetadataDeduplicatorStage metadataDeduplicatorStage, DateMatcher dateMatcher) {
        metadataDeduplicatorStage.dateMatcher = dateMatcher;
    }

    public static void injectDedupeFilter(MetadataDeduplicatorStage metadataDeduplicatorStage, DedupeFilter dedupeFilter) {
        metadataDeduplicatorStage.dedupeFilter = dedupeFilter;
    }

    public static void injectFilterEvents(MetadataDeduplicatorStage metadataDeduplicatorStage, FilterEvents filterEvents) {
        metadataDeduplicatorStage.filterEvents = filterEvents;
    }

    public static void injectItemMappingUtils(MetadataDeduplicatorStage metadataDeduplicatorStage, ItemMappingUtils itemMappingUtils) {
        metadataDeduplicatorStage.itemMappingUtils = itemMappingUtils;
    }

    public static void injectLogger(MetadataDeduplicatorStage metadataDeduplicatorStage, Logger logger) {
        metadataDeduplicatorStage.logger = logger;
    }

    public static void injectMetrics(MetadataDeduplicatorStage metadataDeduplicatorStage, Metrics metrics) {
        metadataDeduplicatorStage.metrics = metrics;
    }

    public static void injectNodeUtils(MetadataDeduplicatorStage metadataDeduplicatorStage, NodeUtils nodeUtils) {
        metadataDeduplicatorStage.nodeUtils = nodeUtils;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MetadataDeduplicatorStage metadataDeduplicatorStage) {
        injectLogger(metadataDeduplicatorStage, this.loggerProvider.mo10268get());
        injectMetrics(metadataDeduplicatorStage, this.metricsProvider.mo10268get());
        injectItemMappingUtils(metadataDeduplicatorStage, this.itemMappingUtilsProvider.mo10268get());
        injectDateMatcher(metadataDeduplicatorStage, this.dateMatcherProvider.mo10268get());
        injectFilterEvents(metadataDeduplicatorStage, this.filterEventsProvider.mo10268get());
        injectDedupeFilter(metadataDeduplicatorStage, this.dedupeFilterProvider.mo10268get());
        injectNodeUtils(metadataDeduplicatorStage, this.nodeUtilsProvider.mo10268get());
    }
}
