package group.siip.userapi.user.web;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class UserControllerAspect {

    @Before(value = "@annotation(group.siip.userapi.user.web.Loggable)")
    public void logRequest(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (log.isDebugEnabled()) {
            log.debug("full method description: {}", signature.getMethod());
        }

//        Arrays.stream(joinPoint.getArgs()).forEach(o -> {
//                    if (log.isDebugEnabled()) {
//                        log.debug("Parameter {}", o);
//                    }
//                }
//        );
    }


}
