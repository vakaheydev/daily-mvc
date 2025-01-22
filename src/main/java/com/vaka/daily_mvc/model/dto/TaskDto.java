package com.vaka.daily_mvc.model.dto;

import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private Integer id;

    private String name;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadline;

    private Boolean status;
    private Schedule schedule;
    private TaskType taskType;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Task {");

        sb.append(String.format("id=%d", id));
        sb.append(String.format(", name=%s", name));
        sb.append(String.format(", description=%s", description));
        sb.append(String.format(", deadline=%s", deadline));
        sb.append(String.format(", status=%s", status));
        sb.append(", schedule=");

        if (schedule != null) {
            sb.append(String.format("(%d, ", schedule.getId()));
            sb.append(String.format("%s)", schedule.getName()));
        }

        sb.append(String.format(", taskType=%s", taskType));
        sb.append("}");

        return sb.toString();
    }

}
