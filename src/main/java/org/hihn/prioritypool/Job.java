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

		int randomNum = ThreadLocalRandom.current().nextInt(100_000, 200_000);
		PrimeGenerator.primeNumbersBruteForce(randomNum);
		System.out.println("Job: " + jobName +" Priority: " + jobPriority);
	}

}
