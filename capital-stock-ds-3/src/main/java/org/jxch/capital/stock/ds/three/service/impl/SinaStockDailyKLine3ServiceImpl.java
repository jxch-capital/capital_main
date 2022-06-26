package org.jxch.capital.stock.ds.three.service.impl;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jxch.capital.stock.ds.entity.StockType;
import org.jxch.capital.stock.ds.entity.dto.SearchAllDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.dto.SearchDailyKLineDTO;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.entity.vo.StockKLineVO;
import org.jxch.capital.stock.ds.three.entity.converter.SinaDailyKLineVOConverter;
import org.jxch.capital.stock.ds.three.entity.vo.SinaKLineVO;
import org.jxch.capital.stock.ds.three.service.AbstractStockDailyKLine3Service;
import org.jxch.capital.stock.ds.util.DateUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service("sinaStockDailyKLine3Service")
public class SinaStockDailyKLine3ServiceImpl extends AbstractStockDailyKLine3Service {
    private final OkHttpClient okHttpClient;

    public SinaStockDailyKLine3ServiceImpl(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public boolean support(Date start, Date end, StockType stockType) {
        return (super.support(start, end, stockType)) &&
                (start.compareTo(DateUtil.string2Date("1970-01-01")) > 0) &&
                (stockType.equals(StockType.SH) || stockType.equals(StockType.SZ));
    }

    @Override
    public List<KLineVO> searchSingleton(SearchDailyKLineDTO dto) {
        Request request = new Request.Builder()
                .url(getURL(dto.getCode(), dto.getStart(), dto.getStockType()))
                .addHeader("Connection", "keep-alive")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            Pattern compile = Pattern.compile("\\[.*]");
            Matcher matcher = compile.matcher(Objects.requireNonNull(response.body()).string());
            if (matcher.find()) {
                String json = matcher.group();
                List<SinaKLineVO> sinaKLineVOS = JSON.parseArray(json, SinaKLineVO.class);
                return SinaDailyKLineVOConverter.CONVERTER.convert(sinaKLineVOS);
            } else throw new RuntimeException("请求未获取结果");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StockKLineVO> searchAll(SearchAllDailyKLineDTO dto) {
        this.decrementOrder();
        return dto.getCodes().stream()
                .map(code -> StockKLineVO.builder().code(code)
                        .kLineVOS(searchSingleton(SearchDailyKLineDTO.builder()
                                .code(code)
                                .stockType(dto.getStockType())
                                .start(dto.getStart())
                                .end(dto.getEnd())
                                .build()))
                        .build()).toList();
    }

    private String getURL(String code, Date start, StockType stockType) {
        String sCode = "";
        switch (stockType) {
            case SH -> sCode = "sh" + code;
            case SZ -> sCode = "sz" + code;
        }

        return "https://quotes.sina.cn/cn/api/jsonp_v2.php/var%20_" + sCode + "_240_" + start.getTime() +
                "=/CN_MarketDataService.getKLineData?symbol=" + sCode + "&scale=240&ma=no&datalen=1023";
    }
}
