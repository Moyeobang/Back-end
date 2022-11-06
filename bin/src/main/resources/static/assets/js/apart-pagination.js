function makePagination(totalCount, currentPage, queryParams, showMap, type){
	let target = document.querySelector("#pagination");
	
	const dataPerPage = 10;
	const pageCount = 5;
	
	const totalPage = Math.ceil(totalCount / dataPerPage);
	const pageGroup = Math.ceil(currentPage / pageCount);
	
	let last = pageGroup * pageCount;
	if(last > totalPage){
		last = totalPage;
	}
	let first = last - (pageCount - 1);
	
	if(first < 1) {
		first = 1;
	}
	
	const next = last + 1;
	const prev = first - 1;
	
	
	if( totalPage < 1){
		first = last;
	}
	
    while (target.hasChildNodes()) {
    	target.removeChild(target.firstChild);
    }
    
	if(currentPage > 5) {
		let li = document.createElement("li");
		li.setAttribute("class", "page-item");
		
		let a = document.createElement("a");
		a.setAttribute("class", "page-link");
		a.setAttribute("onclick", 'javascript:getApartList("/backend_exam/housedeal", "' + queryParams + '", "' + prev + '", ' + showMap +', ' + type + ')');
		a.appendChild(document.createTextNode("이전"));
		li.appendChild(a);
		target.appendChild(li);
		
	}
	for (var i = first; i <= last; i++) {
		let li = document.createElement("li");
		 
		if(i == currentPage){
			li.setAttribute("class", "page-item active");
		} else {
			li.setAttribute("class", "page-item");
		}
		let a = document.createElement("a");
		a.setAttribute("class", "page-link");
		a.setAttribute("onclick", 'javascript:getApartList("/backend_exam/housedeal", "' + queryParams +'", "' + i+'", ' + showMap +', ' + type + ')');
		a.appendChild(document.createTextNode(i));
		li.appendChild(a);
		target.appendChild(li);
	}
	if(next > 5 && next < totalPage){
		let li = document.createElement("li");
		li.setAttribute("class", "page-item");
		
		let a = document.createElement("a");
		a.setAttribute("class", "page-link");
		a.setAttribute("onclick", 'javascript:getApartList("/backend_exam/housedeal", "' + queryParams + '", "'+  next +'", ' + showMap +', ' + type + ')');
		a.appendChild(document.createTextNode("다음"));
		li.appendChild(a);
		target.appendChild(li);
	}
}