<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
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
	<c:if test="${empty houseDealInfo || houseDealInfo eq null}">
		<script type="text/javascript">
			alert("삭제되었거나 정상적인 URL 접근이 아닙니다.");
		</script>
	</c:if>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="sky">실매매가</mark>
				</h2>
			</div>
			<form id="form-modify" method="POST" action="">
				<input type="hidden" name="act" value="modify">
				<input type="hidden" name="no" value="${houseDealInfo.no}">
				<table class="table">
					<tbody>
						<tr>
							<td>시</td>
							<td>${houseDealInfo.sidoName}</td>
						</tr>
						<tr>
							<td>구/군</td>
							<td>${houseDealInfo.gugunName}</td>
						</tr>
						<tr>
							<td>동</td>
							<td>${houseDealInfo.dongName}</td>
						</tr>
						<tr>
							<td>아파트명</td>
							<td>${houseDealInfo.apartmentName}</td>
						</tr>
						<tr>
							<td>거래년도</td>
							<td><input type="text" name="dealYear" value="${houseDealInfo.dealYear}">년</td>
						</tr>
						<tr>
							<td>거래월</td>
							<td><input type="text" name="dealMonth" value="${houseDealInfo.dealMonth}">월</td>
						</tr>
						<tr>
							<td>거래일</td>
							<td><input type="text" name="dealDay" value="${houseDealInfo.dealDay}">일</td>
						</tr>
						<tr>
							<td>면적</td>
							<td><input type="text" name="area" value="${houseDealInfo.area}"> m<sup>2</sup></td>
						</tr>
						<tr>
							<td>층</td>
							<td><input type="text" name="floor" value="${houseDealInfo.floor}"> 층</td>
						</tr>

						<tr>
							<td>거래금액</td>
							<td><input type="text" name="dealAmount" value="${houseDealInfo.dealAmount}"> 만원</td>
						</tr>
					</tbody>
				</table>
			</form>

			<div class="col-auto text-center">
				<button type="button" id="btn-modify"
					class="btn btn-outline-primary mb-3">글수정</button>
				<button type="button" id="btn-list"
					class="btn btn-outline-danger mb-3">목록으로이동...</button>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script>
		document.querySelector("#btn-modify").addEventListener("click",
				function() {
					
						let form = document.querySelector("#form-modify");
						form.setAttribute("action", "${root}/housedeal");
						form.submit();
					
				});

		document.querySelector("#btn-list").addEventListener("click",
				function() {
					if (confirm("취소를 하시면 작성중인 글은 삭제됩니다.\n취소하시겠습니까?")) {
						location.href="${root}/housedeal/list.jsp";
					}
				});
	</script>
</body>
</html>
