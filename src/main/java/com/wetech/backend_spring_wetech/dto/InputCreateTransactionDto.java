package com.wetech.backend_spring_wetech.dto;

import com.wetech.backend_spring_wetech.entity.ListItem;
import com.wetech.backend_spring_wetech.entity.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class InputCreateTransactionDto {
    private Transaction transaction;
    private List<ListItem> listItems;
}
