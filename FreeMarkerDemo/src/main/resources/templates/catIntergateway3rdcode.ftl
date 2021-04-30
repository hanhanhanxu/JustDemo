<!DOCTYPE html>
<html lang="en">
<body>
<font>All:</font>
<font>【S1预警】系统错误 ：最近五分钟，3rdcode错误量${sumErrorCount}次,统计如下（时间：${startTime}-${endTime}），请关注</font>
<br><br>

<#if  errorDetailInfos?? && (errorDetailInfos ? size > 0)>
        <font color='#002060'>各站点错误码TOP10统计如下,请关注：</font><br>
        <table style="border: 1px solid #000;border-collapse: collapse; text-align: center;vertical-align: top; font-size:10pt; font-family:'微软雅黑','sans-serif';color:#002060">
        <tr style="border: 1px solid #000; text-align: center;vertical-align: top;"  bgcolor='#E4F4FD'>
            <td style="border: 1px solid #000; text-align: centel;vertical-align: top;">站点</td>
            <td style="border: 1px solid #000; text-align: centel;vertical-align: top;">错误码</td>
            <td style="border: 1px solid #000; text-align: centel;vertical-align: top;">错误描述</td>
            <td style="border: 1px solid #000; text-align: centel;vertical-align: top;">错误数量</td>
        </tr>
        <#list errorDetailInfos as errorDetail>
            <tr style="border: 1px solid #000; text-align: center;vertical-align: top;">
            <td style="border: 1px solid #000; text-align: center;vertical-align: top;">${errorDetail.domain}</td>
            <td style="border: 1px solid #000; text-align: center;vertical-align: top;"><a href="http://elk.ppdaicorp.com/app/kibana#/discover?_g=(refreshInterval:(display:Off,pause:!f,value:0),time:(from:'',to:''))&_a=(columns:!(_source),index:'logmetric-*',interval:auto,query:(query_string:(analyze_wildcard:!t,query:'appId:${errorDetail.appid}%20AND%20%22${errorDetail.errorCode}%22 ')),sort:!('@timestamp',desc))">${errorDetail.errorCode}</a></td>
            <td style="border: 1px solid #000; text-align: center;vertical-align: top;">${errorDetail.errorInfo}</td>
            <td style="border: 1px solid #000; text-align: center;vertical-align: top;">${errorDetail.errorCount}</td>
            </tr>
        </#list>
        </table>

</#if>

    <br><br>
    Send IP: ${sendIp}
    Send Time: ${endTime}
<br><br>

</body>
</html>