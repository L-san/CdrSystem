package me.sa1zer.cdrsystem.common.payload.dto;

import lombok.Builder;
import me.sa1zer.cdrsystem.common.object.enums.CallType;
import me.sa1zer.cdrsystem.common.object.enums.TariffType;

import java.time.LocalDateTime;

@Builder
public record ReportDto(String phoneNumber, LocalDateTime startTime, LocalDateTime endTime,
                        CallType callType, TariffType tariffType, double cost, long duration) {

}
