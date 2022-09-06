package com.amazon.photos.uploader;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Event.kt */
@Entity(tableName = DefaultDeliveryClient.EVENTS_DIRECTORY)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/uploader/Event;", "", "id", "", "description", "", "eventTimeMillis", "(JLjava/lang/String;J)V", "getDescription", "()Ljava/lang/String;", "getEventTimeMillis", "()J", "getId", "component1", "component2", "component3", "copy", "equals", "", "other", "getEventDate", "Ljava/util/Date;", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Event {
    @ColumnInfo(name = "description")
    @NotNull
    private final String description;
    @ColumnInfo(name = "event_time_millis")
    private final long eventTimeMillis;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final long id;

    public Event(long j, @NotNull String description, long j2) {
        Intrinsics.checkParameterIsNotNull(description, "description");
        this.id = j;
        this.description = description;
        this.eventTimeMillis = j2;
    }

    public static /* synthetic */ Event copy$default(Event event, long j, String str, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = event.id;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            str = event.description;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            j2 = event.eventTimeMillis;
        }
        return event.copy(j3, str2, j2);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.description;
    }

    public final long component3() {
        return this.eventTimeMillis;
    }

    @NotNull
    public final Event copy(long j, @NotNull String description, long j2) {
        Intrinsics.checkParameterIsNotNull(description, "description");
        return new Event(j, description, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Event)) {
                return false;
            }
            Event event = (Event) obj;
            return this.id == event.id && Intrinsics.areEqual(this.description, event.description) && this.eventTimeMillis == event.eventTimeMillis;
        }
        return true;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final Date getEventDate() {
        return new Date(this.eventTimeMillis);
    }

    public final long getEventTimeMillis() {
        return this.eventTimeMillis;
    }

    public final long getId() {
        return this.id;
    }

    public int hashCode() {
        long j = this.id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.description;
        int hashCode = str != null ? str.hashCode() : 0;
        long j2 = this.eventTimeMillis;
        return ((i + hashCode) * 31) + ((int) ((j2 >>> 32) ^ j2));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Event(id=");
        outline107.append(this.id);
        outline107.append(", description=");
        outline107.append(this.description);
        outline107.append(", eventTimeMillis=");
        return GeneratedOutlineSupport1.outline87(outline107, this.eventTimeMillis, ")");
    }

    public /* synthetic */ Event(long j, String str, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, j2);
    }
}
