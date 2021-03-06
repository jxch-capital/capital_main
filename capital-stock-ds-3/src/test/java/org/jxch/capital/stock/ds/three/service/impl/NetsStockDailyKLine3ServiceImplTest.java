package org.jxch.capital.stock.ds.three.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.jxch.capital.stock.ds.entity.StockType;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class NetsStockDailyKLine3ServiceImplTest {
    @Autowired
    @Qualifier("netsStockDailyKLine3Service")
    private NetsStockDailyKLine3ServiceImpl netsStockDailyKLine3Service;

    @Test
    void searchSingleton() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        SearchDailyKLineDTO dto = SearchDailyKLineDTO.builder()
                .start(format.parse("20200101"))
                .end(format.parse("20220101"))
                .code("601318")
                .stockType(StockType.SH)
                .build();

        List<KLineVO> vos = netsStockDailyKLine3Service.searchSingleton(dto);
        log.info(JSON.toJSONString(vos));
    }

    @Test
    void searchAll() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        SearchAllDailyKLineDTO dto = SearchAllDailyKLineDTO.builder()
                .start(format.parse("20200101"))
                .end(format.parse("20220101"))
                .stockType(StockType.SH)
                .codes(Arrays.asList("601318", "601318"))
                .build();

        List<StockKLineVO> vos = netsStockDailyKLine3Service.searchAll(dto);
        log.info(JSON.toJSONString(vos));

    }
}