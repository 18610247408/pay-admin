package com.hhm.bussplat.agent.id;

import com.hhm.bussplat.util.id.ID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 雪花算法
 * 64位ID = 1位符号位(固定为0,表示正数) + 41位时间戳 + 10位工作机器id + 12位序列号
 * @author q
 * @time 2020/5/16 10:32 下午
 */
@Component
@Slf4j
public class SnowFlakeID implements ID<Long>, InitializingBean {
    /**
     * 12位序列号
     */
    //序列号
    private long sequence;
    //序列号位数
    private long sequenceBits = 12L;
    //序列号最大值
    private long maxSequence = -1 ^ (-1 << sequenceBits);

    /**
     * 10位工作机器id = 5位机房id + 5位机器id
     */
    //机器id
    @Value("${snowFlake.workerId}")
    private long workerId;
    //机器id位数
    private long workerIdBits = 5L;
    //机器id最大值
    private long maxWorkerId = -1 ^ (-1 << workerIdBits);
    //机器id左移位数 = 序列号位数
    private long workerIdMoveBits = sequenceBits;
    //左移后的机器id
    private long workerIdAfterMove = 0L;

    //机房id
    @Value("${snowFlake.workerCenterId}")
    private long workerCenterId;
    //机房id位数
    private long workerCenterIdBits = 5L;
    //机房id最大值
    private long maxWorkerCenterId = -1 ^ (-1 << workerCenterIdBits);
    //机房id左移位数 = 机器id位数 + 序列号位数
    private long workerCenterIdMoveBits = workerIdBits + workerIdMoveBits;
    //左移后的机房id
    private long workerCenterIdAfterMove = 0L;

    /**
     * 41位时间戳
     */
    //默认值：-1L
    private long lastTimestamp = -1L;
    //初始时间戳，禁止改动
    private long initTimestamp = 1589727210265L;
    //时间戳左移位数 = 机房id位数 + 机器id位数 + 序列号位数
    private long timestampMoveBits = workerCenterIdBits + workerCenterIdMoveBits;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(workerCenterId <= 0 || workerCenterId > maxWorkerCenterId){
            throw new IllegalArgumentException("workerCenterId is illegal");
        }
        if(workerId <= 0 || workerId > maxWorkerId){
            throw new IllegalArgumentException("workerId is illegal");
        }
        log.info("SnowFlakeID Init workerCenterId={},workerId={}",workerCenterId,workerId);
        this.workerCenterIdAfterMove = this.workerCenterId << this.workerCenterIdMoveBits;
        this.workerIdAfterMove = this.workerCenterId << this.workerCenterIdMoveBits;
    }

    /**
     * 生成ID的核心方法
     */
    @Override
    public synchronized Long nextId(){
        long currentTimestamp = timestamp();
        if(currentTimestamp < lastTimestamp){
            String s = String.format("currentTimestamp is earlier than lastTimestamp,lastTimestamp=%s,currentTimestamp=%s",lastTimestamp,currentTimestamp);
            System.out.println(s);
            //时钟回拨，自动更新为最后一次生成id用的时间戳
            currentTimestamp = lastTimestamp;
        }
        if(currentTimestamp == lastTimestamp){
            //同一时间戳，序列号加1
            sequence = (sequence + 1) & maxSequence;
            if(sequence == 0L){
                //如果序列号加1后的值为0，表示当前时间戳内的序列号已用完，需要获取下一个时间戳
                currentTimestamp = nextTimestamp(currentTimestamp);
            }
        }else{
            //不同时间戳，重置序列号
            sequence = 0L;
        }
        //更新成功生成id的最新时间戳
        lastTimestamp = currentTimestamp;
        return ((currentTimestamp - initTimestamp) << timestampMoveBits) | workerCenterIdAfterMove | workerIdAfterMove | sequence;
    }

    /**
     * 获取timestamp的下一毫秒数
     * @param timestamp 当前毫秒数
     * @return
     */
    public long nextTimestamp(long timestamp){
        long timestamp1 = 0L;
        do{
            timestamp1 = timestamp();
        }while (timestamp >= timestamp1);
        return timestamp1;
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public long timestamp(){
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
