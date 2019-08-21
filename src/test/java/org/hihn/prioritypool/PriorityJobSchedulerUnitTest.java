package org.hihn.prioritypool;

import org.junit.Test;

public class PriorityJobSchedulerUnitTest {

	private static int NUMBER_THREADS = 1;

	private static int JOB_MIN_QUEUE_CAPACITY = 10;

	@Test
	public void whenMultiplePriorityJobsQueued_thenHighestPriorityJobIsPicked() {
		Job job1 = new Job("Job1", JobPriority.LOW);
		Job job2 = new Job("Job2", JobPriority.MEDIUM);
		Job job3 = new Job("Job3", JobPriority.HIGH);
		Job job4 = new Job("Job4", JobPriority.MEDIUM);
		Job job5 = new Job("Job5", JobPriority.LOW);
		Job job6 = new Job("Job6", JobPriority.HIGH);

		PriorityJobScheduler pjs = new PriorityJobScheduler(NUMBER_THREADS, JOB_MIN_QUEUE_CAPACITY);

		pjs.scheduleJob(job1);
		pjs.scheduleJob(job2);
		pjs.scheduleJob(job3);
		pjs.scheduleJob(job4);
		pjs.scheduleJob(job5);
		pjs.scheduleJob(job6);

		pjs.startJob();

		// ensure no tasks is pending before closing the scheduler
		while (pjs.getQueuedTaskCount() != 0) {

		}

		pjs.closeScheduler();
	}

}