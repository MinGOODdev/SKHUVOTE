# 선거 관리 (/election)

## 공통된 요청 헤더
<pre><code>{
   Content-Type: application/json
   Authorization: JWT
}</code></pre>

***

## 선거 전체 목록 조회
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
            "voteId": 1,
            "voteName": "총학생회선거",
            "allCount": 100,
            "voteCount": 50,
            "startTime": 1514764800000,
            "endTime": 1517389200000,
            "target": 1,
            "candidates": [
                {
                    "candidateId": 1,
                    "campName": "선본1",
                    "leaderName": "회장1",
                    "leaderDeptName": "소프",
                    "subLeaderName": "부회장1",
                    "subLeaderDeptName": "소프",
                    "photo": "*.jpg"
                },
                {
                    "candidateId": 2,
                    "campName": "선본2",
                    "leaderName": "회장2",
                    "leaderDeptName": "정통",
                    "subLeaderName": "부회장2",
                    "subLeaderDeptName": "정통",
                    "photo": "*.jpg"
                }
            ]
        },
        {
            "voteId": 2,
            "voteName": "IT학부선거",
            "allCount": 100,
            "voteCount": 70,
            "startTime": 1514764800000,
            "endTime": 1517389200000,
            "target": 40,
            "candidates": [
                {
                    "candidateId": 3,
                    "campName": "선본3",
                    "leaderName": "IT학부회장1",
                    "leaderDeptName": "소프",
                    "subLeaderName": "IT학부부회장1",
                    "subLeaderDeptName": "소프",
                    "photo": "*.jpg"
                },
                {
                    "candidateId": 4,
                    "campName": "선본4",
                    "leaderName": "IT학부회장2",
                    "leaderDeptName": "글티",
                    "subLeaderName": "IT학부부회장1",
                    "subLeaderDeptName": "글티",
                    "photo": "*.jpg"
                }
            ]
        },
        {
            "voteId": 3,
            "voteName": "소프트웨어공학전공선거",
            "allCount": 100,
            "voteCount": 88,
            "startTime": 1514764800000,
            "endTime": 1517389200000,
            "target": 42,
            "candidates": [
                {
                    "candidateId": 5,
                    "campName": "선본5",
                    "leaderName": "소프대표1",
                    "leaderDeptName": "소프",
                    "subLeaderName": "소프부대표1",
                    "subLeaderDeptName": "소프",
                    "photo": "*.jpg"
                },
                {
                    "candidateId": 6,
                    "campName": "선본6",
                    "leaderName": "소프대표2",
                    "leaderDeptName": "소프",
                    "subLeaderName": "소프부대표2",
                    "subLeaderDeptName": "소프",
                    "photo": "*.jpg"
                }
            ]
        },
        {
            "voteId": 15,
            "voteName": "testVote",
            "allCount": 0,
            "voteCount": 0,
            "startTime": 1517788800000,
            "endTime": 1518048000000,
            "target": 30,
            "candidates": [
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
            ]
        }
    ],
    "msg": "선거 목록입니다."
}</code></pre>

***

## 선거별 목록 조회
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
            "voteId": 15,
            "voteName": "testVote",
            "allCount": 0,
            "voteCount": 0,
            "startTime": 1517788800000,
            "endTime": 1518048000000,
            "target": 30,
            "candidates": [
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
            ]
        }
    ],
    "msg": "선거별 정보입니다."
}</code></pre>

***

## 선거 등록 (POST)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>POST</td>
<td></td>
</tr>
</table>

### 요청 바디
<pre><code>{
	"voteName" : "테스트",
	"startTime" : "2018-02-04T18:00:00",
	"endTime" : "2018-02-07T19:00:00",
	"target" : 1
}</code></pre>

### 응답 바디
<pre><code>{
    "status": "SUCCESS",
    "data": {
        "voteName": "테스트",
        "startTime": 1517767200000,
        "endTime": 1518030000000,
        "target": 1
    },
    "msg": "선거를 등록했습니다."
}</code></pre>

***

## 선거 수정 (PUT)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>PUT</td>
<td>/{voteId}</td>
</tr>
</table>

### 요청 바디
<pre><code>{
	    "voteId" : 1,
            "voteName": "총학생회선거하",
            "startTime" : "2018-02-07T18:00:00",
            "endTime" : "2018-02-09T19:00:00",
            "target": 1
}</code></pre>

### 응답 바디
* 수정 성공
<pre><code>{
    "status": "SUCCESS",
    "data": {
        "voteId": 1,
        "voteName": "총학생회선거하",
        "startTime": 1518026400000,
        "endTime": 1518202800000,
        "target": 1
    },
    "msg": "선거 수정 성공."
}</code></pre>

* 수정 실패
<pre><code>{
    "status": "FAIL",
    "data": null,
    "msg": "해당 선거 정보가 존재하지 않습니다."
}</code></pre>

***

## 선거 삭제 (DELETE)
<table>
<tr>
<td>메소드</td>
<td>경로</td>
</tr>
<tr>
<td>DELETE</td>
<td>/{voteId}</td>
</tr>
</table>

### 응답 바디
<pre><code>DB CASCADE 필요</code></pre>