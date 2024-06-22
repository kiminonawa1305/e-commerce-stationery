package com.lamnguyen.stationery_kimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatatableApiRequest implements Serializable {
    private Integer draw;
    private Integer start;
    private Integer length;
    private Search search;
    private List<Order> order;
    private List<Column> columns;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Search {
        private String value;
        private Boolean regex;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Order {
        private Integer column;
        private String name;
        private String dir;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Column {
        private String data;
        private String name;
        private Boolean searchable;
        private Boolean orderable;
        private Search search;
    }

    public static DatatableApiRequest newInstance(Map<String, Object> query) {
        List<Column> columns = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        for (int row = 0; ; row++) {
            if (query.get("columns[" + row + "][data]") == null) break;
            columns.add(Column.builder()
                    .data(query.get("columns[" + row + "][data]").toString())
                    .name(query.get("columns[" + row + "][name]").toString())
                    .search(Search.builder()
                            .value(query.get("columns[" + row + "][search][value]").toString())
                            .regex(Boolean.parseBoolean(query.get("columns[" + row + "][search][regex]").toString()))
                            .build())
                    .searchable(Boolean.parseBoolean(query.get("columns[" + row + "][searchable]").toString()))
                    .orderable(Boolean.parseBoolean(query.get("columns[" + row + "][orderable]").toString()))
                    .build());
            if (query.get("order[" + row + "][column]") == null) continue;
            orders.add(Order.builder()
                    .column(Integer.parseInt(query.get("order[" + row + "][column]").toString()))
                    .dir(query.get("order[" + row + "][dir]").toString())
                    .name(query.get("order[" + row + "][name]").toString())
                    .build());
        }
        return DatatableApiRequest.builder()
                .draw(Integer.parseInt(query.get("draw").toString()))
                .start(Integer.parseInt(query.get("start").toString()))
                .length(Integer.parseInt(query.get("length").toString()))
                .search(Search.builder()
                        .value(query.get("search[value]").toString())
                        .regex(Boolean.parseBoolean(query.get("search[regex]").toString()))
                        .build())
                .columns(columns)
                .order(orders)
                .build();
    }
}
