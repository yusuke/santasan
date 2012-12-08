<%@ page import="twitter4j.DirectMessage" pageEncoding="UTF-8" session="false" %>
<%@ page import="twitter4j.URLEntity" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title>サンタさん</title>
    <link rel="stylesheet" type="text/css" href="./css/docs.css"/>
    <meta name="viewport" content="width = 700">
    <link rel="apple-touch-icon" href="images/santa-icon.png" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
</head>

<body style="background-image: url(images/background.png);background-repeat: repeat">

<div style="text-align:center">
    <img src="images/santa.png" width="300px"/><br>

    <div class="message-back">
        <div class="message">
            <% DirectMessage message = (DirectMessage) request.getAttribute("message");
                if (null != message) {
                    request.setAttribute("text", message.getText().replaceAll("http://[^ ]*", ""));%>
            <c:out value="${text}"/><br>
            <% URLEntity[] medias = message.getURLEntities();
                if (medias.length != 0) {
                    for (URLEntity url : medias) {
                        out.print("<img src='" + url.getExpandedURL() + "' width='300'/><br>");
                    }
                }
            %>
            <% } else {%>
            まだサンタさんからメッセージは届いてないよ！
            <% }%>
        </div>
    </div>
</div>
</body>
</html>

