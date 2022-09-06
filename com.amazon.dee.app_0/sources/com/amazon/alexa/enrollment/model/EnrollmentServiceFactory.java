package com.amazon.alexa.enrollment.model;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.dee.app.http.CoralService;
import com.dee.app.http.UrlResolver;
import dagger.Lazy;
/* loaded from: classes7.dex */
public final class EnrollmentServiceFactory {
    private EnrollmentServiceFactory() {
    }

    public static EnrollmentGateway create(Lazy<Context> lazy, Lazy<CoralService> lazy2, Lazy<PersonIdProvider> lazy3, Lazy<DeviceInformation> lazy4, Lazy<Mobilytics> lazy5, Lazy<IdentityService> lazy6, Lazy<EventBus> lazy7, Lazy<RoutingService> lazy8, Lazy<UrlResolver> lazy9, Lazy<EnvironmentService> lazy10) {
        Injector.initComponent(lazy, lazy2, lazy3, lazy5, lazy4, lazy6, lazy7, lazy8, lazy9, lazy10);
        return Injector.enrollmentService();
    }
}
