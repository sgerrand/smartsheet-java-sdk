package com.smartsheet.api.internal.http;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2023 Smartsheet
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


import com.smartsheet.api.Trace;
import kotlin.Pair;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * a POJO from which is generated JSON from HTTP request/response pairs
 */
public class RequestAndResponseData {
    public static abstract class HttpPayloadData {
        Map<String, String> headers;
        String body;

        public String getBody() {
            return body;
        }

        public boolean hasBody() {
            return body != null;
        }

        public boolean hasHeaders() {
            return headers != null;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        static abstract class Builder<Type extends HttpPayloadData> {
            public void withHeaders() {
                // this is seaprate from addHeader in case headers were requested but none found
                if (getDataObject().headers == null) {
                    getDataObject().headers = new TreeMap<>();
                }
            }

            public Builder addHeader(String key, String val) {
                withHeaders();
                getDataObject().headers.put(key, val);
                return this;
            }

            public Builder setBody(String body) {
                getDataObject().body = body;
                return this;
            }

            public abstract Type build();

            public abstract void reset();

            protected abstract Type getDataObject();
        }
    }

    public static class RequestData extends HttpPayloadData {
        private String command;

        public String getCommand() {
            return command;
        }

        public static class Builder extends HttpPayloadData.Builder<RequestData> {
            private RequestData dataObject;

            @Override
            public void reset() {
                dataObject = null;
            }

            @Override
            protected RequestData getDataObject() {
                if (dataObject == null) {
                    dataObject = new RequestData();
                }
                return dataObject;
            }

            public HttpPayloadData.Builder withCommand(String command) {
                getDataObject().command = command;
                return this;
            }

            public RequestData build() {
                try {
                    return dataObject;  // if nothing was added then nothing was built (i.e., this can be null)
                } finally {
                    reset();
                }
            }
        }
    }

    public static class ResponseData extends HttpPayloadData {
        private String status;

        public String getStatus() {
            return status;
        }

        public static class Builder extends HttpPayloadData.Builder<ResponseData> {
            private ResponseData dataObject;

            @Override
            public void reset() {
                dataObject = null;
            }

            @Override
            protected ResponseData getDataObject() {
                if (dataObject == null) {
                    dataObject = new ResponseData();
                }
                return dataObject;
            }

            public HttpPayloadData.Builder withStatus(String status) {
                getDataObject().status = status;
                return this;
            }

            public ResponseData build() {
                try {
                    return dataObject;  // if nothing was added then nothing was built (i.e., this can be null)
                } finally {
                    reset();
                }
            }
        }
    }

    private static int TRUNCATE_LENGTH = Integer.getInteger("Smartsheet.trace.truncateLen", 1024);

    public final RequestData request;
    public final ResponseData response;

    private RequestAndResponseData(RequestData requestData, ResponseData responseData) {
        request = requestData;
        response = responseData;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean pretty) {
        final String EOL = pretty ? "\n" : "";
        final String INDENT  = pretty ? "  " : "";
        final String INDENT2 = INDENT + INDENT;
        final String INDENT3 = INDENT2 + INDENT;

        StringBuilder buf = new StringBuilder();
        buf.append("{").append(EOL);
        buf.append(INDENT).append("request:");
        if (request == null) {
            buf.append("null,").append(EOL);
        } else {
            buf.append("{").append(EOL);
            buf.append(INDENT2).append("command:'").append(request.getCommand()).append("',").append(EOL);
            if (request.hasHeaders()) {
                buf.append(INDENT2).append("headers:");
                if (request.getHeaders() == null) {
                    buf.append("null");
                } else {
                    buf.append("{").append(EOL);
                    for (Map.Entry<String, String> header : request.headers.entrySet()) {
                        buf.append(INDENT3).append("'").append(header.getKey()).append("':'").append(header.getValue()).append("',").append(EOL);
                    }
                    buf.append(INDENT2).append("},").append(EOL);
                }
            }
            if (request.hasBody()) {
                buf.append(INDENT2).append("body:");
                if (request.body == null) {
                    buf.append("null");
                } else {
                    buf.append("'").append(request.body).append("'");
                }
                buf.append(EOL);
            }
            buf.append(INDENT).append("},").append(EOL);
        }
        buf.append(INDENT).append("response:");
        if (response == null) {
            buf.append("null").append(EOL);
        } else {
            buf.append("{").append(EOL);
            buf.append(INDENT2).append("status:'").append(response.getStatus()).append("',").append(EOL);
            if (response.hasHeaders()) {
                buf.append(INDENT2).append("headers:");
                if (response.getHeaders() == null) {
                    buf.append("null");
                } else {
                    buf.append("{").append(EOL);
                    for (Map.Entry<String, String> header : response.headers.entrySet()) {
                        buf.append(INDENT3).append("'").append(header.getKey()).append("':'").append(header.getValue()).append("',").append(EOL);
                    }
                    buf.append(INDENT2).append("},").append(EOL);
                }
            }
            if (response.hasBody()) {
                buf.append(INDENT2).append("body:");
                if (response.body == null) {
                    buf.append("null");
                } else {
                    buf.append("'").append(response.body).append("'");
                }
                buf.append(EOL);
            }
            buf.append(INDENT).append("}").append(EOL);
        }
        buf.append("}");
        return buf.toString();
    }

