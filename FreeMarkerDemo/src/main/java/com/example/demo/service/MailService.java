package com.example.demo.service;

import com.example.demo.core.mail.SendEmailRepository;
import com.example.demo.entity.po.ErrorDatile;
import com.example.demo.entity.vo.MonitorVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author: HanXu
 * on 2021/4/30
 * Class description:
 */
@Service
public class MailService {

    @Resource
    private SendEmailRepository sendEmailRepository;


    public void sendMail() {
        MonitorVo<Object> monitorVo = new MonitorVo<>();
        monitorVo.setSumErrorCount(10);
        monitorVo.setStartTime(LocalDateTime.now().toString());
        ErrorDatile errorDatile = new ErrorDatile("1", "2", "3", "4", "5");
        ErrorDatile errorDatile2 = new ErrorDatile("1", "2", "3", "4", "5");
        monitorVo.setErrorDetailInfos(new ArrayList<ErrorDatile>(){{add(errorDatile); add(errorDatile2);}});
        String[] to = new String[1];
        to[0] = "1195264226@qq.com";
        sendEmailRepository.sendEmail(monitorVo, "catIntergateway3rdcode.ftl", "这是标题", to, new String[0]);
    }
}
