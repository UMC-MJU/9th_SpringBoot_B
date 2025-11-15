package com.example.myapp.domain.temp.service;

import com.example.myapp.domain.temp.code.TestErrorCode;
import com.example.myapp.domain.temp.exception.TestException;
import org.springframework.stereotype.Service;

@Service
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void checkFlag(Integer flag) {
        if (flag == 1) {
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
