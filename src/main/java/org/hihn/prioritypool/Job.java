package org.hihn.prioritypool;

import java.util.concurrent.ThreadLocalRandom;

public class Job implements Runnable {

	private String jobName;

	private JobPriority jobPriority;

	public Job(String jobName, JobPriority jobPriority) {
		this.jobName = jobName;
		this.jobPriority = jobPriority != null ? jobPriority : JobPriority.MEDIUM;
	}

	public JobPriority getJobPriority() {
		return jobPriority;
	}

	@Override
	public void run() {
		int randomNum = ThreadLocalRandom.current().nextInt(700_000, 1_000_000 + 1);
		PrimeGenerator.primeNumbersBruteForce(randomNum);
	}
}
