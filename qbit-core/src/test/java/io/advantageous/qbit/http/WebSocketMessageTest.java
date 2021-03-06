/*
 * Copyright (c) 2015. Rick Hightower, Geoff Chandler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * QBit - The Microservice lib for Java : JSON, WebSocket, REST. Be The Web!
 */

package io.advantageous.qbit.http;

import io.advantageous.qbit.http.server.websocket.WebSocketMessage;
import io.advantageous.qbit.http.server.websocket.WebSocketMessageBuilder;
import io.advantageous.qbit.http.websocket.WebSocketSender;
import io.advantageous.boon.core.reflection.BeanUtils;
import org.junit.Before;
import org.junit.Test;

import static io.advantageous.boon.Boon.puts;
import static io.advantageous.boon.Exceptions.die;


public class WebSocketMessageTest {


    WebSocketMessage webSocketMessage;
    boolean called;
    boolean ok = true;
    String lastMessage = "";

    @Before
    public void setUp() throws Exception {

        webSocketMessage = new WebSocketMessageBuilder()
                .setUri("/foo")
                .setRemoteAddress("/blah")
                .setSender(new WebsSocketSenderMock())
                .setMessage("foo").build();
    }


    @Test
    public void test() {

        ok |= webSocketMessage.body().equals("foo") || die();

        ok |= webSocketMessage.isSingleton() || die();

        ok |= webSocketMessage.address().equals("/foo") || die();

        ok |= webSocketMessage.getUri().equals("/foo") || die();

        ok |= webSocketMessage.returnAddress().equals("/blah") || die();
        ok |= !webSocketMessage.isHandled() || die();

        webSocketMessage.getSender().sendText("hello mom");
        webSocketMessage.handled();


        ok |= webSocketMessage.isHandled() || die();


        ok |= called || die();

        ok |= lastMessage.equals("hello mom") || die();

        puts(webSocketMessage);

        ok |= webSocketMessage.equals(BeanUtils.copy(webSocketMessage)) || die();

        ok |= webSocketMessage.hashCode() == BeanUtils.copy(webSocketMessage).hashCode() || die();


        ok |= webSocketMessage.timestamp() > 0 || die();


        ok |= webSocketMessage.id() >= 0 || die();

        ok |= !webSocketMessage.hasParams() && webSocketMessage.params().size() == 0 || die();


        ok |= !webSocketMessage.hasHeaders() && webSocketMessage.headers().size() == 0 || die();


        final WebSocketMessageBuilder webSocketMessageBuilder = new WebSocketMessageBuilder();
        webSocketMessageBuilder.getMessage();
        webSocketMessageBuilder.getRemoteAddress();
        webSocketMessageBuilder.getSender();
        webSocketMessageBuilder.getUri();


    }

    public class WebsSocketSenderMock implements WebSocketSender {

        @Override
        public void sendText(String message) {


            lastMessage = message;
            called = true;
        }

        @Override
        public void sendBytes(byte[] message) {

        }
    }

}