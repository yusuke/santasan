<%@page pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title>サンタさん</title>
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.0/css/bootstrap-responsive.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <meta name="viewport" content="width = 700">

</head>

<body style="background-image: url(images/background.png);background-repeat: repeat">

<div style="text-align:center" class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="message">
                <img src="images/santa.png" width="300px"/><br>
                <% String message = (String) request.getAttribute("message");
                    if (null != message) {
                %>
                <%=message%>

                <% } else {%>
                まだサンタさんからメッセージは届いてないよ！
                <% }%>
            </div>
        </div>
    </div>
</div>
<script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.0/js/bootstrap.min.js"></script>
</body>
</html>

