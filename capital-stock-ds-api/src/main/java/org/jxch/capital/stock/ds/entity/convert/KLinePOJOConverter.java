package org.jxch.capital.stock.ds.entity.convert;

import org.jxch.capital.stock.ds.entity.dto.KLineDTO;
import org.jxch.capital.stock.ds.entity.util.StockDateUtil;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface KLinePOJOConverter {

    default KLineVO dto2vo(KLineDTO dto) {
        return KLineVO.builder()
                .date(StockDateUtil.date2string(dto.getDate()))
                .open(dto.getOpen().toString())
                .close(dto.getClose().toString())
                .high(dto.getHigh().toString())
                .low(dto.getLow().toString())
                .vol(dto.getVol().toString())
                .build();
    }

    default List<KLineVO> dto2vo(List<KLineDTO> dtoList) {
        return dtoList.parallelStream().map(this::dto2vo).collect(Collectors.toList());
    }

    default KLineDTO vo2dto(KLineVO vo) {
        return KLineDTO.builder()
                .date(StockDateUtil.string2Date(vo.getDate()))
                .open(new BigDecimal(vo.getOpen()))
                .close(new BigDecimal(vo.getClose()))
                .high(new BigDecimal(vo.getHigh()))
                .low(new BigDecimal(vo.getLow()))
                .vol(new BigDecimal(vo.getVol()))
                .build();
    }

    default List<KLineDTO> vo2dto(List<KLineVO> voList) {
        return voList.parallelStream().map(this::vo2dto).collect(Collectors.toList());
    }

}
