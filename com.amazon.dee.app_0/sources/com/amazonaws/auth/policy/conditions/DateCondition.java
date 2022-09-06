package com.amazonaws.auth.policy.conditions;

import com.amazonaws.auth.policy.Condition;
import com.amazonaws.util.DateUtils;
import java.util.Arrays;
import java.util.Date;
/* loaded from: classes13.dex */
public class DateCondition extends Condition {

    /* loaded from: classes13.dex */
    public enum DateComparisonType {
        DateEquals,
        DateGreaterThan,
        DateGreaterThanEquals,
        DateLessThan,
        DateLessThanEquals,
        DateNotEquals
    }

    public DateCondition(DateComparisonType dateComparisonType, Date date) {
        this.type = dateComparisonType.toString();
        this.conditionKey = ConditionFactory.CURRENT_TIME_CONDITION_KEY;
        this.values = Arrays.asList(DateUtils.formatISO8601Date(date));
    }
}
