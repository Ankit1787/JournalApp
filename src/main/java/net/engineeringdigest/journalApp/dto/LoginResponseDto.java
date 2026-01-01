package net.engineeringdigest.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    String jwt;
    ObjectId userId;

}
