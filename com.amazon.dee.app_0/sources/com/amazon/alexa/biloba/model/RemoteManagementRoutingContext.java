package com.amazon.alexa.biloba.model;

import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsFreeConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class RemoteManagementRoutingContext {
    @SerializedName("errorRoute")
    private String errorRoute;
    @SerializedName("resourceOwner")
    private ResourceOwner resourceOwner;
    @SerializedName(HandsFreeConstants.SUCCESS_ROUTE)
    private String successRoute;

    public RemoteManagementRoutingContext(ResourceOwner resourceOwner, String str, String str2) {
        this.resourceOwner = resourceOwner;
        this.successRoute = str;
        this.errorRoute = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RemoteManagementRoutingContext.class != obj.getClass()) {
            return false;
        }
        RemoteManagementRoutingContext remoteManagementRoutingContext = (RemoteManagementRoutingContext) obj;
        return this.resourceOwner.equals(remoteManagementRoutingContext.resourceOwner) && this.successRoute.equals(remoteManagementRoutingContext.successRoute) && this.errorRoute.equals(remoteManagementRoutingContext.errorRoute);
    }

    public String getErrorRoute() {
        return this.errorRoute;
    }

    public ResourceOwner getResourceOwner() {
        return this.resourceOwner;
    }

    public String getSuccessRoute() {
        return this.successRoute;
    }

    public int hashCode() {
        return Objects.hash(this.resourceOwner, this.successRoute, this.errorRoute);
    }

    public void setErrorRoute(String str) {
        this.errorRoute = str;
    }

    public void setResourceOwner(ResourceOwner resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public void setSuccessRoute(String str) {
        this.successRoute = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RemoteManagementRoutingContext{resourceOwner=");
        outline107.append(this.resourceOwner);
        outline107.append(", successRoute='");
        GeneratedOutlineSupport1.outline176(outline107, this.successRoute, Chars.QUOTE, ", errorRoute='");
        return GeneratedOutlineSupport1.outline90(outline107, this.errorRoute, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
