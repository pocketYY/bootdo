package com.bootdo.zyctd.domain;

import com.bootdo.zyctd.vo.BaseTbDOVO;

import java.io.Serializable;

/**
 * 服务端服务器缉监控信息
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public class ServerMonitorResultDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String base;                //基地编号
    private String alive;               //是否存活
    private String last_beat_time;      //最后一次心跳时间
    private BaseTbDOVO baseTbDOVO;          //基地基础信息

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }

    public String getLast_beat_time() {
        return last_beat_time;
    }

    public void setLast_beat_time(String last_beat_time) {
        this.last_beat_time = last_beat_time;
    }

    public BaseTbDOVO getBaseTbDOVO() {
        return baseTbDOVO;
    }

    public void setBaseTbDOVO(BaseTbDOVO baseTbDOVO) {
        this.baseTbDOVO = baseTbDOVO;
    }

    @Override
    public String toString() {
        return "ServerMonitorResultDO{" +
                "base='" + base + '\'' +
                ", alive='" + alive + '\'' +
                ", last_beat_time='" + last_beat_time + '\'' +
                ", baseTbDOVO=" + baseTbDOVO +
                '}';
    }
}
