<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="title" required="true" %>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title><%=title%> | #countyusuke</title>
    <base href="/santa/">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.0/css/bootstrap-responsive.min.css">
    <link href="./css/docs.css"
          rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
</head>
<body style="background-image: url(images/background.png);background-repeat: repeat">

<div style="text-align:center" class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <jsp:doBody/>
        </div>
    </div>
</div>
<script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.0/js/bootstrap.min.js"></script>
</body>
</html>

