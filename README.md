# priority-pool

Example implementation of a [PriorityBlockingQueue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/PriorityBlockingQueue.html).

Heavily inspired by [baeldung](https://www.baeldung.com/java-priority-job-schedule).

Run with Java 11:

```
mvn clean install
```

Output:

```
Job: #3 Priority: HIGH
Job: #9 Priority: HIGH
Job: #5 Priority: HIGH
Job: #13 Priority: HIGH
Job: #7 Priority: MEDIUM
Job: #15 Priority: MEDIUM
Job: #8 Priority: MEDIUM
Job: #0 Priority: MEDIUM
Job: #20 Priority: MEDIUM
Job: #10 Priority: MEDIUM
Job: #18 Priority: MEDIUM
Job: #12 Priority: MEDIUM
Job: #1 Priority: LOW
Job: #17 Priority: LOW
Job: #4 Priority: LOW
Job: #19 Priority: LOW
Job: #11 Priority: LOW
Job: #6 Priority: LOW
Job: #16 Priority: LOW
Job: #14 Priority: LOW
Job: #2 Priority: LOW

```
