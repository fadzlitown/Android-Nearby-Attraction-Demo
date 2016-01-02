package my.fadzlirazali.myfind.Event;

import my.fadzlirazali.myfind.models.Nearby;

/**
 * Created by agmostudio on 1/2/16.
 */
public class NearbyEvent {

    public static class onGetNearby {
        private Nearby object;

        public onGetNearby(Nearby list) {
            this.object = list;
        }
        public Nearby getNearby() {
            return object;
        }

    }

    public static class onGetNearbyFailed {
        private boolean errorNearbyStatus;

        public onGetNearbyFailed(boolean errorNearbyStatus) {
            this.errorNearbyStatus = errorNearbyStatus;
        }

        public boolean isErrorNearbyStatus() {
            return errorNearbyStatus;
        }
    }


}
