package com.amazonaws.services.s3.model.analytics;
/* loaded from: classes13.dex */
public interface AnalyticsPredicateVisitor {
    void visit(AnalyticsAndOperator analyticsAndOperator);

    void visit(AnalyticsPrefixPredicate analyticsPrefixPredicate);

    void visit(AnalyticsTagPredicate analyticsTagPredicate);
}
