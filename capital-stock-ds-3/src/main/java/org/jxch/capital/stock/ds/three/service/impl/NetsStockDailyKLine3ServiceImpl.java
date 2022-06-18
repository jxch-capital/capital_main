package org.jxch.capital.stock.ds.three.service.impl;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jxch.capital.stock.ds.entity.StockType;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.jxch.capital.stock.ds.three.service.AbstractStockDailyKLine3Service;
import org.jxch.capital.stock.ds.util.DateUtil;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class NetsStockDailyKLine3ServiceImpl extends AbstractStockDailyKLine3Service {
    private final OkHttpClient okHttpClient;

    public NetsStockDailyKLine3ServiceImpl(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public boolean support(Date start, Date end, StockType stockType) {
        return stockType.equals(StockType.SH) || stockType.equals(StockType.SZ);
    }

    @Override
    public List<KLineVO> searchSingleton(SearchDailyKLineDTO dto) {
        Request request = new Request.Builder()
                .url(getURL(dto.getCode(), dto.getStart(), dto.getEnd(), dto.getStockType()))
                .addHeader("Connection", "keep-alive")
                .build();
        try (Response response = okHttpClient.newCall(request).execute();
             Scanner in = new Scanner(new ByteArrayInputStream(Objects.requireNonNull(response.body()).bytes()), "GBK")) {
            in.nextLine();
            List<KLineVO> vos = new ArrayList<>();
            while (in.hasNextLine()) {
                String[] split = in.nextLine().split(",");
                vos.add(KLineVO.builder()
                        .date(split[0])
                        .close(split[3])
                        .high(split[4])
                        .low(split[5])
                        .open(split[6])
                        .vol(split[7])
                        .build());
            }
            return vos;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StockKLineVO> searchAll(SearchAllDailyKLineDTO dto) {
        this.decrementOrder();
        return dto.getCodes().parallelStream()
                .map(code -> StockKLineVO.builder().code(code)
                        .kLineVOS(searchSingleton(SearchDailyKLineDTO.builder()
                                .code(code)
                                .stockType(dto.getStockType())
                                .start(dto.getStart())
                                .end(dto.getEnd())
                                .build())).build()).toList();
    }

    private String getURL(String code, Date start, Date end, StockType stockType) {
        String url = "http://quotes.money.163.com/service/chddata.html?";
        String fields = "&fields=TCLOSE;HIGH;LOW;TOPEN;VOTURNOVER;";
        String pattern = "yyyyMMdd";

        switch (stockType) {
            case SH -> code = "0" + code;
            case SZ -> code = "1" + code;
        }

        return url +
                "code=" + code +
                "&start=" + DateUtil.Date2String(start, pattern) +
                "&end=" + DateUtil.Date2String(end, pattern) +
                fields;
    }

}
