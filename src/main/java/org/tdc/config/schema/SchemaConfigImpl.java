package org.tdc.config.schema;

import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tdc.util.Addr;

public class SchemaConfigImpl implements SchemaConfig {
	
	private static final Logger log = LoggerFactory.getLogger(SchemaConfigImpl.class);
	private static final String CONFIG_FOLDER = "tdc.schema";

	private Path schemasRoot;
	private Addr addr;
	private Path schemaRoot;
	private Path schemaConfigRoot;
	
	public SchemaConfigImpl(Path schemasRoot, Addr addr) {
		this.schemasRoot = schemasRoot;
		this.addr = addr;
		this.schemaRoot = schemasRoot.resolve(addr.getPath());
		this.schemaConfigRoot = schemaRoot.resolve(CONFIG_FOLDER);
		validateDirectories();
		log.debug("Creating SchemaConfigImpl: {}", addr);
	}
	
	@Override
	public Path getSchemasRoot() {
		return schemasRoot;
	}
	
	@Override
	public Addr getAddr() {
		return addr; 
	}

	@Override
	public Path getSchemaRoot() {
		return schemaRoot;
	}

	@Override
	public Path getSchemaConfigRoot() {
		return schemaConfigRoot;
	}
	
	private void validateDirectories() {
		if (!Files.isDirectory(schemaRoot)) {
			throw new IllegalStateException("Schema dir does not exist: " + schemaRoot.toString());
		}
		if (!Files.isDirectory(schemaConfigRoot)) {
			throw new IllegalStateException("Schema '" + CONFIG_FOLDER + "' dir does not exist: " + schemaConfigRoot.toString());
		}
	}
}