    /**
     * factory method for creating a RequestAndResponseData object from request and response data with the specifid trace fields
     */
    public static RequestAndResponseData of(HttpRequestBase request, HttpEntitySnapshot requestEntity,
                                            HttpResponse response, HttpEntitySnapshot responseEntity,
                                            Set<Trace> traces)
            throws IOException {
        RequestData.Builder requestBuilder = new RequestData.Builder();
        ResponseData.Builder responseBuilder = new ResponseData.Builder();

        if (request != null) {
            requestBuilder.withCommand(request.getMethod() + " " + request.getURI());
            boolean binaryBody = false;
            if (traces.contains(Trace.RequestHeaders) && request.getAllHeaders() != null) {
                requestBuilder.withHeaders();
                for (Header header : request.getAllHeaders()) {
                    String headerName = header.getName();
                    String headerValue = header.getValue();
                    if ("Authorization".equals(headerName) && headerValue.length() > 0) {
                        headerValue = "Bearer ****" + headerValue.substring(Math.max(0, headerValue.length() - 4));
                    } else if ("Content-Disposition".equals(headerName)) {
                        binaryBody = true;
                    }
                    requestBuilder.addHeader(headerName, headerValue);
                }
            }
            if (requestEntity != null) {
                if (traces.contains(Trace.RequestBody)) {
                    requestBuilder.setBody(binaryBody ? binaryBody(requestEntity) : getContentAsText(requestEntity));
                } else if (traces.contains(Trace.RequestBodySummary)) {
                    requestBuilder.setBody(binaryBody ? binaryBody(requestEntity) : truncateAsNeeded(getContentAsText(requestEntity), TRUNCATE_LENGTH));
                }
            }
        }
        if (response != null) {
            boolean binaryBody = false;
            responseBuilder.withStatus(response.getStatusText());
            if (traces.contains(Trace.ResponseHeaders) && response.getHeaders() != null) {
                responseBuilder.withHeaders();
                for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                    String headerName = header.getKey();
                    String headerValue = header.getValue();
                    if ("Content-Disposition".equals(headerName)) {
                        binaryBody = true;
                    }
                    responseBuilder.addHeader(headerName, headerValue);
                }
            }
            if (responseEntity != null) {
                if (traces.contains(Trace.ResponseBody)) {
                    responseBuilder.setBody(binaryBody ? binaryBody(responseEntity) : getContentAsText(responseEntity));
                } else if (traces.contains(Trace.ResponseBodySummary)) {
                    responseBuilder.setBody(binaryBody ? binaryBody(responseEntity) : truncateAsNeeded(getContentAsText(responseEntity), TRUNCATE_LENGTH));
                }
            }
        }
        return new RequestAndResponseData(requestBuilder.build(), responseBuilder.build());
    }

    /**
     * factory method for creating a RequestAndResponseData object from request and response okhttp data with the specified trace fields
     */
    public static RequestAndResponseData of(Request request, Response response, Set<Trace> traces)
            throws IOException {
        RequestData requestData = null;
        ResponseData responseData = null;

        if (request != null) {
            requestData = buildRequestData(request, traces);
        }
        if (response != null) {
            responseData = buildResponseData(response, traces);
        }
        return new RequestAndResponseData(requestData, responseData);
    }

