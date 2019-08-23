package org.hihn.prioritypool;

import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityJobScheduler {

	private final ExecutorService workScheduler;

	private final PriorityBlockingQueue<Job> priorityQueue;

	private final static int CAPACITY = 10;

	public PriorityJobScheduler(Integer nThreads, Set<Job> jobs) {
		workScheduler = Executors.newFixedThreadPool(nThreads);
		priorityQueue = new PriorityBlockingQueue<Job>(CAPACITY, Comparator.comparing(Job::getJobPriority));
		priorityQueue.addAll(jobs);
	}

	public void startJobs() throws InterruptedException {
		priorityQueue.forEach(workScheduler::submit);
		workScheduler.shutdown();
		workScheduler.awaitTermination(5, TimeUnit.HOURS);
	}

}
