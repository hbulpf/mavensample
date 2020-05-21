
package dev.json.jackson.crm;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回体
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseEntity<E> {
    String error_code;

    String error_msg;

    String message;

    List<E> infoList;
}
