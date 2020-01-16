package com.foxwho.demo.config;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UidConfig {

    @Bean("disposableWorkerIdAssigner")
    public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
        DisposableWorkerIdAssigner disposableWorkerIdAssigner = new DisposableWorkerIdAssigner();
        return disposableWorkerIdAssigner;
    }

    /*与原始的snowflake算法不同，uid-generator支持自定义时间戳、工作机器id和序列号等各部分的位数，以应用于不同场景。默认分配方式如下。
    sign(1bit)
    固定1bit符号标识，即生成的UID为正数。

    delta seconds (28 bits)
    当前时间，相对于时间基点"2016-05-20"的增量值，单位：秒，最多可支持约8.7年（注意：1. 这里的单位是秒，而不是毫秒！ 2.注意这里的用词，是“最多”可支持8.7年，为什么是“最多”，后面会讲）

    worker id (22 bits)
    机器id，最多可支持约420w次机器启动。内置实现为在启动时由数据库分配，默认分配策略为用后即弃，后续可提供复用策略。

    sequence (13 bits)
    每秒下的并发序列，13 bits可支持每秒8192个并发。（注意下这个地方，默认支持qps最大为8192个, 2的13次方）
*/
    @Bean("cachedUidGenerator")
    public UidGenerator uidGenerator(DisposableWorkerIdAssigner disposableWorkerIdAssigner) {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
        cachedUidGenerator.setPaddingFactor(50);
        cachedUidGenerator.setBoostPower(4);
        cachedUidGenerator.setEpochStr("2020-01-01");

        cachedUidGenerator.setTimeBits(32);//136年
        cachedUidGenerator.setWorkerBits(19);//重启524000次
        cachedUidGenerator.setSeqBits(12);//每秒4096个ID，不够会自动取下一秒的ID
        return cachedUidGenerator;
    }
}
