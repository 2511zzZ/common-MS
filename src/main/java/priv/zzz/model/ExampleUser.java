package priv.zzz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleUser {


    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名过长")
    private String username;

    @Min(0)
    private Integer age;

    @Range(min = 0, max = 1)
    private Integer sex;

    @Email(message = "邮箱格式错误")
    private String email;

}
