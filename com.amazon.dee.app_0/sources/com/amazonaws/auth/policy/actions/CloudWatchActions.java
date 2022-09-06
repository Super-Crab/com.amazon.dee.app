package com.amazonaws.auth.policy.actions;

import com.amazonaws.auth.policy.Action;
/* loaded from: classes13.dex */
public enum CloudWatchActions implements Action {
    AllCloudWatchActions("cloudwatch:*"),
    DeleteAlarms("cloudwatch:DeleteAlarms"),
    DescribeAlarmHistory("cloudwatch:DescribeAlarmHistory"),
    DescribeAlarms("cloudwatch:DescribeAlarms"),
    DescribeAlarmsForMetric("cloudwatch:DescribeAlarmsForMetric"),
    DisableAlarmActions("cloudwatch:DisableAlarmActions"),
    EnableAlarmActions("cloudwatch:EnableAlarmActions"),
    GetMetricStatistics("cloudwatch:GetMetricStatistics"),
    ListMetrics("cloudwatch:ListMetrics"),
    PutMetricAlarm("cloudwatch:PutMetricAlarm"),
    PutMetricData("cloudwatch:PutMetricData"),
    SetAlarmState("cloudwatch:SetAlarmState");
    
    private final String action;

    CloudWatchActions(String str) {
        this.action = str;
    }

    @Override // com.amazonaws.auth.policy.Action
    public String getActionName() {
        return this.action;
    }
}
