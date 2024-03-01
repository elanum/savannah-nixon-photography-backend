package de.savannahnixon.backend.app.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("storage")
public class StorageProperties {
  private String location = "upload-dir";
}
