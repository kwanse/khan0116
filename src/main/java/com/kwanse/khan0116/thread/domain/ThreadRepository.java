package com.kwanse.khan0116.thread.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThreadRepository extends JpaRepository<Thread, Long> {

    List<Thread> findAllByMemberId(Long memberId);
}
