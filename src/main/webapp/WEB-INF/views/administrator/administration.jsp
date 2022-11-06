<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.ssafy.member.model.*"%>
<% List<MemberDto> members = (List<MemberDto>)request.getAttribute("members"); %>
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
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container p-4">
		<h2 style="margin-top:50px;">회원관리</h2>
	</div>
	<div class="container p-4">
		<div class="col-md-7 offset-5">
	              <form class="d-flex" id="form-search" action="">
	              	<input type="hidden" id="act" name="act" value="list">
	                <select
	                  class="form-select form-select-sm ms-5 me-1 w-50"
	                  name="key"
	                  aria-label="검색조건"
	                >
	                  <option value="all" selected>검색조건</option>
	                  <option value="user_id">아이디</option>
	                  <option value="user_name">이름</option>
	                </select>
	                <div class="input-group input-group-sm">
	                  <input type="text" class="form-control" name="word" placeholder="검색어를 입력해주세요." />
	                  <button id="btn-search" class="btn btn-dark" type="button">검색</button>
	                </div>
	              </form>
	</div>
		  <table style="margin-top:50px; text-align:center;" class="table table-hover">
		    <thead>
		      <tr>
		        <th>아이디</th>
		        <th>이름</th>
		        <th>비밀번호</th>
		        <th>이메일</th>
		        <th>가입일</th>
		        <th>회원등급</th>
		        <th></th>
		      </tr>
		    </thead>
		    <tbody>
				<c:forEach var="member" items="${members}">
					<tr>
						<td>${member.getUserId()}</td>
						<td>${member.getUserName()}</td>
						<td>${member.getUserPwd()}</td>
						<td>${member.getEmailId()}@${member.getEmailDomain()}</td>
						<td>${member.getJoinDate()}</td>
						<td>${member.getUserClass()}</td>
						<td> 
							<button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-1 ms-1">
		                		정보수정
		             		</button>
							<button type="button" id="btn-delete" class="btn btn-outline-danger mb-1 ms-1">
			               	 	회원탈퇴
			              	</button>
		              	</td>
					</tr>
				</c:forEach>
		    </tbody>
		  </table>
		  <div class="row">
	          <ul class="pagination justify-content-center">
	            <c:set var ="lastNum" value = "${(memberSize - 1) / 20 + 1}"/>
	            <c:set var ="startNum" value = "${1}"/>
	            <c:if test="${param.pgno > 1}">
		            <li class="page-item">
		              <a class="page-link" href="${root}/user?act=list&pgno=${param.pgno - 1}&key=&word=">이전</a>
		            </li>
	            </c:if>
	            <c:forEach var="item" varStatus="status" begin="${startNum}" end="${lastNum}" step="1">
	            	<c:if test="${item eq param.pgno}">
	            		<li class="page-item active">
	            	</c:if>
	            	<c:if test="${item ne param.pgno}">
	            		<li class="page-item">
	            	</c:if>
		            	<a class="page-link" href="${root}/user?act=list&pgno=${item}&key=&word=">${item}</a>
		            </li>
	            </c:forEach>
	            <c:if test="${param.pgno < lastNum - 1}">
		            <li class="page-item">
		            	<a class="page-link" href="${root}/user?act=list&pgno=${param.pgno + 1}&key=&word=">다음</a>
		            </li>
	            </c:if>
	          </ul>
	        </div>
		</div>
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
			crossorigin="anonymous">
	    </script>
</body>
	<script>
	      document.querySelector("#btn-search").addEventListener("click", function () {
	    	  let form = document.querySelector("#form-search");
	          form.setAttribute("action", "${root}/user");
	          form.submit();
	      });
	</script>
</html>