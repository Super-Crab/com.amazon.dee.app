package com.amazon.alexa.photos;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.common.ResourceVersion;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeRequest;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes9.dex */
public class CdsRequestHelperV2 {
    private static final String TAG = "CdsRequestHelperV2";
    private final CDClient cdClient;
    private final Metrics metrics;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CdsRequestHelperV2(@NonNull CDClient cDClient, @NonNull Metrics metrics) {
        this.cdClient = cDClient;
        this.metrics = metrics;
    }

    public String getAlbumIdAssociatedWithFolder(List<NodeInfo> list, String str) {
        Iterator<NodeInfo> it2 = list.iterator();
        while (it2.hasNext()) {
            NodeInfo next = it2.next();
            try {
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                Log.e(TAG, "Failed to determine if Album and Folder are linked", e);
                this.metrics.recordSimpleErrorEvent(TAG, $$Lambda$CdsRequestHelperV2$29WsGlU9p_BmlB9pTacjKSYprD0.INSTANCE, e);
            }
            if (str.equals(next.getCollectionProperties().getQuery().getInclude().get(0).getFolderIds().get(0))) {
                return next.getId();
            }
            continue;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListNodeRequest getListNodeDevicesAlbumRequest() {
        ListNodeRequest listNodeRequest = new ListNodeRequest();
        listNodeRequest.setFilters(String.format("status:(AVAILABLE) AND kind:%s AND labels:DevicesAlbum", "VISUAL_COLLECTION"));
        listNodeRequest.setSort("[\"createdDate DESC\"]");
        listNodeRequest.setResourceVersion(ResourceVersion.V2);
        return listNodeRequest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListNodeRequest getListNodeDevicesFolderRequest() {
        ListNodeRequest listNodeRequest = new ListNodeRequest();
        listNodeRequest.setFilters(String.format("status:(AVAILABLE) AND kind:%s AND labels:DevicesAlbum", "FOLDER"));
        listNodeRequest.setSort("[\"createdDate DESC\"]");
        listNodeRequest.setResourceVersion(ResourceVersion.V2);
        return listNodeRequest;
    }
}
