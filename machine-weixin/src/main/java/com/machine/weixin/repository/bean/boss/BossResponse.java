package com.machine.weixin.repository.bean.boss;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BossResponse<T> {

    private String repCode;

    private String repMsg;

    private List<T> data;
}
