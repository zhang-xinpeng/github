<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="../static/boot/css/bootstrap.min.css">
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <script src="../static/boot/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4">
            <div style="height: 300px"></div>
            <form>
                <div class="form-group">
                    <label for="username">用户名：</label>
                    <input type="text" id="username" name="username" class="form-control">
                </div>
                <div class="form-group">
                    <label for="password">密码：</label>
                    <input type="password" id="password" name="password" class="form-control"/>
                </div>
                <div class="form-group">
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-info btn-block" id="login">登录</button>
                        <script>
                            $('#login').click(function () {
                                $.ajax({
                                    url: '${pageContext.request.contextPath}/user/login',
                                    method: 'get',
                                    data: {
                                        username: $('#username').val(),
                                        password: $('#password').val()
                                    },
                                    dataType: 'text',
                                    success: function (data) {
                                        if (data == 'success') {
                                            location.href = '${pageContext.request.contextPath}/jsp/main.jsp';
                                        } else
                                            alert(data);
                                    }
                                });
                            });
                        </script>
                    </div>
                    <div class="col-sm-6">
                        <button class="btn btn-primary btn-block">注册</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>