package j.jav25.todomgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoJwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
