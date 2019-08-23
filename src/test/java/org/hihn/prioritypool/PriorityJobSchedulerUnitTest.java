package org.hihn.prioritypool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;
import java.util.Random;

public class PriorityJobSchedulerUnitTest {

	private static int NUMBER_THREADS = 8;
	private static int NUMBER_JOBS = 50;

	@Test
	public void whenMultiplePriorityJobsQueued_thenHighestPriorityJobIsPicked() throws InterruptedException {
		final Set<Job> jobs = new HashSet<>();
        JobPriority[] prios = {JobPriority.LOW, JobPriority.MEDIUM, JobPriority.HIGH};

		for (int i = 0; i <= NUMBER_JOBS; i++) {
            int r = new Random().nextInt(prios.length);
			JobPriority priority = prios[r];
			Job job = new Job("#" + i, priority);
			jobs.add(job);
		}

		final PriorityJobScheduler jobScheduler = new PriorityJobScheduler(NUMBER_THREADS, jobs);
		jobScheduler.startJobs();
	}

}
