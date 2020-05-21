
package dev.json.jackson.crm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WorkflowObj
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowObj {

    String Number;

    String Name;

    String id;

    String status;

    String type;

    String beginTime;

    String expireTime;

    String createdAt;

    String flowId;
}
