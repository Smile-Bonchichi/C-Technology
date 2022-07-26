package kg.smile.ctechnology.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthRequestDto {
    @Size(max = 20, min = 5)
    @NotNull
    String login;

    @Size(max = 50, min = 8)
    @NotNull
    String password;
}
