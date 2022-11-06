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
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a152d2aabef07da25afb167fc3a1a32c&libraries=services">
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12"
			style="margin-top: 40px; margin-bottom: 40px;">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				<mark class="sky">실매매가 관리</mark>
			</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<div class="row align-self-center mb-2">
				<div class="content-search">
					<div class="container">
						<div class="row align-self-center mb-2">
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
							<div class="form-group col-md-4 pe-1 ps-1" id="aptNameDiv">
								<input class="form-control" id="aptName" type="text"
									placeholder="아파트명 입력" />
							</div>
							<div class="col-md-1 pe-1 ps-1">
								<button type="button" class="btn btn-outline-secondary"
									onclick="javascript:searchBtn(false, 1)">검색</button>
							</div>
							<c:if test="${!empty userinfo}">
								<c:if test="${userinfo.userClass eq '관리자'}">
									<div class="col-md-1 pe-1 ps-1">
										<button type="button" id="btn-mv-register"
											class="btn btn-outline-primary">등록</button>
									</div>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="container" style="display: none" id="aptInfoByDong">
				<div class="row">
					<div class="col-sm-12">
						<table class="table table-hover text-center">
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
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous">
	</script>
	<script src="${root}/assets/js/apart-pagination.js"></script>
	<script src="${root}/assets/js/apartment.js"></script>
	<script src="${root}/assets/js/citygugun.js"></script>
	<script src="${root}/assets/js/interesting.js"></script>
	<script>
		window.onload = function() {
			sendRequest("sido", "*00000000");
			//searchBtn(false);
		};
	</script>
</body>
</html>

