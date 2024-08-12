package com.example.qllh.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse {
    private List list;

    private Integer totalRecord;

    private  Integer currentPage;
}
