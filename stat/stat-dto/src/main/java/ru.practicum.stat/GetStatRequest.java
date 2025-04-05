package ru.practicum.stat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class GetStatRequest {
    private OffsetDateTime start;
    private OffsetDateTime end;
    private List<String> uris;
    private Boolean unique;

    public static GetStatRequest of(OffsetDateTime start,
                                    OffsetDateTime end,
                                    List<String> uris,
                                    Boolean unique) {
        GetStatRequest request = new GetStatRequest();
        request.setStart(start);
        request.setEnd(end);
        request.setUnique(unique);
        if (uris != null) {
            request.setUris(uris);
        }

        return request;
    }

    public boolean hasUris() {
        return uris != null && !uris.isEmpty();
    }
}
