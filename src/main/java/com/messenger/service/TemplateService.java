package com.messenger.service;

import com.messenger.bean.Template;
import com.messenger.repository.TemplateRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("templateService")
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository = null;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    public List<Template> getTemplateList() {
        return templateRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    public Template getTemplateDetail(final int id) {
        return templateRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    public void updateTemplate(final Template template) {
        final Template t = templateRepository.findOne(template.getId());
        t.setTemplateName(StringUtils.isNotBlank(template.getTemplateName()) ? template.getTemplateName() : t.getTemplateName());
        t.setTemplateDetails(StringUtils.isNotBlank(template.getTemplateDetails()) ? template.getTemplateDetails() : t.getTemplateDetails());
        t.setTemplateActive(template.getTemplateActive() != 0 ? template.getTemplateActive() : t.getTemplateActive());
        templateRepository.saveAndFlush(t);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public Template createTemplate(final Template template) {
        return templateRepository.saveAndFlush(template);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public void delete(final int id) {
        templateRepository.delete(id);
    }

}
