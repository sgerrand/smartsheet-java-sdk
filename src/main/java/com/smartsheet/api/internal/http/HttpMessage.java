/*
 * Copyright (C) 2023 Smartsheet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartsheet.api.internal.http;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * This is the base class of HTTP messages, it holds headers and an HttpEntity.
 * <p>
 * Thread Safety: This class is not thread safe since it's mutable.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class HttpMessage {
    /**
     * Represents the HTTP headers.
     * <p>
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private Map<String, String> headers;

    /**
     * Represents the HTTP entity.
     * <p>
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private HttpEntity entity;
}
