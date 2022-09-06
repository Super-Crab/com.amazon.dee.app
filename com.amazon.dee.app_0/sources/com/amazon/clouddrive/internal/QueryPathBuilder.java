package com.amazon.clouddrive.internal;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.clouddrive.model.PaginatedCloudDriveRequest;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.AppUrl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
class QueryPathBuilder {
    private final StringBuilder mStringBuilder = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addParameter(String str, String str2) {
        if (str2 != null) {
            try {
                this.mStringBuilder.append(this.mStringBuilder.length() == 0 ? Constants.DEFAULT_IMAGE_CHAR : Typography.amp);
                StringBuilder sb = this.mStringBuilder;
                sb.append(str);
                sb.append(Chars.EQ);
                sb.append(URLEncoder.encode(str2, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Failed to encode ", str2), e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String addQueryToPath(String str) {
        if (this.mStringBuilder.length() == 0) {
            return str;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(this.mStringBuilder.toString());
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addRequestParameters(PaginatedCloudDriveRequest paginatedCloudDriveRequest) {
        addParameter("filters", paginatedCloudDriveRequest.getFilters());
        addParameter("fields", paginatedCloudDriveRequest.getFields());
        addParameter("startToken", paginatedCloudDriveRequest.getStartToken());
        addParameter(MetricsUtil.LegacyMetricTypes.LIMIT, paginatedCloudDriveRequest.getLimit());
        addParameter(AppUrl.ACMS.QueryParam.Keys.SORT_ORDER, paginatedCloudDriveRequest.getSort());
        addParameter("offset", paginatedCloudDriveRequest.getOffset());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addUTF8Parameter(String str, String str2) {
        if (str2 != null) {
            StringBuilder sb = this.mStringBuilder;
            sb.append(sb.length() == 0 ? Constants.DEFAULT_IMAGE_CHAR : Typography.amp);
            GeneratedOutlineSupport1.outline176(this.mStringBuilder, str, Chars.EQ, str2);
        }
    }

    void addParameter(String str, Integer num) {
        if (num != null) {
            addParameter(str, num.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addParameter(String str, Boolean bool) {
        if (bool != null) {
            addParameter(str, bool.toString());
        }
    }
}
