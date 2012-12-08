<%@ page import="twitter4j.DirectMessage" pageEncoding="UTF-8" session="false" %><!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title>サンタさん</title>
    <link rel="stylesheet" type="text/css" href="./css/docs.css"/>
    <meta name="viewport" content="width = 700">

</head>

<body style="background-image: url(images/background.png);background-repeat: repeat">

<div style="text-align:center">
    <img src="images/santa.png" width="300px"/><br>

    <div class="message-back">
        <div class="message">
            <% DirectMessage message = (DirectMessage) request.getAttribute("message");
                if (null != message) {
            %>
            <c:out value="${message.text}"/>
            <% } else {%>
            まだサンタさんからメッセージは届いてないよ！
            <% }%>
        </div>
    </div>
</div>
</body>
</html>