    private static RequestData buildRequestData(Request request, Set<Trace> traces) throws IOException {
        RequestData.Builder builder = new RequestData.Builder();

        builder.withCommand(request.method() + " " + request.url());
        boolean binaryBody = false;
        if (traces.contains(Trace.RequestHeaders)) {
            builder.withHeaders();
            for (Pair<? extends String, ? extends String> header : request.headers()) {
                String headerName = header.getFirst();
                String headerValue = header.getSecond();
                if ("Authorization".equals(headerName) && headerValue.length() > 0) {
                    // gets the last four characters of a string or all of them if the string is less than 5 characters
                    String lastFourChars = headerValue.substring(Math.max(0, headerValue.length() - 4));
                    headerValue = "Bearer ****" + lastFourChars;
                } else if ("Content-Disposition".equals(headerName)) {
                    binaryBody = true;
                }
                builder.addHeader(headerName, headerValue);
            }
        }

        RequestBody requestBody = request.body();
        if (requestBody != null) {
            if (traces.contains(Trace.RequestBody)) {
                builder.setBody(binaryBody ? binaryBody(requestBody) : getContentAsText(requestBody));
            } else if (traces.contains(Trace.RequestBodySummary)) {
                builder.setBody(binaryBody ? binaryBody(requestBody) : truncateAsNeeded(getContentAsText(requestBody), TRUNCATE_LENGTH));
            }
        }

        return builder.build();
    }

    private static ResponseData buildResponseData(Response response, Set<Trace> traces) throws IOException {
        ResponseData.Builder builder = new ResponseData.Builder();

        boolean binaryBody = false;
        builder.withStatus(String.valueOf(response.code()));
        if (traces.contains(Trace.ResponseHeaders)) {
            builder.withHeaders();
            for (Pair<? extends String, ? extends String> header : response.headers()) {
                String headerName = header.getFirst();
                String headerValue = header.getSecond();
                if ("Content-Disposition".equals(headerName)) {
                    binaryBody = true;
                }
                builder.addHeader(headerName, headerValue);
            }
        }

        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            if (traces.contains(Trace.ResponseBody)) {
                builder.setBody(binaryBody ? binaryBody(responseBody) : getContentAsText(responseBody));
            } else if (traces.contains(Trace.ResponseBodySummary)) {
                builder.setBody(binaryBody ? binaryBody(responseBody) : truncateAsNeeded(getContentAsText(responseBody), TRUNCATE_LENGTH));
            }
        }

        return builder.build();
    }

    static String binaryBody(HttpEntitySnapshot entity) {
        return "**possibly-binary(type:" + entity.getContentType() + ", len:" + entity.getContentLength() + ")**";
    }

    static String binaryBody(RequestBody body) throws IOException {
        return "**possibly-binary(type:" + body.contentType() + ", len:" + body.contentLength() + ")**";
    }

    static String binaryBody(ResponseBody body) throws IOException {
        return "**possibly-binary(type:" + body.contentType() + ", len:" + body.contentLength() + ")**";
    }

    public static String getContentAsText(HttpEntitySnapshot entity) throws IOException {
        if (entity == null) {
            return "";
        }
        byte[] contentBytes = entity.getContentArray();
        String contentAsText;
        try {
            contentAsText = new String(contentBytes, "UTF-8");
        } catch (UnsupportedEncodingException badEncodingOrNotText) {
            contentAsText = new String(Hex.encodeHex(contentBytes));
        }
        return contentAsText;
    }

    public static String getContentAsText(RequestBody body) throws IOException {
        if (body == null) {
            return "";
        }

        Buffer bodyBuffer = new Buffer();
        body.writeTo(bodyBuffer);

        return bodyBuffer.readUtf8();
    }

    public static String getContentAsText(ResponseBody body) throws IOException {
        if (body == null) {
            return "";
        }

        return body.string();
    }

    public static String truncateAsNeeded(String string, int truncateLen) {
        if (truncateLen == -1) {
            return string;
        }
        truncateLen = Math.min(string.length(), truncateLen);
        String suffix = truncateLen < string.length() ? "..." : "";
        return string.substring(0, truncateLen) + suffix;
    }
}
