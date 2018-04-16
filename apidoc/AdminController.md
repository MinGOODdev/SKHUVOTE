# 관리자 로그인 (/admin)

***

## 로그인
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>POST</td>
<td>/signin</td>
</tr>
</table>

### 요청 헤더
<pre><code>Content-Type: application/json</code></pre>

### 요청 바디
<pre><code>{
	"id" : "201132000",
	"password" : "1234"
}</code></pre>

### 응답 바디
* 로그인 성공
<pre><code>{
    "status": "SUCCESS",
    "data": "JWT",
    "msg": "선관위원장 로그인 성공."
}</code></pre>

* 로그인 실패
<pre><code>{
    "status": "FAIL",
    "data": null,
    "msg": "올바른 정보를 입력하세요."
}</code></pre>

* 중복 로그인 시도
<pre><code>{
    "status": "FAIL",
    "data": null,
    "msg": "이미 접속중."
}</code></pre>

***

## 로그아웃
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>GET</td>
<td>/signout</td>
</tr>
</table>

### 요청 헤더
<pre><code>{
   Content-Type: application/json
   Authorization: JWT
}</code></pre>

### 응답 바디
* 로그아웃 성공
<pre><code>{
    "status": "SUCCESS",
    "data": {
        "id": "201132000",
        "name": "선관위원장",
        "password": "암호화된 비밀번호",
        "departmentName": "인문융합자율학부",
        "type": "2",
        "lastLogin": 1518219114000
    },
    "msg": "로그아웃 성공."
}</code></pre>

