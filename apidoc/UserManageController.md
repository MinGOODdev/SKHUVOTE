# 유권자 명단 관리 (/usermanage)

## 공통된 요청 헤더 (ExcelUpload 제외)
<pre><code>{
   Content-Type: application/json
   Authorization: JWT
}</code></pre>

***

## 유권자 목록 조회
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>GET</td>
<td>/list</td>
</tr>
</table>

### 응답 바디
<pre><code>{
    "status": "SUCCESS",
    "data": [
        {
            "id": "201132000",
            "name": "조민국",
            "userType": 1,
            "tel": "01012345678",
            "confirmCheck": 1,
            "department": {
                "departmentId": 42,
                "departmentName": "IT융합자율학부 소프트웨어공학전공"
            }
        }
    ],
    "msg": "유권자 목록입니다."
}</code></pre>

***

## 유권자 등록 (POST, ExcelUpload)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>POST</td>
<td>/upload</td>
</tr>
</table>

### 요청 바디
<pre><code>{
        "학번", "이름", "소속", "타입", "전화번호" 가 등록되어있는 엑셀 파일
}</code></pre>

### 응답 바디
<pre><code>{
    "status": "SUCCESS",
    "data": [
        {
            "id": "201132000",
            "name": "조조조",
            "userType": "1",
            "tel": "01033333333",
            "confirmCheck": 0,
            "department": {
                "departmentId": 10,
                "departmentName": "인문융합자율학부"
            }
        },
        {
            "id": "201232000",
            "name": "민민민",
            "userType": "1",
            "tel": "01044444444",
            "confirmCheck": 0,
            "department": {
                "departmentId": 20,
                "departmentName": "사회융합자율학부"
            }
        },
        {
            "id": "201332000",
            "name": "국국국",
            "userType": "1",
            "tel": "01055555555",
            "confirmCheck": 0,
            "department": {
                "departmentId": 30,
                "departmentName": "미디어컨텐츠융합자율학부"
            }
        },
        {
            "id": "201432000",
            "name": "하하하",
            "userType": "0",
            "tel": "01099999999",
            "confirmCheck": 0,
            "department": {
                "departmentId": 40,
                "departmentName": "IT융합자율학부"
            }
        }
    ],
    "msg": "엑설이 업로드되었습니다."
}</code></pre>

***

## 유권자 삭제
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>DELETE</td>
<td>/{id}</td>
</tr>
</table>

### 응답 바디
<pre><code>{
    "status": "SUCCESS",
    "data": {
        "id": "201232000",
        "name": "가나다",
        "userType": 1,
        "tel": "01033333333",
        "confirmCheck": 1,
        "department": {
            "departmentId": 42,
            "departmentName": "IT융합자율학부 소프트웨어공학전공"
        }
    },
    "msg": "해당 유권자가 삭제되었습니다."
}</code></pre>