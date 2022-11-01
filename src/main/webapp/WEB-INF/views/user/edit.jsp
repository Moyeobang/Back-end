<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/common/header.jsp"%>
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
      <form id="form-edit" method="POST" action="">
      <input type="hidden" name="act" value="edit">
      <table class="table">
        <tbody>
          <tr>
            <td>아이디</td>
            <td id="tid" name="userid"><input name="userid" value="${userinfo.userId}" style="border:none;" readonly/></td>
          </tr>
          <tr>
            <td>비밀번호</td>
            <td id="tpw" name="userpwd"><input name="userpwd" value="${userinfo.userPwd}" style="border:none;" readonly/></td>
          </tr>
          <tr>
            <td id="tname" name="username">이름</td>
            <td id="tname"><input name="username" value="${userinfo.userName}" style="width:100%" /></td>
          </tr>
          <tr>
            <td id="temail" name=useremailid>이메일</td>
            <td id="taddress">
            <div class="input-group">
                <input
                  type="text"
                  class="form-control form-group-sm"
                  id="emailid"
                  name="emailid"
                  value="${userinfo.emailId}"
                  style="width:0px;"
                />
                <span class="input-group-text">@</span>
                <select
                  class="form-select"
                  id="emaildomain"
                  name="emaildomain"
                  aria-label="이메일 도메인 선택"
                  style="width:0px;"
                >
                  <option selected>도메인 선택</option>
                  <option value="ssafy.com">ssafy.com</option>
                  <option value="google.com">google.com</option>
                  <option value="naver.com">naver.com</option>
                  <option value="kakao.com">kakao.com</option>
                </select>
              </div></td>
          </tr>
          <tr>
            <td id="tclass" name="userclass">회원등급</td>
            <td id="tgrade"><input name="userclass" value="${userinfo.userClass}" style="border:none;" readonly/></td>
          </tr>
        </tbody>
      </table>
      </form>
      <script>

      </script>
      <button
        type="button"
        class="btn btn-warning"
        id="edit"
        data-toggle="modal"
        data-target="div#mymodal"
      >
       	정보 수정
      </button>
      <button type="button" class="btn btn-outline-dark" id="back">변경 취소</button>
    </div>
  </body>
  	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous">
	</script>
  <script>
  document.querySelector("#back").addEventListener("click", function () {
 	 if(confirm("현재까지 작성된 내용이 사라집니다.")){
 		location.href = "${root}/user?act=mypage";
 	 }
   });
  document.querySelector("#edit").addEventListener("click", function () {
	if(confirm("회원 정보를 수정하시겠습니까?")){
		let form = document.querySelector("#form-edit");
 		form.setAttribute("action", "${root}/user");
        form.submit();
	 }
  });
  </script>
</html>
