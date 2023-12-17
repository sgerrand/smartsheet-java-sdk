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

package com.smartsheet.api.models;

import com.smartsheet.api.models.enums.WidgetType;
import com.smartsheet.api.models.format.Format;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Widget {
    /** Represents the ID. */
    private Long id;

    /**
     * Type of widget
     */
    private WidgetType type;

    /**
     * (Optional) Title of widget
     */
    private String title;

    /**
     * True indicates that the client should display the widget title.
     */
    private Boolean showTitle;

    /**
     * True indicates that the client should display the sheet icon in the widget
     */
    private Boolean showTitleIcon;

    /**
     * Contains the title format descriptor
     */
    private Format titleFormat;

    /**
     * X-coordinate of widget's position on the Sight
     */
    private Integer xPosition;

    /**
     * Y-coordinate of widget's position on the Sight
     */
    private Integer yPosition;

    /**
     * Number of rows that the widget occupies
     */
    private Integer height;

    /**
     * Number of columns that the widget occupies
     */
    private Integer width;

    /**
     * Widget version number
     */
    private Integer version;

    /**
     * Data that specifies the contents of the widget.
     * _Note: the type of WidgetContent object (and attributes within) will depend on the value of Widget.
     */
    private WidgetContent contents;

    /**
     * Present when the widget is in an error state.
     */
    private Error error;
}
