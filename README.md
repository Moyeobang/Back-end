<h1>Spring프로젝트</h1>
<b>광주 4반 홍예진, 정유진</b>
<h2>1) 기본(필수) 기능</h1>
<h4>1-1) 메인 페이지(실거래 정보를 활용하여 구성) (Spring & MyBatis 기반)</h4>
![1-1](/uploads/d50ea74b0cdafede18771b91a9ffbc62/1-1.png)
<h4>1-2) 동별 실거래가 검색 결과 페이지 (Spring & MyBatis 기반)</h4>
![1-2_매매_정보_동별_조회](/uploads/9c85de251fba46bbf1b1763a7c2508cd/1-2_매매_정보_동별_조회.PNG)
<h4>1-3) 아파트별 실거래가 검색 결과 페이지 (Spring & MyBatis 기반)</h4>
![1-3_매매_정보_아파트명별_조회](/uploads/a21f76ee771b3f33f433b1a532a09e2f/1-3_매매_정보_아파트명별_조회.PNG)
<h4>1-4) 회원정보 등록, 수정, 삭제, 검색 페이지 (Spring & MyBatis 기반)</h4>
- 회원 정보 등록   
![1-4-1_회원정보등록_회원가입_](/uploads/4df705282e2dc594449590aa5cc429c3/1-4-1_회원정보등록_회원가입_.PNG)
- 회원 정보 수정
![1-4-2_회원정보수정](/uploads/8a84a5bf5c14242e0aa772b0a73b80a3/1-4-2_회원정보수정.PNG)   
- 회원 정보 삭제
![1-4-3_회원_정보_삭제](/uploads/082f0a2aaf432399327a1c1136e86ad3/1-4-3_회원_정보_삭제.PNG)   
- 회원 정보 검색
![1-4-4_회원_정보_단일_조회](/uploads/117f48de58b00e573b4164202cfd2a02/1-4-4_회원_정보_단일_조회.PNG)
- 아이디 중복 검사
![1-4-5_아이디_중복_검사](/uploads/4bc5f7b80d433742d960d9bcd0d33cf0/1-4-5_아이디_중복_검사.PNG)

<h4>1-5) 로그인/ 로그아웃 페이지 (Spring & MyBatis 기반)</h4>
- 로그인
![1-5-1_로그인](/uploads/cad89c2523211c75d8b0eb75e89a94c3/1-5-1_로그인.PNG)
- 로그아웃
![1-5-2_로그아웃](/uploads/f498625d802bab3e17b85e4303c9e424/1-5-2_로그아웃.PNG)

<h2>2) 추가 기능</h1>
<h4>2-0-1) 관심 지역 조회</h4>
![image](/uploads/39db8bccb208997856ab09ea79f51884/image.png)
<h4>2-0-2) 관심 지역 등록</h4>
![image](/uploads/f9896a6bc971e18b83bb15a67aa4c487/image.png)
![image](/uploads/754089e0b50ec4d265c0ac11e42ea60a/image.png)
<h4>2-0-3) 관심 지역 삭제</h4>
![image](/uploads/634282f3bbbe291515200ff831811831/image.png)
![image](/uploads/edc33aa00ce0826910f19c375e70dbcb/image.png)

<h2>3) 심화 기능</h1>
<h4>3-1) 공지사항 등록</h4>
![3-1](/uploads/7dd3b791add76059f87fbfeb03049b1c/3-1.png)

<b>board.xml</b>

```
<insert id="writeArticle" parameterType="boardDto">
		INSERT INTO
		BOARD (USER_ID, SUBJECT, CONTENT, HIT, REGISTER_TIME)
		VALUES (#{userId}, #{subject}, #{content}, 0, now())
</insert>
```
<br>
<b>boardController</b>

```
	@ResponseBody
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody BoardDto dto) {
		try {
			boardService.writeArticle(dto);

			Map<String, Object> m = new HashMap<String, Object>();
			List<BoardDto> list = boardService.listArticle();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
```

<h4>3-2) 공지사항 수정</h4>
![3-2](/uploads/ade1a738effc7e5f3b39eaf4de089cff/3-2.png)

<b>board.xml</b>
```
<update id="modifyArticle" parameterType="boardDto">
		update board
		set subject = #{subject}, content = #{content}
		where article_no = #{articleNo}
</update>
```
<br>
<b>boardController</b>

```
	@ResponseBody
	@PutMapping("/modify/{articleNo}")
	public ResponseEntity<?> modify(@RequestBody BoardDto dto, @PathVariable int articleNo) {
		try {
			dto.setArticleNo(articleNo);
			boardService.modifyArticle(dto);
			return new ResponseEntity<BoardDto>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
```

<h4>3-3) 공지사항 삭제</h4>
![3-3](/uploads/15d38d00855d9dfad0a813404bc19d4c/3-3.png)

<b>board.xml</b>
```
<delete id="deleteArticle" parameterType="int">
		delete from board
		where article_no = #{articleNo}
</delete>
```
<br>
<b>boardController</b>

```
	@ResponseBody
	@DeleteMapping("/delete/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable int articleNo) {
		try {
			boardService.deleteArticle(articleNo);

			Map<String, Object> m = new HashMap<String, Object>();
			List<BoardDto> list = boardService.listArticle();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
```

<h4>3-4) 공지사항 검색</h4>
![3-4](/uploads/2f32ccac68644c957e520050460eb7af/3-4.png)

<b>board.xml</b>
```
<select id="searchArticle" parameterType="Map" resultMap="board">
		SELECT ARTICLE_NO, USER_ID, SUBJECT, CONTENT, HIT, REGISTER_TIME
		FROM BOARD
		<where>
			<choose>
				<when test="word!=''">
					<choose>
						<when test="key=='all'">
							SUBJECT LIKE CONCAT('%', #{word}, '%')
							OR USER_ID = #{word}
						</when>
						<when test="key=='subject'">
							SUBJECT LIKE CONCAT('%', #{word}, '%')
						</when>
						<otherwise>
							USER_ID = #{word}
						</otherwise>
					</choose>
				</when>
			</choose>
		</where>
</select>
```

<br>
<b>boardController</b>

```
	@ResponseBody
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestBody Map<String, String> m) {
		try {
			List<BoardDto> list = boardService.searchArticle(m);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}

	}
```
<h4>3-1) 공지사항 리스트 조회</h4>
![1-2](/uploads/9d51fe901c4a0c4703e127f05cb88ef3/1-2.png)
