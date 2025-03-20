/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.samples.is.rar.ticket.booking.app.validator;

import org.json.JSONObject;
import org.wso2.carbon.identity.application.authentication.framework.exception.UserIdNotFoundException;
import org.wso2.carbon.identity.oauth.rar.exception.AuthorizationDetailsProcessingException;
import org.wso2.carbon.identity.oauth.rar.model.AuthorizationDetail;
import org.wso2.carbon.identity.oauth.rar.model.AuthorizationDetails;
import org.wso2.carbon.identity.oauth.rar.model.ValidationResult;
import org.wso2.carbon.identity.oauth2.IdentityOAuth2ServerException;
import org.wso2.carbon.identity.oauth2.rar.core.AuthorizationDetailsProcessor;
import org.wso2.carbon.identity.oauth2.rar.model.AuthorizationDetailsContext;

import static org.wso2.samples.is.rar.ticket.booking.app.validator.Constant.BOOKING_CREATION_AUTHORIZATION_PROCESSOR;

/**
 * This class is responsible for processing the authorization details of the booking creation request.
 */
public class BookingCreationAuthorizationProcessor implements AuthorizationDetailsProcessor {

    @Override
    public ValidationResult validate(AuthorizationDetailsContext authorizationDetailsContext)
            throws AuthorizationDetailsProcessingException, IdentityOAuth2ServerException {

        JSONObject authzDetailsObject = new JSONObject(authorizationDetailsContext.
                getAuthorizationDetail().getDetails());
        String userId;
        try {
            userId = authorizationDetailsContext.getAuthenticatedUser().getUserId();
        } catch (UserIdNotFoundException e) {
            throw new IdentityOAuth2ServerException(e.getMessage(), e);
        }
        return new ValidationResult(true, "Access Evaluation performed Successfully.", null);
    }

    @Override
    public String getType() {

        return BOOKING_CREATION_AUTHORIZATION_PROCESSOR;
    }

    @Override
    public boolean isEqualOrSubset(AuthorizationDetail requestedAuthorizationDetail,
                                   AuthorizationDetails existingAuthorizationDetails) {

        return false;
    }

    @Override
    public AuthorizationDetail enrich(AuthorizationDetailsContext authorizationDetailsContext) {

        return authorizationDetailsContext.getAuthorizationDetail();
    }
}
