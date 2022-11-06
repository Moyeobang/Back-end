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
      <h1>현재 비밀번호 확인</h1>
      <hr />
      <img
        src="./assets/img/user.png"
        alt="profile"
        style="width: 30%; height: 30%"
      />
      <br /><br /><br />
      <form id="form-current" method="POST" action="">
      <input type="hidden" name="act" value="current">
      <table class="table">
        <tbody>
          <tr>
            <input id="userid" name="userid" value="${userinfo.userId}" type="hidden" readonly></input>
            <input id="userpwd" name="userpwd" value="${userinfo.userPwd}" type="hidden" readonly></input>
            <td>현재 비밀번호</td>
            <td name="current"><input id="current" name="current" type="password" value=""/></td>
          </tr>    
        </tbody>
      </table>
      </form>
      <script>

      </script>
      <button
        type="button"
        class="btn btn-warning"
        id="currentbtn"
        data-toggle="modal"
        data-target="div#mymodal"
      >
       	비밀번호 확인
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
 	 if(confirm("회원 정보 창으로 되돌아갑니다.")){
 		location.href = "${root}/user?act=mypage";
 	 }
   });
  document.querySelector("#currentbtn").addEventListener("click", function () {
	  if(document.querySelector("#current").value==document.querySelector("#userpwd").value){
		let form = document.querySelector("#form-current");
	 	form.setAttribute("action", "${root}/user");
	    form.submit();
	  }else{
		alert("입력하신 비밀번호가 계정 정보와 일치하지 않습니다.");
	  }
  });
  </script>
</html>
