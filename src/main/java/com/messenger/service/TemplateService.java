package com.messenger.service;

import com.messenger.bean.Template;
import com.messenger.constants.CacheConstants;
import com.messenger.property.Config;
import com.messenger.repository.TemplateRepository;
import com.messenger.util.Utilities;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("templateService")
public class TemplateService {
    private final Logger logger = Logger.getLogger(TemplateService.class.getName());
    @Autowired
    private TemplateRepository templateRepository = null;

    @Autowired
    private Config config = null;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    @Cacheable(cacheNames = CacheConstants.STR_TEMPLATE_CACHE_CONSTANTS)
    public List<Template> getTemplateList() {
        return templateRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    @Cacheable(cacheNames = CacheConstants.STR_TEMPLATE_CACHE_CONSTANTS, key = "#id")
    public Template getTemplateDetail(final int id) {
        return templateRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_TEMPLATE_CACHE_CONSTANTS, allEntries = true, key = "#template.id")
    @CachePut(value = CacheConstants.STR_TEMPLATE_CACHE_CONSTANTS, key = "#template.id")
    public void updateTemplate(final Template template) {
        final Template t = templateRepository.findOne(template.getId());
        t.setTemplateName(StringUtils.isNotBlank(template.getTemplateName()) ? template.getTemplateName() : t.getTemplateName());
        t.setTemplateDetails(StringUtils.isNotBlank(template.getTemplateDetails()) ? template.getTemplateDetails() : t.getTemplateDetails());
        t.setTemplateActive(template.getTemplateActive() != 0 ? template.getTemplateActive() : t.getTemplateActive());
        templateRepository.saveAndFlush(t);
        Utilities.refresh(config.getValue("receiver_template_urls").split(","));
        Utilities.refresh(config.getValue("sender_template_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CachePut(value = CacheConstants.STR_TEMPLATE_CACHE_CONSTANTS, key = "#template.id")
    public Template createTemplate(final Template template) {
        final Template t = templateRepository.saveAndFlush(template);
        Utilities.refresh(config.getValue("receiver_template_urls").split(","));
        Utilities.refresh(config.getValue("sender_template_urls").split(","));
        return t;
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_TEMPLATE_CACHE_CONSTANTS, allEntries = true, key = "#id")
    public void delete(final int id) {
        templateRepository.delete(id);
        Utilities.refresh(config.getValue("receiver_template_urls").split(","));
        Utilities.refresh(config.getValue("sender_template_urls").split(","));
    }
}
