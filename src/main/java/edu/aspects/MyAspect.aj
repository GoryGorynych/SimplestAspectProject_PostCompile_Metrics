package edu.aspects;

import com.codahale.metrics.Timer;
import edu.metrics.MetricService;

import static com.codahale.metrics.MetricRegistry.name;

public aspect MyAspect {

    pointcut logAllMethod(): execution(* edu.Robot.*(..));

    Object around(): logAllMethod() {
        Timer.Context context = null;
        try {
            System.out.println("Aspect...Before method " + thisJoinPoint.getSignature());
            String timerName = name(thisJoinPoint.getSignature().getDeclaringType().getSimpleName(), thisJoinPoint.getSignature().getName());
            context = MetricService.getTimer(timerName).time();

            return proceed();
        } finally {
            System.out.println("Aspect...After method " + thisJoinPoint.getSignature());
            context.stop();
        }
    }

}
