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
    </head>
    <body class="bg-black">

        <div class="form-box" id="login-box">
            <div class="header">欢迎使用<br/>后台管理系统</div>
            <form id="login-form" action="/actionLogin" method="post">
                <div class="body bg-gray">
                	<div id="error-msg" class="text-red">${msg?default("")}</div>
                    <div class="form-group">
                        <input type="text" name="userName" class="form-control" placeholder="登录名"/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="密码"/>
                    </div>
                </div>
                <div class="footer">
                    <button type="submit" class="btn bg-olive btn-block">登录</button>
                    <button type="button" onclick="formReset()" class="btn bg-orange btn-block">重置</button>
                    <#--<button type="button" onclick="formReset()" class="btn bg-orange btn-block">注册新账号</button>-->
                    <a href="/forgetpassword" class="text-right">忘记密码</a>
                    <a href="/register" class="text-right pull-right">注册新账号</a>
                </div>
            </form>
        </div>

        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>