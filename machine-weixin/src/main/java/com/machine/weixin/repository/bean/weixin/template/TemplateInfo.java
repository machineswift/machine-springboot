package com.machine.weixin.repository.bean.weixin.template;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemplateInfo {
    private String templateId;
    private String title;
    private String primaryIndustry;
    private String deputyIndustry;
    private String content;
    private String example;
}
