function loadNews() {
	let url = "/backend_exam/news";
	console.log(`${url}/news`);
	fetch(`${url}`)
	    .then((response) => response.json())
	    .then((data) => makeNewsmentList(data));
}

function makeNewsmentList(datas) {
	
	let news = document.querySelector("#news");
	initNews();
	
	datas.forEach((data) => {
	      let li = document.createElement("li");
	
	      let a = document.createElement("a");
	      a.setAttribute("href", data.url)
	      a.setAttribute("class", "list-group-item");
	      a.setAttribute("target","_blank");
	      a.appendChild(document.createTextNode(data.title));
	      li.appendChild(a);
	
	      news.appendChild(li);
	});
	
  }

function initNews() {
    let news = document.querySelector("#news");
    news.html = "";
  }