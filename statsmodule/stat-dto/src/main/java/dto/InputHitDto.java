package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class InputHitDto {

    @NotEmpty(message = "Не указано наименование приложения!")
    @Size(max = 50, message = "Наименование приложение не может превышать 50 символов!")
    private String app;

    @NotEmpty(message = "Не указан URI !")
    @Size(max = 50, message = "URI не может превышать 50 символов!")
    private String uri;

    @NotEmpty(message = "Не указан IP !")
    @Size(max = 19, message = "URI не может превышать 19 символов!")
    private String ip;

    @NotNull(message = "Не указана дата запроса!")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp;
}
