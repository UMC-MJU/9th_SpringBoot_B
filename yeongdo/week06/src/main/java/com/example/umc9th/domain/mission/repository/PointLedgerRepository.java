package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.PointLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointLedgerRepository extends JpaRepository<PointLedger,Long> {
    // null이 아닌 첫번쨰 값 반환 (아직 미션 한번도 안 한 회원은 sum 결과가 null이어서 0으로 반환되도록)
    @Query("select coalesce( sum( pl.delta), 0) " +
            "from PointLedger pl " +
            "where pl.member.id = :memberId")
    long sumDeltaByMemberId(@Param("memberId") Long memberId);
}
