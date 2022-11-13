<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>支付页</title>
</head>
<body>
    <form action="index/pay" method="post">
        单号:
        <input type="text" name="out_trade_no">
        <br/>
        名称:
        <input type="text" name="subject">
        <br/>
        金额：
        <input type="text" name="total_amount">
        <br/>
        描述：
        <input type="text" name="body">
        <br/>
        产品码：
        <input type="text" name="code">
        <br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
