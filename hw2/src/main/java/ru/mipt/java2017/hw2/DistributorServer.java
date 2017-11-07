package ru.mipt.java2017.hw2;

import java.util.concurrent.Semaphore;
import org.slf4j.Logger;

public class DistributorServer implements Runnable {

  private long number;
  private long start, end;
  private Semaphore semaphore;
  private long amountOfPrimary;
  private Logger logger;


  //check if prime
  private boolean isPrimary(long number) {
    if (number == 1)
      return false;
    for (long i = 2; i < Math.sqrt(number) + 1; ++i) {
      if (number % i == 0)
        return false;
    }
    return true;
  }

  private long findAmountOfPrimaryNumbers() {
    long tmpAmountOfPrimaryNumbers = 0;
    for (long i = start; i <= end; ++i) {
      if (isPrimary(i)) {
        ++tmpAmountOfPrimaryNumbers;
      }
    }
    return tmpAmountOfPrimaryNumbers;
  }

  public DistributorServer(long number, long start, long end, Semaphore semaphore, Logger logger) {
    this.end = end;
    this.number = number;
    this.start = start;
    this.semaphore = semaphore;
    this.amountOfPrimary = 0;
    this.logger = logger;
  }


  public long getAmountOfPrimary() {
    return amountOfPrimary;
  }

  @Override
  public void run() {
    synchronized (logger) {
      logger.info("start finding amount of primary numbers in [{}, {}] in thread {}", start, end,
          number);
    }
    amountOfPrimary = findAmountOfPrimaryNumbers();

    synchronized (logger) {
      logger.info("finish finding amount of primary numbers in [{}, {}] in thread {}", start, end,
          number);
    }
    semaphore.release();
  }
}


