package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.dee.app.services.bluetooth.DefaultBluetoothService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
/* loaded from: classes6.dex */
public class Activity {
    @SerializedName("title")
    private String title = null;
    @SerializedName(DefaultBluetoothService.AUDIO_DEVICE_NAME)
    private String deviceName = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("category")
    private CategoryEnum category = null;
    @SerializedName("iconUrl")
    private String iconUrl = null;
    @SerializedName("iconAltText")
    private String iconAltText = null;
    @SerializedName("localizedDateTime")
    private LocalizedDateTime localizedDateTime = null;
    @SerializedName("utcDateTime")
    private Date utcDateTime = null;

    @JsonAdapter(Adapter.class)
    /* loaded from: classes6.dex */
    public enum CategoryEnum {
        VOICE_ACTIVITY("VOICE_ACTIVITY"),
        SMART_HOME("SMART_HOME"),
        CARE_NOTIFICATION("CARE_NOTIFICATION"),
        COMMUNICATIONS("COMMUNICATIONS"),
        PRESENCE("PRESENCE"),
        AUTOMATIONS("AUTOMATIONS"),
        NON_VOICE("NON_VOICE"),
        OTHER("OTHER");
        
        private String value;

        /* loaded from: classes6.dex */
        public static class Adapter extends TypeAdapter<CategoryEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public CategoryEnum mo8353read(JsonReader jsonReader) throws IOException {
                return CategoryEnum.fromValue(String.valueOf(jsonReader.nextString()));
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, CategoryEnum categoryEnum) throws IOException {
                jsonWriter.value(categoryEnum.getValue());
            }
        }

        CategoryEnum(String str) {
            this.value = str;
        }

        public static CategoryEnum fromValue(String str) {
            CategoryEnum[] values;
            for (CategoryEnum categoryEnum : values()) {
                if (String.valueOf(categoryEnum.value).equals(str)) {
                    return categoryEnum;
                }
            }
            return null;
        }

        public String getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public Activity category(CategoryEnum categoryEnum) {
        this.category = categoryEnum;
        return this;
    }

    public Activity description(String str) {
        this.description = str;
        return this;
    }

    public Activity deviceName(String str) {
        this.deviceName = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Activity.class != obj.getClass()) {
            return false;
        }
        Activity activity = (Activity) obj;
        return Objects.equals(this.title, activity.title) && Objects.equals(this.deviceName, activity.deviceName) && Objects.equals(this.description, activity.description) && Objects.equals(this.category, activity.category) && Objects.equals(this.iconUrl, activity.iconUrl) && Objects.equals(this.iconAltText, activity.iconAltText) && Objects.equals(this.localizedDateTime, activity.localizedDateTime) && Objects.equals(this.utcDateTime, activity.utcDateTime);
    }

    public CategoryEnum getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getIconAltText() {
        return this.iconAltText;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public LocalizedDateTime getLocalizedDateTime() {
        return this.localizedDateTime;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getUtcDateTime() {
        return this.utcDateTime;
    }

    public int hashCode() {
        return Objects.hash(this.title, this.deviceName, this.description, this.category, this.iconUrl, this.iconAltText, this.localizedDateTime, this.utcDateTime);
    }

    public Activity iconAltText(String str) {
        this.iconAltText = str;
        return this;
    }

    public Activity iconUrl(String str) {
        this.iconUrl = str;
        return this;
    }

    public Activity localizedDateTime(LocalizedDateTime localizedDateTime) {
        this.localizedDateTime = localizedDateTime;
        return this;
    }

    public void setCategory(CategoryEnum categoryEnum) {
        this.category = categoryEnum;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setIconAltText(String str) {
        this.iconAltText = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setLocalizedDateTime(LocalizedDateTime localizedDateTime) {
        this.localizedDateTime = localizedDateTime;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUtcDateTime(Date date) {
        this.utcDateTime = date;
    }

    public Activity title(String str) {
        this.title = str;
        return this;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class Activity {\n", "    title: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.title), "\n", "    deviceName: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.deviceName), "\n", "    description: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.description), "\n", "    category: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.category), "\n", "    iconUrl: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.iconUrl), "\n", "    iconAltText: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.iconAltText), "\n", "    localizedDateTime: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.localizedDateTime), "\n", "    utcDateTime: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.utcDateTime), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public Activity utcDateTime(Date date) {
        this.utcDateTime = date;
        return this;
    }
}
