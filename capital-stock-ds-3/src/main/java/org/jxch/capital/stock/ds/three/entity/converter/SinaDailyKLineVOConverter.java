package org.jxch.capital.stock.ds.three.entity.converter;

import lombok.NonNull;
import org.jxch.capital.stock.ds.entity.vo.KLineVO;
import org.jxch.capital.stock.ds.three.entity.vo.SinaKLineVO;
import org.jxch.capital.stock.ds.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface SinaDailyKLineVOConverter {
    SinaDailyKLineVOConverter CONVERTER = Mappers.getMapper(SinaDailyKLineVOConverter.class);

    @Mappings({
            @Mapping(source = "day", target = "date", dateFormat = DateUtil.D_PATTERN),
            @Mapping(source = "open", target = "open"),
            @Mapping(source = "high", target = "high"),
            @Mapping(source = "low", target = "low"),
            @Mapping(source = "close", target = "close"),
            @Mapping(source = "volume", target = "vol"),
    })
    KLineVO convert(SinaKLineVO vo);

    default List<KLineVO> convert(@NonNull List<SinaKLineVO> vos) {
        return vos.stream().map(this::convert).collect(Collectors.toList());
    }

}
