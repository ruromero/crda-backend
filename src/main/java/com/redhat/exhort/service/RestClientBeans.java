/*
 * Copyright 2024 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.redhat.exhort.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.redhat.exhort.integration.Constants;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@ApplicationScoped
public class RestClientBeans {

  @Inject
  @Named("mainRestClient")
  private RestClient restClient;

  @Produces
  @Singleton
  @Named("snyk")
  public RestClient restClient(@ConfigProperty(name = "api.snyk.host") String snykUrl)
      throws URISyntaxException {

    restClient.addServiceUri(
        RestClient.SNYK_PROVIDER_NAME, new URI(snykUrl + Constants.SNYK_TOKEN_API_PATH));
    return restClient;
  }

  @Produces
  @Singleton
  @Named("osvNvd")
  public RestClient osvNvd(@ConfigProperty(name = "api.osvnvd.management.host") String url)
      throws URISyntaxException {
    restClient.addServiceUri(
        RestClient.OSV_NVD_PROVIDER_NAME,
        new URI(url + Constants.OSV_NVD_HEALTH_PATH.replaceFirst("/", "")));
    return restClient;
  }

  @Produces
  @Singleton
  @Named("ossIndex")
  public RestClient ossIndex(@ConfigProperty(name = "api.ossindex.host") String url)
      throws URISyntaxException {
    restClient.addServiceUri(
        RestClient.OSS_INDEX_PROVIDER_NAME,
        new URI(url + Constants.OSS_INDEX_AUTH_COMPONENT_API_PATH));
    return restClient;
  }

  @Produces
  @Singleton
  @Named("trustedContent")
  public RestClient trustedContent(@ConfigProperty(name = "api.trustedcontent.host") String url)
      throws URISyntaxException {
    restClient.addServiceUri(
        RestClient.TRUSTED_CONTENT_PROVIDER_NAME,
        new URI(url + Constants.TRUSTED_CONTENT_PATH.replace("/", "")));
    return restClient;
  }
}
