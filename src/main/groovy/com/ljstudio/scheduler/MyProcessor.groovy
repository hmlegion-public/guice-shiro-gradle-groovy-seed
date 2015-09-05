package com.ljstudio.scheduler
/**
 * Created by lijin on 15-9-5.
 */
//@ImplementedBy(MyProcessorImpl.class)// 配置无效，注释掉,why?
interface MyProcessor {
    void start() throws Exception;
    void shutdown()  throws Exception;
}