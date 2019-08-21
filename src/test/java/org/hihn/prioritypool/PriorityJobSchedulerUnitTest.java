package org.hihn.prioritypool;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

public class PriorityJobSchedulerUnitTest {

    private static int NUMBER_THREADS = 1;

    private static int JOB_MIN_QUEUE_CAPACITY = 10;

    @Test
    public void whenMultiplePriorityJobsQueued_thenHighestPriorityJobIsPicked() {

        PriorityJobScheduler pjs = new PriorityJobScheduler(NUMBER_THREADS, JOB_MIN_QUEUE_CAPACITY);

        for (int i = 0; i <= 20; i++) {
            JobPriority priority;
            int r = ThreadLocalRandom.current().nextInt(1, 9);
            if (r <= 3) {
                priority = JobPriority.LOW;                
            } else if (r <= 6) {
                priority = JobPriority.MEDIUM;
            } else {
                priority = JobPriority.HIGH;
            }
            Job job = new Job(" #" + i, priority);
            pjs.scheduleJob(job);
        }

        pjs.startJob();

        // ensure no tasks is pending before closing the scheduler
        while (pjs.getQueuedTaskCount() != 0) {

        }

        pjs.closeScheduler();
    }

}
