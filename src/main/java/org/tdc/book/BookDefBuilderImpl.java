package org.tdc.book;

import java.util.HashMap;
import java.util.Map;

import org.tdc.config.book.BookDefConfig;
import org.tdc.config.book.PageDefConfig;
import org.tdc.config.model.ModelConfig;
import org.tdc.config.model.ModelConfigFactory;
import org.tdc.modelinst.ModelInst;
import org.tdc.modelinst.ModelInstFactory;

public class BookDefBuilderImpl implements BookDefBuilder {

	@Override
	public BookDef build(BookDefConfig config, ModelInstFactory modelInstFactory, ModelConfigFactory modelConfigFactory) {
		
		Map<String, ModelInst> pageNameModelInstMap = new HashMap<>(); 
		for (PageDefConfig pageDefConfig : config.getPageDefConfigs()) {
			
			ModelConfig modelConfig = modelConfigFactory.getModelConfig(pageDefConfig.getModelInstAddr());
			ModelInst modelInst = modelInstFactory.getModelInst(modelConfig); 
			if (pageNameModelInstMap.containsKey(pageDefConfig.getPageName())) {
				throw new RuntimeException("A book cannot contain two pages with the same name"); // TODO better choice of exception
			}
			pageNameModelInstMap.put(pageDefConfig.getPageName(), modelInst);
		}
		return new BookDefImpl(config, pageNameModelInstMap);
	}
}
