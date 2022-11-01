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

<title>대기 정보</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />
<link rel="stylesheet" href="${root}/assets/css/main.css" />

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a152d2aabef07da25afb167fc3a1a32c&libraries=services"></script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<p style="margin-top: -12px"></p>
	
	<div id="map" style="width: 100%; height: 350px;"></div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a152d2aabef07da25afb167fc3a1a32c&libraries=services"></script>
	
	<script>
	
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 5
		// 지도의 확대 레벨
		};

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		var infowindow = new kakao.maps.InfoWindow({
			zIndex : 1
		});
		<c:forEach var="amp" items="${amps}">
		// 주소로 좌표를 검색합니다
		
		
			geocoder
			.addressSearch(
					"${amp.wrkp_naddr}",
					function(result, status) {
						var com = "${amp.wrkp_naddr}";
							// 정상적으로 검색이 완료됐으면 
							if (status === kakao.maps.services.Status.OK) {

								var coords = new kakao.maps.LatLng(
										result[0].y, result[0].x);

								// 결과값으로 받은 위치를 마커로 표시합니다
								var marker = new kakao.maps.Marker({
									map : map,
									position : coords
								});

								kakao.maps.event
										.addListener(
												marker,
												'click',
												function() {
													// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
													infowindow
															.setContent('<div style="padding:5px;font-size:12px;">"${amp.wrkp_nm}"</div>');
													infowindow.open(map,
															marker);
												});

								// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
								map.setCenter(coords);
							}
						
					});
		
		</c:forEach>
	</script>
	<div>

		
		<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">연번</th>
      <th scope="col">지도점검구분</th>
      <th scope="col">업종명</th>
      <th scope="col">점검사항</th>
      <th scope="col">업체(시설)명</th>
      <th scope="col">업종명</th>
      <th scope="col">도로명 주소</th>
      <th scope="col">지번 주소</th>
    </tr>
  </thead>
  
  <tbody>
  
		<c:forEach var="amp" items="${amps}">
    <tr>
      <td>${amp.idx}</td>
      <td>${amp.drt_insp_se_nm}</td>
      <td>${amp.upch_cob_nm}</td>
      <td>${amp.drt_insp_item}</td>
      <td>${amp.upch_cob_nm}</td>
      <td>${amp.wrkp_nm}</td>
      <td>${amp.wrkp_naddr}</td>
      <td>${amp.wrkp_addr}</td>
    </tr>
    
			</c:forEach>
  </tbody>
  
</table>
	</div>
</body>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
			crossorigin="anonymous">
	    </script>
</html>