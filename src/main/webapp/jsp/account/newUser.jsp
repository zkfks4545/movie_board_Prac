<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1> - new account - </h1>
<form action="user-new" method="post">


<div class="info-wrap">
    <div class="info-row">
        <div>ID.</div>
        <div>
            <input type="text" name="id" id="id" required> <br>
            <button class="movie-btn" type="button" onclick="checkId()">ID 중복체크</button>
            <div id="result"></div>
        </div>
    </div>
    <div class="info-row">
        <div>PW.</div>
        <div><input type="text" name="pw" required></div>
    </div>
    <div class="info-row">
        <div>NAME.</div>
        <div><input type="text" name="name" required></div>
    </div>
    <div>
        <button class="movie-btn" type="submit">done</button>
        <button type="button" class="movie-btn" onclick="history.back()">cancel</button>
    </div>
</div>
</form>
<script>
    function checkId() {
        let id = document.getElementById("id").value;

        fetch("id-check?id=" + id)
            .then(res => res.text())
            .then(data => {
                document.getElementById("result").innerText = data;
            });
    }
</script>
</body>
</html>
