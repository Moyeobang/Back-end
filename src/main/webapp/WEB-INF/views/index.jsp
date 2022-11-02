<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
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
<link rel="stylesheet" href="${root}/assets/css/main.css" />
<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a152d2aabef07da25afb167fc3a1a32c"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<!-- 중앙 content start -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div id="jumbotron"
					class="mt-4 p-5 jumbotron text-black jumbotron-image shadow"
					>
					<h2 class="mb-4" style="text-align: center">아파트별 및 동별 시세 검색</h2>

					<div class="content-search" style="margin-top:50px;">
						<div class="container">
							<div class="row col-md-12 mb-3 mt-3">
								<!-- justify-content-center -->
								<div class="form-group col-md-2 pe-1 ps-1">
									<select class="form-select bg-secondary text-light" id="sido">
										<option value="">시도선택</option>
									</select>
								</div>
								<div class="form-group col-md-2 pe-1 ps-1">
									<select class="form-select bg-secondary text-light" id="gugun">
										<option value="">구군선택</option>
									</select>
								</div>
								<div class="form-group col-md-2 pe-1 ps-1" id="dongDiv">
									<select class="form-select bg-secondary text-light" id="dong">
										<option value="">동선택</option>
									</select>
								</div>
								<!-- <div class="form-group col-md-2 pe-1 ps-1">
											<select class="form-select bg-secondary text-light" id="year"></select>
										</div>
										<div class="form-group col-md-2 pe-1 ps-1">
											<select class="form-select bg-secondary text-light"
												id="month">
												<option value="">매매월</option>
											</select>
										</div> -->
								<div class="form-group col-md-5 pe-1 ps-1" id="aptNameDiv">
									<input class="form-control" id="aptName" type="text"
										placeholder="아파트명 입력" />

								</div>
								<div class="form-group col-md-1 pe-1 ps-1">
									<button type="button" class="btn btn-outline-secondary me-4"
										onclick="javascript:searchBtn(true, 2)">검색</button>
								</div>
							</div>
						</div>
					</div>
					<div class="content-map col-sm-12" style="background-image: url(${root}/assets/img/map.png)">
							<div id="map" style="width: 100%; height: 500px"></div>
						</div>
					<div class="container" style="display: none" id="aptInfoByDong">
						<div class="row">
							<div class="col-sm-12">
								<table class="table table-hover text-center" style="margin-top:30px;">
									<tr>
										<th>동</th>
										<th>아파트이름</th>
										<th>층</th>
										<th>면적</th>
										<th>거래일자</th>
										<th>거래가격</th>
									</tr>
									<tbody id="houseDealList"></tbody>
								</table>
							</div>

						</div>
						<div class="row">
							<ul class="pagination justify-content-center" id="pagination">

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="col-sm-12">
			<div class="row">
				<div class="col-sm-4 text-center">
					<img
						src="https://blog.kakaocdn.net/dn/bPJj9J/btqXRvIP6Iz/P6zBJCUEGysYlGz9qStC8K/img.png"
						class="img-thumbnail mt-5" />
				</div>
				<div class="col-sm-4">
					<ul class="list-group list-group-flush mt-4">
						<li class="list-group-item"><h2>지혜롭게 내집 마련하기</h2></li>
						<li class="list-group-item border-0"><img
							src="data:image/png;base64,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"
							width="40" height="40" /> <a href="https://blog.naver.com/land_admin/221172176139" target='_blank'>가용자금 확인 및 대출 계획</a></li>
						<li class="list-group-item border-0"><img
							src="https://e7.pngegg.com/pngimages/614/475/png-clipart-computer-icons-scalable-graphics-house-illustration-house-cdr-angle.png"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221172968369" target='_blank'>집 종류 및 지역 선택</a></li>
						<li class="list-group-item border-0"><img
							src="data:image/jpeg;base64,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"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221172975575" target='_blank'>정보 수집 & 시세파악</a></li>
						<li class="list-group-item border-0"><img
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKKLwH5KNeqEOraFqXjoqurLvoVT5RaYPx1Q&usqp=CAU"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221173052981" target='_blank'>부동산 방문 & 집구경</a></li>
						<li class="list-group-item border-0"><img
							src="https://w1.pngwing.com/pngs/194/45/png-transparent-pencil-icon-drawing-icon-design-black-and-white-line-material-angle-hardware-accessory.png"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221176290264" target='_blank'>계약 및 잔금 치르기</a></li>
						<li class="list-group-item border-0"><img
							src="https://cdn-icons-png.flaticon.com/512/4883/4883292.png"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221176300611" target='_blank'>소유권 이전등기</a></li>
						<li class="list-group-item border-0"><img
							src="https://kr.seaicons.com/wp-content/uploads/2015/10/Engineering-icon.png"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221176306347" target='_blank'>인테리어 공사</a></li>
							<li class="list-group-item border-0"><img
							src="https://img.icons8.com/ios-filled/344/inventory-flow.png"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221176998743" target='_blank'>이사</a></li>
							<li class="list-group-item border-0"><img
							src="https://img.icons8.com/ios-filled/344/home.png"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221177010341" target='_blank'>거주 하면서</a></li>
							<li class="list-group-item border-0"><img
							src="https://img.icons8.com/ios-glyphs/344/money--v1.png"
							width="40" height="30" /> <a href="https://blog.naver.com/land_admin/221177033918" target='_blank'>매도하기</a></li>
					</ul>
				</div>
				<div class="col-sm-4">
					<ul class="list-group list-group-flush mt-4">
						<li class="list-group-item bo"><h2>부동산 뉴스</h2></li>
						<div id="news"></div>

					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- 중앙 content end -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script src="${root}/assets/js/apart-pagination.js"></script>
	<script src="${root}/assets/js/apartment.js"></script>
	<script src="${root}/assets/js/news.js"></script>
	<script src="${root}/assets/js/citygugun.js"></script>
	<script src="${root}/assets/js/interesting.js"></script>
	<script>
		/* window.onload = function() {
			let date = new Date();
			let yearEl = document.querySelector("#year");
			let yearOpt = `<option value="">매매년도</option>`;
			let year = date.getFullYear();
			for (let i = year; i > year - 20; i--) {
				yearOpt += '<option value="'+ i + '">' + i + '년</option>';
			}
			yearEl.innerHTML = yearOpt;
			loadNews();
		}; */

		/* let date = new Date();
		document.querySelector("#year").addEventListener(
				"change",
				function() {
					let month = date.getMonth() + 1;
					let monthEl = document.querySelector("#month");
					let monthOpt = `<option value="">매매월</option>`;
					let yearSel = document.querySelector("#year");
					let m = yearSel[yearSel.selectedIndex].value == date
							.getFullYear() ? month : 13;
					for (let i = 1; i < m; i++) {
						if (i < 10)
							monthOpt += '<option value="0' + i +'">' + i
									+ `월</option>`;
						else
							monthOpt += '<option value="'  +  i + '">' + i
									+ `월</option>`;
					}
					monthEl.innerHTML = monthOpt;
				}); */
	</script>
	<script>
		// 주변 상권 정보를 DB에 비동기식(XHR)으로 넣는 작업
		window.onload = function() {
			registration();
			loadNews();
			sendRequest("sido", "*00000000");
			searchBtn(true, 2);
		};
		
		function registration() {
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "registration.jsp", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.send("registration");

			xhr.onreadystatechange = function() {
				if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
					xhr.responseText;
				}
			}
		}
	</script>
</body>
</html>
