package com.example.umc9th.domain.test.service.query;

import com.example.umc9th.domain.test.exception.TestException;
import com.example.umc9th.global.apiPayload.code.TestErrorCode;
import org.springframework.stereotype.Service;

@Service
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void checkFlag(Long flag){
        if(flag==1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
