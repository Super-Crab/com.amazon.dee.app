package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.util.concurrent.Service;
@GwtIncompatible
/* loaded from: classes3.dex */
interface ServiceManagerBridge {
    /* renamed from: servicesByState */
    ImmutableMultimap<Service.State, Service> mo8285servicesByState();
}
