<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<table class="table">
				<tbody>
					
					<tr>
						<td>시</td>
						<td id="sidoName" name="sidoName">${houseDealInfo.sidoName}</td>
					</tr>
					<tr>
						<td>구/군</td>
						<td id="gugunName" name="gugunName">${houseDealInfo.gugunName}</td>
					</tr>
					<tr>
						<td>동</td>
						<td id="dongName" name="dongName">${houseDealInfo.dongName}</td>
					</tr>
					<tr>
						<td>아파트명</td>
						<td id="apartmentName" name="apartmentName">${houseDealInfo.apartmentName}</td>
					</tr>
					<tr>
						<td>거래년도</td>
						<td id="dealYear" name="dealYear">${houseDealInfo.dealYear}년</td>
					</tr>
					<tr>
						<td>거래월</td>
						<td id="dealMonth" name="dealMonth">${houseDealInfo.dealMonth}월</td>
					</tr>
					<tr>
						<td>거래일</td>
						<td id="dealDay" name="dealDay">${houseDealInfo.dealDay}일</td>
					</tr>
					<tr>
						<td>면적</td>
						<td id="area" name="area">${houseDealInfo.area}m<sup>2</sup></td>
					</tr>
					<tr>
						<td>층</td>
						<td id="floor" name="floor">${houseDealInfo.floor}층</td>
					</tr>

					<tr>
						<td>거래금액</td>
						<td id="dealAmount" name="dealAmount">${houseDealInfo.dealAmount}
							만원</td>
					</tr>
				</tbody>
			</table>
			<div class="d-flex justify-content-end">
				<button type="button" id="btn-list"
					class="btn btn-outline-primary mb-3">거래목록</button>
				<button type="button" id="btn-mv-modify"
					class="btn btn-outline-success mb-3 ms-1">수정</button>
				<button type="button" id="btn-delete"
					class="btn btn-outline-danger mb-3 ms-1">삭제</button>
			</div>
		</div>
	</div>

	<%@ include file="/common/footer.jsp"%>
	<script type="text/javascript">
		document.querySelector("#btn-list").addEventListener("click",
				function() {
					location.href="${root}/housedeal/list.jsp";
				});

		document.querySelector("#btn-mv-modify").addEventListener("click",
				function() {
					location.href="${root}/housedeal?act=mv-modify&no=${houseDealInfo.no}";
				});

		document.querySelector("#btn-delete").addEventListener("click",
				function() {
					if (confirm("정말 삭제하시겠습니까?")) {
						location.href="${root}/housedeal?act=delete&no=${houseDealInfo.no}";
					}
				});
	</script>
</body>
</html>
