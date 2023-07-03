<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 추가 페이지</title>

<!-- bootstrap CDN link -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<%-- AJAX 사용하려면 원본 필요 --%>
<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

</head>
<body>
	<div class="container">
		<h1>즐겨찾기 추가하기</h1>
		
		<div class="form-group">
			<label for="name">제목</label>
			<input type="text" id="name" class="form-control">
		</div>
		
		<div class="form-group">
			<label for="url">URL 주소</label>
			<div class="form-inline">
				<input type="text" id="url" class="form-control col-10">
				<button type="button" id="duplicationBtn" class="btn btn-info">중복확인</button>
			</div>
			<small id="duplicationText" class="text-danger d-none">중복된 url 입니다.</small>
			<small id="availableText" class="text-success d-none">저장 가능한 url 입니다.</small>
		</div>
		
		<button type="button" id="addBookmarkBtn" class="btn btn-success btn-block">추가</button>
	</div>
	
	<script>
		$(document).ready(function(){
			$("#addBookmarkBtn").on('click', function() {
				//alert("클릭");
				
				let name = $("#name").val().trim();
				let url = $('#url').val().trim();
				
				// validation
				if (name == "") {
					alert("제목을 입력하세요");
					return;
				}
				if (!url) {
					alert("주소를 입력하세요");
					return;
				}
				
				// 주소 형식이 잘못되었을 때 참
				// http 아니고 그리고 https 아닐 때
				if (url.startsWith('http://') == false && url.startsWith("https://") == false) {
					alert("주소 형식이 잘못 되었습니다.");
					return;
				}
				
				// 문제2) 중복확인 체크
				if ($('#availableText').hasClass('d-none')) {	// 잘못된 경우 availableText
					alert("중복된 url 입니다. 다시 확인해주세요");
					return;
				}
				
				// AJAX 통신 => 서버 요청
				$.ajax({
					// request
					type:"post"
					, url:"/lesson06/quiz01/add_bookmark"
					, data:{"name" : name, "url": url}
				
					// response
					, success: function(data) { //	String, JSON => 자바스크립트의 객체 변환
					/* 	alert(data.code);
						alert(data.result); */
						if (data.result == "성공") {
							location.href = "/lesson06/quiz01/after_add_user_view";		// GET Method
						}
					}
					, error: function(request, status, error) {
						alert("즐겨찾기 추가하는데 실패했습니다.");
					}
				});
			});
			
			// 문제2-1) 중복확인
			$('#duplicationBtn').on('click', function() {
				//alert("중복확인");
				let url = $('#url').val().trim();
				if (!url) {
					alert("검사할 url을 입력해주세요.");
					return;
				}
				
				// AJAX 통신 => DB URL 존재 여부 확인
				$.ajax({
					//request
					type:"post"
					, url:"/lesson06/quiz01/is_duplication_url"
					, data:{"url":url}
					
					//response
					,success:function(data) {
						// {"code":1, "isDuplication":true}
						if (data.isDuplication) {	// 중복
							$('#duplicationText').removeClass('d-none');
							$('#availableText').addClass('d-none');
						} else {	// 사용 가능(중복아님)
							$('#duplicationText').addClass('d-none');
							$('#availableText').removeClass('d-none');
						}
					}
				});
			});
		});
	</script>
	
</body>
</html>