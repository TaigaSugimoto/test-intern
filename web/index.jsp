<%-- 
    Document   : index
    Created on : 2018/05/25
    Author     : A.Miyazato
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="img/quillpen_line.png" type="image/x-icon">
        <meta name="description" content="">
        <title>Translator APP</title>

        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap/bootstrap-grid.min.css" rel="stylesheet">
        <link href="css/bootstrap/bootstrap-reboot.min.css" rel="stylesheet">
        <link href="css/mobirise/mobirise-icons/mobirise-icons.css" rel="stylesheet">
        <link href="css/mobirise/tether.min.css" rel="stylesheet">
        <link href="css/mobirise/theme/style.css" rel="stylesheet">
        <link href="css/mobirise/dropdown/style.css" rel="stylesheet">
        <link href="css/mobirise/mbr-additional.css" rel="stylesheet" type="text/css">
        <link href="css/translatorappstyle.css" rel="stylesheet">

        <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="js/popper.min.js"></script>
        <script type="text/javascript" src="js/tether.min.js"></script>
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/smooth-scroll.js"></script>
        <script type="text/javascript" src="js/dropdown/script.min.js"></script>
        <script type="text/javascript" src="js/jquery.touch-swipe.min.js"></script>
        <script type="text/javascript" src="js/theme/script.js"></script>
        <script type="text/javascript" src="js/clipboard.min.js"></script>
        <script type="text/javascript" src="js/translator-app.js"></script>

    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
            <%@include file="translator.jsp" %>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>