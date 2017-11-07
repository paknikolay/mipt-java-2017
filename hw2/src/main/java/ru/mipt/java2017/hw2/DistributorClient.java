package ru.mipt.java2017.hw2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javafx.util.Pair;
import org.slf4j.Logger;

class DistributorClient implements Runnable{
  private int number;
  private int originalNumber;
  private String host;
  private int port;
  private long start, end;
  private Semaphore semaphore;
  private long amountOfPrimary;
  private Logger logger;
  private ArrayList<Pair<String, Integer>> servers;
  private ArrayList<Integer> validServers;
  private MyLong amountOfFreeServers;
  private MyLong amountOfBusyServers;
  private Semaphore serverSemaphore;

  public DistributorClient(int number, String host, int port, long start, long end,
        Semaphore semaphore, Logger logger, ArrayList<Pair<String, Integer>> servers,
      ArrayList<Integer> validServers, MyLong amountOfFreeServers, MyLong amountOfBusyServers,
      Semaphore serverSemaphore){
    this.end = end;
    this.number = number;
    this.start = start;
    this.host = host;
    this.port = port;
    this.semaphore = semaphore;
    this.amountOfPrimary = 0;
    this.logger = logger;
    this.servers = servers;
    this.validServers = validServers;
    this.amountOfBusyServers = amountOfBusyServers;
    this.amountOfFreeServers = amountOfFreeServers;
    this.serverSemaphore = serverSemaphore;
    originalNumber = number;
  }


  public long getAmountOfPrimary() {
    return amountOfPrimary;
  }

  @Override
  public void run(){
    Client client = new Client(host, port);
    synchronized (validServers) {
      logger.info("sending to server host: {}  port : {}  request: thread number {}", host, port,
          originalNumber);
    }
    long tmpAmountOfPrimaryNumbers = 0;
    tmpAmountOfPrimaryNumbers = client.calculateAmountOfPrimaryNumbers(originalNumber, start, end);
    while (tmpAmountOfPrimaryNumbers < 0){
        validServers.set(number, 2);
        amountOfBusyServers.minus(1);

      logger.info("amount of free servers {}, busy servers {}, thread number {}", amountOfFreeServers.getValue(), amountOfBusyServers.getValue(), originalNumber);
      int newNumber = number;
      synchronized (validServers) {
        if (amountOfFreeServers.getValue() == 0) {
          if (amountOfBusyServers.getValue() == 0) {
            logger.error("No available server found, thread number {}", originalNumber);
            serverSemaphore.release(servers.size());
            semaphore.release();
            return;
          }
          try {//waiting for free server
            logger.info("Waiting for free server, thread number {}", originalNumber);
            serverSemaphore.acquire();
          } catch (InterruptedException e) {
            logger.error("InterruptedException, thread number {}", originalNumber);
            semaphore.release();
            e.printStackTrace();
            return;
          }
        }
        logger.info("Searching for free server, thread number {}", originalNumber);
        //searching for free server
        for (int i = 0; i < validServers.size(); ++i) {
          if (validServers.get(i) == 0) {
            newNumber = i;
            validServers.set(number, 1);
            amountOfFreeServers.minus(1);
            amountOfBusyServers.plus(1);
            break;
          }
        }
      }
        try {
          client.shutdown();
        } catch (InterruptedException e) {
          synchronized (validServers) {
            logger.error("Cannot shutdown client in thread number {}", originalNumber);
          }
          semaphore.release();
          e.printStackTrace();
          return;
        }

        if (newNumber == number) {
          logger.error("No available server found, thread number {}", originalNumber);
          serverSemaphore.release(servers.size());
          semaphore.release();
          return;
        }


      number = newNumber;
      client = new Client(servers.get(number).getKey(), servers.get(number).getValue());
      tmpAmountOfPrimaryNumbers = client.calculateAmountOfPrimaryNumbers(originalNumber, start, end);
    }


    synchronized (servers){
      amountOfBusyServers.minus(1);
      amountOfFreeServers.plus(1);
      serverSemaphore.release();
      validServers.set(number, 0);
    }

    synchronized (servers){
      logger.info("got from server host: {}  port : {}  respond : thread number {}", host, port, originalNumber );
    }
    try {
      client.shutdown();
    } catch (InterruptedException e) {
      synchronized (servers){
        logger.error("Cannot shutdown client in thread number {}", originalNumber);
      }
      e.printStackTrace();
      return;
    }

    amountOfPrimary+= tmpAmountOfPrimaryNumbers;
    semaphore.release();
  }
}
