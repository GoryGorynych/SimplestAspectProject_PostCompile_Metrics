package aspects;

import com.codahale.metrics.Timer;
import metrics.MetricService;

public aspect MyAspect {

    pointcut logAllMethod(): execution(* Robot.*(..));

    Object around(): logAllMethod() {
        Timer.Context context = null;
        try {
            System.out.println("Aspect...Before method " + thisJoinPoint.getSignature());
            context = MetricService.getTimer(thisJoinPoint.getSignature().toString()).time();

            return proceed();
        } finally {
            System.out.println("Aspect...After method " + thisJoinPoint.getSignature());
            context.stop();
        }
    }

}
