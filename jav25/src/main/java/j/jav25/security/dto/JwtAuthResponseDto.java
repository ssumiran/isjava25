package j.jav25.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JwtAuthResponseDto {

	private String accessToken;
    private String tokenType = "Bearer";
    
    public JwtAuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
