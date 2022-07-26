package kg.smile.ctechnology.dto.response;

import kg.smile.ctechnology.dto.response.base.BaseResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthResponseDto extends BaseResponseDto {
    String token;
}
