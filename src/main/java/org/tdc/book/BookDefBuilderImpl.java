package org.tdc.book;

import java.util.HashMap;
import java.util.Map;

import org.tdc.config.book.BookDefConfig;
import org.tdc.config.book.PageDefConfig;
import org.tdc.config.modelinst.ModelInstConfig;
import org.tdc.config.modelinst.ModelInstConfigFactory;
import org.tdc.modelinst.ModelInst;
import org.tdc.modelinst.ModelInstFactory;

public class BookDefBuilderImpl implements BookDefBuilder {

	@Override
	public BookDef build(BookDefConfig config, ModelInstFactory modelInstFactory, ModelInstConfigFactory modelInstConfigFactory) {
		
		Map<String, ModelInst> pageNameModelInstMap = new HashMap<>(); 
		for (PageDefConfig pageDefConfig : config.getPageDefConfigs()) {
			
			ModelInstConfig modelInstConfig = modelInstConfigFactory.getModelInstConfig(pageDefConfig.getModelInstAddr());
			ModelInst modelInst = modelInstFactory.getModelInst(modelInstConfig); 
			if (pageNameModelInstMap.containsKey(pageDefConfig.getPageName())) {
				throw new RuntimeException("A book cannot contain two pages with the same name"); // TODO better choice of exception
			}
			pageNameModelInstMap.put(pageDefConfig.getPageName(), modelInst);
		}
		return new BookDefImpl(config, pageNameModelInstMap);
	}
}
