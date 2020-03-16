package org.bootvue.jwt.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class User {

    private Long id;
    private String username;
    private String description;
}
