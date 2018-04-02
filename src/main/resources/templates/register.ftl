<!DOCTYPE html>
<html class="bg-black">
    <head>
        <meta charset="UTF-8">
        <title>管理员登录 - Beehive餐饮后台管理系统</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <script type="text/javascript">
            function formReset()
            {
                document.getElementById("login-form").reset();
                document.getElementById("error-msg").innerHTML = "";
            }
        </script>
        <script>
            function check(){
                var nameValue=window.document.getElementById("input-username").value;
                if (nameValue == "") // 或者是!nameValue
                {
                    window.alert("用户名不能为空!");
                    return false;
                }
                var nameValue=window.document.getElementById("input-password").value;
                if (nameValue == "") // 或者是!nameValue
                {
                    window.alert("密码不能为空!");
                    return false;
                }
                var nameValue=window.document.getElementById("input-email").value;
                if (nameValue == "") // 或者是!nameValue
                {
                    window.alert("邮箱不能为空!");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body class="bg-black">

        <div class="form-box" id="login-box">
            <div class="header">欢迎使用<br/>后台管理系统</div>
            <form id="login-form" action="/adduser" method="post" onsubmit="return check()">
                <div class="body bg-gray">
                	<div id="error-msg" class="text-red">${msg?default("")}</div>
                    <div class="form-group">
                        <input id="input-username" type="text" name="userName" class="form-control" placeholder="登录名"/>
                    </div>
                    <div class="form-group">
                        <input id="input-password" type="password" name="password" class="form-control" placeholder="密码"/>
                    </div>
                    <div class="form-group">
                        <input id="input-email" type="email" name="email" class="form-control" placeholder="邮箱"/>
                    </div>
                </div>
                <div class="footer">
                    <button type="submit" class="btn bg-olive btn-block">立即注册</button>
                </div>
            </form>
        </div>

        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>