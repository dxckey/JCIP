package chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于监视器模式的车辆追踪
 */
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy(value = "this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint location = locations.get(id);
        return location == null ? null : new MutablePoint(location);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint location = locations.get(id);
        if (location == null ){
            throw new IllegalArgumentException("No such ID:" + id);
        }
        location.x = x;
        location.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> sourceMap) {
        Map<String, MutablePoint> resultMap = new HashMap<>();
        for (String id : sourceMap.keySet()) {
            resultMap.put(id, new MutablePoint(sourceMap.get(id)));
        }
        return Collections.unmodifiableMap(resultMap);
    }
}
