package com.machine.weixin.repository.bean.spectrum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpectrumNode {
    private String parentKey;
    private String absoluteKey;
    private String key;
    private String value;
    private Boolean dir;
}
