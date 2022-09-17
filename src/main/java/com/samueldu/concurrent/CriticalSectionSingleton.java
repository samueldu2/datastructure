package com.samueldu.concurrent;

public class CriticalSectionSingleton {
    private static volatile CriticalSectionSingleton _instance;

    /**
     * Double checked locking code on Singleton
     *
     * @return Singelton instance
     */
    public static CriticalSectionSingleton getInstance() {
        if (_instance == null) {
            synchronized (CriticalSectionSingleton.class) {
                if (_instance == null) {
                    _instance = new CriticalSectionSingleton();
                }
            }
        }
        return _instance;
    }
}