package com.smartsheet.api.models;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
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
 * %[license]
 */

import com.smartsheet.api.models.enums.WebhookStatus;

import java.util.Date;
import java.util.List;

public class Webhook extends NamedModel<Long> {

    /**
     * API Client Id corresponding to third-party app that created the Webhook.
     */
    private String apiClientId;

    /**
     * Id of the object that is subscripted to.
     */
    private Long scopeObjectId;

    /**
     * API Client Name corresponding to third-party app that created the Webhook.
     */
    private String apiClientName;

    /**
     * HTTPS URL where callbacks will be sent.
     */
    private String callbackUrl;

    /**
     * Time of creation
     */
    private Date createdAt;

    /**
     * Details about the reason the Webhook is disabled.
     */
    private String disabledDetails;

    /**
     * Flag indicating whether the Webhook is on (true) or off (false).
     */
    private Boolean enabled;

    /**
     * Array of events that are subscribed to.
     */
    private List<String> events;

    /**
     * Time of last modification
     */
    private Date modifiedAt;

    /**
     * Scope of the subscription.
     */
    private String scope;

    /**
     * Shared secret for this webhook, randomly generated by Smartsheet.
     */
    private String sharedSecret;

    /**
     * Useful statistics for this webhook.
     */
    private WebhookStats stats;

    /**
     * Webhook status.
     */
    private WebhookStatus status;

    /**
     * Array of columnIds if you want to limit the subscription to a subscope.
     * Specified when a webhook is created and cannot be changed.
     */
    private WebhookSubscope subscope;

    /**
     * Webhook version.
     */
    private Integer version;

    /**
     * Get the API client Id corresponding to third-party app that created the webhook.
     *
     * @return apiClientId
     */
    public String getApiClientId() {
        return apiClientId;
    }

    /**
     * Set the API client Id corresponding to third-party app that created the webhook.
     *
     * @param apiClientId
     */
    public Webhook setApiClientId(String apiClientId) {
        this.apiClientId = apiClientId;
        return this;
    }

    /**
     * Get the Id of the object that is subscripted to
     *
     * @return scopeObjectId
     */
    public Long getScopeObjectId() {
        return scopeObjectId;
    }

    /**
     * Set the Id of the object that is subscribed to
     *
     * @param scopeObjectId
     */
    public Webhook setScopeObjectId(Long scopeObjectId) {
        this.scopeObjectId = scopeObjectId;
        return this;
    }

    /**
     * Get the API client name corresponding to third-party app that created the webhook.
     *
     * @return apiClientName;
     */
    public String getApiClientName() {
        return apiClientName;
    }

    /**
     * Set the API client name corresponding to third-party app that created the webhook.
     *
     * @param apiClientName
     */
    public Webhook setApiClientName(String apiClientName) {
        this.apiClientName = apiClientName;
        return this;
    }

    /**
     * Get the HTTPS URL where callbacks will be sent
     *
     * @return
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     * Set the HTTPS URL where callbacks will be sent
     *
     * @param callbackUrl
     */
    public Webhook setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    /**
     * Gets the time that the webhook was created.
     *
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the time the webhook was created.
     *
     * @param createdAt
     */
    public Webhook setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Get the details about the reason the webhook was disabled.
     *
     * @return disabledDetails
     */
    public String getDisabledDetails() {
        return disabledDetails;
    }

    /**
     * Set the details about the reason the webhook was disabled.
     *
     * @param disabledDetails
     */
    public Webhook setDisabledDetails(String disabledDetails) {
        this.disabledDetails = disabledDetails;
        return this;
    }

    /**
     * Get flag indicating whether the webhook is on(true) or off(false)
     *
     * @return enabled
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Set flag indicating whether the webhook is on or off.
     *
     * @param enabled
     */
    public Webhook setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the array of events that are subscribed to
     *
     * @return events
     */
    public List<String> getEvents() {
        return events;
    }

    /**
     * Set the array of events that are subscribed to
     *
     * @param events
     */
    public Webhook setEvents(List<String> events) {
        this.events = events;
        return this;
    }

    /**
     * Get the time that the webhook was last modified.
     *
     * @return modifiedAt
     */
    public Date getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Set the time that the webhook was last modified.
     *
     * @param modifiedAt
     */
    public Webhook setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * Provide an 'override' of setName (returns Webhook not NamedModel)
     *
     * @param name the new name
     */
    public Webhook setName(String name){
        super.setName(name);
        return this;
    }

    /**
     * Get the scope of the subscription.
     *
     * @return scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * Set the scope of the subscription
     *
     * @param scope
     */
    public Webhook setScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * Get the shared secret for this webhook, randomly generated by Smartsheet
     *
     * @return sharedSecret
     */
    public String getSharedSecret() {
        return sharedSecret;
    }

    /**
     * Set the shared secret for this webhook
     *
     * @param sharedSecret
     */
    public Webhook setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
        return this;
    }

    /**
     * Get the statistics for this webhook.
     *
     * @return stats
     */
    public WebhookStats getStats() {
        return stats;
    }

    /**
     * Set the statistics for this webhook.
     *
     * @param stats
     */
    public Webhook setStats(WebhookStats stats) {
        this.stats = stats;
        return this;
    }

    /**
     * Get the webhook status
     *
     * @return status
     */
    public WebhookStatus getStatus() {
        return status;
    }

    /**
     * Set the webhook status
     *
     * @param status
     */
    public Webhook setStatus(WebhookStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the webhook subscope
     *
     * @return subscope
     */
    public WebhookSubscope getSubscope() {
        return subscope;
    }

    /**
     * Set the Webhook subscope
     *
     * @param subscope
     */
    public Webhook setSubscope(WebhookSubscope subscope) {
        this.subscope = subscope;
        return this;
    }

    /**
     * Get the webhook version.
     *
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Set the webhook version
     *
     * @param version
     */
    public Webhook setVersion(Integer version) {
        this.version = version;
        return this;
    }
}
