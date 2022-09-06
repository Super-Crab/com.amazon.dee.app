package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlertConfigurationsResponse {
    @SerializedName("alertConfigurations")
    private List<AlertConfiguration> alertConfigurations = null;
    @SerializedName("paginationContext")
    private PaginationContext paginationContext = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public AlertConfigurationsResponse addAlertConfigurationsItem(AlertConfiguration alertConfiguration) {
        if (this.alertConfigurations == null) {
            this.alertConfigurations = new ArrayList();
        }
        this.alertConfigurations.add(alertConfiguration);
        return this;
    }

    public AlertConfigurationsResponse alertConfigurations(List<AlertConfiguration> list) {
        this.alertConfigurations = list;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AlertConfigurationsResponse.class != obj.getClass()) {
            return false;
        }
        AlertConfigurationsResponse alertConfigurationsResponse = (AlertConfigurationsResponse) obj;
        return Objects.equals(this.alertConfigurations, alertConfigurationsResponse.alertConfigurations) && Objects.equals(this.paginationContext, alertConfigurationsResponse.paginationContext);
    }

    public List<AlertConfiguration> getAlertConfigurations() {
        return this.alertConfigurations;
    }

    public PaginationContext getPaginationContext() {
        return this.paginationContext;
    }

    public int hashCode() {
        return Objects.hash(this.alertConfigurations, this.paginationContext);
    }

    public AlertConfigurationsResponse paginationContext(PaginationContext paginationContext) {
        this.paginationContext = paginationContext;
        return this;
    }

    public void setAlertConfigurations(List<AlertConfiguration> list) {
        this.alertConfigurations = list;
    }

    public void setPaginationContext(PaginationContext paginationContext) {
        this.paginationContext = paginationContext;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class AlertConfigurationsResponse {\n", "    alertConfigurations: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.alertConfigurations), "\n", "    paginationContext: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.paginationContext), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
