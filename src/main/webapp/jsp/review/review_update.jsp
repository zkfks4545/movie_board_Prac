<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="review-update" method="post">
  <div class="review-wrap">
    <div>

      <div>
        <div class="review-reg-col">Title</div>
        <div class="review-reg-col2">
          <input name="title" value="${review.title}">
        </div>
      </div>

      <div>
        <div class="review-reg-col">Text</div>
        <div class="review-reg-col2">
          <textarea name="txt" maxlength="200">${review.txt}</textarea>
        <br> <span id="cntSpan">0</span> / 200
        </div>
      </div>
      <div>
        <div>Posted at ${review.date}</div>
      </div>

      <div>
        <button class="review-reg-btn" name="no" value="${param.no}">
                done</button>
        <button class="review-reg-btn" type="button"
                onclick="history.back()">cancel</button>
        <button class="review-reg-btn" type="button"
                onclick="history.go(-2)">list</button>

<%--        <button class="review-reg-btn" type="button"--%>
<%--                onclick="location.href='review'">list</button>--%>
      </div>

    </div>
  </div>
</form>
  <script type="text/javascript">
    const textarea = document.querySelector("textarea[name='txt']");
    const cntSpan = document.querySelector("#cntSpan");
    cntSpan.innerText = textarea.value.length;

  </script>
