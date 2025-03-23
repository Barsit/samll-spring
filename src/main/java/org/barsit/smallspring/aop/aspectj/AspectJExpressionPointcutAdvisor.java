package org.barsit.smallspring.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.barsit.smallspring.aop.Pointcut;
import org.barsit.smallspring.aop.PointcutAdvisor;

/**
 * @description:AspectJExpressionPointcutAdvisor 实现了 PointcutAdvisor 接口，把切面 pointcut、
 * 拦截方法 advice 和
 * 具体的拦截表达式包装在一起。这样就可以在 xml 的配置中定义一个 pointcutAdvisor 切面拦截器了。
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.aop.aspectj
 * @author:db
 * @createTime:2025/3/22 10:15
 * @version:1.0
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }
}
