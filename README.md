<h1>1) 기본(필수) 기능</h1>
<h2>1-1) 메인 페이지(실거래 정보를 활용하여 구성) (Spring & MyBatis 기반)</h2>
![1-1](/uploads/d50ea74b0cdafede18771b91a9ffbc62/1-1.png)
<h2>1-2) 동별 실거래가 검색 결과 페이지 (Spring & MyBatis 기반)</h2>
![1-2](/uploads/9d51fe901c4a0c4703e127f05cb88ef3/1-2.png)
<h2>1-3) 아파트별 실거래가 검색 결과 페이지 (Spring & MyBatis 기반)</h2>
<h2>1-4) 회원정보 등록, 수정, 삭제, 검색 페이지 (Spring & MyBatis 기반)</h2>
<h2>1-5) 로그인/ 로그아웃 페이지 (Spring & MyBatis 기반)</h2>
<h1>2) 추가 기능</h1>
<h1>3) 심화 기능</h1>
<h2>3-1) 공지사항 등록</h2>
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

<h2>3-2) 공지사항 수정</h2>
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

<h2>3-3) 공지사항 삭제</h2>
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

<h2>3-4) 공지사항 검색</h2>
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


