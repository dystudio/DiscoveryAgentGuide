package com.nepxion.discovery.guide.agent;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.Map;

import com.nepxion.discovery.guide.sdk.MyContext;
import com.nepxion.discovery.plugin.strategy.agent.threadlocal.AbstractThreadLocalHook;

public class MyContextHook extends AbstractThreadLocalHook {
    @Override
    public Object create() {
        // 从主线程的ThreadLocal里获取并返回上下文对象
        return MyContext.getCurrentContext().getAttributes();
    }

    @Override
    public void before(Object object) {
        // 把create方法里获取到的上下文对象放置到子线程的ThreadLocal里
        if (object instanceof Map) {
            MyContext.getCurrentContext().setAttributes((Map<String, String>) object);
        }
    }

    @Override
    public void after() {
        // 线程结束，销毁上下文对象
        MyContext.clearCurrentContext();
    }
}