package com.machine.weixin.repository.bean.spectrum;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class SpectrumAttributes {
    private List<SpectrumNode> nodes;
    private Map<String, Boolean> writablePermissionMap;
}
