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

package com.smartsheet.api.models.enums;

/**
 * Represents specific objects that can be included in some responses.
 */
public enum SheetTemplateInclusion {
    ATTACHMENTS("attachments"),
    CELLLINKS("cellLinks"),
    DATA("data"),
    DISCUSSIONS("discussions"),
    FORMS("forms"),
    RULERECIPIENTS("ruleRecipients"),
    RULES("rules"),
    ;

    String inclusion;

    SheetTemplateInclusion(String inclusion) {
        this.inclusion = inclusion;
    }

    @Override
    public String toString() {
        return inclusion;
    }
}
