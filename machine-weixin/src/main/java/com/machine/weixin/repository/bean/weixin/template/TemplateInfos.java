package com.machine.weixin.repository.bean.weixin.template;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TemplateInfos {
    private List<TemplateInfo> templateList;
}
