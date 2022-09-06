package com.amazonaws.auth.policy.actions;

import com.amazonaws.auth.policy.Action;
/* loaded from: classes13.dex */
public enum ElasticLoadBalancingActions implements Action {
    AllElasticLoadBalancingActions("elasticloadbalancing:*"),
    AddTags("elasticloadbalancing:AddTags"),
    ApplySecurityGroupsToLoadBalancer("elasticloadbalancing:ApplySecurityGroupsToLoadBalancer"),
    AttachLoadBalancerToSubnets("elasticloadbalancing:AttachLoadBalancerToSubnets"),
    ConfigureHealthCheck("elasticloadbalancing:ConfigureHealthCheck"),
    CreateAppCookieStickinessPolicy("elasticloadbalancing:CreateAppCookieStickinessPolicy"),
    CreateLBCookieStickinessPolicy("elasticloadbalancing:CreateLBCookieStickinessPolicy"),
    CreateLoadBalancer("elasticloadbalancing:CreateLoadBalancer"),
    CreateLoadBalancerListeners("elasticloadbalancing:CreateLoadBalancerListeners"),
    CreateLoadBalancerPolicy("elasticloadbalancing:CreateLoadBalancerPolicy"),
    DeleteLoadBalancer("elasticloadbalancing:DeleteLoadBalancer"),
    DeleteLoadBalancerListeners("elasticloadbalancing:DeleteLoadBalancerListeners"),
    DeleteLoadBalancerPolicy("elasticloadbalancing:DeleteLoadBalancerPolicy"),
    DeregisterInstancesFromLoadBalancer("elasticloadbalancing:DeregisterInstancesFromLoadBalancer"),
    DescribeAccountLimits("elasticloadbalancing:DescribeAccountLimits"),
    DescribeInstanceHealth("elasticloadbalancing:DescribeInstanceHealth"),
    DescribeLoadBalancerAttributes("elasticloadbalancing:DescribeLoadBalancerAttributes"),
    DescribeLoadBalancerPolicies("elasticloadbalancing:DescribeLoadBalancerPolicies"),
    DescribeLoadBalancerPolicyTypes("elasticloadbalancing:DescribeLoadBalancerPolicyTypes"),
    DescribeLoadBalancers("elasticloadbalancing:DescribeLoadBalancers"),
    DescribeTags("elasticloadbalancing:DescribeTags"),
    DetachLoadBalancerFromSubnets("elasticloadbalancing:DetachLoadBalancerFromSubnets"),
    DisableAvailabilityZonesForLoadBalancer("elasticloadbalancing:DisableAvailabilityZonesForLoadBalancer"),
    EnableAvailabilityZonesForLoadBalancer("elasticloadbalancing:EnableAvailabilityZonesForLoadBalancer"),
    ModifyLoadBalancerAttributes("elasticloadbalancing:ModifyLoadBalancerAttributes"),
    RegisterInstancesWithLoadBalancer("elasticloadbalancing:RegisterInstancesWithLoadBalancer"),
    RemoveTags("elasticloadbalancing:RemoveTags"),
    SetLoadBalancerListenerSSLCertificate("elasticloadbalancing:SetLoadBalancerListenerSSLCertificate"),
    SetLoadBalancerPoliciesForBackendServer("elasticloadbalancing:SetLoadBalancerPoliciesForBackendServer"),
    SetLoadBalancerPoliciesOfListener("elasticloadbalancing:SetLoadBalancerPoliciesOfListener");
    
    private final String action;

    ElasticLoadBalancingActions(String str) {
        this.action = str;
    }

    @Override // com.amazonaws.auth.policy.Action
    public String getActionName() {
        return this.action;
    }
}
