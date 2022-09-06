package com.amazon.alexa.client.alexaservice.messages;

import com.amazon.alexa.client.core.capabilities.CapabilityInterface;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.dnp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AvsApiConstants {
    public static final Set<Namespace> zZm = new HashSet(Arrays.asList(SpeechRecognizer.zZm, AudioPlayer.zZm, AudioActivityTracker.zZm, Speaker.zZm, Geolocation.zZm, SpeechSynthesizer.zZm, ExternalMediaPlayer.zZm));
    public static final Set<Namespace> BIo = new HashSet(Arrays.asList(SpeechRecognizer.zZm, AudioPlayer.zZm, Speaker.zZm, Settings.zZm, System.zZm, Geolocation.zZm, PlaybackController.zZm, TemplateRuntime.zZm, CardRenderer.zZm, Navigation.zZm, SpeechSynthesizer.zZm, ExternalMediaPlayer.zZm));

    /* loaded from: classes6.dex */
    public static final class AccessoryKit {
        public static final Namespace zZm = Namespace.create(AccessoryKit.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(AccessoryKit.class.getSimpleName());
    }

    /* loaded from: classes6.dex */
    public static final class Alerts {
        public static final dnp BIo;
        public static final CapabilityInterface zZm;

        static {
            Namespace.create(Alerts.class.getSimpleName());
            zZm = CapabilityInterface.create(Alerts.class.getSimpleName());
            BIo = dnp.zZm(Alerts.class.getSimpleName());
        }
    }

    /* loaded from: classes6.dex */
    public static final class Alexa {

        /* loaded from: classes6.dex */
        public static final class AudioSignal {

            /* loaded from: classes6.dex */
            public static final class ActiveNoiseControl {
                public static final Namespace zZm = Namespace.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), AudioSignal.class.getSimpleName(), ActiveNoiseControl.class.getSimpleName()));
                public static final CapabilityInterface BIo = CapabilityInterface.create(zZm.getValue());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Comms {

            /* loaded from: classes6.dex */
            public static final class PhoneCallController {
                public static final Namespace zZm = Namespace.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), Comms.class.getSimpleName(), PhoneCallController.class.getSimpleName()));
                public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), Comms.class.getSimpleName(), PhoneCallController.class.getSimpleName()));

                /* loaded from: classes6.dex */
                public static final class ComponentStates {

                    /* loaded from: classes6.dex */
                    public static final class PhoneCallControllerState {
                        public static final Name zZm = Name.create(PhoneCallControllerState.class.getSimpleName());
                    }
                }
            }
        }

        /* loaded from: classes6.dex */
        public static final class Display {
            public static final CapabilityInterface zZm;

            /* loaded from: classes6.dex */
            public static final class Window {
                public static final Namespace zZm = Namespace.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), Display.class.getSimpleName(), Window.class.getSimpleName()));
                public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), Display.class.getSimpleName(), Window.class.getSimpleName()));

                /* loaded from: classes6.dex */
                public static final class ComponentStates {

                    /* loaded from: classes6.dex */
                    public static final class WindowState {
                        public static final Name zZm = Name.create(WindowState.class.getSimpleName());
                    }
                }
            }

            static {
                Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), Display.class.getSimpleName()));
                zZm = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), Display.class.getSimpleName()));
            }
        }

        /* loaded from: classes6.dex */
        public static final class FavoritesController {
            public static final Namespace zZm = Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), FavoritesController.class.getSimpleName()));
            public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), FavoritesController.class.getSimpleName()));

            /* loaded from: classes6.dex */
            public static class Directives {

                /* loaded from: classes6.dex */
                public static final class Favorite {
                    public static final Name zZm = Name.create(Favorite.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class Unfavorite {
                    public static final Name zZm = Name.create(Unfavorite.class.getSimpleName());
                }
            }
        }

        /* loaded from: classes6.dex */
        public static final class IOComponents {
            public static final Namespace zZm = Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), IOComponents.class.getSimpleName()));
            public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), IOComponents.class.getSimpleName()));

            /* loaded from: classes6.dex */
            public static final class ComponentStates {

                /* loaded from: classes6.dex */
                public static final class IOComponentStates {
                    public static final Name zZm = Name.create(IOComponentStates.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class TrustedStates {
                    public static final Name zZm = Name.create(TrustedStates.class.getSimpleName());
                }
            }
        }

        /* loaded from: classes6.dex */
        public static final class InteractionMode {
            public static final CapabilityInterface zZm;

            static {
                Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), InteractionMode.class.getSimpleName()));
                zZm = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), InteractionMode.class.getSimpleName()));
            }
        }

        /* loaded from: classes6.dex */
        public static final class Launcher {
            public static final Namespace zZm = Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), Launcher.class.getSimpleName()));
            public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), Launcher.class.getSimpleName()));

            /* loaded from: classes6.dex */
            public static class Directives {

                /* loaded from: classes6.dex */
                public static final class DisambiguateAndLaunchTarget {
                    public static final Name zZm = Name.create(DisambiguateAndLaunchTarget.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class LaunchTarget {
                    public static final Name zZm = Name.create(LaunchTarget.class.getSimpleName());
                }
            }

            /* loaded from: classes6.dex */
            public static final class Events {

                /* loaded from: classes6.dex */
                public static final class ErrorResponse {
                    public static final Name zZm = Name.create(ErrorResponse.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class Response {
                    public static final Name zZm = Name.create(Response.class.getSimpleName());
                }
            }
        }

        /* loaded from: classes6.dex */
        public static final class Notifications {

            /* loaded from: classes6.dex */
            public static final class Multipart {
                public static final Namespace zZm = Namespace.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), Notifications.class.getSimpleName(), Multipart.class.getSimpleName()));
                public static final CapabilityInterface BIo = CapabilityInterface.create(String.format(zZm.getValue(), new Object[0]));
            }
        }

        /* loaded from: classes6.dex */
        public static final class PlaybackController {
            public static final Namespace zZm = Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), PlaybackController.class.getSimpleName()));
            public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), PlaybackController.class.getSimpleName()));

            /* loaded from: classes6.dex */
            public static class Directives {

                /* loaded from: classes6.dex */
                public static final class FastForward {
                    public static final Name zZm = Name.create(FastForward.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class Next {
                    public static final Name zZm = Name.create(Next.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class Pause {
                    public static final Name zZm = Name.create(Pause.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class Play {
                    public static final Name zZm = Name.create(Play.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class Previous {
                    public static final Name zZm = Name.create(Previous.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class Rewind {
                    public static final Name zZm = Name.create(Rewind.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class StartOver {
                    public static final Name zZm = Name.create(StartOver.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class Stop {
                    public static final Name zZm = Name.create(Stop.class.getSimpleName());
                }
            }
        }

        /* loaded from: classes6.dex */
        public static final class PlaybackStateReporter {
            public static final Namespace zZm = Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), PlaybackStateReporter.class.getSimpleName()));
            public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), PlaybackStateReporter.class.getSimpleName()));

            /* loaded from: classes6.dex */
            public static final class ComponentStates {

                /* loaded from: classes6.dex */
                public static final class PlaybackState {
                    public static final Name zZm = Name.create(PlaybackState.class.getSimpleName());
                }
            }
        }

        /* loaded from: classes6.dex */
        public static final class PlaylistController {
            public static final Namespace zZm = Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), PlaylistController.class.getSimpleName()));
            public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), PlaylistController.class.getSimpleName()));

            /* loaded from: classes6.dex */
            public static class Directives {

                /* loaded from: classes6.dex */
                public static final class DisableRepeat {
                    public static final Name zZm = Name.create(DisableRepeat.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class DisableShuffle {
                    public static final Name zZm = Name.create(DisableShuffle.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class EnableRepeat {
                    public static final Name zZm = Name.create(EnableRepeat.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class EnableRepeatOne {
                    public static final Name zZm = Name.create(EnableRepeatOne.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class EnableShuffle {
                    public static final Name zZm = Name.create(EnableShuffle.class.getSimpleName());
                }
            }
        }

        /* loaded from: classes6.dex */
        public static final class SeekController {
            public static final Namespace zZm = Namespace.create(String.format("%s.%s", Alexa.class.getSimpleName(), SeekController.class.getSimpleName()));
            public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s", Alexa.class.getSimpleName(), SeekController.class.getSimpleName()));

            /* loaded from: classes6.dex */
            public static class Directives {

                /* loaded from: classes6.dex */
                public static final class AdjustSeekPosition {
                    public static final Name zZm = Name.create(AdjustSeekPosition.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class SetSeekPosition {
                    public static final Name zZm = Name.create(SetSeekPosition.class.getSimpleName());
                }
            }
        }

        /* loaded from: classes6.dex */
        public static final class Translation {

            /* loaded from: classes6.dex */
            public static final class LiveTranslation {
                public static final CapabilityInterface zZm = CapabilityInterface.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), Translation.class.getSimpleName(), LiveTranslation.class.getSimpleName()));
            }
        }

        static {
            Namespace.create(Alexa.class.getSimpleName());
        }
    }

    /* loaded from: classes6.dex */
    public static final class ApplicationManager {
        public static final Namespace zZm = Namespace.create(ApplicationManager.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static class Directives {

            /* loaded from: classes6.dex */
            public static final class Navigation {
                public static final Name zZm = Name.create(Navigation.class.getSimpleName());
            }
        }

        static {
            CapabilityInterface.create(ApplicationManager.class.getSimpleName());
        }
    }

    /* loaded from: classes6.dex */
    public static final class AudioActivityTracker {
        public static final Namespace zZm = Namespace.create(AudioActivityTracker.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(AudioActivityTracker.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class ComponentStates {

            /* loaded from: classes6.dex */
            public static final class ActivityState {
                public static final Name zZm = Name.create(ActivityState.class.getSimpleName());
            }
        }

        static {
            dnp.zZm(AudioActivityTracker.class.getSimpleName());
        }
    }

    /* loaded from: classes6.dex */
    public static final class AudioPlayer {
        public static final Namespace zZm = Namespace.create(AudioPlayer.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(AudioPlayer.class.getSimpleName());
        public static final dnp zQM = dnp.zZm(AudioPlayer.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class ComponentStates {

            /* loaded from: classes6.dex */
            public static final class PlaybackState {
                public static final Name zZm = Name.create(PlaybackState.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Directives {

            /* loaded from: classes6.dex */
            public static final class ClearQueue {
                public static final Name zZm = Name.create(ClearQueue.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class Play {
                public static final Name zZm = Name.create(Play.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class Stop {
                public static final Name zZm = Name.create(Stop.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Events {

            /* loaded from: classes6.dex */
            public static final class PlaybackFailed {
                public static final Name zZm = Name.create(PlaybackFailed.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackFinished {
                public static final Name zZm = Name.create(PlaybackFinished.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackNearlyFinished {
                public static final Name zZm = Name.create(PlaybackNearlyFinished.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackPaused {
                public static final Name zZm = Name.create(PlaybackPaused.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackQueueCleared {
                public static final Name zZm = Name.create(PlaybackQueueCleared.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackResumed {
                public static final Name zZm = Name.create(PlaybackResumed.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackStarted {
                public static final Name zZm = Name.create(PlaybackStarted.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackStopped {
                public static final Name zZm = Name.create(PlaybackStopped.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackStutterFinished {
                public static final Name zZm = Name.create(PlaybackStutterFinished.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlaybackStutterStarted {
                public static final Name zZm = Name.create(PlaybackStutterStarted.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class ProgressReportDelayElapsed {
                public static final Name zZm = Name.create(ProgressReportDelayElapsed.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class ProgressReportIntervalElapsed {
                public static final Name zZm = Name.create(ProgressReportIntervalElapsed.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class CardRenderer {
        public static final Namespace zZm = Namespace.create(CardRenderer.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(CardRenderer.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class Directives {

            /* loaded from: classes6.dex */
            public static final class PlayerInfo {
                public static final Name zZm = Name.create(PlayerInfo.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class RenderCard {
                public static final Name zZm = Name.create(RenderCard.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class ExternalMediaPlayer {
        public static final Namespace zZm = Namespace.create(ExternalMediaPlayer.class.getSimpleName());
        public static final dnp BIo = dnp.zZm(ExternalMediaPlayer.class.getSimpleName());
        public static final CapabilityInterface zQM = CapabilityInterface.create(ExternalMediaPlayer.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class ComponentStates {

            /* loaded from: classes6.dex */
            public static final class ExternalMediaPlayerState {
                public static final Name zZm = Name.create(ExternalMediaPlayerState.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static class Directives {

            /* loaded from: classes6.dex */
            public static final class AuthorizeDiscoveredPlayers {
                public static final Name zZm = Name.create(AuthorizeDiscoveredPlayers.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class Login {
                public static final Name zZm = Name.create(Login.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class Logout {
                public static final Name zZm = Name.create(Logout.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class Play {
                public static final Name zZm = Name.create(Play.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Events {

            /* loaded from: classes6.dex */
            public static final class AuthorizationComplete {
                public static final Name zZm = Name.create(AuthorizationComplete.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlayerError {
                public static final Name zZm = Name.create(PlayerError.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PlayerEvent {
                public static final Name zZm = Name.create(PlayerEvent.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class ReportDiscoveredPlayers {
                public static final Name zZm = Name.create(ReportDiscoveredPlayers.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class Geolocation {
        public static final Namespace zZm = Namespace.create(Geolocation.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(Geolocation.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class ComponentStates {

            /* loaded from: classes6.dex */
            public static final class GeolocationState {
                public static final Name zZm = Name.create(GeolocationState.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class Input {

        /* loaded from: classes6.dex */
        public static final class Text {
            public static final Namespace zZm = Namespace.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), Input.class.getSimpleName(), Text.class.getSimpleName()));
            public static final CapabilityInterface BIo = CapabilityInterface.create(String.format("%s.%s.%s", Alexa.class.getSimpleName(), Input.class.getSimpleName(), Text.class.getSimpleName()));

            /* loaded from: classes6.dex */
            public static final class Directives {

                /* loaded from: classes6.dex */
                public static final class ExpectText {
                    public static final Name zZm = Name.create(ExpectText.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class SetEndOfSpeechOffset {
                    public static final Name zZm = Name.create(SetEndOfSpeechOffset.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class StopCapture {
                    public static final Name zZm = Name.create(StopCapture.class.getSimpleName());
                }

                /* loaded from: classes6.dex */
                public static final class TextMessage {
                    public static final Name zZm = Name.create(TextMessage.class.getSimpleName());
                }
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class InteractionModel {
        public static final Namespace zZm = Namespace.create(InteractionModel.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(InteractionModel.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static class Directives {

            /* loaded from: classes6.dex */
            public static final class NewDialogRequest {
                public static final Name zZm = Name.create(NewDialogRequest.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class RequestProcessingCompleted {
                public static final Name zZm = Name.create(RequestProcessingCompleted.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class RequestProcessingStarted {
                public static final Name zZm = Name.create(RequestProcessingStarted.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class Navigation {
        public static final Namespace zZm = Namespace.create(Navigation.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(Navigation.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static class Directives {

            /* loaded from: classes6.dex */
            public static final class CancelNavigation {
                public static final Name zZm = Name.create(CancelNavigation.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SetDestination {
                public static final Name zZm = Name.create(SetDestination.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class NavigationManager {
        public static final Namespace zZm = Namespace.create(NavigationManager.class.getSimpleName());
    }

    /* loaded from: classes6.dex */
    public static final class Notifications {
        public static final Namespace zZm = Namespace.create(Notifications.class.getSimpleName());
        public static final dnp BIo = dnp.zZm(Notifications.class.getSimpleName());
    }

    /* loaded from: classes6.dex */
    public static final class NotificationsApp {
        public static final Namespace zZm = Namespace.create(NotificationsApp.class.getSimpleName());
    }

    /* loaded from: classes6.dex */
    public static final class PlaybackController {
        public static final Namespace zZm = Namespace.create(PlaybackController.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(PlaybackController.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class Events {

            /* loaded from: classes6.dex */
            public static final class NextCommandIssued {
                public static final Name zZm = Name.create(NextCommandIssued.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PauseCommandIssued {
                static {
                    Name.create(PauseCommandIssued.class.getSimpleName());
                }
            }

            /* loaded from: classes6.dex */
            public static final class PlayCommandIssued {
                public static final Name zZm = Name.create(PlayCommandIssued.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class PreviousCommandIssued {
                public static final Name zZm = Name.create(PreviousCommandIssued.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class Settings {
        public static final Namespace zZm = Namespace.create(Settings.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(Settings.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class Events {

            /* loaded from: classes6.dex */
            public static final class SettingsUpdated {
                public static final Name zZm = Name.create(SettingsUpdated.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class SipClient {
        public static final CapabilityInterface zZm = CapabilityInterface.create(SipClient.class.getSimpleName());
        public static final dnp BIo = dnp.zZm(SipClient.class.getSimpleName());
    }

    /* loaded from: classes6.dex */
    public static class Speaker {
        public static final Namespace zZm = Namespace.create(Speaker.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(Speaker.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class ComponentStates {

            /* loaded from: classes6.dex */
            public static final class VolumeState {
                public static final Name zZm = Name.create(VolumeState.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Directives {

            /* loaded from: classes6.dex */
            public static final class AdjustVolume {
                public static final Name zZm = Name.create(AdjustVolume.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SetMute {
                public static final Name zZm = Name.create(SetMute.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SetVolume {
                public static final Name zZm = Name.create(SetVolume.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Events {

            /* loaded from: classes6.dex */
            public static final class MuteChanged {
                public static final Name zZm = Name.create(MuteChanged.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class VolumeChanged {
                public static final Name zZm = Name.create(VolumeChanged.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class SpeechRecognizer {
        public static final Namespace zZm = Namespace.create(SpeechRecognizer.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(SpeechRecognizer.class.getSimpleName());
        public static final dnp zQM = dnp.zZm(SpeechRecognizer.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class ComponentStates {

            /* loaded from: classes6.dex */
            public static final class RecognizerState {
                public static final Name zZm = Name.create(RecognizerState.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Directives {

            /* loaded from: classes6.dex */
            public static final class ExpectSpeech {
                public static final Name zZm = Name.create(ExpectSpeech.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SetEndOfSpeechOffset {
                public static final Name zZm = Name.create(SetEndOfSpeechOffset.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class StopCapture {
                public static final Name zZm = Name.create(StopCapture.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Events {

            /* loaded from: classes6.dex */
            public static final class Recognize {
                public static final Name zZm = Name.create(Recognize.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class SpeechSynthesizer {
        public static final Namespace zZm = Namespace.create(SpeechSynthesizer.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(SpeechSynthesizer.class.getSimpleName());
        public static final dnp zQM = dnp.zZm(SpeechSynthesizer.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class ComponentStates {

            /* loaded from: classes6.dex */
            public static final class SpeechState {
                public static final Name zZm = Name.create(SpeechState.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Directives {

            /* loaded from: classes6.dex */
            public static final class Speak {
                public static final Name zZm = Name.create(Speak.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Events {

            /* loaded from: classes6.dex */
            public static final class SpeechFinished {
                public static final Name zZm = Name.create(SpeechFinished.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SpeechStarted {
                public static final Name zZm = Name.create(SpeechStarted.class.getSimpleName());
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class System {
        public static final Namespace zZm = Namespace.create(System.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(System.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class Directives {

            /* loaded from: classes6.dex */
            public static final class ReportSoftwareInfo {
                public static final Name zZm = Name.create(ReportSoftwareInfo.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class ReportState {
                public static final Name zZm = Name.create(ReportState.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class ResetUserInactivity {
                public static final Name zZm = Name.create(ResetUserInactivity.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class RevokeAuthorization {
                public static final Name zZm = Name.create(RevokeAuthorization.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SetEndpoint {
                public static final Name zZm = Name.create(SetEndpoint.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SetLocales {
                public static final Name zZm = Name.create(SetLocales.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SetTimeZone {
                public static final Name zZm = Name.create(SetTimeZone.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Events {

            /* loaded from: classes6.dex */
            public static final class ExceptionEncountered {
                public static final Name zZm = Name.create(ExceptionEncountered.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class LocalesChanged {
                public static final Name zZm = Name.create(LocalesChanged.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class LocalesReport {
                public static final Name zZm = Name.create(LocalesReport.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SettingsUpdated {
                public static final Name zZm = Name.create(SettingsUpdated.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SoftwareInfo {
                public static final Name zZm = Name.create(SoftwareInfo.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class StateReport {
                public static final Name zZm = Name.create(StateReport.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class SynchronizeState {
                public static final Name zZm = Name.create(SynchronizeState.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class TimeZoneChanged {
                public static final Name zZm = Name.create(TimeZoneChanged.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class TimeZoneReport {
                public static final Name zZm = Name.create(TimeZoneReport.class.getSimpleName());
            }

            /* loaded from: classes6.dex */
            public static final class UserInactivityReport {
                public static final Name zZm = Name.create(UserInactivityReport.class.getSimpleName());
            }
        }

        /* loaded from: classes6.dex */
        public static final class Exception {
            public static final Name zZm = Name.create(Exception.class.getSimpleName());
        }
    }

    /* loaded from: classes6.dex */
    public static final class TemplateRuntime {
        public static final Namespace zZm = Namespace.create(TemplateRuntime.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class Directives {

            /* loaded from: classes6.dex */
            public static final class RenderPlayerInfo {
                static {
                    Name.create(RenderPlayerInfo.class.getSimpleName());
                }
            }

            /* loaded from: classes6.dex */
            public static final class RenderTemplate {
                static {
                    Name.create(RenderTemplate.class.getSimpleName());
                }
            }
        }

        static {
            CapabilityInterface.create(TemplateRuntime.class.getSimpleName());
        }
    }

    /* loaded from: classes6.dex */
    public static final class VisualActivityTracker {
        public static final Namespace zZm = Namespace.create(VisualActivityTracker.class.getSimpleName());
        public static final CapabilityInterface BIo = CapabilityInterface.create(VisualActivityTracker.class.getSimpleName());

        /* loaded from: classes6.dex */
        public static final class ComponentStates {

            /* loaded from: classes6.dex */
            public static final class ActivityState {
                public static final Name zZm = Name.create(ActivityState.class.getSimpleName());
            }
        }

        static {
            dnp.zZm(VisualActivityTracker.class.getSimpleName());
        }
    }

    public static boolean zZm(Namespace namespace, Name name, Message message) {
        return namespace.equals(message.getHeader().getNamespace()) && name.equals(message.getHeader().getName());
    }
}
