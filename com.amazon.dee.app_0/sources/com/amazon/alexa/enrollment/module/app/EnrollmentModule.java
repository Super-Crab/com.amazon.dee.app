package com.amazon.alexa.enrollment.module.app;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.model.EnrollmentGateway;
import com.amazon.alexa.enrollment.model.EnrollmentServiceFactory;
import com.amazon.alexa.enrollment.route.EnrollmentRoutingAdapter;
import com.amazon.alexa.enrollment.route.KidsEnrollmentRoutingAdapter;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.dee.app.http.CoralService;
import com.dee.app.http.UrlResolver;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes7.dex */
public class EnrollmentModule {
    @Provides
    public EnrollmentRoutingAdapter provideEnrollmentRoutingAdapter(Lazy<Context> lazy, Lazy<EnrollmentGateway> lazy2) {
        return new EnrollmentRoutingAdapter(lazy, lazy2);
    }

    @Provides
    public EnrollmentGateway provideEnrollmentService(Lazy<Context> lazy, Lazy<CoralService> lazy2, Lazy<PersonIdProvider> lazy3, Lazy<Mobilytics> lazy4, Lazy<DeviceInformation> lazy5, Lazy<IdentityService> lazy6, Lazy<EventBus> lazy7, Lazy<RoutingService> lazy8, Lazy<UrlResolver> lazy9, Lazy<EnvironmentService> lazy10) {
        return EnrollmentServiceFactory.create(lazy, lazy2, lazy3, lazy5, lazy4, lazy6, lazy7, lazy8, lazy9, lazy10);
    }

    @Provides
    public KidsEnrollmentRoutingAdapter provideKidsEnrollmentRoutingAdapter(Lazy<Context> lazy, Lazy<EnrollmentGateway> lazy2, Lazy<IdentityService> lazy3) {
        return new KidsEnrollmentRoutingAdapter(lazy, lazy2, lazy3);
    }
}
