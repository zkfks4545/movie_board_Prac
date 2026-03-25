<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>- Movie Edit -</h1>
<form action="edit" method="post" enctype="multipart/form-data">
<div style="display: flex; justify-content: center;">
    <div class="movie-detail">
        <div>
            <div class="col-1">No.</div>
            <div class="col-2"><input type="text" value="${movie.no}" disabled></div>
        </div>
        <div>
            <div><img src="/images/${movie.img}">
                <input type="file" name="newImg">
                <input name="oldImg" value="${movie.img}">
            </div>
        </div>
        <div>
            <div class="col-1">Title.</div>
            <div class="col-2"><input type="text" name="title" value="${movie.title}"></div>
<%--            <input type="text" value="${movie.no}" name="no" hidden="">--%>
        </div>
        <div>
            <div class="col-1">Actor.</div>
            <div class="col-2"><input type="text" name="actor" value="${movie.actor}">}</div>
        </div>

        <div>
            <div class="col-1">Story.</div>
            <div class="col-2"><textarea name="story" id="" cols="30" rows="10">${movie.story}</textarea></div>
        </div>
        <div>
            <input type="hidden" name="no" value="${movie.no}">
            <button class="movie-btn">done</button>
<%--            <button class="movie-btn" name="no" value="${movie.no}">done</button>--%>
<%--            <button class="movie-btn">done</button>--%>
        </div>
    </div>
</div>
</form>
</body>
</html>
