# 후보자 관리 (/candidate)

## 공통된 요청 헤더
<pre><code>{
   Content-Type: application/json
   Authorization: JWT
}</code></pre>

***

## 후보자 전체 목록 조회
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
            "candidateId": 1,
            "campName": "선본1",
            "leaderName": "회장1",
            "leaderDeptName": "소프",
            "subLeaderName": "부회장1",
            "subLeaderDeptName": "소프",
            "photo": "*.jpg"
        }
    ],
    "msg": "후보자 목록입니다."
}</code></pre>

***

## 선거별 후보자 목록 조회
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>GET</td>
<td>/list/{voteId}</td>
</tr>
</table>

### 응답 바디
<pre><code>{
    "status": "SUCCESS",
    "data": [
        {
            "candidateId": 46,
            "campName": "기권",
            "leaderName": null,
            "leaderDeptName": null,
            "subLeaderName": null,
            "subLeaderDeptName": null,
            "photo": null
        },
        {
            "candidateId": 47,
            "campName": "test",
            "leaderName": "leader",
            "leaderDeptName": "leaderDep",
            "subLeaderName": "subLeader",
            "subLeaderDeptName": "subLeaderDep",
            "photo": "photo"
        },
        {
            "candidateId": 48,
            "campName": "반대",
            "leaderName": null,
            "leaderDeptName": null,
            "subLeaderName": null,
            "subLeaderDeptName": null,
            "photo": null
        }
    ],
    "msg": "해당 선거별 후보자 목록입니다."
}</code></pre>

***

## 후보자 등록 (POST)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>POST</td>
<td>/{voteId}</td>
</tr>
</table>

### 요청 바디
<pre><code>{
	"campName" : "test",
	"leaderName" : "leader",
	"leaderDepName" : "leaderDep",
	"subLeaderName" : "subLeader",
	"subLeaderDepName" : "subLeaderDep",
}</code></pre>

### 응답 바디
<pre><code>{
    "status": "SUCCESS",
    "data": [
        {
            "candidateId": 29,
            "campName": "test",
            "leaderName": "leader",
            "leaderDeptName": "leaderDep",
            "subLeaderName": "subLeader",
            "subLeaderDeptName": "subLeaderDep",
            "photo": "photo"
        },
        {
            "candidateId": 30,
            "campName": "기권",
            "leaderName": null,
            "leaderDeptName": null,
            "subLeaderName": null,
            "subLeaderDeptName": null,
            "photo": null
        }
    ],
    "msg": "후보자를 등록했습니다."
}</code></pre>

***

## 후보자 삭제
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>DELETE</td>
<td>/{voteId}/{candidateId}</td>
</tr>
</table>

### 응답 바디
<pre><code>{
    "status": "SUCCESS",
    "data": {
        "candidateId": 13,
        "campName": "test",
        "leaderName": "leader",
        "leaderDeptName": "leaderDep",
        "subLeaderName": "subLeader",
        "subLeaderDeptName": "subLeaderDep",
        "photo": "photo"
    },
    "msg": "해당 유권자가 삭제되었습니다."
}</code></pre>