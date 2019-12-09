package com.zhang.aspect;

import com.zhang.annocation.CacheAnnocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {
    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.zhang.annocation.CacheAnnocation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        CacheAnnocation annotation = signature.getMethod().getAnnotation(CacheAnnocation.class);
        //获取注解值，用于判断操作
        String value = annotation.value();
        System.out.println(value);
        //获取类的全限定名。用于做redis中的大key
        String className = signature.getDeclaringTypeName();
        System.out.println(className);
        //获取方法名，用于内部hash的key
        String methodName = signature.getMethod().getName();
        System.out.println(methodName);
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println(proceed);
        HashOperations hash = redisTemplate.opsForHash();
        if (value.equals("find")) {
            Object o = hash.get(className, methodName);
            if (o == null) {
                hash.put(className, methodName, proceed);
                return proceed;
            } else {
                return o;
            }
        } else {
            redisTemplate.delete(className);
            return proceed;
        }
    }
}
