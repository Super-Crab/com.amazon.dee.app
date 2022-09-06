package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.drive.navigation.SavedLocations;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse;
import com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
/* loaded from: classes7.dex */
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
        if (GetCommuteDestinationRequest.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) GetCommuteDestinationRequest.typeAdapter(gson);
        }
        if (GetCommuteDestinationResponse.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) GetCommuteDestinationResponse.typeAdapter(gson);
        }
        if (GetCommuteDetailsRequest.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) GetCommuteDetailsRequest.typeAdapter(gson);
        }
        if (GetCommuteDetailsResponse.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) GetCommuteDetailsResponse.typeAdapter(gson);
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
        if (SavedLocations.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SavedLocations.typeAdapter(gson);
        }
        if (SavedLocations.Item.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SavedLocations.Item.typeAdapter(gson);
        }
        if (SavedLocations.Item.Address.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SavedLocations.Item.Address.typeAdapter(gson);
        }
        if (SavedLocations.Item.Coordinate.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SavedLocations.Item.Coordinate.typeAdapter(gson);
        }
        if (SavedLocations.Links.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SavedLocations.Links.typeAdapter(gson);
        }
        if (SendCommuteNotificationRequest.class.isAssignableFrom(rawType)) {
            return (TypeAdapter<T>) SendCommuteNotificationRequest.typeAdapter(gson);
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
