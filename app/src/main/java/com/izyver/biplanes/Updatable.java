package com.izyver.biplanes;

public interface Updatable {
    /**
     *
     * @param delta time from last update
     * @return true if update is successfully
     */
    boolean update(long delta);
}
