//var mapContainer = document.getElementById("map"), // 지도를 표시할 div
//	mapOption = {
//		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
//		level: 10, // 지도의 확대 레벨
//    };
//
//var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
//
//// HTML5의 geolocation으로 사용할 수 있는지 확인합니다
//if (navigator.geolocation) {
//// GeoLocation을 이용해서 접속 위치를 얻어옵니다
//  	navigator.geolocation.getCurrentPosition(function (position) {
//  		var lat = position.coords.latitude, // 위도
//  		lon = position.coords.longitude; // 경도
//
//  		var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
//  		message = '<div style="padding:5px;"> 여기에 계신가요?! </div>'; // 인포윈도우에 표시될 내용입니다
//
//  		// 마커와 인포윈도우를 표시합니다
//  		displayMarker(locPosition, message);
//  	});
//} else {
//  	// HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
//
//  	var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
//  		message = "geolocation을 사용할수 없어요..";
//
//  	displayMarker(locPosition, message);
//}
//
//  // 지도에 마커와 인포윈도우를 표시하는 함수입니다
//function displayMarker(locPosition, message) {
//    // 마커를 생성합니다
//	var marker = new kakao.maps.Marker({
//		map: map,
//		position: locPosition,
//	});
//
//	var iwContent = message, // 인포윈도우에 표시할 내용
//	iwRemoveable = true;
//
//	// 인포윈도우를 생성합니다
//	var infowindow = new kakao.maps.InfoWindow({
//		content: iwContent,
//		removable: iwRemoveable,
//	});
//
//	// 인포윈도우를 마커위에 표시합니다
//	infowindow.open(map, marker);
//
//	// 지도 중심좌표를 접속위치로 변경합니다
//    map.setCenter(locPosition);
//}


// /////////////////////// 아파트 매매 정보 /////////////////////////
      

function searchBtn(showMap, type){
	
	document.querySelector("#aptInfoByDong").style.display = "inline"

	let url = "/housedeal/housedeal";
	
	let sidoSel = document.querySelector("#sido");
	let sidoCode = "";
	if(sidoSel[sidoSel.selectedIndex] != null){
		sidoCode = sidoSel[sidoSel.selectedIndex].value.substring(0,2);
	}
	
	let gugunSel = document.querySelector("#gugun");
	let gugunCode = "";
	if(gugunSel[gugunSel.selectedIndex] != null){
		gugunCode = gugunSel[gugunSel.selectedIndex].value.substring(0,5);
	}
	
	let dongSel = document.querySelector("#dong");
	let dongCode = ""
	if(dongSel[dongSel.selectedIndex] != null){
		dongCode = dongSel[dongSel.selectedIndex].value;
	}
	
// let yearSel = document.querySelector("#year");
// let year = yearSel[yearSel.selectedIndex].value;
// let monthSel = document.querySelector("#month");
// let month = monthSel[monthSel.selectedIndex].value;
// let dealYM = year + month;
	
	let aptName = document.querySelector("#aptName");
	
	let queryParams ="?";
	queryParams += encodeURIComponent("sidoCode") + "=" + encodeURIComponent(sidoCode);
	queryParams += "&" + encodeURIComponent("gugunCode") + "=" + encodeURIComponent(gugunCode);
	queryParams += "&" + encodeURIComponent("dongCode") + "=" + encodeURIComponent(dongCode);
	queryParams += "&" + encodeURIComponent("apartmentName") + "=" + encodeURIComponent(aptName.value); 
	
	getApartList(url, queryParams, 1, showMap, type);

}

function getApartList(url, queryParams, page, showMap, type){
	fetch(`${url}?${queryParams}`+"&pgno="+page)
    .then((response) => response.json())
    .then((data) => makeApartmentList(data, queryParams, showMap, type));
}

function makeApartmentList(datas, queryParams, showMap, type) {
   

	document.querySelector("table").setAttribute("style", "display: ;");
	let tbody = document.querySelector("#houseDealList");
	initTable("houseDealList");
	if(datas.size == 0) {
		let tr = document.createElement("tr");
		let td = document.createElement("td");
		td.setAttribute("colspan", "6");
		td.appendChild(document.createTextNode("조건에 맞는 데이터가 없습니다."));
		tr.appendChild(td);
		tbody.appendChild(tr);
	} else {
		makePagination(datas.size, datas.pgno, queryParams, showMap, type);
	}

	if(showMap) {
		if(datas.position){
			var mapContainer = document.getElementById("map"), // 지도를 표시할 div
			mapOption = {
				center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				level: 3, // 지도의 확대 레벨
			};

			var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
			datas.position.forEach((data) => {
				var coords = new kakao.maps.LatLng(data.lat, data.lng);
			
				// 결과값으로 받은 위치를 마커로 표시합니다
				var marker = new kakao.maps.Marker({
					map: map,
					position: coords,
				});
			
				var infowindow = new kakao.maps.InfoWindow({
					content: `<div style="width:250px;padding:10px 10px; color:black;">
						<div>아파트 이름 : ${data.apartmentName}</div>
						<div>연도 : ${data.buildYear}</div>
						<div>도로명 : ${data.roadName}</div>
						</div>`,
				});
			
				kakao.maps.event.addListener(marker, "mouseover", function () {
					// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
					infowindow.open(map, marker);
				});
			
				// 마커에 마우스아웃 이벤트를 등록합니다
				kakao.maps.event.addListener(marker, "mouseout", function () {
					// 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
					infowindow.close();
				});
	
				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				map.setCenter(coords);
			});
		}
  	}
	if(datas.datas){
		datas.datas.forEach((data) => {
			let tr = document.createElement("tr");
      
			let dongTd = document.createElement("td");
			dongTd.appendChild(document.createTextNode(data.sidoName + " " + data.gugunName + " " + data.dongName));
			tr.appendChild(dongTd);
      
			let nameTd = document.createElement("td");
			nameTd.appendChild(document.createTextNode(data.apartmentName));
			tr.appendChild(nameTd);

			let floorTd = document.createElement("td");
			floorTd.appendChild(document.createTextNode(data.floor + "층"));
			tr.appendChild(floorTd);

			let areaTd = document.createElement("td");
			areaTd.appendChild(document.createTextNode(data.area));
			tr.appendChild(areaTd);

			let dateTd = document.createElement("td");
			dateTd.appendChild(document.createTextNode(data.dealYear + "/" + data.dealMonth + "/" + data.dealDay));
			tr.appendChild(dateTd);
      
			let costTd = document.createElement("td");
			costTd.appendChild(document.createTextNode(data.dealAmount));
			tr.appendChild(costTd);
      
				tr.addEventListener("click", function () {
					if(type == 1){
						location.href = "/backend_exam/housedeal?act=view&no="+data.no;
					} else {
						moveMap(map, data.aptCode);
					}
				});
			tbody.appendChild(tr);
      
		});
	}
}

function moveMap(map, aptCode){
	let url = "/backend_exam/house?act=get&aptCode=" + aptCode;
	fetch(`${url}`)
    .then((response) => response.json())
    .then((data) => setCenter(map, data));
}

function initTable(listname) {
	let tbody = document.querySelector(`#${listname}`);
    let len = tbody.rows.length;
    for (let i = len - 1; i >= 0; i--) {
    	tbody.deleteRow(i);
    }
}

function setCenter(map, houseInfo) {            
    // 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(houseInfo.data.lat, houseInfo.data.lng);
    
    // 지도 중심을 이동 시킵니다
    map.panTo(moveLatLon); 
}
 