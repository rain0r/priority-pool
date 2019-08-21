package org.hihn.prioritypool;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityJobScheduler {

	private ExecutorService priorityJobPoolExecutor;

	private ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();

	private PriorityBlockingQueue<Job> priorityQueue;

	public PriorityJobScheduler(Integer nThreads, Integer minJobQueueSize) {
		priorityJobPoolExecutor = Executors.newFixedThreadPool(nThreads);

		priorityQueue = new PriorityBlockingQueue<Job>(minJobQueueSize,
				Comparator.comparing(Job::getJobPriority));
	}

	public void startJob() {
		priorityJobScheduler.execute(() -> {
			while (true) {
				try {
					priorityJobPoolExecutor.execute(priorityQueue.take());
				} catch (InterruptedException e) {
					// exception needs special handling
					break;
				}
			}
		});
	}

	public void scheduleJob(Job job) {
		priorityQueue.add(job);
	}

	public int getQueuedTaskCount() {
		return priorityQueue.size();
	}

	protected void close(ExecutorService scheduler) {
		scheduler.shutdown();
		try {
			scheduler.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Error wating for workers");
		}
	}

	public void closeScheduler() {
		close(priorityJobPoolExecutor);
		close(priorityJobScheduler);
	}
}
