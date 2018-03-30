package ch.hslu.mobprog.servicesreceivers;

/**
 * Created by sandr on 30.03.2018.
 */

public interface DemoServiceApi {
    int getNumberOfJobsRunning();
    int getNumberOfJobsCompleted();
    void resetCounters();
}
