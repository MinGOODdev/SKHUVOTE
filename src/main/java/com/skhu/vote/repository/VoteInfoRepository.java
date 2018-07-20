package com.skhu.vote.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skhu.vote.domain.VOTEINFO;

public interface VoteInfoRepository extends JpaRepository<VOTEINFO, Integer> {

  List<VOTEINFO> findByVoteId(int voteId);

  // @Query에 정의된 JPQL 명령이 UPDATE 또는 DELETE라면 @Modifying 어노테이션도 반드시 붙어있어야 한다.
  @Modifying
  @Query("UPDATE VOTEINFO SET voteName=:voteName, startTime=:startTime, endTime=:endTime, target=:target WHERE voteId=:voteId")
  void update(@Param("voteId") int voteId, @Param("voteName") String voteName, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("target") int target);

}
