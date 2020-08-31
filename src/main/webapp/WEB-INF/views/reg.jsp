<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type="text/javascript">
        $(document).ready(function () {
            $('#name').blur(function () {
                let name = $('#name').val()
                if (name !== '') {
                    $.ajax({
                        type: 'post',
                        url: '/check',
                        data: {name: name},
                        dataType: "json",
                    }).done(function (data) {
                        if (data === 'not') {
                            $('#check').empty();
                        } else {
                            $('#check').text('! пользователь уже существует ');
                        }
                    })
                } else {
                    $('#check').empty();
                }
            })
        });

        function validate() {
            const msg = "пожалуйста, заполните поле : ";
            if ($('#name').val() === '') {
                alert(msg + $('#name').attr('title'))
                return false;
            } else if ($('#email').val() === '') {
                alert(msg + $('#email').attr('title'))
                return false;
            } else if ($('#password').val() === '') {
                alert(msg + $('#password').attr('title'))
                return false;
            }
            return true;
        }
    </script>
    <title>Форум job4j</title>
</head>
<body>


<div class="container mt-3">
    <div class="row">
        <h4>Регистрация</h4>
    </div>
    <div class="row">
        <table class="table">
            <tbody>
            <form action="<c:url value='/reg'/>" method="post">
                <tr>
                    <td>
                        <div>
                            Имя : <input id="name" type="text" name="username" title="имя"
                                         placeholder="введите имя"/>
                            <span id="check" style="color: brown"></span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div>
                            почта : <input id="email" type="text" name="email" title="почта"
                                           placeholder="введите почту">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div>
                            пароль : <input id="password" type="text" name="password" title="пароль"
                                            placeholder="введите пароль">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div>
                            <input name="submit" type="submit" onclick="return validate()" value="Зарегистрироваться"/>
                        </div>
                    </td>
                </tr>
            </form>
            </tbody>
        </table>
        <span style="color: brown"><c:out value="${errorReg}"/></span>
    </div>
</div>
<div style="position: absolute; right: 10%; font-style: italic; color: grey "><a href="<c:url value="/"/>"><<< назад
    <<<</a></div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>