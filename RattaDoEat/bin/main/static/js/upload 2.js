var fileList = []; // FileList를 담을 array
	
// 미리보기 생성
uploadFile.onchange = function(e) {
	var files = e.target.files; // input type=file을 통한 FileList 객체
	var file; // files 요소
	var idx; // file index
	var div; // div 태그
	var img; // img 태그
	var hidden; // hidden 태그
	var oriName; // 파일 original명
	var del; // button 태그(삭제버튼)
	var delText; // 삭제 텍스트
		
	for(i=0; i<files.length; i++) {
		file = files[i];
		fileList.push(file); // formData에 저장할 fileList 요소 삽입
		idx = fileList.indexOf(file);
		oriName = files[0].name;
		console.log(files[0].name);
		console.log(fileList);
		
		// 썸네일 영역 생성
		div = document.createElement("div");
		div.id = "thumbImgBox" + idx;
		document.querySelector("#thumbnail").appendChild(div);
		
		// 이미지
		img = document.createElement("img");
		img.id = "thumbImg";
		div.appendChild(img);
		
		// 히든 값(fileList에 담은 객체 삭제를 위한 것)
		hidden = document.createElement("input");
		hidden.type = "hidden";
		hidden.value = oriName;
		
		// 삭제 버튼
		del = document.createElement("button");
		delText = document.createTextNode("삭제");
		del.id = "delBtn";
		del.onclick=()=>delImg(idx, oriName);
		del.appendChild(delText);
		div.appendChild(del);
		
		if(window.FileReader) {
			// https://developer.mozilla.org/ko/docs/Web/API/File/Using_files_from_web_applications
            var reader = new FileReader();
            reader.onload = (function(aImg) { return function(e) { aImg.src = e.target.result; }; })(img);
    		reader.readAsDataURL(file);
		}
	}
}

// 썸네일 삭제
function delImg(idx, oriName){
	var thumbImgBox = document.getElementById("thumbImgBox" + idx);
	thumbImgBox.remove(); // 썸네일 이미지 삭제
	var fidx = fileList.findIndex(function(item){return item.name === oriName}); // oriName과 name이 동일한 fileList 요소의 인덱스 찾기
	fileList.splice(fidx, 1); // array.splice(start, deleteCount)
	console.log(fileList);
}

// 키워드
var keywordList = [];
function pressSpace(){
	if(event.keyCode == 32){ // 스페이스바 이벤트
		var div; // div 태그
		var span; // span 태그
		var del; // img 태그(삭제버튼)
		var keyword = document.getElementById("keyword").value; // 키워드
		var idx; // keyword index
		
		keywordList.push(keyword); // formData에 저장할 keywordList 요소 삽입
		idx = keywordList.indexOf(keyword);
		console.log(keywordList);
		console.log(idx);
		
		// 키워드 박스
		div = document.createElement("div");
		div.id = "keywordBox" + idx;
		div.onclick=()=>delKeyword(idx, keyword);
		document.querySelector("#keywordList").appendChild(div);
		
		// 입력 키워드		
		span = document.createElement("span");
		span.innerHTML = keyword;
		div.appendChild(span);
		
		// 삭제 이미지
		del = document.createElement("img");
		div.appendChild(del);
		
		// input값 초기화
		document.getElementById("keyword").value = null;
	}
}

// 키워드 삭제
function delKeyword(idx, keyword){
	var keywordBox = document.getElementById("keywordBox" + idx);
	keywordBox.remove();
	keywordList.splice(keywordList.indexOf(keyword), 1); // 해당 키워드의 인덱스 값을 찾아서 삭제
	console.log(keywordList);
}

// 파일 전송
function upload() {
	var formData = new FormData(); // 보낼 데이터를 담을 FormData 생성

	// formData에 fileList 배열 요소 저장
	for(i=0; i<fileList.length; i++) {
		formData.append("images[]", fileList[i]);
	}
	
	// formData에 type1값 저장
	formData.append("type1", document.getElementById("type1").value);
	// formData에 type2값 저장
	formData.append("type2", document.getElementById("type2").value);
	// formData에 content값 저장
	formData.append("content", document.getElementById("content").value);
	
	// formData에 keywordList값 저장
	formData.append("keywordList", keywordList);
	
	// formData에 저장된 요소 확인
	for(var key of formData.keys()) {
	  console.log(key);
	}
	for(var value of formData.values()) {
	  console.log(value);
	}
 
	// Ajax를 통해 formData값 전달
	$.ajax({
		url:"uploadP",
		type: "POST",
		data: formData,
		processData : false, // true=get방식, false=post방식
		contentType : false, // true=application/x-www-form-urlencoded, false=multipart/form-data
		cache: false,
		success: function(data) {
			if(data == "success"){
				alert("등록이 완료되었습니다.")
				document.location.href="/RattaDoEat/gallery";
			}
		},
		error: function () {
			alert("failed!")
		}
	});
	
}