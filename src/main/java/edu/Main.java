package edu;

import edu.metrics.MetricService;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Robot robot = new Robot();

        robot.sayHello(0);
        robot.sayBye();

        robot.sayHello(500);
        robot.sayHello(100);

//        MetricService.removeMetrics();
        MetricService.reportToConsole();
    }


}
