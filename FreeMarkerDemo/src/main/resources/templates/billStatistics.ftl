<!DOCTYPE html>
<html lang="en">
<body>
<font>All:</font>
<font>【账单统计通知】</font>
<br><br>
    <table style="border: 1px solid #000;border-collapse: collapse;
        text-align: center;vertical-align: top; font-size:10pt;
        font-family:'微软雅黑','sans-serif';color:#002060">
        <tr style="border: 1px solid #000; text-align: center;vertical-align: top;">
            <td bgcolor='#E4F4FD' style="border: 1px solid #000; text-align: centel;vertical-align: top;">任务名称</td>
            <td style="border: 1px solid #000;">${monitorData.taskName}</td>
        </tr>
        <tr style="border: 1px solid #000; text-align: center;vertical-align: top;">
            <td bgcolor='#E4F4FD' style="border: 1px solid #000; text-align: centel;vertical-align: top;">任务状态</td>
            <td style="border: 1px solid #000;">${monitorData.taskDesc}</td>
        </tr>
        <tr style="border: 1px solid #000; text-align: center;vertical-align: top;">
            <td bgcolor='#E4F4FD' style="border: 1px solid #000; text-align: centel;vertical-align: top;">账单日期</td>
            <td style="border: 1px solid #000;">${monitorData.logDate}</td>
        </tr>
        <tr style="border: 1px solid #000; text-align: center;vertical-align: top;">
            <td bgcolor='#E4F4FD' style="border: 1px solid #000; text-align: centel;vertical-align: top;">写入数量</td>
            <td style="border: 1px solid #000;">${monitorData.writeCount}</td>
        </tr>

    </table>
<br>
Send IP: ${sendIp}
Send Time: ${endTime}
<br><br>
</body>
</html>