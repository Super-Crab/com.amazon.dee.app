package com.amazonaws.auth.policy.actions;

import com.amazonaws.auth.policy.Action;
/* loaded from: classes13.dex */
public enum AutoScalingActions implements Action {
    AllAutoScalingActions("autoscaling:*"),
    AttachInstances("autoscaling:AttachInstances"),
    AttachLoadBalancerTargetGroups("autoscaling:AttachLoadBalancerTargetGroups"),
    AttachLoadBalancers("autoscaling:AttachLoadBalancers"),
    BatchDeleteScheduledAction("autoscaling:BatchDeleteScheduledAction"),
    BatchPutScheduledUpdateGroupAction("autoscaling:BatchPutScheduledUpdateGroupAction"),
    CompleteLifecycleAction("autoscaling:CompleteLifecycleAction"),
    CreateAutoScalingGroup("autoscaling:CreateAutoScalingGroup"),
    CreateLaunchConfiguration("autoscaling:CreateLaunchConfiguration"),
    CreateOrUpdateTags("autoscaling:CreateOrUpdateTags"),
    DeleteAutoScalingGroup("autoscaling:DeleteAutoScalingGroup"),
    DeleteLaunchConfiguration("autoscaling:DeleteLaunchConfiguration"),
    DeleteLifecycleHook("autoscaling:DeleteLifecycleHook"),
    DeleteNotificationConfiguration("autoscaling:DeleteNotificationConfiguration"),
    DeletePolicy("autoscaling:DeletePolicy"),
    DeleteScheduledAction("autoscaling:DeleteScheduledAction"),
    DeleteTags("autoscaling:DeleteTags"),
    DescribeAccountLimits("autoscaling:DescribeAccountLimits"),
    DescribeAdjustmentTypes("autoscaling:DescribeAdjustmentTypes"),
    DescribeAutoScalingGroups("autoscaling:DescribeAutoScalingGroups"),
    DescribeAutoScalingInstances("autoscaling:DescribeAutoScalingInstances"),
    DescribeAutoScalingNotificationTypes("autoscaling:DescribeAutoScalingNotificationTypes"),
    DescribeLaunchConfigurations("autoscaling:DescribeLaunchConfigurations"),
    DescribeLifecycleHookTypes("autoscaling:DescribeLifecycleHookTypes"),
    DescribeLifecycleHooks("autoscaling:DescribeLifecycleHooks"),
    DescribeLoadBalancerTargetGroups("autoscaling:DescribeLoadBalancerTargetGroups"),
    DescribeLoadBalancers("autoscaling:DescribeLoadBalancers"),
    DescribeMetricCollectionTypes("autoscaling:DescribeMetricCollectionTypes"),
    DescribeNotificationConfigurations("autoscaling:DescribeNotificationConfigurations"),
    DescribePolicies("autoscaling:DescribePolicies"),
    DescribeScalingActivities("autoscaling:DescribeScalingActivities"),
    DescribeScalingProcessTypes("autoscaling:DescribeScalingProcessTypes"),
    DescribeScheduledActions("autoscaling:DescribeScheduledActions"),
    DescribeTags("autoscaling:DescribeTags"),
    DescribeTerminationPolicyTypes("autoscaling:DescribeTerminationPolicyTypes"),
    DetachInstances("autoscaling:DetachInstances"),
    DetachLoadBalancerTargetGroups("autoscaling:DetachLoadBalancerTargetGroups"),
    DetachLoadBalancers("autoscaling:DetachLoadBalancers"),
    DisableMetricsCollection("autoscaling:DisableMetricsCollection"),
    EnableMetricsCollection("autoscaling:EnableMetricsCollection"),
    EnterStandby("autoscaling:EnterStandby"),
    ExecutePolicy("autoscaling:ExecutePolicy"),
    ExitStandby("autoscaling:ExitStandby"),
    PutLifecycleHook("autoscaling:PutLifecycleHook"),
    PutNotificationConfiguration("autoscaling:PutNotificationConfiguration"),
    PutScalingPolicy("autoscaling:PutScalingPolicy"),
    PutScheduledUpdateGroupAction("autoscaling:PutScheduledUpdateGroupAction"),
    RecordLifecycleActionHeartbeat("autoscaling:RecordLifecycleActionHeartbeat"),
    ResumeProcesses("autoscaling:ResumeProcesses"),
    SetDesiredCapacity("autoscaling:SetDesiredCapacity"),
    SetInstanceHealth("autoscaling:SetInstanceHealth"),
    SetInstanceProtection("autoscaling:SetInstanceProtection"),
    SuspendProcesses("autoscaling:SuspendProcesses"),
    TerminateInstanceInAutoScalingGroup("autoscaling:TerminateInstanceInAutoScalingGroup"),
    UpdateAutoScalingGroup("autoscaling:UpdateAutoScalingGroup");
    
    private final String action;

    AutoScalingActions(String str) {
        this.action = str;
    }

    @Override // com.amazonaws.auth.policy.Action
    public String getActionName() {
        return this.action;
    }
}
