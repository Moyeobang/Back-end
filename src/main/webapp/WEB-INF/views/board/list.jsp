<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>SSAFY BOOK CAFE</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a152d2aabef07da25afb167fc3a1a32c&libraries=services"></script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12" style="margin-top:40px; margin-bottom:40px;">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">공지사항</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
            <c:if test="${!empty userinfo}">
	            <c:if test="${userinfo.userClass eq '관리자'}">
	              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm">
	                	글쓰기
	              </button>
	            </c:if>
            </c:if>
            </div>
            <div class="col-md-7 offset-3">
              <form class="d-flex" id="form-search" action="">
              	<input type="hidden" id="act" name="act" value="list">
                <select
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  name="key"
                  aria-label="검색조건"
                >
                  <option value="all" selected>검색조건</option>
                  <option value="subject">제목</option>
                  <option value="userid">작성자</option>
                </select>
                <div class="input-group input-group-sm">
                  <input type="text" class="form-control" name="word" placeholder="검색어..." />
                  <button id="btn-search" class="btn btn-dark" type="button">검색</button>
                </div>
              </form>
            </div>
          </div>
          <table class="table table-hover" style="margin-top:20px;">
            <thead>
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
              </tr>
            </thead>
            <tbody>
			<c:forEach var="article" items="${articles}">
              <tr class="text-center">
                <th scope="row">${article.articleNo}</th>
                <td class="text-start">
                  <a
                    href="#"
                    class="article-title link-dark"
                    data-no="${article.articleNo}"
                    style="text-decoration: none"
                  >
                    	${article.subject}
                  </a>
                </td>
                <td>${article.userId}</td>
                <td>${article.hit}</td>
                <td>${article.registerTime}</td>
              </tr>
			</c:forEach>
            </tbody>
          </table>
        </div>
        <div class="row">
          <ul class="pagination justify-content-center">
            <c:set var ="lastNum" value = "${(size - 1) / 20 + 1}"/>
            <c:set var ="startNum" value = "${1}"/>
            <c:if test="${param.pgno > 1}">
	            <li class="page-item">
	              <a class="page-link" href="${root}/board?act=list&pgno=${param.pgno - 1}&key=&word=">이전</a>
	            </li>
            </c:if>
            <c:forEach var="item" varStatus="status" begin="${startNum}" end="${lastNum}" step="1">
            	<c:if test="${item eq param.pgno}">
            		<li class="page-item active">
            	</c:if>
            	<c:if test="${item ne param.pgno}">
            		<li class="page-item">
            	</c:if>
	            	<a class="page-link" href="${root}/board?act=list&pgno=${item}&key=&word=">${item}</a>
	            </li>
            </c:forEach>
            <c:if test="${param.pgno < lastNum - 1}">
	            <li class="page-item">
	            	<a class="page-link" href="${root}/board?act=list&pgno=${param.pgno + 1}&key=&word=">다음</a>
	            </li>
            </c:if>
          </ul>
        </div>
      </div>
      
      <!-- 공지사항 작성 성공 시 뜰 Toast div -->
		<div class="toast-container position-fixed bottom-0 end-0 p-3">
		  <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
		    <div class="toast-header">
		      <i class="bi bi-bell"></i>
		      <strong class="me-auto">공지사항 등록</strong>
		      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
		    </div>
		    <div class="toast-body">
		      	새로운 공지사항이 등록되었습니다.
		    </div>
		  </div>
		</div>
    </div>
    <form id="form-no-param" method="get" action="${root}/board">
      <input type="hidden" id="act" name="act" value="view">
      <input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
      <input type="hidden" id="key" name="key" value="${param.key}">
      <input type="hidden" id="word" name="word" value="${param.word}">
      <input type="hidden" id="articleno" name="articleno" value="">
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
    <script>
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
       	 	document.querySelector("#articleno").value = this.getAttribute("data-no");
       	 	document.querySelector("#form-no-param").submit();
        });
      });

      document.querySelector("#btn-mv-register").addEventListener("click", function () {
        location.href = "${root}/board?act=mvwrite";
      });
      
      document.querySelector("#btn-search").addEventListener("click", function () {
    	  let form = document.querySelector("#form-search");
          form.setAttribute("action", "${root}/board");
          form.submit();
      });
      
      setTimeout(function() {
    	  if("${msg}" === "성공") {
        	  const toastLiveExample = document.getElementById('liveToast');
        	  const toast = new bootstrap.Toast(toastLiveExample)

       	      toast.show();
         }
		}, 500);
    </script>

	<%@ include file="/common/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous"></script>
</body>
</html>

