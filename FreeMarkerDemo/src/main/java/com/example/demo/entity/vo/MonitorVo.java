package com.example.demo.entity.vo;

import com.example.demo.entity.po.ErrorDatile;
import com.example.demo.util.IpAddressUtil;
import lombok.Data;

import javax.mail.util.ByteArrayDataSource;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class MonitorVo<T> implements Serializable{
    
    private static final long serialVersionUID = -8010653010897037109L;
    // 业务数据
    private T monitorData;
    //接口编号
    private String serviceType;
    //
    private String serviceTypeName;
    private String subjectName;
    //查询开始时间
    private String startTime;
    //查询结束时间
    private String endTime = LocalDateTime.now().toString();
    //UTC时间格式，查询开始时间
    private String utcSrartTime;
    //UTC时间格式，查询结束时间
    private String utcEndTime;
    //规则名称
    private String ruleName;
    //持续时间
    private Long duration;
    //阈值
    private Integer threshold;
    //错误占比阈值
    private double thresholdRatio;
    //用户错误集
    /*private List<UserErrorVo> userErrorVos;*/
    //错误详情集
    private List<ErrorDatile> errorDetailInfos;
    //接口错误，以及错误码集
    /*private List<ServiceErrorDetailVo> serviceErrorDetailVos = new ArrayList<>();
    //聚合接口错误集
    private List<AggregationErrorVo> aggregationErrorVos;
    //基础接口错误集
    private List<ThirdBaseErrorVo> thirdBaseErrorVos;
    //聚合服务接口调用量变化
    private List<AggregationStatisticsVo> aggregationStatisticsVos;
    //供应商限量
    private List<SupplierLimitVo> supplierLimitVos;*/
    //错误总量
    private int sumErrorCount;
    //错误类型
    private String sumErrorType;
    //fee，third均存在但是不一致list
    /*private List<LogInconsistencyVo > inconsistentList;*/
    //fee，third均存在但是不一致数目
    private long inconsistentNum=0;
    /*private List<LogInconsistencyVo> thirdMissingList;*/
    //third_log缺失数目
    private long thirdMissingNum=0;
    //fee_log缺失list
    /*private List<LogInconsistencyVo> feeMissingList;*/
    //fee_log缺失数目
    private long feeMissingNum=0;
    //发送IP
    private String sendIp = IpAddressUtil.getLocalIP();
    // 邮件文本内容
    private String textContent;
    private String dateStr;


    private Map<String, ByteArrayDataSource> dataSourceMap;
}
