package vn.techmaster.jobhunt.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record EmployerRequest(
String id,
String name,
String website,
String email)
{

}
