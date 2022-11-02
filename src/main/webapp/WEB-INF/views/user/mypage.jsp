<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
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
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a152d2aabef07da25afb167fc3a1a32c&libraries=services">
	</script>
    <style>
      * {
        text-align: center;
      }

      ul.nav {
        background-color: rgba(201, 201, 201, 0.5);
        padding: 10px;
        border-radius: 10px;
      }
      footer {
        font-family: "Nanum Gothic", sans-serif;
        text-align: center;
        background-color: rgba(125, 111, 111, 0.5);
      }
      .carousel-inner img {
        width: 100%;
        height: 100%;
      }

      label {
        display: inline-block;
        width: 140px;
        text-align: left;
      }
    </style>
  </head>
  <body>
    <br /><br />
    <div class="container">
      <h1>회원 정보</h1>
      <hr />
      <img
        src="./assets/img/user.png"
        alt="profile"
        style="width: 30%; height: 30%"
      />
      <br /><br /><br />
      <form id="form-info" method="POST" action="">
      <input type="hidden" name="act" value="delete">
      <table class="table">
        <tbody>
          <tr>
            <td>아이디</td>
            <input type="hidden" name="userid" value="${userinfo.userId}">
            <td id="tid" name="userid">${userinfo.userId}</td>
          </tr>
          <tr>
            <td>비밀번호</td>
            <input type="hidden" name="userpwd" value="${userinfo.userPwd}">
            <td id="tpw" name="userpwd">${userinfo.userPwd}</td>
          </tr>
          <tr>
            <td id="tname" name="username">이름</td>
            <td id="tname">${userinfo.userName}</td>
          </tr>
          <tr>
            <td id="temail" name="emailid">이메일</td>
            <td id="taddress">${userinfo.emailId}@${userinfo.emailDomain}</td>
          </tr>
          <tr>
            <td id="tclass" name="userclass">회원등급</td>
            <td id="tgrade">${userinfo.userClass}</td>
          </tr>
        </tbody>
      </table>
      </form>

	  <form id="form-edit" method="POST" action="">
      <input type="hidden" name="act" value="mvedit">
      <button
        type="button"
        class="btn btn-warning"
        id="edit"
        data-toggle="modal"
        data-target="div#mymodal"
      >
       	정보 수정
      </button>
      <button type="button" class="btn btn-danger" id="remove">회원 탈퇴</button>
      <button type="button" class="btn btn-primary" id="change">비밀번호 변경</button>
      </form>
    </div>
  </body>
  <script>
  document.querySelector("#remove").addEventListener("click", function () {
 	 if(confirm("'확인'을 누르시면 탈퇴됩니다.")){
 		let form = document.querySelector("#form-info");
 		form.setAttribute("action", "${root}/user");
        form.submit();
 		alert("정상적으로 탈퇴되었습니다.");
 	 }
   });
  document.querySelector("#edit").addEventListener("click", function () {
	 	 if(confirm("회원 정보를 수정하시겠습니까?")){
	 		let form = document.querySelector("#form-edit");
	 		form.setAttribute("action", "${root}/user");
	        form.submit();
	 	 }
  });
  document.querySelector("#change").addEventListener("click", function () {
	  location.href = "${root}/user?act=mvcurrent";
});
  </script>
  	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous">
	</script>
</html>
