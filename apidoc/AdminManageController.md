# 선관위 명단 관리 (/adminmanage)

## 공통된 요청 헤더
<pre><code>{
   Content-Type: application/json
   Authorization: JWT
}</code></pre>

***

## 선관위 목록 조회
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
            "id": "1",
            "name": "배다슬",
            "password": "$2a$10$IUTWXkLvCHf9bGNaQn8UPO32LCiE3PiExaBE6Nen.szlcN0mkQDrK",
            "departmentName": "소프",
            "type": "1"
        },
        {
            "id": "201132000",
            "name": "선관위원장",
            "password": "d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db",
            "departmentName": "인문융합자율학부",
            "type": "2"
        },
        {
            "id": "201232000",
            "name": "선관위1",
            "password": "0a6f9ebaa55e21ce270b6df2e7d812c987d511ab0472d24b501622b5878f9e4b03011356f3c9f85b084cf763a995a93f142d5107fa9a92d8e60e78d3c96a614a",
            "departmentName": "사회융합자율학부",
            "type": "1"
        },
        {
            "id": "201332000",
            "name": "선관위2",
            "password": "33275a8aa48ea918bd53a9181aa975f15ab0d0645398f5918a006d08675c1cb27d5c645dbd084eee56e675e25ba4019f2ecea37ca9e2995b49fcb12c096a032e",
            "departmentName": "미디어컨텐츠융합자율학부",
            "type": "1"
        }
    ],
    "msg": "선관위 목록입니다."
}</code></pre>

***

## 선관위 등록 (POST, ExcelUpload)
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

### 요청 헤더
<pre><code>@RequestParam</code></pre>

### 요청 바디
<pre><code>{
        "학번", "이름", "비밀번호", "소속", "타입" 가 등록되어있는 엑셀 파일
}</code></pre>

### 응답 바디
<pre><code>{
    "status": "SUCCESS",
    "data": [
        {
            "id": "201132000",
            "name": "선관위원장",
            "password": "d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db",
            "departmentName": "인문융합자율학부",
            "type": "2"
        },
        {
            "id": "201232000",
            "name": "선관위1",
            "password": "0a6f9ebaa55e21ce270b6df2e7d812c987d511ab0472d24b501622b5878f9e4b03011356f3c9f85b084cf763a995a93f142d5107fa9a92d8e60e78d3c96a614a",
            "departmentName": "사회융합자율학부",
            "type": "1"
        },
        {
            "id": "201332000",
            "name": "선관위2",
            "password": "33275a8aa48ea918bd53a9181aa975f15ab0d0645398f5918a006d08675c1cb27d5c645dbd084eee56e675e25ba4019f2ecea37ca9e2995b49fcb12c096a032e",
            "departmentName": "미디어컨텐츠융합자율학부",
            "type": "1"
        },
        {
            "id": "201432000",
            "name": "선관위3",
            "password": "a8cebf1698dc14282c507b1e1cfb7f2c9d5216aa7bd0854b50561e02c2b99d9a38945ec0f81e55f9699062b1eac6d0083411c839ba2b27c6a15b494463bc5c73",
            "departmentName": "IT융합자율학부",
            "type": "1"
        }
    ],
    "msg": "엑설이 업로드되었습니다."
}</code></pre>

***

## 선관위 삭제
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
        "id": "201432000",
        "name": "선관위3",
        "password": "a8cebf1698dc14282c507b1e1cfb7f2c9d5216aa7bd0854b50561e02c2b99d9a38945ec0f81e55f9699062b1eac6d0083411c839ba2b27c6a15b494463bc5c73",
        "departmentName": "IT융합자율학부",
        "type": "1"
    },
    "msg": "해당 선관위가 삭제되었습니다."
}</code></pre>
