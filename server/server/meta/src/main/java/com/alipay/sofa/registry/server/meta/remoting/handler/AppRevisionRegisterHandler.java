/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.registry.server.meta.remoting.handler;

import com.alipay.sofa.registry.common.model.Node;
import com.alipay.sofa.registry.common.model.store.AppRevision;
import com.alipay.sofa.registry.remoting.Channel;
import com.alipay.sofa.registry.server.meta.revision.AppRevisionRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class AppRevisionRegisterHandler extends MetaServerHandler<AppRevision> {
    @Autowired
    private AppRevisionRegistry appRevisionRegistry;

    @Autowired
    private ThreadPoolExecutor  appRevisionExecutor;

    @Override
    protected Node.NodeType getConnectNodeType() {
        return Node.NodeType.SESSION;
    }

    @Override
    public Object doHandle(Channel channel, AppRevision message) {
        appRevisionRegistry.register(message);
        return message;
    }

    @Override
    public Class interest() {
        return AppRevision.class;
    }

    @Override
    public Executor getExecutor() {
        return appRevisionExecutor;
    }
}