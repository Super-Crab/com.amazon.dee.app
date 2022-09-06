package com.amazon.photos.discovery.internal.util;

import android.util.Log;
import com.amazon.clouddrive.cdasdk.cds.common.ContentProperties;
import com.amazon.clouddrive.cdasdk.cds.common.ISO8601;
import com.amazon.clouddrive.cdasdk.cds.common.ImageProperties;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.common.VideoProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.ParseException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NodeUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001e\u0010\b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006J\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/NodeUtils;", "", "()V", "getNodeContentDate", "", "node", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "getNodeCreatedDate", "getNodeImageResolution", "Lkotlin/Pair;", "", "getNodeMD5", "", "getNodeVideoDuration", "", "(Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;)Ljava/lang/Double;", "getNodeVideoResolution", "getNodeVisualDigest", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class NodeUtils {
    public static final NodeUtils INSTANCE = new NodeUtils();

    private NodeUtils() {
    }

    public final long getNodeContentDate(@NotNull NodeInfo node) {
        ISO8601 contentDate;
        Intrinsics.checkParameterIsNotNull(node, "node");
        try {
            ContentProperties contentProperties = node.getContentProperties();
            if (contentProperties == null || (contentDate = contentProperties.getContentDate()) == null) {
                return 0L;
            }
            DateUtils dateUtils = DateUtils.INSTANCE;
            String value = contentDate.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "it.value");
            return dateUtils.getDateFromString(value).getTime();
        } catch (ParseException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not parse content date for node: ");
            outline107.append(node.getId());
            Log.e("NodeUtils", outline107.toString());
            return 0L;
        }
    }

    public final long getNodeCreatedDate(@NotNull NodeInfo node) {
        Intrinsics.checkParameterIsNotNull(node, "node");
        try {
            ISO8601 createdDate = node.getCreatedDate();
            if (createdDate == null) {
                return 0L;
            }
            DateUtils dateUtils = DateUtils.INSTANCE;
            String value = createdDate.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "it.value");
            return dateUtils.getDateFromString(value).getTime();
        } catch (ParseException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not parse created date for node: ");
            outline107.append(node.getId());
            Log.e("NodeUtils", outline107.toString());
            return 0L;
        }
    }

    @NotNull
    public final Pair<Integer, Integer> getNodeImageResolution(@NotNull NodeInfo node) {
        ImageProperties image;
        ImageProperties image2;
        Intrinsics.checkParameterIsNotNull(node, "node");
        ContentProperties contentProperties = node.getContentProperties();
        Integer num = null;
        Integer width = (contentProperties == null || (image2 = contentProperties.getImage()) == null) ? null : image2.getWidth();
        ContentProperties contentProperties2 = node.getContentProperties();
        if (contentProperties2 != null && (image = contentProperties2.getImage()) != null) {
            num = image.getHeight();
        }
        return new Pair<>(width, num);
    }

    @Nullable
    public final String getNodeMD5(@NotNull NodeInfo node) {
        Intrinsics.checkParameterIsNotNull(node, "node");
        ContentProperties contentProperties = node.getContentProperties();
        if (contentProperties != null) {
            return contentProperties.getMd5();
        }
        return null;
    }

    @Nullable
    public final Double getNodeVideoDuration(@NotNull NodeInfo node) {
        VideoProperties video;
        Intrinsics.checkParameterIsNotNull(node, "node");
        ContentProperties contentProperties = node.getContentProperties();
        if (contentProperties == null || (video = contentProperties.getVideo()) == null) {
            return null;
        }
        return video.getDuration();
    }

    @NotNull
    public final Pair<Integer, Integer> getNodeVideoResolution(@NotNull NodeInfo node) {
        VideoProperties video;
        VideoProperties video2;
        Intrinsics.checkParameterIsNotNull(node, "node");
        ContentProperties contentProperties = node.getContentProperties();
        Integer num = null;
        Integer width = (contentProperties == null || (video2 = contentProperties.getVideo()) == null) ? null : video2.getWidth();
        ContentProperties contentProperties2 = node.getContentProperties();
        if (contentProperties2 != null && (video = contentProperties2.getVideo()) != null) {
            num = video.getHeight();
        }
        return new Pair<>(width, num);
    }

    @Nullable
    public final String getNodeVisualDigest(@NotNull NodeInfo node) {
        Intrinsics.checkParameterIsNotNull(node, "node");
        return null;
    }
}
