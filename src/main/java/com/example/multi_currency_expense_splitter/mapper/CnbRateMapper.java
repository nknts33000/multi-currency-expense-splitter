package com.example.multi_currency_expense_splitter.mapper;

import com.example.multi_currency_expense_splitter.dto.CnbRateDto;
import com.example.multi_currency_expense_splitter.entity.CnbRate;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        builder = @Builder(disableBuilder = true))
public interface CnbRateMapper {
    @Mapping(target = "id", ignore = true)
    CnbRate toEntity(CnbRateDto dto);
}
