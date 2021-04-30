package com.example.demo.core.mail;

import com.example.demo.entity.vo.MonitorVo;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author: HanXu
 * on 2021/4/30
 * Class description: 发送邮件
 */
@Slf4j
@Component
public class SendEmailRepository {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件（收件人、抄送人使用配置）
     *
     * @param monitorVo
     * @param ftlName
     * @param subjectName
     */
    public void sendEmail(MonitorVo monitorVo, String ftlName, String subjectName) {
        this.sendEmail(monitorVo, ftlName, subjectName, null, null);
    }

    /**
     * 发送邮件（收件人、抄送人使用用户自定义或配置）
     *
     * @param monitorVo
     * @param ftlName
     * @param subjectName
     * @param to
     * @param cc
     */
    public void sendEmail(MonitorVo monitorVo, String ftlName, String subjectName, String[] to, String[] cc) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper ;
        try {
            Map<String, ByteArrayDataSource> dataSourceMap = monitorVo.getDataSourceMap();
            if (monitorVo != null && dataSourceMap != null && dataSourceMap.size() > 0) {
                helper = new MimeMessageHelper(msg, true, "utf-8");
            } else {
                helper = new MimeMessageHelper(msg, "utf-8");
            }

            /*if (to == null) {
                to = emailTo(ftlName);
            }
            if (cc == null) {
                cc = emailCc(ftlName);
            }*/

            //发件人
            helper.setFrom(from);
            //收件人
            helper.setTo(to);
            helper.setCc(cc);
            //标题
            helper.setSubject(subjectName);
            Template temp = freeMarkerConfigurer.getConfiguration().getTemplate(ftlName, "utf-8");
            StringWriter out = new StringWriter();
            temp.process(monitorVo, out);
            String content = out.toString();
            helper.setText(content, true);
            if(monitorVo != null && dataSourceMap != null && dataSourceMap.size() > 0){
                for(Map.Entry<String, ByteArrayDataSource> entry : dataSourceMap.entrySet()){
                    helper.addInline(entry.getKey(), entry.getValue());
                }
            }
            javaMailSender.send(msg);
            log.info("邮件发送成功,发送标题Subject={},模板名称FtlName={},收件人To={},抄送人Cc={}", subjectName, ftlName, to, cc);
        } catch (Exception e) {
            log.error("邮件发送失败,发送标题Subject={},模板名称FtlName={},收件人To={},抄送人Cc={}", subjectName, ftlName, to, cc, e);
        }
    }
}
