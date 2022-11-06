function addInterestedArea() {
	
	let sidoSel = document.querySelector("#sido-modal");
	let sidoCode = sidoSel[sidoSel.selectedIndex].value.substr(0, 2);
	let sidoName = sidoSel[sidoSel.selectedIndex].text;
	let gugunSel = document.querySelector("#gugun-modal");
	let gugunCode = gugunSel[gugunSel.selectedIndex].value.substr(2, 5);
	let gugunName = gugunSel[gugunSel.selectedIndex].text;
	let dongSel = document.querySelector("#dong-modal");
	let dongCode = dongSel[dongSel.selectedIndex].value.substr(7, 3);
	let dongName = dongSel[dongSel.selectedIndex].text;

	var interestedArea = {
			act : "insert",
		sidoCode : sidoCode,
		sidoName : sidoName,
		gugunCode : gugunCode,
		gugunName : gugunName,
		dongCode : dongCode,
		dongName : dongName,
	};
	
	var formData = 'act=insert';
	formData += "&sidoCode="+ sidoCode;
	formData += "&sidoName="+ sidoName;
	formData += "&gugunCode="+ gugunCode;
	formData += "&gugunName="+ gugunName;
	formData += "&dongCode="+ dongCode;
	formData += "&dongName="+ dongName;
	
	async("/backend_exam/interest", "POST", formData);
}

function showInterestedArea() {
	async(`/backend_exam/interest?act=select`, "GET");
}

function async(url, method, formData) {
	if(method == "GET"){
		fetch(url)
		 	.then((response) => response.json())
	    	.then((data) => makeList(data));
	} else {
		
		fetch(url, {
			method: 'POST',
		    cache: 'no-cache',
		    headers: {
		        'Content-Type': 'application/x-www-form-urlencoded'
		    },
		    body: formData // body 부분에 폼데이터 변수를 할당
		})
		.then((response) => response.json())
		.then((data) => {
			 makeList(data);
		});
		
	}
	
}

function foo(callback) {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/backend_exam/interest");
	xhr.onload = function(event){
	    alert("Success, server responded with: " + event.target.response); // raw response
	};
	
	var formData = new FormData(document.querySelector("#TEST"));
	xhr.send(JSON.stringify({act : "insert", sidoCode : 11 , sidoName : "서울특별시"}));
	
	
	xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) { // request is done
            if (xhr.status === 200) { // successfully
                callback(xhr.responseText); // we're calling our method
            }
        }
    };
}

function makeList(data) {
	let tbody = document.querySelector("#interestAreaList");
	let len = tbody.rows.length;
	for (let i = len - 1; i >= 0; i--) {
		tbody.deleteRow(i);
	}
	
	if(data.interests){
		data.interests.forEach((data) => {
			let tr = document.createElement("tr");
      
			let sido = document.createElement("td");
			sido.appendChild(document.createTextNode(data.sidoName));
			tr.appendChild(sido);
      
			let gugun = document.createElement("td");
			gugun.appendChild(document.createTextNode(data.gugunName));
			tr.appendChild(gugun);

			let dong = document.createElement("td");
			dong.appendChild(document.createTextNode(data.dongName));
			tr.appendChild(dong);

			console.log(data.user_id)
			console.log(typeof data.user_id);
			let main = document.createElement("td");
			let mainBtn = document.createElement("button");
//			mainBtn.setAttribute("onClick","javascript:mainInterestArea("+data.user_id+","+data.seq+")");
			mainBtn.addEventListener("click", function(){
				mainInterestArea(data.user_id, data.seq);
			});
			mainBtn.setAttribute("class","btn-secondary");
			mainBtn.appendChild(document.createTextNode("메인설정"));
			mainBtn.setAttribute("type", "button");
			main.appendChild(mainBtn);
			tr.appendChild(main);
			
			let deleteTd = document.createElement("td");
			let deletebtn = document.createElement("button");
			deletebtn.setAttribute("onclick","javascript:deleteInterestArea("+data.seq+")");
			deletebtn.setAttribute("class","btn-danger");
			deletebtn.appendChild(document.createTextNode("삭제"));
			deletebtn.setAttribute("type", "button");
			deleteTd.appendChild(deletebtn);
			tr.appendChild(deleteTd);
			
			tbody.appendChild(tr);
      
		});
	}
}

function deleteInterestArea(seq){
	async("/backend_exam/interest?act=delete&seq="+seq, "GET");
}

function mainInterestArea(user_id,seq){
	console.log(typeof user_id);
	alert('메인 지역이 변경되었습니다!');
	async("/backend_exam/interest?act=mainChange&user_id="+user_id+"&seq="+seq, "GET");
}
