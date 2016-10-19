/*
 * Copyright 2016 Pablo Navais
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.pnavais.rezolver.loader.impl;

import com.github.pnavais.rezolver.ResourceInfo;
import com.github.pnavais.rezolver.loader.IResourceLoader;

/**
 * <b>FallbackLoader</b>
 * <p>
 *     A loader of URL resources using a fallback location as prefix
 *     in case the resolution failed.
 * </p>
 */
public abstract class URL_Loader implements IResourceLoader {

    /**
     * Use the default loader resolution algorithm and
     * sets the resolved URL.
     *
     * @param location the location of the resource
     * @return the resolved URL or null if not resolved
     */
    @Override
    public ResourceInfo resolve(String location) {
        // Try direct resolution
        ResourceInfo resourceInfo = lookup(location);

        // Try to resolve without schema prefix
        if ((!resourceInfo.isResolved()) && (location.startsWith(getURL_Scheme()))) {
            resourceInfo = lookup(location.replaceFirst("^"+getURL_Scheme()+":", ""));
        }

        return resourceInfo;
    }

    public abstract ResourceInfo lookup(String location);

    /**
     * Retrieves the URL schema
     *
     * @return the URL schema
     */
    public abstract String getURL_Scheme();

}
