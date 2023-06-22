/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates
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

package com.redhat.ecosystemappeng.crda.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum Severity {
  CRITICAL,
  HIGH,
  MEDIUM,
  LOW;

  public static Severity fromValue(String value) {
    if (value == null) {
      return null;
    }
    return Severity.valueOf(value.toUpperCase());
  }

  public String toString() {
    return name().toLowerCase();
  }

  // From: https://nvd.nist.gov/vuln-metrics/cvss
  public static Severity fromScore(float score) {
    if (score < 4) {
      return LOW;
    }
    if (score < 7) {
      return MEDIUM;
    }
    if (score < 9) {
      return HIGH;
    }
    return CRITICAL;
  }
}
