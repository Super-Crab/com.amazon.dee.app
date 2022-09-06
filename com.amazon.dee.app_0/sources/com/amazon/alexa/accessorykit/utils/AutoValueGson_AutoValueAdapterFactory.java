package com.amazon.alexa.accessorykit.utils;

import com.amazon.alexa.accessorykit.findmy.reporter.Altitude;
import com.amazon.alexa.accessorykit.findmy.reporter.Coordinate;
import com.amazon.alexa.accessorykit.findmy.reporter.DeviceInfo;
import com.amazon.alexa.accessorykit.findmy.reporter.Geolocation;
import com.amazon.alexa.accessorykit.findmy.reporter.Heading;
import com.amazon.alexa.accessorykit.findmy.reporter.ReportLocationsRequest;
import com.amazon.alexa.accessorykit.findmy.reporter.ReportLocationsResponse;
import com.amazon.alexa.accessorykit.findmy.reporter.Speed;
import com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice;
import com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
/* loaded from: classes6.dex */
public final class AutoValueGson_AutoValueAdapterFactory extends AutoValueAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (Altitude.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Altitude.typeAdapter(gson);
        }
        if (Coordinate.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Coordinate.typeAdapter(gson);
        }
        if (DeviceInfo.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) DeviceInfo.typeAdapter(gson);
        }
        if (Geolocation.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Geolocation.typeAdapter(gson);
        }
        if (Heading.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Heading.typeAdapter(gson);
        }
        if (ReportLocationsRequest.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) ReportLocationsRequest.typeAdapter(gson);
        }
        if (ReportLocationsResponse.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) ReportLocationsResponse.typeAdapter(gson);
        }
        if (Speed.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) Speed.typeAdapter(gson);
        }
        if (TrackableDevice.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) TrackableDevice.typeAdapter(gson);
        }
        if (!TrackableDevicesLocation.class.isAssignableFrom(rawType)) {
            return null;
        }
        return (TypeAdapter<T>) TrackableDevicesLocation.typeAdapter(gson);
    }
}
