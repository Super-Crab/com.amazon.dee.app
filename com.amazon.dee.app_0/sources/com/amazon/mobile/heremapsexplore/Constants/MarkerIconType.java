package com.amazon.mobile.heremapsexplore.Constants;

import com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable;
import com.amazon.mobile.heremapsexplore.Factories.ImageFactory;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum MarkerIconType implements MarkerLayoutPositionable, ImageIndexable {
    SMALL_ACTIVE_FETCH_ICON_TYPE("SmallActiveFetch") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.1
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_FETCH_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_TILE_ICON_TYPE("SmallActiveTile") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.2
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_TILE_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_ECHO_AUTO_ICON_TYPE("SmallActiveEchoAuto") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.3
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_ECHO_AUTO_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_LEFT_BUD_ICON_TYPE("SmallActiveLeftBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.4
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_LEFT_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_LEFT_BUD_V2_ICON_TYPE("SmallActiveLeftBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.5
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_LEFT_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_RIGHT_BUD_ICON_TYPE("SmallActiveRightBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.6
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_RIGHT_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_RIGHT_BUD_V2_ICON_TYPE("SmallActiveRightBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.7
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_RIGHT_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_ECHO_BUD_ICON_TYPE("SmallActiveEchoBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.8
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_ECHO_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_ECHO_BUD_V2_ICON_TYPE("SmallActiveEchoBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.9
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_ECHO_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_SMART_THINGS_ICON_TYPE("SmallActiveSmartThings") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.10
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_SMART_THINGS_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_ACTIVE_DEFAULT_ICON_TYPE("SmallActiveDefault") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.11
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_ACTIVE_DEFAULT_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_FETCH_ICON_TYPE("SmallInactiveFetch") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.12
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_FETCH_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_TILE_ICON_TYPE("SmallInactiveTile") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.13
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_TILE_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_ECHO_AUTO_ICON_TYPE("SmallInactiveEchoAuto") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.14
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_ECHO_AUTO_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_LEFT_BUD_ICON_TYPE("SmallInactiveLeftBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.15
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_LEFT_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_LEFT_BUD_V2_ICON_TYPE("SmallInactiveLeftBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.16
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_LEFT_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_RIGHT_BUD_ICON_TYPE("SmallInactiveRightBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.17
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_RIGHT_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_RIGHT_BUD_V2_ICON_TYPE("SmallInactiveRightBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.18
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_RIGHT_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_ECHO_BUD_ICON_TYPE("SmallInactiveEchoBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.19
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_ECHO_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_ECHO_BUD_V2_ICON_TYPE("SmallInactiveEchoBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.20
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_ECHO_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_SMART_THINGS_ICON_TYPE("SmallInactiveSmartThings") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.21
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_SMART_THINGS_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    SMALL_INACTIVE_DEFAULT_ICON_TYPE("SmallInactiveDefault") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.22
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.SMALL_INACTIVE_DEFAULT_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    LARGE_ACTIVE_FETCH_ICON_TYPE("LargeActiveFetch") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.23
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_FETCH_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_TILE_ICON_TYPE("LargeActiveTile") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.24
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_TILE_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_ECHO_AUTO_ICON_TYPE("LargeActiveEchoAuto") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.25
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_ECHO_AUTO_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_LEFT_BUD_ICON_TYPE("LargeActiveLeftBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.26
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_LEFT_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_LEFT_BUD_V2_ICON_TYPE("LargeActiveLeftBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.27
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_LEFT_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_RIGHT_BUD_ICON_TYPE("LargeActiveRightBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.28
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_RIGHT_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_RIGHT_BUD_V2_ICON_TYPE("LargeActiveRightBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.29
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_RIGHT_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_ECHO_BUD_ICON_TYPE("LargeActiveEchoBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.30
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_ECHO_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_ECHO_BUD_V2_ICON_TYPE("LargeActiveEchoBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.31
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_ECHO_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_ACTIVE_SMART_THINGS_ICON_TYPE("LargeActiveSmartThings") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.32
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_SMART_THINGS_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    LARGE_ACTIVE_DEFAULT_ICON_TYPE("LargeActiveDefault") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.33
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_ACTIVE_DEFAULT_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_FETCH_ICON_TYPE("LargeInactiveFetch") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.34
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_FETCH_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_TILE_ICON_TYPE("LargeInactiveTile") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.35
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_TILE_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_ECHO_AUTO_ICON_TYPE("LargeInactiveEchoAuto") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.36
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_ECHO_AUTO_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_LEFT_BUD_ICON_TYPE("LargeInactiveLeftBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.37
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_LEFT_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_LEFT_BUD_V2_ICON_TYPE("LargeInactiveLeftBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.38
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_LEFT_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_RIGHT_BUD_ICON_TYPE("LargeInactiveRightBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.39
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_RIGHT_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_RIGHT_BUD_V2_ICON_TYPE("LargeInactiveRightBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.40
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_RIGHT_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_ECHO_BUD_ICON_TYPE("LargeInactiveEchoBud") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.41
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_ECHO_BUD_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_ECHO_BUD_V2_ICON_TYPE("LargeInactiveEchoBudV2") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.42
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_ECHO_BUD_V2_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    LARGE_INACTIVE_SMART_THINGS_ICON_TYPE("LargeInactiveSmartThings") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.43
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_SMART_THINGS_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    },
    LARGE_INACTIVE_DEFAULT_ICON_TYPE("LargeInactiveDefault") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.44
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.LARGE_INACTIVE_DEFAULT_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.BOTTOM_CENTER;
        }
    },
    CLUSTER_ICON("ClusterIcon") { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerIconType.45
        @Override // com.amazon.mobile.heremapsexplore.Constants.ImageIndexable
        public ImageFactory.ImageId getImageId() {
            return ImageFactory.ImageId.CLUSTER_ICON;
        }

        @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable
        public MarkerLayoutPositionable.LayoutPosition getLayoutPosition() {
            return MarkerLayoutPositionable.LayoutPosition.CENTER;
        }
    };
    
    private static final Map<String, MarkerIconType> map = new HashMap(values().length, 1.0f);
    private final String name;

    static {
        MarkerIconType[] values;
        for (MarkerIconType markerIconType : values()) {
            map.put(markerIconType.name, markerIconType);
        }
    }

    public static MarkerIconType of(String str) {
        return map.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }

    MarkerIconType(String str) {
        this.name = str;
    }
}
