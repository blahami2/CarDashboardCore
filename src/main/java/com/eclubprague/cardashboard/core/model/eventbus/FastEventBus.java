package com.eclubprague.cardashboard.core.model.eventbus;

import android.util.Log;

import com.eclubprague.cardashboard.core.model.eventbus.interfaces.Event;
import com.eclubprague.cardashboard.core.model.eventbus.interfaces.MainThreadReceiver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Michael on 11. 8. 2015.
 */
public enum FastEventBus {
    INSTANCE;

    private static final String TAG = FastEventBus.class.getSimpleName();

    public static FastEventBus getInstance() {
        return INSTANCE;
    }

    private final Map<Class<? extends Event>, Set<MainThreadReceiver>> listenersMap = new HashMap<>();

    public synchronized <T extends Event> void register(MainThreadReceiver<T> receiver, Class<T> eventClass) {
        Log.d(TAG, "receiver registered: " + receiver);
        Set<MainThreadReceiver> eventListeners;
        eventListeners = listenersMap.get(eventClass);
        if (eventListeners == null) {
            eventListeners = new HashSet<>();
            listenersMap.put(eventClass, eventListeners);
        }
//        if (!eventListeners.contains(receiver)) {
        eventListeners.add(receiver);
//        }
    }

    public synchronized <T extends Event> void unregister(MainThreadReceiver<T> receiver, Class<T> eventClass) {
        Log.d(TAG, "receiver unregistered: " + receiver);
        Set<MainThreadReceiver> eventListeners = listenersMap.get(eventClass);
        if (eventListeners != null) {
            eventListeners.remove(receiver);
//            Log.d(TAG, "remaining listeners:");
//            for(MainThreadReceiver<T> r : eventListeners){
//                Log.d(TAG, r.getClass().getSimpleName());
//            }
        }
    }

    public synchronized void post(Event event) {
        Set<MainThreadReceiver> eventListeners = listenersMap.get(event.getClass());
        if (eventListeners != null) {
//            Log.d(TAG, "posting event: " + event.getClass().getSimpleName() + " to listeners");
            for (MainThreadReceiver receiver : eventListeners) {
//                if(event instanceof GlobalMediumUpdateEvent){
//                    Log.d(TAG, "posting event for: " + receiver.getClass().getSimpleName());
//                }
//                Log.d(TAG, "posting event to: " + receiver.getClass().getSimpleName());
                receiver.onEventMainThread(event);
            }
        }
    }
}
