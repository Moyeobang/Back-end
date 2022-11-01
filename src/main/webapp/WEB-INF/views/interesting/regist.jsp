<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal" id="interestingEnrollModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">관심지역 등록</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<form action="${root}/interest?act=insert" method="POST" id="TEST">
				<input type="hidden" name="act" value="insert" /> <input
					type="hidden" name="userid" value="${userinfo.userId}" /> <input
					type="hidden" id="sidoName" name="sidoName" value=""> <input
					type="hidden" id="sidoCode" name="sidoCode" value=""> <input
					type="hidden" id="gugunCode" name="gugunCode" value=""> <input
					type="hidden" id="gugunName" name="gugunName" value=""> <input
					type="hidden" id="dongCode" name="dongCode" value=""> <input
					type="hidden" id="dongName" name="dongName" value="">

				<!-- Modal body -->
				<div class="modal-body">
					<input type="hidden" name="act" value="insert" /> <input
						type="hidden" name="userid" value="${userinfo.userId}" />
					<div class="content-search">
						<div class="container">
							<div class="row col-md-12 justify-content-center mb-2">
								<div class="form-group col-md-4">
									<select class="form-select bg-secondary text-light"
										id="sido-modal">
										<option value="">시도선택</option>
									</select>
								</div>
								<div class="form-group col-md-4">
									<select class="form-select bg-secondary text-light"
										id="gugun-modal">
										<option value="">구군선택</option>
									</select>
								</div>
								<div class="form-group col-md-4">
									<select class="form-select bg-secondary text-light"
										id="dong-modal">
										<option value="">동선택</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="addInterestedArea()">등록</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">닫기</button>
				</div>
				<hr />
				<div class="container p-3">
					<table class="table table-striped text-center">
						<thead>
							<h2>관심 지역 목록</h2>
							<tr>
								<th scope="col">시도</th>
								<th scope="col">구군</th>
								<th scope="col">동</th>
								<th>메인</th>
								<th scope="col">삭제</th>
							</tr>
						</thead>
						<tbody id="interestAreaList">

						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>